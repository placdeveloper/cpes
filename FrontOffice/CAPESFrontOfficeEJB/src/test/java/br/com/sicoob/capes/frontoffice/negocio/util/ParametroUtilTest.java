package br.com.sicoob.capes.frontoffice.negocio.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

/**
 * A Classe ParametroUtilTest.
 */
public final class ParametroUtilTest {

	/**
	 * O método Test recuperar valor parametro.
	 */
	@Test
	public void testRecuperarValorParametro() {

		ParametroEnum cpfCnpj = ParametroEnum.CPF_CNPJ;

		Parametro parametro = new Parametro(cpfCnpj.getRotulo(), cpfCnpj.getTipoParametro(), "63503611134",
				cpfCnpj.getTipoDadoPersistencia());

		Map<String, Parametro> parametros = new HashMap<String, Parametro>();
		parametros.put(cpfCnpj.getRotulo(), parametro);

		Object valor = ParametroUtil.recuperarValorParametro(parametros, cpfCnpj, cpfCnpj.getTipoDado());

		Assert.assertNotNull(valor);
		Assert.assertEquals(cpfCnpj.getTipoDado(), valor.getClass());
		Assert.assertEquals("63503611134", valor);
	}
}
