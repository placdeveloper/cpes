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
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.negocio.excecao.NegocioException;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioGrupoEconomicoDTO;
import br.com.sicoob.capes.relatorio.negocio.relatorios.RelatorioGenerico;
import br.com.sicoob.capes.relatorio.negocio.relatorios.Relatorios;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioGrupoEconomicoNovoVO;
import br.com.sicoob.capes.relatorio.persistencia.dao.RelatorioGrupoEconomicoNovoDAO;
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
public class RelatorioGrupoEconomicoNovoServicoEJB extends CAPESRelatorioServicoEJB implements IProcessamentoRelatorioServico {

	private static final Integer TIPO_VIGENTES = 2;

	@Inject
	@Default
	private RelatorioGrupoEconomicoNovoDAO dao;

	private SCIIntegracaoDelegate integracaoSCIServico = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		RelatorioGrupoEconomicoDTO grupoEconomicoDTO = new RelatorioGrupoEconomicoDTO();

		grupoEconomicoDTO.setIdInstituicao((Integer) dto.getDados().get("idInstituicao"));
		grupoEconomicoDTO.setNumSingular((Integer) dto.getDados().get("numSingular"));
		grupoEconomicoDTO.setNumCentral((Integer) dto.getDados().get("numCentral"));
		grupoEconomicoDTO.setUnidade((Integer) dto.getDados().get("numUnidade"));
		grupoEconomicoDTO.setNucleo((Integer) dto.getDados().get("numNucleo"));
		grupoEconomicoDTO.setNomeNucleo((String) dto.getDados().get("nomeNucleo"));
		grupoEconomicoDTO.setGerente((Integer) dto.getDados().get("numGerente"));
		grupoEconomicoDTO.setNomeGerente((String) dto.getDados().get("nomeGerente"));
		grupoEconomicoDTO.setDataInicio((DateTime) dto.getDados().get("dataInicio"));
		grupoEconomicoDTO.setDataFim((DateTime) dto.getDados().get("dataFim"));
		grupoEconomicoDTO.setCpfCnpj((String) dto.getDados().get("cpfCnpj"));
		grupoEconomicoDTO.setNumTipoOrigemRegistro((Integer) dto.getDados().get("numTipoOrigemRegistro"));
		grupoEconomicoDTO.setNumTipoVigencia((Integer) dto.getDados().get("numTipoVigencia"));

		List<RelatorioGrupoEconomicoNovoVO> lista = dao.consultaRelatorioGrupoEconomico(grupoEconomicoDTO);

		if (CollectionUtils.isEmpty(lista)) {
			throw new NegocioException("Não foram encontrados resultados para os filtros selecionados.");
		}

		final RelatorioGenerico relatorio;
		InstituicaoVO instituicao = integracaoSCIServico.obterInstituicaoPorId(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		if (grupoEconomicoDTO.getNumTipoVigencia().equals(TIPO_VIGENTES)) {
			relatorio = new RelatorioGenerico(grupoEconomicoDTO, Relatorios.GRUPO_ECONOMICO_VIGENTE, lista, instituicao);
		} else {
			relatorio = new RelatorioGenerico(grupoEconomicoDTO, Relatorios.GRUPO_ECONOMICO_HISTORICO, lista, instituicao);
		}
		return relatorio.gerarRelatorio(rDto);
	}

}
