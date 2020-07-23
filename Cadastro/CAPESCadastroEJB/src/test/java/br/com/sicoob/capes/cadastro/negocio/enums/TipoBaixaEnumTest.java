// 29/10/2013 - 14:00:39
package br.com.sicoob.capes.cadastro.negocio.enums;

import static br.com.sicoob.capes.cadastro.negocio.enums.TipoBaixaEnum.BAIXA_AUTOMATICA_NOVA_CONSULTA;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoBaixaEnum.BAIXA_AUTOMATICA_SISBR;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoBaixaEnum.BAIXA_MANUAL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoBaixaEnumTest {

	/**
	 * O método Test get id tipo baixa.
	 */
	@Test
	public void testGetIdTipoBaixa() {
		assertEquals(BAIXA_AUTOMATICA_NOVA_CONSULTA.getIdTipoBaixa(), Short.valueOf((short) 2));
		assertEquals(BAIXA_AUTOMATICA_SISBR.getIdTipoBaixa(), Short.valueOf((short) 3));
		assertEquals(BAIXA_MANUAL.getIdTipoBaixa(), NumberUtils.SHORT_ONE);
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(BAIXA_AUTOMATICA_NOVA_CONSULTA.getDescricao(), "Baixa Automática (Nova Consulta)");
		assertEquals(BAIXA_AUTOMATICA_SISBR.getDescricao(), "Baixa Automática (SISBR)");
		assertEquals(BAIXA_MANUAL.getDescricao(), "Baixa manual");
	}

	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(3), Integer.valueOf(TipoBaixaEnum.values().length));
		assertNotNull(TipoBaixaEnum.valueOf("BAIXA_AUTOMATICA_NOVA_CONSULTA"));
		assertNotNull(TipoBaixaEnum.valueOf("BAIXA_AUTOMATICA_SISBR"));
		assertNotNull(TipoBaixaEnum.valueOf("BAIXA_MANUAL"));
	}	
}
