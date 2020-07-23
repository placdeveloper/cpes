// 29/10/2013 - 17:00:31
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoEmailEnum.COMERCIAL;
import static br.com.sicoob.capes.comum.negocio.enums.TipoEmailEnum.OUTROS;
import static br.com.sicoob.capes.comum.negocio.enums.TipoEmailEnum.PARTICULAR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoEmailEnumTest {

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.negocio.enums.TipoEmailEnum#getCodigo()}
	 */
	@Test
	public final void testGetCodigo() {
		assertEquals(PARTICULAR.getCodigo(), NumberUtils.SHORT_ZERO);
		assertEquals(COMERCIAL.getCodigo(), NumberUtils.SHORT_ONE);
		assertEquals(OUTROS.getCodigo(), Short.valueOf((short) 2));
	}

	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(3), Integer.valueOf(TipoEmailEnum.values().length));
		assertNotNull(TipoEmailEnum.valueOf("PARTICULAR"));
		assertNotNull(TipoEmailEnum.valueOf("COMERCIAL"));
		assertNotNull(TipoEmailEnum.valueOf("OUTROS"));
	}
}
