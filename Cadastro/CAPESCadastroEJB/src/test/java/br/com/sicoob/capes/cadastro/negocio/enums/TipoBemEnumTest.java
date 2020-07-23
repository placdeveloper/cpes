// 29/10/2013 - 14:10:16
package br.com.sicoob.capes.cadastro.negocio.enums;

import static br.com.sicoob.capes.cadastro.negocio.enums.TipoBemEnum.IMOVEL;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoBemEnum.MOVEL;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoBemEnum.OUTROS_BENS;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoBemEnum.SEMOVENTES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoBemEnumTest {

	/**
	 * O método Test get id tipo bem.
	 */
	@Test
	public void testGetIdTipoBem() {
		assertEquals(IMOVEL.getIdTipoBem(), NumberUtils.SHORT_ONE);
		assertEquals(MOVEL.getIdTipoBem(), NumberUtils.SHORT_ZERO);
		assertEquals(OUTROS_BENS.getIdTipoBem(), Short.valueOf((short) 2));
		assertEquals(SEMOVENTES.getIdTipoBem(), Short.valueOf((short) 3));
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(IMOVEL.getDescricao(), "Imóvel");
		assertEquals(MOVEL.getDescricao(), "Móvel");
		assertEquals(OUTROS_BENS.getDescricao(), "Outros bens");
		assertEquals(SEMOVENTES.getDescricao(), "Semoventes");
	}

	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(4), Integer.valueOf(TipoBemEnum.values().length));
		assertNotNull(TipoBemEnum.valueOf("IMOVEL"));
		assertNotNull(TipoBemEnum.valueOf("MOVEL"));
		assertNotNull(TipoBemEnum.valueOf("OUTROS_BENS"));
		assertNotNull(TipoBemEnum.valueOf("SEMOVENTES"));
	}
}
