// 30/10/2013 - 08:38:19
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoLocalidadeEnum.DISTRITO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoLocalidadeEnum.MUNICIPIO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoLocalidadeEnum.OUTRO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoLocalidadeEnum.SUBDISTRITO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

import br.com.sicoob.capes.comum.negocio.enums.TipoLocalidadeEnum;

/**
 * @author Rodrigo.Chaves
 */
public class TipoLocalidadeEnumTest extends AbstractEnumTest {

	/**
	 * Test method for {@link br.com.sicoob.capes.comum.negocio.enums.TipoLocalidadeEnum#getIdTipoLocalidade()}.
	 */
	@Test
	public void testGetIdTipoLocalidade() {
		assertEquals(MUNICIPIO.getIdTipoLocalidade(), NumberUtils.INTEGER_ONE);
		assertEquals(DISTRITO.getIdTipoLocalidade(), Integer.valueOf(2));
		assertEquals(SUBDISTRITO.getIdTipoLocalidade(), Integer.valueOf(3));
		assertEquals(OUTRO.getIdTipoLocalidade(), Integer.valueOf(4));
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.comum.negocio.enums.TipoLocalidadeEnum#getDescricao()}.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(MUNICIPIO.getDescricao(), "MUNICIPIO");
		assertEquals(DISTRITO.getDescricao(), "DISTRITO");
		assertEquals(SUBDISTRITO.getDescricao(), "SUBDISTRITO");
		assertEquals(OUTRO.getDescricao(), "OUTRO");
	}

	/**
	 * {@inheritDoc}
	 */
	@Test
	@Override
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(4), Integer.valueOf(TipoLocalidadeEnum.values().length));
		assertNotNull(TipoLocalidadeEnum.valueOf("MUNICIPIO"));
		assertNotNull(TipoLocalidadeEnum.valueOf("DISTRITO"));
		assertNotNull(TipoLocalidadeEnum.valueOf("SUBDISTRITO"));
		assertNotNull(TipoLocalidadeEnum.valueOf("OUTRO"));
	}
}
