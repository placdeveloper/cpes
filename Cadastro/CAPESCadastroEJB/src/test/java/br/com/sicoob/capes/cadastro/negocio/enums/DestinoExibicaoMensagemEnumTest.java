// 28/10/2013 - 10:45:35
package br.com.sicoob.capes.cadastro.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class DestinoExibicaoMensagemEnumTest {

	/**
	 * O método Test get codigo destino.
	 */
	@Test
	public void testGetCodigoDestino() {
		assertEquals(DestinoExibicaoMensagemEnum.AMBAS.getCodigoDestino(), Short.valueOf((short) 2));
		assertEquals(DestinoExibicaoMensagemEnum.CONSULTAS_CLIENTE.getCodigoDestino(), Short.valueOf((short) 1));
		assertEquals(DestinoExibicaoMensagemEnum.INTERNA.getCodigoDestino(), Short.valueOf((short) 0));
	}

	/**
	 * O método Test get descricao destino.
	 */
	@Test
	public void testGetDescricaoDestino() {
		assertEquals(DestinoExibicaoMensagemEnum.AMBAS.getDescricaoDestino(), "Ambas");
		assertEquals(DestinoExibicaoMensagemEnum.CONSULTAS_CLIENTE.getDescricaoDestino(), "Nas Consultas Efetuadas pelo Cliente");
		assertEquals(DestinoExibicaoMensagemEnum.INTERNA.getDescricaoDestino(), "Interna");
	}
	
	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(3), Integer.valueOf(DestinoExibicaoMensagemEnum.values().length));
		assertNotNull(DestinoExibicaoMensagemEnum.valueOf("AMBAS"));
		assertNotNull(DestinoExibicaoMensagemEnum.valueOf("CONSULTAS_CLIENTE"));
		assertNotNull(DestinoExibicaoMensagemEnum.valueOf("INTERNA"));
	}
	
}
