// 29/07/2013 - 15:57:39
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_AVAL_FINANCEIRA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_CLIENTE;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_EMAIL_PESSOA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum.OPERACAO_ALTERACAO_ENDERECO_CORRESPONDENCIA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum.OPERACAO_EXCLUSAO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum.OPERACAO_INCLUSAO;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum.OPERACAO_INCLUSAO_CLIENTE;
import static br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum.OPERACAO_INCLUSAO_EMAIL_PESSOA;
import static br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum.A;
import static br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum.E;
import static br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum.I;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * 
 * 
 * @author rodrigo.chaves
 */
public class TipoAtualizacaoCadastralEnumTest extends 
AbstractEnumTest {

	/**
	 * O método Test value of.
	 */
	@Test
	public void testValueOf() {
		assertEquals(OPERACAO_ALTERACAO,
				TipoAtualizacaoCadastralEnum.valueOf(A.getCodigo()));
		assertEquals(OPERACAO_EXCLUSAO,
				TipoAtualizacaoCadastralEnum.valueOf(E.getCodigo()));
		assertEquals(OPERACAO_INCLUSAO,
				TipoAtualizacaoCadastralEnum.valueOf(I.getCodigo()));
		assertEquals(OPERACAO_ALTERACAO_CLIENTE,
				TipoAtualizacaoCadastralEnum.valueOf('C'));
		assertEquals(OPERACAO_INCLUSAO_CLIENTE,
				TipoAtualizacaoCadastralEnum.valueOf('D'));
		assertEquals(OPERACAO_ALTERACAO_ENDERECO_CORRESPONDENCIA,
				TipoAtualizacaoCadastralEnum.valueOf('T'));
		assertEquals(OPERACAO_ALTERACAO_EMAIL_PESSOA,
				TipoAtualizacaoCadastralEnum.valueOf('H'));
		assertEquals(OPERACAO_INCLUSAO_EMAIL_PESSOA,
				TipoAtualizacaoCadastralEnum.valueOf('J'));
		assertEquals(OPERACAO_ALTERACAO_AVAL_FINANCEIRA,
				TipoAtualizacaoCadastralEnum.valueOf('V'));
	}

	/**
	 * O método Test value of inexistente.
	 */
	@Test
	public void testValueOfInexistente() {
		IllegalArgumentException exception = null;
		TipoAtualizacaoCadastralEnum value = null;
		try {
			value = TipoAtualizacaoCadastralEnum.valueOf('#');
		} catch (IllegalArgumentException e) {
			exception = e;
		}
		assertNull(value);
		assertNotNull(exception);
	}

	/**
	 * O método Test to string.
	 */
	@Test
	public void testToString() {
		for (TipoAtualizacaoCadastralEnum tipoAtualizacao : TipoAtualizacaoCadastralEnum.values()) {
			assertEquals(tipoAtualizacao.toString(), tipoAtualizacao.getTipoOperacao() + " - "
					+ tipoAtualizacao.getDescricao());
		}
	}

	/**
	 * O método Test get descricao.
	 */
	@Test
	public void testGetDescricao() {
		assertEquals(OPERACAO_ALTERACAO.getDescricao(), A.getDescricao());
		assertEquals(OPERACAO_EXCLUSAO.getDescricao(), E.getDescricao());
		assertEquals(OPERACAO_INCLUSAO.getDescricao(), I.getDescricao());
		assertEquals(OPERACAO_ALTERACAO_CLIENTE.getDescricao(), "Alteração Cliente");
		assertEquals(OPERACAO_INCLUSAO_CLIENTE.getDescricao(), "Inclusão Cliente");
		assertEquals(OPERACAO_ALTERACAO_ENDERECO_CORRESPONDENCIA.getDescricao(), "Alteração de endereço de correspondência");
		assertEquals(OPERACAO_ALTERACAO_EMAIL_PESSOA.getDescricao(), "Alteração do e-mail de uma pessoa");
		assertEquals(OPERACAO_INCLUSAO_EMAIL_PESSOA.getDescricao(), "Inclusão do e-mail de uma pessoa");
		assertEquals(OPERACAO_ALTERACAO_AVAL_FINANCEIRA.getDescricao(), "Alteração Aval Financeira da pessoa compartilhamento");
	}

	/**
	 * @return the tipoInclusao
	 */
	@Test
	public void testGetTipoInclusao() {
		assertEquals(OPERACAO_ALTERACAO.getTipoInclusao(), I.getCodigo());
		assertNull(OPERACAO_EXCLUSAO.getTipoInclusao());
		assertNull(OPERACAO_INCLUSAO.getTipoInclusao());
		assertEquals(OPERACAO_ALTERACAO_CLIENTE.getTipoInclusao(), Character.valueOf('D'));
		assertNull(OPERACAO_INCLUSAO_CLIENTE.getTipoInclusao());
		assertNull(OPERACAO_ALTERACAO_ENDERECO_CORRESPONDENCIA.getTipoInclusao());
		assertEquals(OPERACAO_ALTERACAO_EMAIL_PESSOA.getTipoInclusao(), Character.valueOf('J'));
		assertNull(OPERACAO_INCLUSAO_EMAIL_PESSOA.getTipoInclusao());
		assertNull(OPERACAO_ALTERACAO_AVAL_FINANCEIRA.getTipoInclusao());
	}

	/**
	 * @return the operacaoBase
	 */
	@Test
	public void testGetOperacaoBase() {

		assertEquals(OPERACAO_ALTERACAO.getOperacaoBase(), A);
		assertEquals(OPERACAO_EXCLUSAO.getOperacaoBase(), E);
		assertEquals(OPERACAO_INCLUSAO.getOperacaoBase(), I);
		assertEquals(OPERACAO_ALTERACAO_CLIENTE.getOperacaoBase(), A);
		assertEquals(OPERACAO_INCLUSAO_CLIENTE.getOperacaoBase(), I);
		assertEquals(OPERACAO_ALTERACAO_ENDERECO_CORRESPONDENCIA.getOperacaoBase(), A);
		assertEquals(OPERACAO_ALTERACAO_EMAIL_PESSOA.getOperacaoBase(), A);
		assertEquals(OPERACAO_INCLUSAO_EMAIL_PESSOA.getOperacaoBase(), I);
		assertEquals(OPERACAO_ALTERACAO_AVAL_FINANCEIRA.getOperacaoBase(), A);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(9), Integer.valueOf(TipoAtualizacaoCadastralEnum.values().length));
		assertNotNull(TipoAtualizacaoCadastralEnum.valueOf("OPERACAO_ALTERACAO"));
		assertNotNull(TipoAtualizacaoCadastralEnum.valueOf("OPERACAO_EXCLUSAO"));
		assertNotNull(TipoAtualizacaoCadastralEnum.valueOf("OPERACAO_INCLUSAO"));
		assertNotNull(TipoAtualizacaoCadastralEnum.valueOf("OPERACAO_ALTERACAO_CLIENTE"));
		assertNotNull(TipoAtualizacaoCadastralEnum.valueOf("OPERACAO_INCLUSAO_CLIENTE"));
		assertNotNull(TipoAtualizacaoCadastralEnum.valueOf("OPERACAO_ALTERACAO_ENDERECO_CORRESPONDENCIA"));
		assertNotNull(TipoAtualizacaoCadastralEnum.valueOf("OPERACAO_ALTERACAO_EMAIL_PESSOA"));
		assertNotNull(TipoAtualizacaoCadastralEnum.valueOf("OPERACAO_INCLUSAO_EMAIL_PESSOA"));
		assertNotNull(TipoAtualizacaoCadastralEnum.valueOf("OPERACAO_ALTERACAO_AVAL_FINANCEIRA"));
	}	
}
