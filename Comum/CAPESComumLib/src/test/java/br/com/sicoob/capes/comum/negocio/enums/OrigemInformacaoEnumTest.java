// 30/10/2013 - 16:19:10
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.OrigemInformacaoEnum.BACEN;
import static br.com.sicoob.capes.comum.negocio.enums.OrigemInformacaoEnum.INTERNA;
import static br.com.sicoob.capes.comum.negocio.enums.OrigemInformacaoEnum.RECEITA;
import static br.com.sicoob.capes.comum.negocio.enums.OrigemInformacaoEnum.SERASA;
import static br.com.sicoob.capes.comum.negocio.enums.OrigemInformacaoEnum.SISBR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class OrigemInformacaoEnumTest extends AbstractEnumTest {

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.OrigemInformacaoEnum#getIdOrigemInformacao()}.
	 */
	@Test
	public void testGetIdOrigemInformacao() {

		assertEquals(Short.valueOf((short) 1), BACEN.getIdOrigemInformacao());
		assertEquals(Short.valueOf((short) 2), INTERNA.getIdOrigemInformacao());
		assertEquals(Short.valueOf((short) 3), RECEITA.getIdOrigemInformacao());
		assertEquals(Short.valueOf((short) 4), SERASA.getIdOrigemInformacao());
		assertEquals(Short.valueOf((short) 5), SISBR.getIdOrigemInformacao());
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.OrigemInformacaoEnum#getDescricao()}.
	 */
	@Test
	public void testGetDescricao() {

		assertEquals("Bacen", BACEN.getDescricao());
		assertEquals("Interna", INTERNA.getDescricao());
		assertEquals("Receita", RECEITA.getDescricao());
		assertEquals("Serasa", SERASA.getDescricao());
		assertEquals("Sisbr", SISBR.getDescricao());
	}

	/**
	 * {@inheritDoc}
	 */
	@Test
	public void testMetodosHerdados() {

		assertEquals(Integer.valueOf(9), Integer.valueOf(OrigemInformacaoEnum.values().length));
		assertNotNull(OrigemInformacaoEnum.valueOf("BACEN"));
		assertNotNull(OrigemInformacaoEnum.valueOf("INTERNA"));
		assertNotNull(OrigemInformacaoEnum.valueOf("RECEITA"));
		assertNotNull(OrigemInformacaoEnum.valueOf("SERASA"));
		assertNotNull(OrigemInformacaoEnum.valueOf("SISBR"));
		assertNotNull(OrigemInformacaoEnum.valueOf("BOA_VISTA"));
		assertNotNull(OrigemInformacaoEnum.valueOf("ETICA_ONLINE"));
		assertNotNull(OrigemInformacaoEnum.valueOf("AML_CONSULTING"));
		assertNotNull(OrigemInformacaoEnum.valueOf("SPC"));
	}

}
