package br.com.sicoob.capes.cadastro.negocio.vo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * A Classe CapesCadastroVOCodeCoverageTest.
 */
public class CapesCadastroVOCodeCoverageTest {

	/** A constante setArgs. */
	public static final Object setArgs[] = { null };

	/** A constante noArgs. */
	public static final Object noArgs[] = {};

	/**
	 * O método Test superficial capes cadastro vo code coverage.
	 * 
	 * @throws Exception
	 *             lança a exceção Exception
	 */
	@Test
	public void testSuperficialCapesCadastroVOCodeCoverage() throws Exception {
		superficialCapesCadastroVOCodeCoverage(ArquivoExportacaoVO.class);
		superficialCapesCadastroVOCodeCoverage(CamposTelaVO.class);
		superficialCapesCadastroVOCodeCoverage(CampoTelaListaVO.class);
		superficialCapesCadastroVOCodeCoverage(CampoTelaVO.class);
		superficialCapesCadastroVOCodeCoverage(CentralSingularVO.class);
		superficialCapesCadastroVOCodeCoverage(ConsultaAlteracaoDocumentoVO.class);
		superficialCapesCadastroVOCodeCoverage(ContaVO.class);
		superficialCapesCadastroVOCodeCoverage(DadosAlteracaoGrupoVO.class);
		superficialCapesCadastroVOCodeCoverage(DadosListagemBemVO.class);
		superficialCapesCadastroVOCodeCoverage(DefinicoesComponenteGedVO.class);
		superficialCapesCadastroVOCodeCoverage(EncaminharAutorizacaoVO.class);
		superficialCapesCadastroVOCodeCoverage(GrupoEconomicoVO.class);
		superficialCapesCadastroVOCodeCoverage(ItemAutorizacaoVO.class);
		superficialCapesCadastroVOCodeCoverage(MensagemVO.class);
		superficialCapesCadastroVOCodeCoverage(MonitoracaoFilaCapesVO.class);
		superficialCapesCadastroVOCodeCoverage(MonitoracaoMensagensVO.class);
		superficialCapesCadastroVOCodeCoverage(PerfilTarifarioVO.class);
		superficialCapesCadastroVOCodeCoverage(PosseBemVO.class);
		superficialCapesCadastroVOCodeCoverage(ProprietarioBemVO.class);
		superficialCapesCadastroVOCodeCoverage(StatusTransferenciaClienteVO.class);
		superficialCapesCadastroVOCodeCoverage(StatusTransferenciaGrupoEconomicoVO.class);
		superficialCapesCadastroVOCodeCoverage(SumarioAnotacaoVO.class);
		superficialCapesCadastroVOCodeCoverage(TipoOperacaoVO.class);
		superficialCapesCadastroVOCodeCoverage(TransicaoPessoaVO.class);
		superficialCapesCadastroVOCodeCoverage(UnidadeVO.class);
		superficialCapesCadastroVOCodeCoverage(ValidacaoCadastralVO.class);
		superficialCapesCadastroVOCodeCoverage(ValoresBensVO.class);
	}

	/**
	 * O método Superficial capes cadastro vo code coverage.
	 * 
	 * @param classe
	 *            o valor de classe
	 */
	private void superficialCapesCadastroVOCodeCoverage(Class<?> classe) {
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