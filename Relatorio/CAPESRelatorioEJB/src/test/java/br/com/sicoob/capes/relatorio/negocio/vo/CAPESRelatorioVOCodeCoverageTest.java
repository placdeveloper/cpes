package br.com.sicoob.capes.relatorio.negocio.vo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * A Classe CAPESRelatorioVOCodeCoverageTest.
 */
public class CAPESRelatorioVOCodeCoverageTest {

	/** A constante setArgs. */
	public static final Object setArgs[] = { null };

	/** A constante noArgs. */
	public static final Object noArgs[] = {};

	/**
	 * O método Superficial capes entidade code coverage.
	 * 
	 * @param classe
	 *            o valor de classe
	 * @param entidate
	 *            o valor de entidate
	 */
	@SuppressWarnings("rawtypes")
	public static void superficialCapesEntidadeCodeCoverage(Class classe, Object entidate) {
		Method[] methods = classe.getMethods();
		try {
			for (Method method : methods) {
				if (method.getName().startsWith("set")) {
					method.invoke(entidate, setArgs);
				}
				if (method.getName().startsWith("get")) {
					method.invoke(entidate, noArgs);
				}
			}
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		}
	}

	/**
	 * O método Test superficial capes entidade code coverage.
	 * 
	 * @throws Exception
	 *             lança a exceção Exception
	 */
	@Test
	public void testSuperficialCapesEntidadeCodeCoverage() throws Exception {
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(FichaCadastralBemVO.class, new FichaCadastralBemVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(FichaCadastralContatoVO.class, new FichaCadastralContatoVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(FichaCadastralVO.class, new FichaCadastralVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(FormularioVisitaVO.class, new FormularioVisitaVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelatorioCadastroCompartilhadoVO.class, new RelatorioCadastroCompartilhadoVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelatorioDeclaracaoPropositoVO.class, new RelatorioDeclaracaoPropositoVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelatorioGrupoEconomicoVO.class, new RelatorioGrupoEconomicoVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelatorioTributacaoVO.class, new RelatorioTributacaoVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelatorioValidacaoCadastralAnaliticoVO.class, new RelatorioValidacaoCadastralAnaliticoVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelatorioValidacaoCadastralSinteticoVO.class, new RelatorioValidacaoCadastralSinteticoVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelatorioValidacaoCadastralVO.class, new RelatorioValidacaoCadastralVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelCompartilhamentoVO.class, new RelCompartilhamentoVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelVencimentoCadastroVO.class, new RelVencimentoCadastroVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaFisicaVO.class, new PessoaFisicaVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaVO.class, new PessoaVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(NacionalidadeVO.class, new NacionalidadeVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(EstadoCivilVO.class, new EstadoCivilVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CnaeFiscalVO.class, new CnaeFiscalVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(AtividadeEconomicaVO.class, new AtividadeEconomicaVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CompartilhamentoCadastroVO.class, new CompartilhamentoCadastroVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PerfilCadastroVO.class, new PerfilCadastroVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(OcupacaoProfissionalVO.class, new OcupacaoProfissionalVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(GrauInstrucaoVO.class, new GrauInstrucaoVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(VinculoEmpregaticioVO.class, new VinculoEmpregaticioVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RegimeCasamentoVO.class, new RegimeCasamentoVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoDocumentoIdentificacaoVO.class, new TipoDocumentoIdentificacaoVO());
		CAPESRelatorioVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(HistoricoPessoaFisicaVO.class, new HistoricoPessoaFisicaVO());
	}
}

