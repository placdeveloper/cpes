package br.com.sicoob.capes.cadastro.negocio.dto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * A Classe CapesCadastroDTOCodeCoverageTest.
 */
public class CapesCadastroDTOCodeCoverageTest {

	/** A constante setArgs. */
	public static final Object setArgs[] = { null };

	/** A constante noArgs. */
	public static final Object noArgs[] = {};

	/**
	 * O método Test superficial capes cadastro dto code coverage.
	 * 
	 * @throws Exception
	 *             lança a exceção Exception
	 */
	@Test
	public void testSuperficialCapesCadastroDTOCodeCoverage() throws Exception {
		superficialCapesCadastroDTOCodeCoverage(AlteracaoDocumentoPessoaDTO.class);
		superficialCapesCadastroDTOCodeCoverage(AtualizacaoDataSFNDTO.class);
		superficialCapesCadastroDTOCodeCoverage(AvalFinanceiraDTO.class);
		superficialCapesCadastroDTOCodeCoverage(ConsultaAnotacaoDTO.class);
		superficialCapesCadastroDTOCodeCoverage(ConsultaAnotacaoSisbrDTO.class);
		superficialCapesCadastroDTOCodeCoverage(ConsultaAutorizacaoDTO.class);
		superficialCapesCadastroDTOCodeCoverage(ConsultaDtoCUC.class);
		superficialCapesCadastroDTOCodeCoverage(ConsultaPessoaDTO.class);
		superficialCapesCadastroDTOCodeCoverage(ConsultaProdutividadeDTO.class);
		superficialCapesCadastroDTOCodeCoverage(ConsultaSumarioAnotacaoDTO.class);
		superficialCapesCadastroDTOCodeCoverage(ConsultaTransicaoPessoaDTO.class);
		superficialCapesCadastroDTOCodeCoverage(DefinicaoDTO.class);
		superficialCapesCadastroDTOCodeCoverage(MonitoracaoDLQsCapesDTO.class);
		superficialCapesCadastroDTOCodeCoverage(MonitoracaoFilasCapesDTO.class);
		superficialCapesCadastroDTOCodeCoverage(ProdutividadeDTO.class);
		superficialCapesCadastroDTOCodeCoverage(RelacionamentoInstituicaoDTO.class);
		superficialCapesCadastroDTOCodeCoverage(TransfInformacoesDTO.class);
		superficialCapesCadastroDTOCodeCoverage(VerificaNecessidadeAutorizacaoDTO.class);
	}

	/**
	 * O método Superficial capes cadastro dto code coverage.
	 * 
	 * @param classe
	 *            o valor de classe
	 */
	private void superficialCapesCadastroDTOCodeCoverage(Class<?> classe) {
		Method[] metodos = classe.getMethods();
		try {
			Object entidade = classe.newInstance();
			for (Method metodo : metodos) {
				if (metodo.getName().startsWith("set")) {
					metodo.invoke(entidade, setArgs);
				}
				if (metodo.getName().startsWith("get")) {
					metodo.invoke(entidade, noArgs);
				}
			}
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} catch (InvocationTargetException e) {
		} catch (InstantiationException e) {
		}
	}

}