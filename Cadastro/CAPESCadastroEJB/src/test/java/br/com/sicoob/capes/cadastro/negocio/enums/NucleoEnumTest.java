// 28/10/2013 - 14:53:19
package br.com.sicoob.capes.cadastro.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class NucleoEnumTest {

	/**
	 * O método Test get num nucleo.
	 */
	@Test
	public void testGetNumNucleo() {
		assertEquals(NucleoEnum.NENHUM.getNumNucleo(), NumberUtils.INTEGER_ZERO);
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(NucleoEnum.NENHUM.getDescricao(), "Nenhum");
	}
	
	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(1), Integer.valueOf(NucleoEnum.values().length));
		assertNotNull(NucleoEnum.valueOf("NENHUM"));
	}
	
}
