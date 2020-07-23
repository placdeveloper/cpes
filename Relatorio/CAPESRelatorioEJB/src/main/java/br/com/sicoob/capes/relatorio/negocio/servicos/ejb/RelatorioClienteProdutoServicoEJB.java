package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelClienteProdutoProxy;
import br.com.sicoob.capes.relatorio.negocio.dto.RelClienteProdutoVO;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioClienteProduto;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelClienteProdutoDao;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

@Stateless
@Remote({ IProcessamentoRelatorioServico.class })
public class RelatorioClienteProdutoServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {


	@Inject
	@Default
	private RelClienteProdutoDao dao;

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		
		RelClienteProdutoVO parametrosVO = (RelClienteProdutoVO) dto.getDados().get("parametros");
		Map<String, Object> parametros = criarMapaParametro(parametrosVO);
		
		List<RelClienteProdutoProxy> lista = dao.obterDadosRelatorio(parametros);

		if (CollectionUtils.isEmpty(lista)) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}
		RelatorioClienteProduto relatorio = new RelatorioClienteProduto(parametrosVO, lista);
		return relatorio.gerarRelatorio(rDto);
	}
	
	private Map<String, Object> criarMapaParametro(RelClienteProdutoVO vo) throws BancoobException {

		Map<String, Object> parametros = new HashMap<String, Object>();
		
		parametros.put("pNumCooperativa", vo.getCooperativa());
		parametros.put("pNumCoopPac", vo.getPac());
		parametros.put("pNomeCoopPac", vo.getNomeCoopPac());
		parametros.put("pNumNucleo", vo.getNucleo());
		parametros.put("pNomeNucleo", vo.getNomeNucleo());
		parametros.put("pNumGrupoEconomico", vo.getGrupoEconomico());
		parametros.put("pNomeGrupoEconomico", vo.getNomeGrupoEconomico());
		parametros.put("pNumGerente", vo.getGerente());
		parametros.put("pNomeGerente", vo.getNomeGerente());
		parametros.put("pTipoPessoa", vo.getTipoPessoa());

		parametros.put("pContaCapital", vo.getContaCapital());
		parametros.put("pContaCorrente", vo.getContaCorrente());
		parametros.put("pAplicacao", vo.getAplicacoes());
		parametros.put("pPoupanca", vo.getPoupanca());
		parametros.put("pOpCredito", vo.getOpCredito());
		parametros.put("pCartaoCredito", vo.getCartaoCredito());
		parametros.put("pDebitoAutomatico", vo.getDebitoAutomatico());

		parametros.put("pRendaMin", vo.getRendaMin());
		parametros.put("pRendaMax", vo.getRendaMax());

		if (vo.getDtNascInicio() != null) {
			parametros.put("pDtNascInicio", new SimpleDateFormat("yyyy-MM-dd").format(vo.getDtNascInicio()));
			parametros.put("pRelDtNascInicio", new SimpleDateFormat("dd/MM/yyyy").format(vo.getDtNascInicio()));
		} else {
			parametros.put("pRelDtNascInicio", "");
			parametros.put("pDtNascInicio", null);
		}

		if (vo.getDtNascFim() != null) {
			parametros.put("pDtNascFim", new SimpleDateFormat("yyyy-MM-dd").format(vo.getDtNascFim()));
			parametros.put("pRelDtNascFim", new SimpleDateFormat("dd/MM/yyyy").format(vo.getDtNascFim()));
		} else {
			parametros.put("pRelDtNascFim", "");
			parametros.put("pDtNascFim", null);
		}

		parametros.put("pDependente", vo.getDependente());
		parametros.put("pSexo", vo.getSexo());

		parametros.put("pNumMunicipio", vo.getMunicipio());
		parametros.put("pNomeMunicipio", vo.getNomeMunicipio());
		parametros.put("pNumCategoriaProdutor", vo.getCategoriaProdutor());
		parametros.put("pNomeCategoriaProdutor", vo.getNomeCategoriaProdutor());
		parametros.put("pOrdenacao", vo.getOrdenacao());

		return parametros;
	}

}
