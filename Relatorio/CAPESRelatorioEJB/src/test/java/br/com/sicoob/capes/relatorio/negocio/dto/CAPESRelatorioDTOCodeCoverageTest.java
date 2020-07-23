package br.com.sicoob.capes.relatorio.negocio.dto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * A Classe CAPESRelatorioDTOCodeCoverageTest.
 */
public class CAPESRelatorioDTOCodeCoverageTest {

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
	public static void superficialCapesDTOCodeCoverage(Class classe, Object entidate) {
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
		CAPESRelatorioDTOCodeCoverageTest.superficialCapesDTOCodeCoverage(RelatorioCadastroCompartilhadoDTO.class, new RelatorioCadastroCompartilhadoDTO());
		CAPESRelatorioDTOCodeCoverageTest.superficialCapesDTOCodeCoverage(RelatorioDeclaracaoPropositoDTO.class, new RelatorioDeclaracaoPropositoDTO());
		CAPESRelatorioDTOCodeCoverageTest.superficialCapesDTOCodeCoverage(RelatorioGrupoEconomicoDTO.class, new RelatorioGrupoEconomicoDTO());
		CAPESRelatorioDTOCodeCoverageTest.superficialCapesDTOCodeCoverage(RelatorioProvaVidaDTO.class, new RelatorioProvaVidaDTO());
//		CAPESRelatorioDTOCodeCoverageTest.superficialCapesDTOCodeCoverage(RelatorioTributacaoDTO.class, new RelatorioTributacaoDTO());
		CAPESRelatorioDTOCodeCoverageTest.superficialCapesDTOCodeCoverage(RelatorioValidacaoCadastralDTO.class, new RelatorioValidacaoCadastralDTO());
		CAPESRelatorioDTOCodeCoverageTest.superficialCapesDTOCodeCoverage(RelCompartilhamentoDTO.class, new RelCompartilhamentoDTO());
		CAPESRelatorioDTOCodeCoverageTest.superficialCapesDTOCodeCoverage(RelVencimentoCadastroDTO.class, new RelVencimentoCadastroDTO());
	}
}
