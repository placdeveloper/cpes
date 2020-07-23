package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.MensagemReplicacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.MonitoracaoMensagensVO;
import br.com.sicoob.capes.negocio.entidades.MensagemReplicacao;

/**
 * A Classe MensagemReplicacaoDelegate.
 */
public class MensagemReplicacaoDelegate extends
		CAPESCadastroCrudDelegate<MensagemReplicacao, MensagemReplicacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MensagemReplicacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarMensagemReplicacaoServico();
	}

	/**
	 * Pesquisar mensagens nao enviadas por filtro.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public ConsultaDto<MensagemReplicacao> pesquisarMensagensNaoEnviadasPorFiltro(
			ConsultaDto<MensagemReplicacao> criterios) throws BancoobException {
		return getServico().pesquisarMensagensNaoEnviadasPorFiltro(criterios);
	}

	/**
	 * Obt�m todas as mensagens que <strong>n�o</strong> foram processadas ou tenham sido
	 * processadas com erro.
	 * 
	 * @param filtro
	 *            os criterios de consulta
	 * 
	 * @return a mensagem ou {@code null}, caso a mensagem n�o exista, ou tenha sido processada com
	 *         sucesso
	 */
	public List<MensagemReplicacao> listarMensagensNaoProcessadasPorFiltro(MensagemReplicacao filtro)
			throws BancoobException {
		return getServico().listarMensagensNaoProcessadasPorFiltro(filtro);
	}

	/**
	 * Obt�m todas as mensagens que <strong>n�o</strong> foram processadas. Caso o par�metro
	 * {@code incluiMsgComErro} seja {@code true}, tamb�m recupera as mensagens que tenham sido
	 * processadas com erro.
	 * 
	 * @param filtro
	 *            os criterios de consulta
	 * @param incluiMsgComErro
	 *            define se deve ou n�o recuperar mensagens processadas com erro.
	 * 
	 * @return as mensagens ou {@code null}, caso n�o existam mensagens n�o processadas
	 */
	public List<MensagemReplicacao> listarMensagensNaoProcessadasPorFiltro(
			MensagemReplicacao filtro, Boolean incluiMsgComErro) throws BancoobException {

		return getServico().listarMensagensNaoProcessadasPorFiltro(filtro, incluiMsgComErro);
	}

	/**
	 * O m�todo Enviar mensagens.
	 *
	 * @param operacoes o valor de operacoes
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void enviarMensagens(String... operacoes) throws BancoobException {
		getServico().enviarMensagens(operacoes);
	}

	/**
	 * O m�todo Reenviar mensagens.
	 *
	 * @param mensagens o valor de mensagens
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void reenviarMensagens(List<MensagemReplicacao> mensagens) throws BancoobException {
		getServico().reenviarMensagens(mensagens);
	}

	/**
	 * Obter mensagens nao processadas.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public ConsultaDto<MensagemReplicacao> obterMensagensNaoProcessadas(
			ConsultaDto<MensagemReplicacao> criterios) throws BancoobException {
		return getServico().obterMensagensNaoProcessadas(criterios);
	}

	/**
	 * O m�todo Atualizar datas envio.
	 *
	 * @param identificadorOperacao o valor de identificador operacao
	 * @param idInstituicao o valor de id instituicao
	 * @param data o valor de data
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void atualizarDatasEnvio(String identificadorOperacao, Integer idInstituicao, Date data)
			throws BancoobException {

		getServico().atualizarDatasEnvio(identificadorOperacao, idInstituicao, data);
	}

	/**
	 * O m�todo Atualizar data processamento.
	 *
	 * @param idMensagemReplicacao o valor de id mensagem replicacao
	 * @param data o valor de data
	 * @param erro o valor de erro
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void atualizarDataProcessamento(Integer idMensagemReplicacao, Date data, String erro)
			throws BancoobException {

		getServico().atualizarDataProcessamento(idMensagemReplicacao, data, erro);
	}

	/**
	 * Expurgar os registros da tabela CLI.MENSAGEMREPLICACAO, o expurgo ser� para mensagem com mais de 5 dias.
	 * @throws BancoobException
	 */
	public void expurgarMensagensReplicacao() throws BancoobException {
		getServico().expurgarMensagensReplicacao();
	}

	/**
	 * O m�todo Reprocessar mensagem.
	 *
	 * @param mensagem o valor de mensagem
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public void reprocessarMensagem(MensagemReplicacao mensagem) throws BancoobException {
		getServico().reprocessarMensagem(mensagem);
	}

	/**
	 * Remove as informa��es sobre a sita��o da mensagem em rela��o ao processamento.
	 * 
	 * @param idMensagemReplicacao
	 *            o ID da mensagem de onde devem ser removidas as informa��es
	 */
	public void limparDadosProcessamento(Integer idMensagemReplicacao) throws BancoobException {
		getServico().limparDadosProcessamento(idMensagemReplicacao);
	}

	/**
	 * Monitorar mensagens nao processadas.
	 *
	 * @return MonitoracaoMensagensVO
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public MonitoracaoMensagensVO monitorarMensagensNaoProcessadas() throws BancoobException {
		return getServico().monitorarMensagensNaoProcessadas();
	}

	/**
	 * Monitorar mensagens nao enviadas.
	 *
	 * @return MonitoracaoMensagensVO
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public MonitoracaoMensagensVO monitorarMensagensNaoEnviadas() throws BancoobException {
		return getServico().monitorarMensagensNaoEnviadas();
	}

}
