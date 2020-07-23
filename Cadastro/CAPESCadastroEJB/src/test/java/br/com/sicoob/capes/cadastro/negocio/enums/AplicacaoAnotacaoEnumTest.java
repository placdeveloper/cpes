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
	 * O m�todo Test get id aplicacao.
	 */
	@Test
	public void testGetIdAplicacao() {
		assertEquals(AplicacaoAnotacaoEnum.PESSOA_FISICA.getIdAplicacao(), Short.valueOf((short) 1));
		assertEquals(AplicacaoAnotacaoEnum.PESSOA_JURIDICA.getIdAplicacao(), Short.valueOf((short) 2));
		assertEquals(AplicacaoAnotacaoEnum.AMBAS.getIdAplicacao(), Short.valueOf((short) 3));
	}

	/**
	 * O m�todo Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(AplicacaoAnotacaoEnum.PESSOA_FISICA.getDescricao(), "Pessoa F�sica");
		assertEquals(AplicacaoAnotacaoEnum.PESSOA_JURIDICA.getDescricao(), "Pessoa Jur�dica");
		assertEquals(AplicacaoAnotacaoEnum.AMBAS.getDescricao(), "Ambas");
	}
	
	/**
	 * O m�todo Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(3), Integer.valueOf(AplicacaoAnotacaoEnum.values().length));
		assertNotNull(AplicacaoAnotacaoEnum.valueOf("PESSOA_FISICA"));
		assertNotNull(AplicacaoAnotacaoEnum.valueOf("PESSOA_JURIDICA"));
		assertNotNull(AplicacaoAnotacaoEnum.valueOf("AMBAS"));
	}

}
