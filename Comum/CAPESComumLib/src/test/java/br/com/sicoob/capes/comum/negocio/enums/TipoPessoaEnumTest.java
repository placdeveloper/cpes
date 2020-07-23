// 30/10/2013 - 08:45:24
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum.PESSOA_FISICA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum.PESSOA_JURIDICA;
import static br.com.sicoob.capes.comum.util.Constantes.Negocio.GED_SIGLA_ASSUNTO_PF;
import static br.com.sicoob.capes.comum.util.Constantes.Negocio.GED_SIGLA_ASSUNTO_PJ;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoPessoaEnumTest extends 
AbstractEnumTest {

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoPessoaEnum#getCodigo()}.
	 */
	@Test
	public void testGetCodigo() {
		assertEquals(PESSOA_FISICA.getCodigo(), NumberUtils.SHORT_ZERO);
		assertEquals(PESSOA_JURIDICA.getCodigo(), NumberUtils.SHORT_ONE);
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoPessoaEnum#getDescricao()}.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(PESSOA_FISICA.getDescricao(), "Pessoa Física");
		assertEquals(PESSOA_JURIDICA.getDescricao(), "Pessoa Jurídica");
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoPessoaEnum#getSiglaAssuntoGED()}.
	 */
	@Test
	public void testGetSiglaAssuntoGED() {
		assertEquals(PESSOA_FISICA.getSiglaAssuntoGED(), GED_SIGLA_ASSUNTO_PF);
		assertEquals(PESSOA_JURIDICA.getSiglaAssuntoGED(), GED_SIGLA_ASSUNTO_PJ);
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoPessoaEnum#getDocumento()}.
	 */
	@Test
	public void testGetDocumento() {
		assertEquals(PESSOA_FISICA.getDocumento(), "CPF");
		assertEquals(PESSOA_JURIDICA.getDocumento(), "CNPJ");
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoPessoaEnum#getSiglaAssuntoGED(java.lang.Short)}.
	 */
	@Test
	public void testGetSiglaAssuntoGEDShort() {
		assertEquals(TipoPessoaEnum.getSiglaAssuntoGED(NumberUtils.SHORT_ZERO), GED_SIGLA_ASSUNTO_PF);
		assertEquals(TipoPessoaEnum.getSiglaAssuntoGED(NumberUtils.SHORT_ONE), GED_SIGLA_ASSUNTO_PJ);
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoPessoaEnum#isPessoaFisica(java.lang.Short)}.
	 */
	@Test
	public void testIsPessoaFisica() {
		assertTrue(TipoPessoaEnum.isPessoaFisica(NumberUtils.SHORT_ZERO));
	}
	
	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoPessoaEnum#isPessoaJuridica(java.lang.Short)}.
	 */
	@Test
	public void testIsPessoaJuridica() {
		assertTrue(TipoPessoaEnum.isPessoaJuridica(NumberUtils.SHORT_ONE));
		assertFalse(TipoPessoaEnum.isPessoaJuridica(NumberUtils.SHORT_ZERO));
	}
	
	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoPessoaEnum#valueOf(java.lang.Short)}.
	 */
	@Test
	public void testValueOf() {
		assertEquals(PESSOA_FISICA, TipoPessoaEnum.valueOf(NumberUtils.SHORT_ZERO));
		assertEquals(PESSOA_JURIDICA, TipoPessoaEnum.valueOf(NumberUtils.SHORT_ONE));
	}
	
	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoPessoaEnum#valueOf(java.lang.Short)}.
	 */
	@Test
	public void testValueOfInexistente() {
		assertNull(TipoPessoaEnum.valueOf(NumberUtils.SHORT_MINUS_ONE));
	}

	/**
	 * {@inheritDoc}
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(2), Integer.valueOf(TipoPessoaEnum.values().length));
		assertNotNull(TipoPessoaEnum.valueOf("PESSOA_FISICA"));
		assertNotNull(TipoPessoaEnum.valueOf("PESSOA_JURIDICA"));
	}

}
