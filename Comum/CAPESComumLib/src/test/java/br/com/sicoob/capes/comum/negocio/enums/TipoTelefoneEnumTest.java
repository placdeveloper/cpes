// 30/10/2013 - 13:48:56
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum.CELULAR;
import static br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum.COMERCIAL;
import static br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum.FAX;
import static br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum.INTERNACIONAL;
import static br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum.OUTROS;
import static br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum.RECADO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum.RESIDENCIAL;
import static org.apache.commons.lang.math.NumberUtils.SHORT_MINUS_ONE;
import static org.apache.commons.lang.math.NumberUtils.SHORT_ONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoTelefoneEnumTest {

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum#getCodigo()}
	 * .
	 */
	@Test
	public void testGetCodigo() {

		assertEquals(Short.valueOf((short) 0), RESIDENCIAL.getCodigo());
		assertEquals(Short.valueOf((short) 1), COMERCIAL.getCodigo());
		assertEquals(Short.valueOf((short) 2), OUTROS.getCodigo());
		assertEquals(Short.valueOf((short) 3), INTERNACIONAL.getCodigo());
		assertEquals(Short.valueOf((short) 4), RECADO.getCodigo());
		assertEquals(Short.valueOf((short) 5), FAX.getCodigo());
		assertEquals(Short.valueOf((short) 6), CELULAR.getCodigo());
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum#getDescricao()}
	 * .
	 */
	@Test
	public void testGetDescricao() {

		assertEquals("Residencial", RESIDENCIAL.getDescricao());
		assertEquals("Comercial", COMERCIAL.getDescricao());
		assertEquals("Outros", OUTROS.getDescricao());
		assertEquals("Internacional", INTERNACIONAL.getDescricao());
		assertEquals("Recado", RECADO.getDescricao());
		assertEquals("Fax", FAX.getDescricao());
		assertEquals("Celular", CELULAR.getDescricao());
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum#valueOf(java.lang.Short)}
	 * .
	 */
	@Test
	public void testValueOf() {

		try {
			assertEquals(COMERCIAL, TipoTelefoneEnum.valueOf(SHORT_ONE));
			assertEquals(OUTROS, TipoTelefoneEnum.valueOf((short) 2));
			assertEquals(INTERNACIONAL, TipoTelefoneEnum.valueOf((short) 3));
			assertEquals(RECADO, TipoTelefoneEnum.valueOf((short) 4));
			assertEquals(FAX, TipoTelefoneEnum.valueOf((short) 5));
			assertEquals(CELULAR, TipoTelefoneEnum.valueOf((short) 6));
		} catch (IllegalArgumentException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum#valueOf(java.lang.Short)}
	 * .
	 */
	@Test
	public void testValueOfInexistente() {

		try {
			fail("Era esperada uma excecao mas foi recebido: " + TipoTelefoneEnum.valueOf(SHORT_MINUS_ONE));
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
	}

	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {

		assertEquals(Integer.valueOf(8), Integer.valueOf(TipoTelefoneEnum.values().length));
		assertNotNull(TipoTelefoneEnum.valueOf("PARTICULAR"));
		assertNotNull(TipoTelefoneEnum.valueOf("RESIDENCIAL"));
		assertNotNull(TipoTelefoneEnum.valueOf("COMERCIAL"));
		assertNotNull(TipoTelefoneEnum.valueOf("OUTROS"));
		assertNotNull(TipoTelefoneEnum.valueOf("INTERNACIONAL"));
		assertNotNull(TipoTelefoneEnum.valueOf("RECADO"));
		assertNotNull(TipoTelefoneEnum.valueOf("FAX"));
		assertNotNull(TipoTelefoneEnum.valueOf("CELULAR"));
	}

}
