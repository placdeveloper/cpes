// 29/10/2013 - 11:26:21
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoSemiAutomaticaEnum.SISBR_FRUSTRACAO_SAFRA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoAnotacaoSemiAutomaticaEnumTest extends 
AbstractEnumTest {

	/** A constante CODIGO_FRUSTRACAO_SAFRA. */
	private static final Integer CODIGO_FRUSTRACAO_SAFRA = 111;

	/**
	 * O método Test get cod tipo anotacao.
	 */
	@Test
	public void testGetCodTipoAnotacao() {
		assertEquals(SISBR_FRUSTRACAO_SAFRA.getCodTipoAnotacao(), CODIGO_FRUSTRACAO_SAFRA);
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(SISBR_FRUSTRACAO_SAFRA.getDescricao(), "Frustração de Safra");
	}

	/**
	 * O método Test is nada consta.
	 */
	@Test
	public void testIsNadaConsta() {
		assertFalse(SISBR_FRUSTRACAO_SAFRA.isNadaConsta());
	}

	/**
	 * O método Test get origem.
	 */
	@Test
	public void testGetOrigem() {
		assertEquals(SISBR_FRUSTRACAO_SAFRA.getOrigem(), OrigemInformacaoEnum.SISBR);
	}

	/**
	 * O método Test recuperar tipo anotacao automatica enum por codigo.
	 */
	@Test
	public void testRecuperarTipoAnotacaoAutomaticaEnumPorCodigo() {
		assertEquals(SISBR_FRUSTRACAO_SAFRA,
				TipoAnotacaoSemiAutomaticaEnum
						.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(CODIGO_FRUSTRACAO_SAFRA));
	}

	/**
	 * O método Test recuperar tipo anotacao automatica enum por codigo inexistente.
	 */
	@Test
	public void testRecuperarTipoAnotacaoAutomaticaEnumPorCodigoInexistente() {
		assertNull(TipoAnotacaoSemiAutomaticaEnum
				.recuperarTipoAnotacaoAutomaticaEnumPorCodigo(NumberUtils.INTEGER_MINUS_ONE));
	}

	/**
	 * {@inheritDoc}
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(1), Integer.valueOf(TipoAnotacaoSemiAutomaticaEnum.values().length));
		assertNotNull(TipoAnotacaoSemiAutomaticaEnum.valueOf("SISBR_FRUSTRACAO_SAFRA"));
	}	
}
