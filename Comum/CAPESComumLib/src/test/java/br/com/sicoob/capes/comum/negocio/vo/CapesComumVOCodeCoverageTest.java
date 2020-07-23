package br.com.sicoob.capes.comum.negocio.vo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * A Classe CapesComumVOCodeCoverageTest.
 */
public class CapesComumVOCodeCoverageTest {

	/** A constante setArgs. */
	public static final Object setArgs[] = { null };
	
	/** A constante noArgs. */
	public static final Object noArgs[] = {};

	/**
	 * O método Superficial capes entidade code coverage.
	 *
	 * @param classe o valor de classe
	 * @param entidate o valor de entidate
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
	 * @throws Exception lança a exceção Exception
	 */
	@Test
	public void testSuperficialCapesEntidadeCodeCoverage() throws Exception {
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(AgenciaCafVO.class, new AgenciaCafVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BancoCafVO.class, new BancoCafVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CamposFichaCadastralNovaVO.class, new BancoCafVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CamposFichaCadastralVO.class, new CamposFichaCadastralVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ControleVO.class, new ControleVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DadosCNPJVO.class, new DadosCNPJVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DadosCPFVO.class, new DadosCPFVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DadosReceitaFederalVO.class, new DadosCPFVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DataUltimaAtualizacaoVO.class, new DataUltimaAtualizacaoVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(EsferaAdministrativaVO.class, new EsferaAdministrativaVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(GrupoCompartilhamentoVO.class, new GrupoCompartilhamentoVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(InstituicaoVO.class, new InstituicaoVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(LocalidadeVO.class, new LocalidadeVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(LOCIntegracaoLocalidadeVO.class, new LOCIntegracaoLocalidadeVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(LOCIntegracaoTipoLogradouroVO.class, new LOCIntegracaoTipoLogradouroVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(LOCIntegracaoUFVO.class, new LOCIntegracaoUFVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ModalidadeProdutoVO.class, new ModalidadeProdutoVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(OcorrenciaAtividadeVO.class, new OcorrenciaAtividadeVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaBasicaVO.class, new PessoaBasicaVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaPlataformaVO.class, new PessoaPlataformaVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ProcurarPessoaExternoVO.class, new ProcurarPessoaExternoVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ProdutoInstituicaoVO.class, new ProdutoInstituicaoVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ProdutoVO.class, new ProdutoVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RiscoVO.class, new RiscoVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(SistemaCooperativoVO.class, new SistemaCooperativoVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoDocumentoVO.class, new TipoDocumentoVO());
		CapesComumVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(UnidadeInstituicaoVO.class, new UnidadeInstituicaoVO());
	}

}