/*
 * SICOOB
 * 
 * FuncionalidadeValidacaoCadastralEnumTest.java(br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnumTest)
 */
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum.BEM;
import static br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum.CERTIDAO;
import static br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum.EMAIL;
import static br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum.ENDERECO;
import static br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum.FONTE_RENDA;
import static br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum.INFO_PROFISSIONAL;
import static br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum.PESSOA;
import static br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum.PESSOA_INSTITUICAO;
import static br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum.PRODUTOR;
import static br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum.REFERENCIA;
import static br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum.RELACIONAMENTO;
import static br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum.TELEFONE;
import static br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum.TRIBUTACAO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Rodrigo.Chaves 29/08/2014
 */
public class FuncionalidadeValidacaoCadastralEnumTest extends AbstractEnumTest {

	/**
	 * Test method for
	 * {@link br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum#getDescricao()}.
	 */
	@Test
	public final void testGetDescricao() {

		Assert.assertEquals("Bem", BEM.getDescricao());
		Assert.assertEquals("Certidão", CERTIDAO.getDescricao());
		Assert.assertEquals("E-Mail", EMAIL.getDescricao());
		Assert.assertEquals("Endereço", ENDERECO.getDescricao());
		Assert.assertEquals("Fonte de Renda", FONTE_RENDA.getDescricao());
		Assert.assertEquals("Informação Profissional", INFO_PROFISSIONAL.getDescricao());
		Assert.assertEquals("Pessoa", PESSOA.getDescricao());
		Assert.assertEquals("Dados do Cliente", PESSOA_INSTITUICAO.getDescricao());
		Assert.assertEquals("Produtor", PRODUTOR.getDescricao());
		Assert.assertEquals("Referência", REFERENCIA.getDescricao());
		Assert.assertEquals("Relacionamento", RELACIONAMENTO.getDescricao());
		Assert.assertEquals("Telefone", TELEFONE.getDescricao());
		Assert.assertEquals("Tributação", TRIBUTACAO.getDescricao());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void testMetodosHerdados() {

		assertEquals(Integer.valueOf(13), Integer.valueOf(FuncionalidadeValidacaoCadastralEnum.values().length));
		assertNotNull(FuncionalidadeValidacaoCadastralEnum.valueOf("BEM"));
		assertNotNull(FuncionalidadeValidacaoCadastralEnum.valueOf("CERTIDAO"));
		assertNotNull(FuncionalidadeValidacaoCadastralEnum.valueOf("EMAIL"));
		assertNotNull(FuncionalidadeValidacaoCadastralEnum.valueOf("ENDERECO"));
		assertNotNull(FuncionalidadeValidacaoCadastralEnum.valueOf("FONTE_RENDA"));
		assertNotNull(FuncionalidadeValidacaoCadastralEnum.valueOf("INFO_PROFISSIONAL"));
		assertNotNull(FuncionalidadeValidacaoCadastralEnum.valueOf("PESSOA"));
		assertNotNull(FuncionalidadeValidacaoCadastralEnum.valueOf("PESSOA_INSTITUICAO"));
		assertNotNull(FuncionalidadeValidacaoCadastralEnum.valueOf("PRODUTOR"));
		assertNotNull(FuncionalidadeValidacaoCadastralEnum.valueOf("REFERENCIA"));
		assertNotNull(FuncionalidadeValidacaoCadastralEnum.valueOf("RELACIONAMENTO"));
		assertNotNull(FuncionalidadeValidacaoCadastralEnum.valueOf("TELEFONE"));
		assertNotNull(FuncionalidadeValidacaoCadastralEnum.valueOf("TRIBUTACAO"));
	}

	/**
	 * Testa se a ordem não foi alterada.
	 */
	@Test
	public void testOrdem() {

		final FuncionalidadeValidacaoCadastralEnum[] ordem = { BEM, CERTIDAO, EMAIL, ENDERECO, FONTE_RENDA,
				INFO_PROFISSIONAL, PESSOA, PESSOA_INSTITUICAO, PRODUTOR, REFERENCIA, RELACIONAMENTO, TELEFONE,
				TRIBUTACAO };
		
		Assert.assertTrue(Arrays.equals(ordem, FuncionalidadeValidacaoCadastralEnum.values()));
	}
}