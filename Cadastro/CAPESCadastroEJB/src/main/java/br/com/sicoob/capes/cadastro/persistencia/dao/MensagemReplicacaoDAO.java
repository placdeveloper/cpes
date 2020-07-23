package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.vo.MonitoracaoMensagensVO;
import br.com.sicoob.capes.negocio.entidades.MensagemReplicacao;

/**
 * @author Rodrigo.Chaves
 */
public interface MensagemReplicacaoDAO extends
		CAPESCadastroCrudDaoIF<MensagemReplicacao> {

	/**
	 * Pesquisar mensagens nao enviadas por filtro.
	 *
	 * @param criterios o valor de criterios
	 * @return ConsultaDto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	ConsultaDto<MensagemReplicacao> pesquisarMensagensNaoEnviadasPorFiltro(
			ConsultaDto<MensagemReplicacao> criterios) throws BancoobException;

	/**
	 * Obtém todas as mensagens que <strong>não</strong> foram processadas ou tenham sido
	 * processadas com erro.
	 * 
	 * @param filtro
	 *            os criterios de consulta
	 * 
	 * @return a mensagem ou {@code null}, caso a mensagem não exista, ou tenha sido processada com
	 *         sucesso
	 */
	List<MensagemReplicacao> listarMensagensNaoProcessadasPorFiltro(MensagemReplicacao filtro)
			throws BancoobException;

	/**
	 * Obtém todas as mensagens que <strong>não</strong> foram processadas. Caso o parâmetro
	 * {@code incluiMsgComErro} seja {@code true}, também recupera as mensagens que tenham sido
	 * processadas com erro.
	 * 
	 * @param filtro
	 *            os criterios de consulta
	 * @param incluiMsgComErro
	 *            define se deve ou não recuperar mensagens processadas com erro.
	 * 
	 * @return as mensagens ou {@code null}, caso não existam mensagens não processadas
	 */
	List<MensagemReplicacao> listarMensagensNaoProcessadasPorFiltro(
			MensagemReplicacao filtro, Boolean incluiMsgComErro) throws BancoobException;

	/**
	 * <p>
	 * Atualiza a data de envio das mensagens com os mesmos {@code identificadorOperacao} e
	 * {@code idInsitituicao}.
	 * </p>
	 * <p>
	 * <strong>Atenção!</strong> Caso a {@link MensagemReplicacao#getDataEnvio()} seja {@code null},
	 * <strong>a data de envio será <i>APAGADA</i></strong>.
	 * </p>
	 * <p>
	 * <i>Nenhuma outra informação é atualizada além da data de envio</i>
	 * </p>
	 * 
	 * @param mensagem
	 *            a mensagem com as informações a serem utilizadas. Informações obrigatórias:
	 *            {@link MensagemReplicacao#getIdentificadorOperacao()} e
	 *            {@link MensagemReplicacao#getIdInstituicao()}.
	 */
	void atualizarDatasEnvio(MensagemReplicacao mensagem) throws BancoobException;

	/**
	 * <p>
	 * Atualiza a data de processamento e o erro de uma mensagem.
	 * </p>
	 * <p>
	 * <strong>Atenção!</strong> Caso o {@link MensagemReplicacao#getErro()} ou a
	 * {@link MensagemReplicacao#getDataProcessamento()} sejam {@code null}, <strong>eles serão
	 * <i>APAGADOs</i></strong>.
	 * </p>
	 * <p>
	 * <i>Nenhuma outra informação é atualizada além da data de processamento e do erro</i>
	 * </p>
	 * 
	 * @param mensagem
	 *            a mensagem com as informações a serem utilizadas. Informações obrigatórias:
	 *            {@link MensagemReplicacao#getIdMensagemReplicacao()} e
	 *            {@link MensagemReplicacao#getDataProcessamento()}.
	 */
	void atualizarDataProcessamentoErro(MensagemReplicacao mensagem) throws BancoobException;
	
	
	/**
	 * Expurgar os registros da tabela CLI.MENSAGEMREPLICACAO, o expurgo será para mensagem com mais de 5 dias.
	 * @throws BancoobException
	 */
	void expurgarMensagensReplicacao() throws BancoobException;

	/**
	 * Expurgar os registros da tabela CLI.MENSAGEMREPLICACAO, o expurgo será para mensagens com erro e com mais de 15 
	 * dias.
	 * @throws BancoobException
	 */
	void expurgarMensagensReplicacaoComErro() throws BancoobException;
		
	/**
	 * Monitorar mensagens nao processadas.
	 *
	 * @return MonitoracaoMensagensVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	MonitoracaoMensagensVO monitorarMensagensNaoProcessadas() throws BancoobException;

	/**
	 * Monitorar mensagens nao enviadas.
	 *
	 * @return MonitoracaoMensagensVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	MonitoracaoMensagensVO monitorarMensagensNaoEnviadas() throws BancoobException;

}
