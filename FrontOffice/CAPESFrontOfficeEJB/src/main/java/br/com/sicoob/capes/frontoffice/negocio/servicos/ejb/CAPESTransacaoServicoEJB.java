/*
 * SICOOB
 * 
 * CAPESTransacaoServicoEJB.java(br.com.sicoob.capes.frontoffice.negocio.servicos.ejb.CAPESTransacaoServicoEJB)
 */
package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.interceptor.Interceptors;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.bancoob.srtb.interceptors.TransacaoInterceptor;
import br.com.bancoob.srtb.montagemconteudo.MontagemConteudoRetorno;
import br.com.bancoob.srtb.montagemconteudo.objeto.MontagemConteudoRetornoObjeto;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.Resultado;
import br.com.bancoob.srtb.montagemconteudo.objeto.definicao.RetornoTransacaoObjeto;
import br.com.sicoob.capes.frontoffice.negocio.excecao.ValidacaoNegocialException;
import br.com.sicoob.capes.frontoffice.negocio.servicos.CAPESTransacaoServico;
import br.com.sicoob.capes.frontoffice.negocio.util.Constantes;
import br.com.sicoob.capes.frontoffice.negocio.util.ConteudoRetornoUtil;
import br.com.sicoob.capes.frontoffice.negocio.util.MensagemUtil;
import br.com.sicoob.capes.frontoffice.negocio.validacao.ExecutorValidacao;
import br.com.sicoob.capes.frontoffice.negocio.validacao.Validador;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;

/**
 * The Class CAPESTransacaoServicoEJB.
 */
