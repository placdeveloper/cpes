// 28/10/2013 - 13:53:47
package br.com.sicoob.capes.comum.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */

public class IntegracaoSistemasEnumTest extends 
AbstractEnumTest {

	/**
	 * O m�todo Test get nome sistema.
	 */
	@Test
	public void testGetNomeSistema() {
		assertEquals(IntegracaoSistemasEnum.GED.getNomeSistema(), "Gest�o Eletr�nica de Documentos");
		assertEquals(IntegracaoSistemasEnum.GFT.getNomeSistema(), "Gest�o de Fluxo de Trabalho");
		assertEquals(IntegracaoSistemasEnum.ITX.getNomeSistema(), "Integra��es Externas");
		assertEquals(IntegracaoSistemasEnum.LOC.getNomeSistema(), "Localidade");
	}

	/**
	 * O m�todo Test get sigla sistema.
	 */
	@Test
	public void testGetSiglaSistema() {
		assertEquals(IntegracaoSistemasEnum.GED.getSiglaSistema(), "GED");
		assertEquals(IntegracaoSistemasEnum.GFT.getSiglaSistema(), "GFT");
		assertEquals(IntegracaoSistemasEnum.ITX.getSiglaSistema(), "ITX");
		assertEquals(IntegracaoSistemasEnum.LOC.getSiglaSistema(), "LOC");
	}

	/**
	 * O m�todo Test to string.
	 */
	@Test
	public void testToString() {
		for (IntegracaoSistemasEnum valorEnum : IntegracaoSistemasEnum.values()) {
			assertEquals(valorEnum.toString(),
					valorEnum.getSiglaSistema() + " - " + valorEnum.getNomeSistema());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(5), Integer.valueOf(IntegracaoSistemasEnum.values().length));
		assertNotNull(IntegracaoSistemasEnum.valueOf("GED"));
		assertNotNull(IntegracaoSistemasEnum.valueOf("GFT"));
		assertNotNull(IntegracaoSistemasEnum.valueOf("ITX"));
		assertNotNull(IntegracaoSistemasEnum.valueOf("LOC"));
		assertNotNull(IntegracaoSistemasEnum.valueOf("SCI"));
	}
	
}
