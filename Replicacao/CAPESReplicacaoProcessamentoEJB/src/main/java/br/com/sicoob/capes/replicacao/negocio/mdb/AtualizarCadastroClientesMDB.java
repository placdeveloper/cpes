/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.mdb;

import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.mdb.BancoobMdb;
import br.com.sicoob.capes.cadastro.negocio.excecao.MensagemInvalidaException;
import br.com.sicoob.capes.negocio.entidades.MensagemReplicacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.replicacao.negocio.excecao.LeituraMensagemException;
import br.com.sicoob.capes.replicacao.negocio.excecao.MensagemReplicacaoInacessivelException;
import br.com.sicoob.capes.replicacao.negocio.excecao.ProcessamentoMensagemException;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.AtualizarCadastroClienteServicoLocal;

/**
 * MDB utilizado para atualizações cadastrais dos clientes.
 * 
 * @author Erico.Junior
 */
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AtualizarCadastroClientesMDB<R extends EntidadeReplicavel<?>, E extends Replicavel>
		extends BancoobMdb {

	/** O atributo servicoAtualizacao. */
	@EJB(mappedName = "capes/replicacao/AtualizarCadastroClienteServico")
	private transient AtualizarCadastroClienteServicoLocal<R, E> servicoAtualizacao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onMessage(Message mensagem) {
		String textoMensagem = null;
		textoMensagem = obterTextoMensagem(mensagem);
		if (StringUtils.isNotBlank(textoMensagem)) {
			DadosMensagem dados = obterDados(textoMensagem);
			processarMensagem(dados.getIdOperacao(), dados.getIdInstituicao());
		}
	}

	/**
	 * O método Processar mensagem.
	 *
	 * @param idOperacao o valor de id operacao
	 * @param idInstituicao o valor de id instituicao
	 * @throws MensagemReplicacaoInacessivelException lança a exceção MensagemReplicacaoInacessivelException
	 * @throws ProcessamentoMensagemException lança a exceção ProcessamentoMensagemException
	 */
	private void processarMensagem(final String idOperacao, final Integer idInstituicao)
			throws MensagemReplicacaoInacessivelException, ProcessamentoMensagemException {

		MensagemReplicacao mensagemReplicacao = null;
		try {
			List<MensagemReplicacao> mensagens = servicoAtualizacao.obterMensagensReplicacao(
					idOperacao, idInstituicao);
			if (!mensagens.isEmpty()) {
				Iterator<MensagemReplicacao> it = mensagens.iterator();
				while (it.hasNext()) {
					mensagemReplicacao = it.next();
					if (mensagemReplicacao != null && (mensagemReplicacao.getDataProcessamento() == null
							|| mensagemReplicacao.getErro() != null)) {
						servicoAtualizacao.atualizarCadastro(mensagemReplicacao);
					} else {
						getLogger().info("Mensagem processada anteriormente: ",
								idOperacao + MensagemReplicacao.SEPARADOR_MENSAGEM + idInstituicao);
					}
				}
			} else {
				getLogger().alerta("Mensagem nao localizada: ",
						idOperacao + MensagemReplicacao.SEPARADOR_MENSAGEM + idInstituicao);
			}
		} catch (MensagemReplicacaoInacessivelException e) {
			throw e;
		} catch (BancoobException e) {
			Integer id = mensagemReplicacao.getIdMensagemReplicacao();
			servicoAtualizacao.atualizarDataProcessamento(mensagemReplicacao, e);
			throw new ProcessamentoMensagemException(e, id);
		} catch(Exception e) { //NOPMD
			Integer id = null;
			if (mensagemReplicacao != null) {
				id = mensagemReplicacao.getIdMensagemReplicacao();
				servicoAtualizacao.atualizarDataProcessamento(mensagemReplicacao, e);
			}
			throw new ProcessamentoMensagemException(e, id);
		}
	}

	/**
	 * Obter texto mensagem.
	 *
	 * @param mensagem o valor de mensagem
	 * @return String
	 * @throws LeituraMensagemException lança a exceção LeituraMensagemException
	 */
	private String obterTextoMensagem(Message mensagem) throws LeituraMensagemException {
		try {
			String texto = ((TextMessage) mensagem).getText();
			getLogger().debug("Mensagem recebida: identificadorOperacao=",
					texto.replace(",", ", idInstituicao="));
			return texto;
		} catch (JMSException excecao) {
			throw new LeituraMensagemException(excecao, mensagem.toString());
		}
	}

	/**
	 * Obter dados.
	 *
	 * @param textoMensagem o valor de texto mensagem
	 * @return DadosMensagem
	 * @throws MensagemInvalidaException lança a exceção MensagemInvalidaException
	 */
	private DadosMensagem obterDados(String textoMensagem) throws MensagemInvalidaException {

		Integer idInstituicao = null;
		String dadosMsg[] = textoMensagem.split(MensagemReplicacao.SEPARADOR_MENSAGEM);
		if (dadosMsg.length != 2) {
			throw new MensagemInvalidaException(
					MensagemInvalidaException.QUANTIDADE_PARAMETROS_INVALIDA, dadosMsg.length);
		}
		if (dadosMsg[0].length() != 36) {
			throw new MensagemInvalidaException(
					MensagemInvalidaException.IDENTIFICADOR_OPERACAO_INVALIDO, dadosMsg[0]);
		}
		try {
			String txtIdInstituicao = dadosMsg[1].trim();
			idInstituicao = Integer.valueOf(txtIdInstituicao.replaceAll("\\s+", ""));
		} catch (NumberFormatException excecao) {
			throw new MensagemInvalidaException(excecao,
					MensagemInvalidaException.ID_INSTITUICAO_INVALIDO, dadosMsg[1]);
		}
		return new DadosMensagem(dadosMsg[0], idInstituicao);
	}

	/**
	 * A Classe DadosMensagem.
	 */
	private class DadosMensagem {
		
		/** O atributo idOperacao. */
		private String idOperacao;
		
		/** O atributo idInstituicao. */
		private Integer idInstituicao;

		/**
		 * Instancia um novo DadosMensagem.
		 *
		 * @param idOperacao o valor de id operacao
		 * @param idInstituicao o valor de id instituicao
		 */
		public DadosMensagem(String idOperacao, Integer idInstituicao) {
			this.idOperacao = idOperacao;
			this.idInstituicao = idInstituicao;
		}

		/**
		 * Recupera o valor de idOperacao.
		 *
		 * @return o valor de idOperacao
		 */
		public String getIdOperacao() {
			return idOperacao;
		}

		/**
		 * Recupera o valor de idInstituicao.
		 *
		 * @return o valor de idInstituicao
		 */
		public Integer getIdInstituicao() {
			return idInstituicao;
		}

	}
}
