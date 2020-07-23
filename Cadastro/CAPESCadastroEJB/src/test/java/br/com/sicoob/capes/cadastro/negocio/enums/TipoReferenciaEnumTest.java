// 30/10/2013 - 10:37:18
package br.com.sicoob.capes.cadastro.negocio.enums;

import static br.com.sicoob.capes.cadastro.negocio.enums.TipoReferenciaEnum.BANCARIA;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoReferenciaEnum.CLIENTE;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoReferenciaEnum.COMERCIAL;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoReferenciaEnum.COMERCIALIZACAO_SAFRA;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoReferenciaEnum.COOPERADO;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoReferenciaEnum.FORNECEDOR;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoReferenciaEnum.PARTICULAR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoReferenciaEnumTest {

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoReferenciaEnum#getCodigo()}.
	 */
	@Test
	public void testGetCodigo() {
		assertEquals(BANCARIA.getCodigo(), NumberUtils.SHORT_ZERO);
		assertEquals(COMERCIAL.getCodigo(), NumberUtils.SHORT_ONE);
		assertEquals(PARTICULAR.getCodigo(), Short.valueOf((short) 2));
		assertEquals(COMERCIALIZACAO_SAFRA.getCodigo(), Short.valueOf((short) 3));
		assertEquals(COOPERADO.getCodigo(), Short.valueOf((short) 4));
		assertEquals(FORNECEDOR.getCodigo(), Short.valueOf((short) 5));
		assertEquals(CLIENTE.getCodigo(), Short.valueOf((short) 6));
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoReferenciaEnum#getDescricao()}.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(BANCARIA.getDescricao(), "Bancária");
		assertEquals(COMERCIAL.getDescricao(), "Comercial");
		assertEquals(PARTICULAR.getDescricao(), "Particular");
		assertEquals(COMERCIALIZACAO_SAFRA.getDescricao(), "Comercialização da Safra");
		assertEquals(COOPERADO.getDescricao(), "Cooperado");
		assertEquals(FORNECEDOR.getDescricao(), "Fornecedor");
		assertEquals(CLIENTE.getDescricao(), "Cliente");
	}

	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(7), Integer.valueOf(TipoReferenciaEnum.values().length));
		assertNotNull(TipoReferenciaEnum.valueOf("BANCARIA"));
		assertNotNull(TipoReferenciaEnum.valueOf("COMERCIAL"));
		assertNotNull(TipoReferenciaEnum.valueOf("PARTICULAR"));
		assertNotNull(TipoReferenciaEnum.valueOf("COMERCIALIZACAO_SAFRA"));
		assertNotNull(TipoReferenciaEnum.valueOf("COOPERADO"));
		assertNotNull(TipoReferenciaEnum.valueOf("FORNECEDOR"));
		assertNotNull(TipoReferenciaEnum.valueOf("CLIENTE"));
	}

}
