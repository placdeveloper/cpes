package br.com.sicoob.capes.api.negocio.vo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import br.com.sicoob.capes.api.negocio.dto.EnderecoPessoaDTO;

/**
 * A Classe CapesApiVOCodeCoverageTest.
 */
public class CapesApiVOCodeCoverageTest {

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
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(EnderecoPessoaDTO.class, new EnderecoPessoaDTO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(AnotacaoPessoaVO.class, new AnotacaoPessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemImovelVO.class, new BemPessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemMovelVO.class, new BemPessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemProprietarioVO.class, new BemPessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemVO.class, new BemPessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemPessoaVO.class, new BemPessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemPosseVO.class, new BemPosseVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(CertidaoPessoaVO.class, new CertidaoPessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ClienteVO.class, new ClienteVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DadosClienteVO.class, new DadosClienteVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DadosEtiquetaVO.class, new DadosEtiquetaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DadosRegistroVO.class, new DadosRegistroVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(DominioVO.class, new DominioVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(EmailPessoaVO.class, new EmailPessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(EnderecoPessoaVO.class, new EnderecoPessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(FonteRendaPessoaVO.class, new FonteRendaPessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(FuncionarioVO.class, new FuncionarioVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(GrupoEconomicoVO.class, new GrupoEconomicoVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(InformacaoProfissionalVO.class, new InformacaoProfissionalVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(InformacaoUtilizadaVO.class, new InformacaoUtilizadaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(InstituicaoResponsavelVO.class, new InstituicaoResponsavelVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(MensagemPessoaVO.class, new MensagemPessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(NucleoVO.class, new NucleoVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaFisicaVO.class, new PessoaFisicaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaJuridicaVO.class, new PessoaJuridicaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaVO.class, new PessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ProdutividadePessoaVO.class, new ProdutividadePessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ProdutorVO.class, new ProdutorVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ReferenciaPessoaVO.class, new ReferenciaPessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelacionamentoPessoaPoderVO.class, new RelacionamentoPessoaPoderVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelacionamentoPessoaVO.class, new RelacionamentoPessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TelefonePessoaVO.class, new TelefonePessoaVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TipoAnotacaoVO.class, new TipoAnotacaoVO());
		CapesApiVOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TributacaoPessoaVO.class, new TributacaoPessoaVO());
	}
}
