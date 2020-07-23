// 29/10/2013 - 10:43:49
package br.com.sicoob.capes.cadastro.negocio.enums;

import static br.com.sicoob.capes.cadastro.negocio.enums.SituacaoProdutividadeEnum.EM_ABERTO;
import static br.com.sicoob.capes.cadastro.negocio.enums.SituacaoProdutividadeEnum.FINALIZADO_FRUSTRACAO;
import static br.com.sicoob.capes.cadastro.negocio.enums.SituacaoProdutividadeEnum.FINALIZADO_SUCESSO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class SituacaoProdutividadeEnumTest {

	/**
	 * O método Test get codigo.
	 */
	@Test
	public void testGetCodigo() {
		assertEquals(EM_ABERTO.getCodigo(), Short.valueOf((short) 1));
		assertEquals(FINALIZADO_FRUSTRACAO.getCodigo(), Short.valueOf((short) 3));
		assertEquals(FINALIZADO_SUCESSO.getCodigo(), Short.valueOf((short) 2));
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(EM_ABERTO.getDescricao(), "Em Aberto");
		assertEquals(FINALIZADO_FRUSTRACAO.getDescricao(), "Finalizado com Frustação");
		assertEquals(FINALIZADO_SUCESSO.getDescricao(), "Finalizado com Sucesso");
	}
	
	/**
	 * O método Test recuperar descricao.
	 */
	@Test
	public void testRecuperarDescricao() {
		assertEquals(SituacaoProdutividadeEnum.recuperarDescricao(EM_ABERTO.getCodigo()),
				"Em Aberto");
		assertEquals(SituacaoProdutividadeEnum.recuperarDescricao(FINALIZADO_FRUSTRACAO.getCodigo()),
				"Finalizado com Frustação");
		assertEquals(SituacaoProdutividadeEnum.recuperarDescricao(FINALIZADO_SUCESSO.getCodigo()),
				"Finalizado com Sucesso");
	}
	
	/**
	 * O método Test recuperar descricao inexistente.
	 */
	@Test
	public void testRecuperarDescricaoInexistente() {
		assertEquals(SituacaoProdutividadeEnum.recuperarDescricao(NumberUtils.SHORT_MINUS_ONE), "");
	}

	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(3), Integer.valueOf(SituacaoProdutividadeEnum.values().length));
		assertNotNull(SituacaoProdutividadeEnum.valueOf("EM_ABERTO"));
		assertNotNull(SituacaoProdutividadeEnum.valueOf("FINALIZADO_FRUSTRACAO"));
		assertNotNull(SituacaoProdutividadeEnum.valueOf("FINALIZADO_SUCESSO"));
	}	
}
