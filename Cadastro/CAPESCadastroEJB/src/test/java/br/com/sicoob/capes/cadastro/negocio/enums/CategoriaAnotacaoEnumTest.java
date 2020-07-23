// 28/10/2013 - 10:37:45
package br.com.sicoob.capes.cadastro.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class CategoriaAnotacaoEnumTest {

	/**
	 * O método Test get id categoria anotacao.
	 */
	@Test
	public void testGetIdCategoriaAnotacao() {
		assertEquals(CategoriaAnotacaoEnum.INFORMATIVA.getIdCategoriaAnotacao(), Short.valueOf((short) 1));
		assertEquals(CategoriaAnotacaoEnum.IMPEDITIVA_RELATIVA.getIdCategoriaAnotacao(), Short.valueOf((short) 2));
		assertEquals(CategoriaAnotacaoEnum.IMPEDITIVA_ABSOLUTA.getIdCategoriaAnotacao(), Short.valueOf((short) 3));
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(CategoriaAnotacaoEnum.INFORMATIVA.getDescricao(), "Informativa");
		assertEquals(CategoriaAnotacaoEnum.IMPEDITIVA_RELATIVA.getDescricao(), "Impeditiva relativa");
		assertEquals(CategoriaAnotacaoEnum.IMPEDITIVA_ABSOLUTA.getDescricao(), "Impeditiva absoluta");
	}
	
	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(3), Integer.valueOf(CategoriaAnotacaoEnum.values().length));
		assertNotNull(CategoriaAnotacaoEnum.valueOf("INFORMATIVA"));
		assertNotNull(CategoriaAnotacaoEnum.valueOf("IMPEDITIVA_RELATIVA"));
		assertNotNull(CategoriaAnotacaoEnum.valueOf("IMPEDITIVA_ABSOLUTA"));
	}

}
