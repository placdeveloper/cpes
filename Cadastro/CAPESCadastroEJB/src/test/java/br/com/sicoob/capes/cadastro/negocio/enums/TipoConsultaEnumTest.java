package br.com.sicoob.capes.cadastro.negocio.enums;

import static br.com.sicoob.capes.cadastro.negocio.enums.TipoConsultaEnum.ATUAL;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoConsultaEnum.AUTORIZACAO_FICHA_PREVIA;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoConsultaEnum.PERIODO;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoConsultaEnum.TUDO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * A Classe TipoConsultaEnumTest.
 */
public class TipoConsultaEnumTest {

	/**
	 * O método Test get name.
	 */
	@Test
	public final void testGetName() {
		assertEquals(AUTORIZACAO_FICHA_PREVIA.getName(), "AUTORIZACAO_FICHA_PREVIA");
		assertEquals(PERIODO.getName(), "PERIODO");
		assertEquals(TUDO.getName(), "TUDO");
		assertEquals(ATUAL.getName(), "ATUAL");
	}

	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(4), Integer.valueOf(TipoConsultaEnum.values().length));
		assertNotNull(TipoConsultaEnum.valueOf("AUTORIZACAO_FICHA_PREVIA"));
		assertNotNull(TipoConsultaEnum.valueOf("PERIODO"));
		assertNotNull(TipoConsultaEnum.valueOf("TUDO"));
		assertNotNull(TipoConsultaEnum.valueOf("ATUAL"));
	}

}
