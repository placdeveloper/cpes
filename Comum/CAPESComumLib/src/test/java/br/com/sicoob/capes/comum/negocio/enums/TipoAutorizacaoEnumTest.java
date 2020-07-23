// 30/10/2013 - 16:26:57
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum.BEM;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum.CERTIDAO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum.ENDERECO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum.FONTE_RENDA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum.PESSOA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum.PRODUTIVIDADE;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum.PRODUTOR;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum.RELACIONAMENTO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum.RESPONSAVEL;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum.TRIBUTACAO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * @author Rodrigo.Chaves
 */
public class TipoAutorizacaoEnumTest {

	/**
	 * Test method for {@link br.com.sicoob.capes.negocio.enums.TipoAutorizacaoEnum#getDescricao()}.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals("BENS", BEM.getDescricao());
		assertEquals("CERTIDÃO", CERTIDAO.getDescricao());
		assertEquals("ENDEREÇO", ENDERECO.getDescricao());
		assertEquals("FONTE DE RENDA", FONTE_RENDA.getDescricao());
		assertEquals("PERFIL TRIBUTÁRIO", TRIBUTACAO.getDescricao());
		assertEquals("PESSOA", PESSOA.getDescricao());
		assertEquals("PRODUTIVIDADE", PRODUTIVIDADE.getDescricao());
		assertEquals("PRODUTOR", PRODUTOR.getDescricao());
		assertEquals("RELACIONAMENTO", RELACIONAMENTO.getDescricao());
		assertEquals("RESPONSÁVEL PELO CADASTRO", RESPONSAVEL.getDescricao());
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.negocio.enums.TipoAutorizacaoEnum#isResponsavel()}.
	 */
	@Test
	public void testIsResponsavel() {
		assertFalse(BEM.isResponsavel());
		assertFalse(CERTIDAO.isResponsavel());
		assertFalse(ENDERECO.isResponsavel());
		assertFalse(FONTE_RENDA.isResponsavel());
		assertFalse(TRIBUTACAO.isResponsavel());
		assertFalse(PESSOA.isResponsavel());
		assertFalse(PRODUTIVIDADE.isResponsavel());
		assertFalse(PRODUTOR.isResponsavel());
		assertFalse(RELACIONAMENTO.isResponsavel());
		assertFalse(RESPONSAVEL.isResponsavel());
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.negocio.enums.TipoAutorizacaoEnum#toString()}.
	 */
	@Test
	public void testToString() {
		for (TipoAutorizacaoEnum value : TipoAutorizacaoEnum.values()) {
			assertEquals(value.getClass().getSimpleName() + "=" + value.name(), value.toString());
		}
	}

	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(11), Integer.valueOf(TipoAutorizacaoEnum.values().length));
		assertNotNull(TipoAutorizacaoEnum.valueOf("BEM_NOVO"));
		assertNotNull(TipoAutorizacaoEnum.valueOf("BEM"));
		assertNotNull(TipoAutorizacaoEnum.valueOf("CERTIDAO"));
		assertNotNull(TipoAutorizacaoEnum.valueOf("ENDERECO"));
		assertNotNull(TipoAutorizacaoEnum.valueOf("FONTE_RENDA"));
		assertNotNull(TipoAutorizacaoEnum.valueOf("TRIBUTACAO"));
		assertNotNull(TipoAutorizacaoEnum.valueOf("PESSOA"));
		assertNotNull(TipoAutorizacaoEnum.valueOf("PRODUTIVIDADE"));
		assertNotNull(TipoAutorizacaoEnum.valueOf("PRODUTOR"));
		assertNotNull(TipoAutorizacaoEnum.valueOf("RELACIONAMENTO"));
		assertNotNull(TipoAutorizacaoEnum.valueOf("RESPONSAVEL"));
	}

}