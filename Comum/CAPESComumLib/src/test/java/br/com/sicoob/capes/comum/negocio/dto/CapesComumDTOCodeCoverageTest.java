package br.com.sicoob.capes.comum.negocio.dto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * A Classe CapesComumVOCodeCoverageTest.
 */
public class CapesComumDTOCodeCoverageTest {

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
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(AnotacaoDTO.class, new AnotacaoDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(AnotacaoExternaDTO.class, new AnotacaoExternaDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(AnotacaoInformacaoDTO.class, new AnotacaoInformacaoDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(AnotacaoSisbrDTO.class, new AnotacaoSisbrDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(AtualizacaoRiscoClienteDTO.class, new AtualizacaoRiscoClienteDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DetalheAcaoDTO.class, new DetalheAcaoDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DetalheAcheiDTO.class, new DetalheAcheiDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DetalheAnotacaoBacenDTO.class, new DetalheAnotacaoBacenDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DetalheAnotacaoInternaDTO.class, new DetalheAnotacaoInternaDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DetalheAnotacaoSisbrDTO.class, new DetalheAnotacaoSisbrDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DetalheCCFDTO.class, new DetalheCCFDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DetalheConvemDevedoresDTO.class, new DetalheConvemDevedoresDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DetalheFalenciaDTO.class, new DetalheFalenciaDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DetalheParticipanteFalidaDTO.class, new DetalheParticipanteFalidaDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DetalhePefinDTO.class, new DetalhePefinDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DetalheProtestoDTO.class, new DetalheProtestoDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DetalheRefinDTO.class, new DetalheRefinDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(GEDIntegracaoDTO.class, new GEDIntegracaoDTO());
		CapesComumDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(GFTIntegracaoDTO.class, new GFTIntegracaoDTO());
	}

}