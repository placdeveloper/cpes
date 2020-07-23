// 30/10/2013 - 13:38:09
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoSexoEnum.FEMININO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoSexoEnum.MASCULINO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoSexoEnum.NAO_INFORMADO;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.apache.commons.lang.math.NumberUtils.INTEGER_ONE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoSexoEnumTest extends 
AbstractEnumTest {

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoSexoEnum#getCodigo()}.
	 */
	@Test
	public void testGetCodigo() {
		assertEquals(Character.valueOf('M'), MASCULINO.getCodigo());
		assertEquals(Character.valueOf('F'), FEMININO.getCodigo());
		assertEquals(Character.valueOf('N'), NAO_INFORMADO.getCodigo());
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoSexoEnum#getDescricao()}.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals("Masculino", MASCULINO.getDescricao());
		assertEquals("Feminino", FEMININO.getDescricao());
		assertEquals("Não Informado", NAO_INFORMADO.getDescricao());
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoSexoEnum#getCodigoRFB()}.
	 */
	@Test
	public void testGetCodigoRFB() {
		assertEquals(INTEGER_ONE, MASCULINO.getCodigoRFB());
		assertEquals(Integer.valueOf(2), FEMININO.getCodigoRFB());
		assertEquals(Integer.valueOf(9), NAO_INFORMADO.getCodigoRFB());
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoSexoEnum#recuperarTipoSexoEnumPorCodigo(java.lang.Character)}.
	 */
	@Test
	public void testRecuperarTipoSexoEnumPorCodigo() {
		assertEquals(MASCULINO, TipoSexoEnum.recuperarTipoSexoEnumPorCodigo(Character.valueOf('M')));
		assertEquals(FEMININO, TipoSexoEnum.recuperarTipoSexoEnumPorCodigo(Character.valueOf('F')));
		assertEquals(NAO_INFORMADO, TipoSexoEnum.recuperarTipoSexoEnumPorCodigo(Character.valueOf('N')));
	}
	
	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoSexoEnum#recuperarTipoSexoEnumPorCodigo(java.lang.Character)}.
	 */
	@Test
	public void testRecuperarTipoSexoEnumPorCodigoInexistente() {
		assertNull(TipoSexoEnum.recuperarTipoSexoEnumPorCodigo(Character.valueOf('Z')));
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoSexoEnum#recuperarPorCodigoRFB(java.lang.Integer)}.
	 */
	@Test
	public void testRecuperarPorCodigoRFB() {
		assertEquals(MASCULINO, TipoSexoEnum.recuperarPorCodigoRFB(INTEGER_ONE));
		assertEquals(FEMININO, TipoSexoEnum.recuperarPorCodigoRFB(Integer.valueOf(2)));
		assertEquals(NAO_INFORMADO, TipoSexoEnum.recuperarPorCodigoRFB(Integer.valueOf(9)));
	}
	
	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoSexoEnum#recuperarPorCodigoRFB(java.lang.Integer)}.
	 */
	@Test
	public void testRecuperarPorCodigoRFBInexistente() {
		assertNull(TipoSexoEnum.recuperarPorCodigoRFB(INTEGER_MINUS_ONE));
	}

	/**
	 * {@inheritDoc}
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(3), Integer.valueOf(TipoSexoEnum.values().length));
		assertNotNull(TipoSexoEnum.valueOf("MASCULINO"));
		assertNotNull(TipoSexoEnum.valueOf("FEMININO"));
		assertNotNull(TipoSexoEnum.valueOf("NAO_INFORMADO"));
	}
	
}
