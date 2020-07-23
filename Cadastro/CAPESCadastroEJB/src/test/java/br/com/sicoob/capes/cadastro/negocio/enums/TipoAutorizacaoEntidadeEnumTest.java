// 30/10/2013 - 16:26:57
package br.com.sicoob.capes.cadastro.negocio.enums;

import static br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum.BEM;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum.CERTIDAO;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum.ENDERECO;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum.FONTE_RENDA;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum.PESSOA;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum.PRODUTIVIDADE;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum.PRODUTOR;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum.RELACIONAMENTO;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum.RESPONSAVEL;
import static br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum.TRIBUTACAO;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.vigente.Certidao;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtividade;
import br.com.sicoob.capes.negocio.entidades.vigente.Produtor;
import br.com.sicoob.capes.negocio.entidades.vigente.RelacionamentoPessoa;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * @author Rodrigo.Chaves
 */
public class TipoAutorizacaoEntidadeEnumTest {

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum#getDescricao()}.
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
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum#getTipo()}.
	 */
	@Test
	public void testGetTipo() {
		assertEquals(Bem.class, BEM.getTipo());
		assertEquals(Certidao.class, CERTIDAO.getTipo());
		assertEquals(Endereco.class, ENDERECO.getTipo());
		assertEquals(FonteRenda.class, FONTE_RENDA.getTipo());
		assertEquals(Tributacao.class, TRIBUTACAO.getTipo());
		assertEquals(PessoaCompartilhamento.class, PESSOA.getTipo());
		assertEquals(Produtividade.class, PRODUTIVIDADE.getTipo());
		assertEquals(Produtor.class, PRODUTOR.getTipo());
		assertEquals(RelacionamentoPessoa.class, RELACIONAMENTO.getTipo());
		assertEquals(ResponsavelCadastro.class, RESPONSAVEL.getTipo());
	}

	/**
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum#isResponsavel()}.
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
	 * Test method for {@link br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum#toString()}.
	 */
	@Test
	public void testToString() {
		for (TipoAutorizacaoEntidadeEnum value : TipoAutorizacaoEntidadeEnum.values()) {
			assertEquals(value.getClass().getSimpleName() + "=" + value.name(), value.toString());
		}
	}

	/**
	 * O método Test metodos herdados.
	 */
	@Test
	public void testMetodosHerdados() {
		assertEquals(Integer.valueOf(11), Integer.valueOf(TipoAutorizacaoEntidadeEnum.values().length));
		assertNotNull(TipoAutorizacaoEntidadeEnum.valueOf("BEM_NOVO"));
		assertNotNull(TipoAutorizacaoEntidadeEnum.valueOf("BEM"));
		assertNotNull(TipoAutorizacaoEntidadeEnum.valueOf("CERTIDAO"));
		assertNotNull(TipoAutorizacaoEntidadeEnum.valueOf("ENDERECO"));
		assertNotNull(TipoAutorizacaoEntidadeEnum.valueOf("FONTE_RENDA"));
		assertNotNull(TipoAutorizacaoEntidadeEnum.valueOf("TRIBUTACAO"));
		assertNotNull(TipoAutorizacaoEntidadeEnum.valueOf("PESSOA"));
		assertNotNull(TipoAutorizacaoEntidadeEnum.valueOf("PRODUTIVIDADE"));
		assertNotNull(TipoAutorizacaoEntidadeEnum.valueOf("PRODUTOR"));
		assertNotNull(TipoAutorizacaoEntidadeEnum.valueOf("RELACIONAMENTO"));
		assertNotNull(TipoAutorizacaoEntidadeEnum.valueOf("RESPONSAVEL"));
	}

}
