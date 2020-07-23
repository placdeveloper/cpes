// 28/10/2013 - 14:38:59
package br.com.sicoob.capes.cadastro.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class NacionalidadeEnumTest {

	/**
	 * O m�todo Test get codigo.
	 */
	@Test
	public void testGetCodigo() {
		assertEquals(NacionalidadeEnum.BRASILEIRA.getCodigo(), Short.valueOf((short) 10));
		assertEquals(NacionalidadeEnum.OUTRAS.getCodigo(), Short.valueOf((short) 50));
	}

	/**
	 * O m�todo Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(NacionalidadeEnum.BRASILEIRA.getDescricao(), "Brasileira");
		assertEquals(NacionalidadeEnum.OUTRAS.getDescricao(), "Outras");
	}

	/**
	 * O m�todo Test is brasileira.
	 */
	@Test
	public void testIsBrasileira() {
		assertTrue(NacionalidadeEnum.isBrasileira(Short.valueOf((short) 10)));
		assertFalse(NacionalidadeEnum.isBrasileira(Short.valueOf((short) 11)));
	}
	
	/**
	 * O m�todo Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(2), Integer.valueOf(NacionalidadeEnum.values().length));
		assertNotNull(NacionalidadeEnum.valueOf("BRASILEIRA"));
		assertNotNull(NacionalidadeEnum.valueOf("OUTRAS"));
	}
	
}