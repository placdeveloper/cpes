package br.com.sicoob.capes.relatorio.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioGrupoEconomicoDTO;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioGrupoEconomico;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioGrupoEconomicoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioGrupoEconomicoDAO;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;
import br.com.sicoob.tipos.DateTime;

/**
 * EJB contendo servicos relacionados a RelatorioGrupoEconomico.
 */
@Stateless
@Remote({ IProcessamentoRelatorioServico.class })
public class RelatorioGrupoEconomicoServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {
	
	@Inject
	@Default
	private RelatorioGrupoEconomicoDAO dao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		RelatorioGrupoEconomicoDTO grupoEconomicoDTO = new RelatorioGrupoEconomicoDTO();
		grupoEconomicoDTO.setIdInstituicao((Integer) dto.getDados().get("idInstituicao"));
		grupoEconomicoDTO.setCpfCnpj((String) dto.getDados().get("cpfCnpj"));
		grupoEconomicoDTO.setDataFim((DateTime) dto.getDados().get("dataFim"));
		grupoEconomicoDTO.setDataInicio((DateTime) dto.getDados().get("dataInicio"));
		grupoEconomicoDTO.setGerente((Integer) dto.getDados().get("numGerente"));
		grupoEconomicoDTO.setNomeGerente((String) dto.getDados().get("nomeGerente"));
		grupoEconomicoDTO.setNucleo((Integer) dto.getDados().get("idNucleo"));
		grupoEconomicoDTO.setNomeNucleo((String) dto.getDados().get("nomeNucleo"));
		grupoEconomicoDTO.setNumCentral((Integer) dto.getDados().get("numCentral"));
		grupoEconomicoDTO.setNumSingular((Integer) dto.getDados().get("numSingular"));
		grupoEconomicoDTO.setNumTipoRegistro((Integer) dto.getDados().get("numTipoRegistro"));
		grupoEconomicoDTO.setUnidade((Integer) dto.getDados().get("numUnidade"));

		List<RelatorioGrupoEconomicoVO> lista = dao.consultaRelatorioGrupoEconomico(grupoEconomicoDTO);

		if (CollectionUtils.isEmpty(lista)) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}

		RelatorioGrupoEconomico relatorio = new RelatorioGrupoEconomico(grupoEconomicoDTO, lista);
		return relatorio.gerarRelatorio(rDto);
	}

}
