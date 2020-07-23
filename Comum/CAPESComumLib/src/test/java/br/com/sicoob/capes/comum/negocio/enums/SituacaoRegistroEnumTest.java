// 29/10/2013 - 11:06:54
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum.A_ENCAMINHAR;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum.BLOQUEADO;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum.DEVOLVIDO;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum.EM_AUTORIZACAO;
import static br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum.VIGENTE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class SituacaoRegistroEnumTest extends 
AbstractEnumTest {
	
	/**
	 * O método Test value of.
	 */
	@Test
	public void testValueOf() {
		assertEquals(A_ENCAMINHAR, SituacaoRegistroEnum.valueOf(Short.valueOf((short) 4)));
		assertEquals(BLOQUEADO, SituacaoRegistroEnum.valueOf(NumberUtils.SHORT_ONE));
		assertEquals(DEVOLVIDO, SituacaoRegistroEnum.valueOf(Short.valueOf((short) 3)));
		assertEquals(EM_AUTORIZACAO, SituacaoRegistroEnum.valueOf(Short.valueOf((short) 2)));
		assertEquals(VIGENTE, SituacaoRegistroEnum.valueOf(NumberUtils.SHORT_ZERO));
	}
	
	/**
	 * O método Test value of inexistente.
	 */
	@Test
	public void testValueOfInexistente() {
		assertNull(SituacaoRegistroEnum.valueOf(NumberUtils.SHORT_MINUS_ONE));
	}

	/**
	 * {@inheritDoc}
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(5), Integer.valueOf(SituacaoRegistroEnum.values().length));
		assertNotNull(SituacaoRegistroEnum.valueOf("A_ENCAMINHAR"));
		assertNotNull(SituacaoRegistroEnum.valueOf("BLOQUEADO"));
		assertNotNull(SituacaoRegistroEnum.valueOf("DEVOLVIDO"));
		assertNotNull(SituacaoRegistroEnum.valueOf("EM_AUTORIZACAO"));
		assertNotNull(SituacaoRegistroEnum.valueOf("VIGENTE"));
	}	
}
