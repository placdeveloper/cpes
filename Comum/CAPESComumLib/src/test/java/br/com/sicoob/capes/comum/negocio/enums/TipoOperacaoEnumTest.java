// 28/10/2013 - 09:06:10
package br.com.sicoob.capes.comum.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoOperacaoEnumTest extends 
AbstractEnumTest {

	/**
	 * O método Test get codigo.
	 */
	@Test
	public void testGetCodigo() {
		assertEquals(TipoOperacaoEnum.A.getCodigo(), Character.valueOf('A'));
		assertEquals(TipoOperacaoEnum.E.getCodigo(), Character.valueOf('E'));
		assertEquals(TipoOperacaoEnum.I.getCodigo(), Character.valueOf('I'));
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(TipoOperacaoEnum.A.getDescricao(), "Alteração");
		assertEquals(TipoOperacaoEnum.E.getDescricao(), "Exclusão");
		assertEquals(TipoOperacaoEnum.I.getDescricao(), "Inclusão");
	}

	/**
	 * O método Test to string.
	 */
	@Test
	public void testToString() {
		for (TipoOperacaoEnum tipoOperacao : TipoOperacaoEnum.values()) {
			assertEquals(tipoOperacao.toString(),
					tipoOperacao.getCodigo() + " - " + tipoOperacao.getDescricao());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(3), Integer.valueOf(TipoOperacaoEnum.values().length));
		assertNotNull(TipoOperacaoEnum.valueOf("A"));
		assertNotNull(TipoOperacaoEnum.valueOf("E"));
		assertNotNull(TipoOperacaoEnum.valueOf("I"));
	}
}
