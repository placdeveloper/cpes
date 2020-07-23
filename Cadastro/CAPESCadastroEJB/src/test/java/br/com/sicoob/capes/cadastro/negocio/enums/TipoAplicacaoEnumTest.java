// 29/10/2013 - 11:37:36
package br.com.sicoob.capes.cadastro.negocio.enums;

import static br.com.sicoob.capes.cadastro.negocio.enums.TipoAplicacaoEnum.AMBAS;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoAplicacaoEnum.PESSOA_FISICA;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoAplicacaoEnum.PESSOA_JURIDICA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoAplicacaoEnumTest {

	/**
	 * O m�todo Test get id tipo aplicacao.
	 */
	@Test
	public void testGetIdTipoAplicacao() {
		assertEquals(AMBAS.getIdTipoAplicacao(), Short.valueOf((short) 3));
		assertEquals(PESSOA_FISICA.getIdTipoAplicacao(), NumberUtils.SHORT_ONE);
		assertEquals(PESSOA_JURIDICA.getIdTipoAplicacao(), Short.valueOf((short) 2));
	}

	/**
	 * O m�todo Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(AMBAS.getDescricao(), "Ambas");
		assertEquals(PESSOA_FISICA.getDescricao(), "Pessoa F�sica");
		assertEquals(PESSOA_JURIDICA.getDescricao(), "Pessoa Jur�dica");
	}

	/**
	 * O m�todo Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(3), Integer.valueOf(TipoAplicacaoEnum.values().length));
		assertNotNull(TipoAplicacaoEnum.valueOf("AMBAS"));
		assertNotNull(TipoAplicacaoEnum.valueOf("PESSOA_FISICA"));
		assertNotNull(TipoAplicacaoEnum.valueOf("PESSOA_JURIDICA"));
	}	

	/**
	 * O m�todo Test value of.
	 */
	@Test
	public void testValueOf() {
		assertEquals(PESSOA_FISICA, TipoAplicacaoEnum.valueOf(PESSOA_FISICA.getIdTipoAplicacao()));
		assertEquals(PESSOA_JURIDICA,
		        TipoAplicacaoEnum.valueOf(PESSOA_JURIDICA.getIdTipoAplicacao()));
		assertEquals(AMBAS, TipoAplicacaoEnum.valueOf(AMBAS.getIdTipoAplicacao()));
	}
	
	/**
	 * O m�todo Test value of inexistente.
	 */
	@Test
	public void testValueOfInexistente() {
		try {
			assertFalse(TipoAplicacaoEnum.valueOf(NumberUtils.SHORT_MINUS_ONE) instanceof TipoAplicacaoEnum);
		} catch(IllegalArgumentException e) {
			assertNotNull(e);
		}
	}
}
