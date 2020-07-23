package br.com.sicoob.capes.relatorio.negocio.fachada;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.bancoob.sisbrweb.util.ContextoHttp;
import br.com.sicoob.capes.relatorio.negocio.dto.RelCompartilhamentoDTO;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe RelCompartilhamentoFachada.
 */
@RemoteService
public class RelCompartilhamentoFachada extends CAPESRelatorioFachada {

	/**
	 * Emitir relatorio.
	 *
	 * @param dto o valor de dto
	 * @return RetornoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RetornoDTO emitirRelatorio(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retorno = new RetornoDTO();
		RelCompartilhamentoDTO proxy = new RelCompartilhamentoDTO(); 
		
		Integer numSingular;
		Integer numCentral = Integer.valueOf(dto.getDados().get("numCentral").toString());
		if (dto.getDados().get("numSingular") == null){
			numSingular = null;
		}
		else{
			numSingular = Integer.valueOf(dto.getDados().get("numSingular").toString());
		}
		Integer compartilhamento = (Integer) dto.getDados().get("compartilhamento");
		DateTime  dataInicio = (DateTime) dto.getDados().get("dataInicio");
		DateTime  dataFim = (DateTime) dto.getDados().get("dataFim");
		
		proxy.setNumCentral(numCentral);
		proxy.setNumSingular(numSingular);
		proxy.setCompartilhamento(compartilhamento);
		proxy.setDataInicio(dataInicio);
		proxy.setDataFim(dataFim);
		

		ContextoHttp.getInstance().getCaminhoContextoWeb();
		return retorno;		
	}
}