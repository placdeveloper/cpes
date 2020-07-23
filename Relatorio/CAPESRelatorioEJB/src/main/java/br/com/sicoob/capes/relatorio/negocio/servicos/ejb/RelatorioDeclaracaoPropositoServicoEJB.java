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
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioDeclaracaoPropositoDTO;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioDeclaracaoProposito;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioDeclaracaoPropositoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioDeclaracaoPropositoDAO;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * EJB contendo servicos relacionados a RelatorioDeclaracaoProposito.
 */
@Stateless
@Remote({ IProcessamentoRelatorioServico.class })
public class RelatorioDeclaracaoPropositoServicoEJB extends CAPESRelatorioServicoEJB implements	IProcessamentoRelatorioServico {
	
	@Inject
	@Default
	private RelatorioDeclaracaoPropositoDAO dao;
	
	/**
	 * {@inheritDoc}
	 */
	private List<RelatorioDeclaracaoPropositoVO> obterDadosRelatorio(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		List<RelatorioDeclaracaoPropositoVO> lista = dao.obterDadosRelatorioDeclaracaoProposito(idPessoa, idInstituicao);

		if (lista == null || lista.isEmpty()) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}

		return lista;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		Integer idPessoa = (Integer) dto.getDados().get("idPessoa");
		Integer idInstituicao = (Integer) dto.getDados().get("idInstituicao");
		RelatorioDeclaracaoPropositoDTO dtoRelatorio = (RelatorioDeclaracaoPropositoDTO) dto.getDados().get("dto");

		List<RelatorioDeclaracaoPropositoVO> lista = obterDadosRelatorio(idPessoa, idInstituicao);
		RelatorioDeclaracaoProposito relatorio = new RelatorioDeclaracaoProposito(dtoRelatorio, lista);
		return relatorio.gerarRelatorio(rDto);
	}
}