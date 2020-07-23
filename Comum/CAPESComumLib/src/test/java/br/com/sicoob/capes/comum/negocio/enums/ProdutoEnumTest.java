package br.com.sicoob.capes.comum.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * A Classe ProdutoEnumTest.
 */
public class ProdutoEnumTest extends AbstractEnumTest {

	/**
	 * O método Test value of id.
	 */
	@Test
	public void testValueOfId() {

		assertEquals(ProdutoEnum.CCS, ProdutoEnum.valueOf((short) 41));
	}

	/**
	 * O método Test value of id inexistente.
	 */
	@Test
	public void testValueOfIdInexistente() {

		assertNull(ProdutoEnum.valueOf((short) -1));
	}

	/**
	 * O método Test get nome.
	 */
	@Test
	public void testGetNome() {

		assertEquals("CCS", ProdutoEnum.CCS.getNome());
	}

	/**
	 * O método Test get id.
	 */
	@Test
	public void testGetId() {

		assertEquals(Short.valueOf((short) 41), ProdutoEnum.CCS.getId());
	}

	/**
	 * {@inheritDoc}
	 */
	@Test
	@Override
	public void testMetodosHerdados() {

		assertEquals(1, ProdutoEnum.values().length);
		assertNotNull(ProdutoEnum.valueOf("CCS"));
	}

}
