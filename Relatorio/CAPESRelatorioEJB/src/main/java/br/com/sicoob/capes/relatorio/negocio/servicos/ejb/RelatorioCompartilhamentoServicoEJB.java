package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelCompartilhamentoDTO;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelCompartilhamento;
import br.com.sicoob.capes.relatorio.negocio.vo.RelCompartilhamentoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelCompartilhamentoDAO;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;
import br.com.sicoob.tipos.DateTime;

/**
 * EJB contendo servicos relacionados a RelatorioCompartilhamento.
 */
@Stateless
@Remote( { IProcessamentoRelatorioServico.class })
public class RelatorioCompartilhamentoServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico{
	
	@Inject
	@Default
	private RelCompartilhamentoDAO dao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		RelCompartilhamentoDTO proxy = new RelCompartilhamentoDTO(); 
		
		Integer numSingular = null;
		Integer numCentral = Integer.valueOf(dto.getDados().get("numCentral").toString());
		if (dto.getDados().get("numSingular") != null){
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

		List<RelCompartilhamentoVO> listaRetorno = obterDadosRelatorio(proxy);
		if(listaRetorno == null || listaRetorno.isEmpty()) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}
		
		RelCompartilhamento relatorio = new RelCompartilhamento(listaRetorno, proxy);
		return relatorio.gerarRelatorio(rDto);
	}
	
	/**
	 * {@inheritDoc}
	 */
	private List<RelCompartilhamentoVO> obterDadosRelatorio(RelCompartilhamentoDTO proxy) throws BancoobException {
		return dao.obterDadosRelatorio(proxy);		
	}
	
}