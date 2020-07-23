/*
 * SICOOB
 * 
 * TipoRegraValidacaoCadastralEnumTest.java(br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnumTest)
 */
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum.I;
import static br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum.R;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 *
 * 29/08/2014
 */
public class TipoRegraValidacaoCadastralEnumTest extends AbstractEnumTest {

	/**
	 * Test method for {@link br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum#getDescricao()}.
	 */
	@Test
	public final void testGetDescricao() {

		assertEquals("Informativa", I.getDescricao());
		assertEquals("Restritiva", R.getDescricao());
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum#getCodigo()}.
	 */
	@Test
	public final void testGetCodigo() {
		assertEquals(Character.valueOf('I'), I.getCodigo());
		assertEquals(Character.valueOf('R'), R.getCodigo());
	}
	
	/**
	 * Test method for {@link br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum#valueOf(Character)}.
	 */
	@Test
	public final void testValueOf() {
		assertEquals(TipoRegraValidacaoCadastralEnum.I, TipoRegraValidacaoCadastralEnum.valueOf('I'));
		assertEquals(TipoRegraValidacaoCadastralEnum.R, TipoRegraValidacaoCadastralEnum.valueOf('R'));
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void testMetodosHerdados() {

		assertEquals(Integer.valueOf(2), Integer.valueOf(TipoRegraValidacaoCadastralEnum.values().length));
		assertNotNull(TipoRegraValidacaoCadastralEnum.valueOf("I"));
		assertNotNull(TipoRegraValidacaoCadastralEnum.valueOf("R"));
	}

}
