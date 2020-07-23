// 28/10/2013 - 15:01:08
package br.com.sicoob.capes.cadastro.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class ProcessoEnumTest {

	/**
	 * O método Test get sigla.
	 */
	@Test
	public void testGetSigla() {
		assertEquals(ProcessoEnum.CAPES_AECD.getSigla(), "CAPES-AECD");
		assertEquals(ProcessoEnum.CAPES_AESD.getSigla(), "CAPES-AESD");
		assertEquals(ProcessoEnum.CAPES_AICD.getSigla(), "CAPES-AICD");
		assertEquals(ProcessoEnum.CAPES_AISD.getSigla(), "CAPES-AISD");
	}

	/**
	 * O método Test get nome.
	 */
	@Test
	public void testGetNome() {
		assertEquals(ProcessoEnum.CAPES_AECD.getNome(), "CAPES - APROVAÇÃO EXTERNA COM DOCUMENTO");
		assertEquals(ProcessoEnum.CAPES_AESD.getNome(), "CAPES - APROVAÇÃO EXTERNA SEM DOCUMENTO");
		assertEquals(ProcessoEnum.CAPES_AICD.getNome(), "CAPES - APROVAÇÃO INTERNA COM DOCUMENTO");
		assertEquals(ProcessoEnum.CAPES_AISD.getNome(), "CAPES - APROVAÇÃO INTERNA SEM DOCUMENTO");
	}

	/**
	 * O método Test get aceita documento.
	 */
	@Test
	public void testGetAceitaDocumento() {
		assertTrue(ProcessoEnum.CAPES_AECD.getAceitaDocumento());
		assertFalse(ProcessoEnum.CAPES_AESD.getAceitaDocumento());
		assertTrue(ProcessoEnum.CAPES_AICD.getAceitaDocumento());
		assertFalse(ProcessoEnum.CAPES_AISD.getAceitaDocumento());
	}

	/**
	 * O método Test value of sigla.
	 */
	@Test
	public void testValueOfSigla() {
		assertEquals(ProcessoEnum.valueOfSigla("CAPES-AECD"), ProcessoEnum.CAPES_AECD);
		assertEquals(ProcessoEnum.valueOfSigla("CAPES-AESD"), ProcessoEnum.CAPES_AESD);
		assertEquals(ProcessoEnum.valueOfSigla("CAPES-AICD"), ProcessoEnum.CAPES_AICD);
		assertEquals(ProcessoEnum.valueOfSigla("CAPES-AISD"), ProcessoEnum.CAPES_AISD);
	}

	/**
	 * O método Test value of sigla inexistente.
	 */
	@Test
	public void testValueOfSiglaInexistente() {
		IllegalArgumentException exception = null;
		try {
			ProcessoEnum.valueOfSigla("INEXISTENTE");
		} catch (IllegalArgumentException e) {
			exception = e;
		} finally {
			assertNotNull(exception);
		}
	}
	
	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(4), Integer.valueOf(ProcessoEnum.values().length));
		assertNotNull(ProcessoEnum.valueOf("CAPES_AECD"));
		assertNotNull(ProcessoEnum.valueOf("CAPES_AESD"));
		assertNotNull(ProcessoEnum.valueOf("CAPES_AICD"));
		assertNotNull(ProcessoEnum.valueOf("CAPES_AISD"));
	}

}
