// 28/10/2013 - 10:21:01
package br.com.sicoob.capes.cadastro.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * TODO javadoc
 * 
 * 
 * @author Rodrigo.Chaves
 */
public class AtividadeEconomicaEnumTest {

	/**
	 * O m�todo Test get codigo.
	 */
	@Test
	public void testGetCodigo() {
		assertEquals(AtividadeEconomicaEnum.PESSOA_FISICA.getCodigo(), Short.valueOf((short) 55));
		assertEquals(AtividadeEconomicaEnum.PESSOA_JURIDICA.getCodigo(), Short.valueOf((short) 54));
	}

	/**
	 * O m�todo Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(AtividadeEconomicaEnum.PESSOA_FISICA.getDescricao(), "Pessoa F�sica");
		assertEquals(AtividadeEconomicaEnum.PESSOA_JURIDICA.getDescricao(), "Setor Privado - Outros Servi�os");
	}

	/**
	 * O m�todo Test to string.
	 */
	@Test
	public void testToString() {
		for (AtividadeEconomicaEnum atividade : AtividadeEconomicaEnum.values()) {
			assertEquals(atividade.toString(), AtividadeEconomicaEnum.class.getSimpleName() + ": "
					+ atividade.getCodigo() + " - " + atividade.getDescricao());
		}
	}
	
	/**
	 * O m�todo Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(2), Integer.valueOf(AtividadeEconomicaEnum.values().length));
		assertNotNull(AtividadeEconomicaEnum.valueOf("PESSOA_FISICA"));
		assertNotNull(AtividadeEconomicaEnum.valueOf("PESSOA_JURIDICA"));
	}
	
}
