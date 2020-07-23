// 28/10/2013 - 10:00:52
package br.com.sicoob.capes.cadastro.negocio.enums;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class AplicacaoAnotacaoEnumTest {

	/**
	 * O método Test get id aplicacao.
	 */
	@Test
	public void testGetIdAplicacao() {
		assertEquals(AplicacaoAnotacaoEnum.PESSOA_FISICA.getIdAplicacao(), Short.valueOf((short) 1));
		assertEquals(AplicacaoAnotacaoEnum.PESSOA_JURIDICA.getIdAplicacao(), Short.valueOf((short) 2));
		assertEquals(AplicacaoAnotacaoEnum.AMBAS.getIdAplicacao(), Short.valueOf((short) 3));
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(AplicacaoAnotacaoEnum.PESSOA_FISICA.getDescricao(), "Pessoa Física");
		assertEquals(AplicacaoAnotacaoEnum.PESSOA_JURIDICA.getDescricao(), "Pessoa Jurídica");
		assertEquals(AplicacaoAnotacaoEnum.AMBAS.getDescricao(), "Ambas");
	}
	
	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(3), Integer.valueOf(AplicacaoAnotacaoEnum.values().length));
		assertNotNull(AplicacaoAnotacaoEnum.valueOf("PESSOA_FISICA"));
		assertNotNull(AplicacaoAnotacaoEnum.valueOf("PESSOA_JURIDICA"));
		assertNotNull(AplicacaoAnotacaoEnum.valueOf("AMBAS"));
	}

}
