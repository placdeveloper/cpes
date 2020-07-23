package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

public class CAPESApiInclusaoDTOCodeCoverageTest {

	public static final Object setArgs[] = { null };
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
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(AnotacaoDTO.class, new AnotacaoDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemDTO.class, new BemDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemImovelDTO.class, new BemImovelDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemMovelDTO.class, new BemMovelDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(BemProprietarioDTO.class, new BemProprietarioDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ClienteDTO.class, new ClienteDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(EmailDTO.class, new EmailDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(EncaminharAutorizacaoDTO.class, new EncaminharAutorizacaoDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(EnderecoDTO.class, new EnderecoDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ExecutarProcedimentoAutorizacaoDTO.class, new ExecutarProcedimentoAutorizacaoDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(FonteRendaDTO.class, new FonteRendaDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(MensagemDTO.class, new MensagemDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaFisicaDTO.class, new PessoaFisicaDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(PessoaJuridicaDTO.class, new PessoaJuridicaDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(ReferenciaDTO.class, new ReferenciaDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(RelacionamentoDTO.class, new RelacionamentoDTO());
		CAPESApiInclusaoDTOCodeCoverageTest.superficialCapesEntidadeCodeCoverage(TelefoneDTO.class, new TelefoneDTO());
	}
}
