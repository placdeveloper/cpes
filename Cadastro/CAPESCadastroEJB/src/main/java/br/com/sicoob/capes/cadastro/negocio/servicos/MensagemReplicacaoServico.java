package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.negocio.entidades.MensagemReplicacao;
import br.com.sicoob.capes.cadastro.negocio.vo.MonitoracaoMensagensVO;

/**
 * A Interface MensagemReplicacaoServico.
 */
public interface MensagemReplicacaoServico extends
		CAPESCadastroCrudServico<MensagemReplicacao> {

	/**
	 * Pesquisar mensagens nao enviadas por filtro.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	ConsultaDto<MensagemReplicacao> pesquisarMensagensNaoEnviadasPorFiltro(
			ConsultaDto<MensagemReplicacao> criterios) throws BancoobException;

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
	List<MensagemReplicacao> listarMensagensNaoProcessadasPorFiltro(MensagemReplicacao filtro)
			throws BancoobException;

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
	List<MensagemReplicacao> listarMensagensNaoProcessadasPorFiltro(MensagemReplicacao filtro,
			Boolean incluiMsgComErro) throws BancoobException;

	/**
	 * @param operacoes
	 *            os identificadores das opera��es que se deseja enviar as mensagens
	 */
	void enviarMensagens(String... operacoes) throws BancoobException;

	/**
	 * O m�todo Reenviar mensagens.
	 *
	 * @param mensagens o valor de mensagens
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void reenviarMensagens(List<MensagemReplicacao> mensagens) throws BancoobException;

	/**
	 * Obter mensagens nao processadas.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	ConsultaDto<MensagemReplicacao> obterMensagensNaoProcessadas(ConsultaDto<MensagemReplicacao> criterios)
			throws BancoobException;

	/**
	 * O m�todo Atualizar data processamento.
	 *
	 * @param idMensagemReplicacao o valor de id mensagem replicacao
	 * @param data o valor de data
	 * @param erro o valor de erro
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void atualizarDataProcessamento(Integer idMensagemReplicacao, Date data, String erro)
			throws BancoobException;

	/**
	 * O m�todo Atualizar datas envio.
	 *
	 * @param identificadorOperacao o valor de identificador operacao
	 * @param idInstituicao o valor de id instituicao
	 * @param data o valor de data
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void atualizarDatasEnvio(String identificadorOperacao, Integer idInstituicao, Date data)
			throws BancoobException;

	/**
	 * Expurgar os registros da tabela CLI.MENSAGEMREPLICACAO, o expurgo ser� para mensagem com mais de 5 dias.
	 * @throws BancoobException
	 */
	void expurgarMensagensReplicacao() throws BancoobException;

	/**
	 * O m�todo Reprocessar mensagem.
	 *
	 * @param mensagem o valor de mensagem
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void reprocessarMensagem(MensagemReplicacao mensagem) throws BancoobException;

	/**
	 * Remove as informa��es sobre a sita��o da mensagem em rela��o ao processamento.
	 * 
	 * @param idMensagemReplicacao
	 *            o ID da mensagem de onde devem ser removidas as informa��es
	 */
	void limparDadosProcessamento(Integer idMensagemReplicacao) throws BancoobException;

	/**
	 * Monitorar mensagens nao enviadas.
	 *
	 * @return MonitoracaoMensagensVO
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	MonitoracaoMensagensVO monitorarMensagensNaoEnviadas() throws BancoobException;

	/**
	 * Monitorar mensagens nao processadas.
	 *
	 * @return MonitoracaoMensagensVO
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	MonitoracaoMensagensVO monitorarMensagensNaoProcessadas() throws BancoobException;
}
