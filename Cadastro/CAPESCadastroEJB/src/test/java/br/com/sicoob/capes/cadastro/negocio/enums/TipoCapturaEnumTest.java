package br.com.sicoob.capes.cadastro.negocio.enums;

import static br.com.sicoob.capes.cadastro.negocio.enums.TipoCapturaEnum.AUTOMATICA;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoCapturaEnum.MANUAL;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoCapturaEnum.SEMI_AUTOMATICA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * A Classe TipoCapturaEnumTest.
 */
public class TipoCapturaEnumTest {

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(AUTOMATICA.getDescricao(), "Automática");
		assertEquals(MANUAL.getDescricao(), "Manual");
		assertEquals(SEMI_AUTOMATICA.getDescricao(), "Semi-automática");
	}

	/**
	 * O método Test get codigo.
	 */
	@Test
	public void testGetCodigo() {
		assertEquals(AUTOMATICA.getCodigo(), NumberUtils.SHORT_ONE);
		assertEquals(MANUAL.getCodigo(), Short.valueOf((short) 3));
		assertEquals(SEMI_AUTOMATICA.getCodigo(), Short.valueOf((short) 2));
	}

	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(3), Integer.valueOf(TipoCapturaEnum.values().length));
		assertNotNull(TipoCapturaEnum.valueOf("AUTOMATICA"));
		assertNotNull(TipoCapturaEnum.valueOf("MANUAL"));
		assertNotNull(TipoCapturaEnum.valueOf("SEMI_AUTOMATICA"));
	}
}
