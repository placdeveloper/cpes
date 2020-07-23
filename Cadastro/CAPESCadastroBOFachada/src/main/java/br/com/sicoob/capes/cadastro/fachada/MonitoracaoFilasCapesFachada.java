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
import br.com.sicoob.capes.cadastro.negocio.dto.MonitoracaoFilasCapesDTO;

/**
 * @author erico.junior
 */
@RemoteService
public class MonitoracaoFilasCapesFachada extends CAPESCadastroBOFachada {

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
		
		MonitoracaoFilasCapesDTO monitoracao = delegate.monitorarFilas();
		
		RetornoDTO retorno = new RetornoDTO();
		retorno.getDados().put("dataConsulta", monitoracao.getDataConsulta());
		retorno.getDados().put("dlqConsultasExternas", monitoracao.getDlqConsultasExternas());
		retorno.getDados().put("dlqReplicacao", monitoracao.getDlqReplicacao());
		retorno.getDados().put("filaConsultasExternas", monitoracao.getFilaConsultasExternas());
		retorno.getDados().put("filaReplicacao", monitoracao.getFilaReplicacao());
		retorno.getDados().put("mensagensNaoEnviadas", monitoracao.getMensagensNaoEnviadas());
		retorno.getDados().put("mensagensNaoProcessadas", monitoracao.getMensagensNaoProcessadas());
		return  retorno;

	}
}
