// 29/10/2013 - 18:14:16
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum.CAIXA_POSTAL;
import static br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum.COMERCIAL;
import static br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum.OUTROS;
import static br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum.RESIDENCIAL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoEnderecoEnumTest {

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum#getCodigo()}
	 */
	@Test
	public void testGetCodigo() {
		assertEquals(RESIDENCIAL.getCodigo(), NumberUtils.SHORT_ZERO);
		assertEquals(COMERCIAL.getCodigo(), NumberUtils.SHORT_ONE);
		assertEquals(OUTROS.getCodigo(), Short.valueOf((short) 2));
		assertEquals(CAIXA_POSTAL.getCodigo(), Short.valueOf((short) 3));
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum#getDescricao()}
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(RESIDENCIAL.getDescricao(), "Residencial");
		assertEquals(COMERCIAL.getDescricao(), "Comercial");
		assertEquals(OUTROS.getDescricao(), "Outros");
		assertEquals(CAIXA_POSTAL.getDescricao(), "Caixa Postal");
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum#valueOf(java.lang.Short)}
	 */
	@Test
	public void testValueOf() {
		try {
			assertEquals(RESIDENCIAL, TipoEnderecoEnum.valueOf(NumberUtils.SHORT_ZERO));
			assertEquals(COMERCIAL, TipoEnderecoEnum.valueOf(NumberUtils.SHORT_ONE));
			assertEquals(OUTROS, TipoEnderecoEnum.valueOf(Short.valueOf((short) 2)));
			assertEquals(CAIXA_POSTAL, TipoEnderecoEnum.valueOf(Short.valueOf((short) 3)));
		} catch(IllegalArgumentException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum#valueOf(java.lang.Short)}
	 */
	@Test
	public void testValueOfInexistente() {
		try {
			Assert.fail("Era esperada uma exceção e foi recebido "
					+ TipoEnderecoEnum.valueOf(NumberUtils.SHORT_MINUS_ONE));
		} catch (IllegalArgumentException e) {
			assertNotNull(e);
		}
	}

	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(4), Integer.valueOf(TipoEnderecoEnum.values().length));
		assertNotNull(TipoEnderecoEnum.valueOf("RESIDENCIAL"));
		assertNotNull(TipoEnderecoEnum.valueOf("COMERCIAL"));
		assertNotNull(TipoEnderecoEnum.valueOf("OUTROS"));
		assertNotNull(TipoEnderecoEnum.valueOf("CAIXA_POSTAL"));
	}
}
