// 28/10/2013 - 11:02:21
package br.com.sicoob.capes.comum.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class GrupoCompartilhamentoEnumTest extends 
AbstractEnumTest {

	/**
	 * O método Test get codigo.
	 */
	@Test
	public void testGetCodigo() {
		assertEquals(GrupoCompartilhamentoEnum.SICOOB.getCodigo(), Short.valueOf((short) 1));
		assertEquals(GrupoCompartilhamentoEnum.FEDERAL_CRED.getCodigo(), Short.valueOf((short) 2));
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(GrupoCompartilhamentoEnum.SICOOB.getDescricao(), "Sicoob");
		assertEquals(GrupoCompartilhamentoEnum.FEDERAL_CRED.getDescricao(), "Federal Cred");
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(2), Integer.valueOf(GrupoCompartilhamentoEnum.values().length));
		assertNotNull(GrupoCompartilhamentoEnum.valueOf("SICOOB"));
		assertNotNull(GrupoCompartilhamentoEnum.valueOf("FEDERAL_CRED"));
	}
	
}
