// 28/10/2013 - 10:59:15
package br.com.sicoob.capes.cadastro.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class FuncaoEnumTest {

	/**
	 * O método Test get id tipo funcao.
	 */
	@Test
	public void testGetIdTipoFuncao() {
		assertEquals(FuncaoEnum.GERENTE.getIdTipoFuncao(), Short.valueOf((short) 2));
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(FuncaoEnum.GERENTE.getDescricao(), "Gerente");
	}
	
	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(1), Integer.valueOf(FuncaoEnum.values().length));
		assertNotNull(FuncaoEnum.valueOf("GERENTE"));
	}
	
}
