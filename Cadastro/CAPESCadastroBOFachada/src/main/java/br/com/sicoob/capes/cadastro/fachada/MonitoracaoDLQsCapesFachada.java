/**
 * 
 */
package br.com.sicoob.capes.cadastro.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.MonitoracaoFilasCapesDelegate;
import br.com.sicoob.capes.cadastro.negocio.dto.MonitoracaoDLQsCapesDTO;

/**
 * @author Erico.Junior
 */
@RemoteService
public class MonitoracaoDLQsCapesFachada extends CAPESCadastroBOFachada {

	/** O atributo delegate. */
	private transient MonitoracaoFilasCapesDelegate delegate = 
			CAPESCadastroFabricaDelegates.getInstance().criarMonitoracaoFilasCapesDelegate();
	
	/**
	 * Obter dados.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO obterDados(RequisicaoReqDTO dto) throws BancoobException {
		MonitoracaoDLQsCapesDTO monitoracao = delegate.monitorarDLQs();
		
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("dlqConsultasExternas", monitoracao.getDlqConsultasExternas());
		retorno.getDados().put("dlqReplicacao", monitoracao.getDlqReplicacao());
		return  retorno;
	}

	/**
	 * Reprocessar dlq atualizacao.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO reprocessarDLQAtualizacao(RequisicaoReqDTO dto)
			throws BancoobException {
		delegate.reprocessarDLQCadastro();
		return new RetornoDTO();
	}
	
	/**
	 * Limpar fila dlq consultas externas.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO limparFilaDLQConsultasExternas(RequisicaoReqDTO dto)
			throws BancoobException {
		delegate.limparFilaDLQConsultasExternas();
		return new RetornoDTO();
	}

}