public abstract class CAPESTransacaoServicoEJB extends CAPESFrontofficeServicoEJB implements CAPESTransacaoServico,
		Constantes.Negocio {

	/** Indica que o objeto de montagem é inválido. */
	private static final String MSG_OBJETO_MONTAGEM_INVALIDO = "O objeto de montagem passado deve ser uma subclasse de "
			+ "MontagemConteudoRetornoObjeto. Classe informada: ";

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Interceptors(TransacaoInterceptor.class)
	public RetornoMensagem executarTransacao(Mensagem mensagem) throws ExcecaoTransacao {
		RetornoMensagem retornoMensagem = null;
		
		try {
			Map<String, Parametro> parametros = MensagemUtil.recuperarParametros(mensagem);
			validar(parametros);
			RetornoTransacaoObjeto retornoTransacao = executarTransacao(mensagem, parametros);

			getLogger().info("Transacao executada com sucesso");

			retornoMensagem = criarConteudoRetornoSucesso(retornoTransacao, mensagem);

			getLogger().info("[CAPES] RetornoMensagemSucesso: " + ReflectionToStringBuilder.toString(retornoMensagem, ToStringStyle.MULTI_LINE_STYLE));
		} catch (ValidacaoNegocialException e) {
			getLogger().info("[ValidacaoException] - Erro na transacao do CAPES.");
			retornoMensagem = obterRetornoMensagemErro(e.getMessage(), RetornoMensagem.ERRO_VALIDACAO);
			retornoMensagem.setCampoErro(e.getNomeCampo());
			getLogger().info("[ValidacaoException] - " + ReflectionToStringBuilder.toString(retornoMensagem, ToStringStyle.MULTI_LINE_STYLE));
		} catch (NegocioException e) {
			getLogger().info("[NegocioException] - Erro na transacao do CAPES.");
			retornoMensagem = obterRetornoMensagemErro(e.getMessage(), RetornoMensagem.ERRO_NEGOCIO);
			getLogger().info("[NegocioException] - " + ReflectionToStringBuilder.toString(retornoMensagem, ToStringStyle.MULTI_LINE_STYLE));
		} catch (BancoobException e) {
			getLogger().erro(e, "[BancoobException] - Erro na transacao do CAPES.");
			retornoMensagem = obterRetornoMensagemErro(e.getMessage(), RetornoMensagem.ERRO_EXECUCAO);
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, "[BancoobRuntimeException] - Erro na transacao do CAPES.");
			retornoMensagem = obterRetornoMensagemErro(e.getMessage(), RetornoMensagem.ERRO_EXECUCAO);
		}
		return retornoMensagem;
	}

	/**
	 * Validar.
	 * 
	 * @param parametros
	 *            the parametros
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	protected void validar(Map<String, Parametro> parametros) throws BancoobException {
		ExecutorValidacao validador = new ExecutorValidacao();
		validador.adicionarValidadores(getValidadores(parametros));
		validador.validar();
	}

	/**
	 * Recupera os validadores que devem ser utilizados na validação
	 * 
	 * @param parametros
	 *            os parâmetros recebidos na {@link Mensagem}
	 * @return uma lista de {@link Validador}es
	 */
	protected abstract List<Validador> getValidadores(Map<String, Parametro> parametros); 
	
	/**
	 * Recupera o objeto de montagem do conteúdo de retorno.
	 * 
	 * @param montagem
	 *            o objeto de montagem do conteúdo de retorno informado na mensagem.
	 * @return o objeto que será utilizado na montagem do conteúdo de retorno.
	 * @throws ExcecaoTransacao
	 *             Caso o objeto de montagem não seja uma instância de MontagemConteudoRetornoObjeto
	 */
	protected MontagemConteudoRetornoObjeto obterMontagem(MontagemConteudoRetorno montagem) throws ExcecaoTransacao {
		MontagemConteudoRetornoObjeto novaMontagem = null;
		if (montagem == null) {
			novaMontagem = new MontagemConteudoRetornoObjeto();
		} else {
			if (!(montagem instanceof MontagemConteudoRetornoObjeto)) {
				throw new ExcecaoTransacao(MSG_OBJETO_MONTAGEM_INVALIDO + montagem.getClass().getName());
			}
			novaMontagem = (MontagemConteudoRetornoObjeto) montagem;
		}
		return novaMontagem;
	}

	/**
	 * Monta um objeto RetornoMensagem para situações de sucesso na transação.
	 * @param mensagem TODO
	 * @param retornoDTO
	 *            O dto com o retorno da transação de poupança.
	 * 
	 * @return um objeto RetornoMensagem para situações de sucesso na transação.
	 * @throws ExcecaoTransacao
	 *             Caso o objeto de montagem não seja uma instância de MontagemConteudoRetornoObjeto
	 *             
	 * @deprecated Utilizar o método criarConteudoRetorno
	 */
	@Deprecated
	protected RetornoMensagem obterRetornoMensagemSucesso(RetornoTransacaoObjeto retornoTransacao, Mensagem mensagem) throws ExcecaoTransacao {
		getLogger().info("[CAPES] Gerando conteudo de retorno...");

		MontagemConteudoRetornoObjeto montagem = obterMontagem(mensagem.getMontagem());
		montagem.setRetornoTransacao(retornoTransacao);
		RetornoMensagem retorno = montagem.criarConteudoRetorno();
		retorno.setSucesso(true);
		retorno.setCodRetorno(CODIGO_RETORNO_SUCESSO);
		getLogger().info("[CAPES] Conteudo gerado:\n ", retorno.getConteudoRetorno());

		return retorno;
	}
	
	/**
	 * Método que substituirá o {@code obterRetornoMensagemSucesso} que não
	 * possui um código de tipo de registro, pois a implementação do mesmo não
	 * necessitava de um retorno.
	 * 
	 * @param retornoTransacao
	 * @return
	 * @throws ExcecaoTransacao
	 */
	protected RetornoMensagem criarConteudoRetornoSucesso(RetornoTransacaoObjeto retornoTransacao) throws ExcecaoTransacao {
		return criarConteudoRetornoSucesso(retornoTransacao, null);
	}
	
	/**
	 * Método para preencher o retorno utilizando o tipo de registro para o
	 * retorno da mensagem.
	 * 
	 * Até o momento não foi necessário utilizar outro tipo de registro, então
	 * esse método passa apenas o tipo 1 (Padrão dos documentos DET).
	 * 
	 * <b>Modificar de acordo com a necessidade de outros tipos. </b>
	 * 
	 * @param retornoTransacao
	 *            o retorno da transação
	 * @param mensagem
	 *            usada somente para adaptação dos métodos antigos que ainda
	 *            utilizam o método {@code obterRetornoMensagemSucesso} 
	 *            TODO: remover a mensagem
	 * @return {@code RetornoMensagem} o retorno.
	 * @throws ExcecaoTransacao
	 */
	protected RetornoMensagem criarConteudoRetornoSucesso(RetornoTransacaoObjeto retornoTransacao, Mensagem mensagem) throws ExcecaoTransacao {
		StringBuilder builder = new StringBuilder();
		while ((retornoTransacao != null) && (retornoTransacao.hasNext())) {
			Resultado<? extends Serializable> resultado = retornoTransacao.next();
			String conteudo = ConteudoRetornoUtil.gerarConteudoIterator(1, resultado.iterator());
			getLogger().debug("Conteudo do resultado:\n", conteudo);
			builder.append(conteudo);
		}

		RetornoMensagem retorno = new RetornoMensagem();
		retorno.setConteudoRetorno(builder.toString());
		retorno.setSucesso(true);
		retorno.setCodRetorno(CODIGO_RETORNO_SUCESSO);

		return retorno;
	}

	/**
	 * Monta um objeto RetornoMensagem para situações de erro na transação.
	 * 
	 * @param mensagemErro
	 *            A mensagem do erro.
	 * @param tipoErro
	 *            O tipo de erro. Ex: RetornoMensagem.ERRO_NEGOCIO RetornoMensagem.ERRO_EXECUCAO
	 * @return um objeto RetornoMensagem para situações de erro na transação.
	 */
	protected RetornoMensagem obterRetornoMensagemErro(String mensagemErro, Integer tipoErro) {
		RetornoMensagem retorno = new RetornoMensagem();
		retorno.setSucesso(false);
		retorno.setCodRetorno(CODIGO_RETORNO_ERRO);
		retorno.setMensagem(mensagemErro);
		retorno.setTipoErro(tipoErro);
		return retorno;
	}
	
	/**
	 * Monta um objeto RetornoMensagem para situações de erro na transação.
	 * 
	 * @param mensagemErro
	 *            A mensagem do erro.
	 * @param tipoErro
	 *            O tipo de erro. Ex: RetornoMensagem.ERRO_NEGOCIO RetornoMensagem.ERRO_EXECUCAO
	 *            @param campoErro o campo que 
	 * @return um objeto RetornoMensagem para situações de erro na transação.
	 */
	protected RetornoMensagem obterRetornoMensagemErro(String mensagemErro, Integer tipoErro, String campoErro) {
		RetornoMensagem retorno = obterRetornoMensagemErro(mensagemErro, tipoErro);
		if (RetornoMensagem.ERRO_VALIDACAO.equals(tipoErro)) {
			retorno.setCampoErro(campoErro);
		}
		return retorno;
	}
	
	/**
	 * Obtém o usuário por código do canal.
	 * 
	 * @param codigoCanal
	 * @return
	 */
	protected String obterUsuarioCanal(Short codigoCanal) {
		if (codigoCanal.equals((short) 4)) {
			return "INTERNETB0300_00";
		} else if (codigoCanal.equals((short) 6)) {
			return "MOBILE0300_00";
		}
		return null;
	}
	
	/**
	 * Obtém o id da instituição
	 * 
	 * @param mensagem
	 * @param numeroCooperativa
	 * @return
	 * @throws BancoobException
	 */
	protected Integer obterIdInstituicao(Integer idInstituicao, Integer numeroCooperativa) throws BancoobException {
		if (idInstituicao == null) {
			SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
			idInstituicao = sciDelegate.obterIdInstituicao(numeroCooperativa);
		}
		return idInstituicao;
	}

	/**
	 * Executar transacao.
	 * 
	 * @param parametros
	 *            the parametros
	 * @return retorno transacao objeto
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	protected abstract RetornoTransacaoObjeto executarTransacao(Mensagem mensagem, Map<String, Parametro> parametros) throws BancoobException;

}