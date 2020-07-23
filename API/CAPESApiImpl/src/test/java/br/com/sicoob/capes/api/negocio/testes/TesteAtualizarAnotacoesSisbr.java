package br.com.sicoob.capes.api.negocio.testes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.AtualizarAnotacoesSisbrServicoRemote;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;

/**
 * A Classe TesteAtualizarAnotacoesSisbr.
 */
public class TesteAtualizarAnotacoesSisbr extends CAPESTesteAbstract {

	/** O atributo atualizarAnotacoesSisbrServicoRemote. */
	AtualizarAnotacoesSisbrServicoRemote atualizarAnotacoesSisbrServicoRemote;

	/**
	 * O método Sets the up.
	 *
	 * @throws Exception lança a exceção Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setPrincipalContexto();
		ServiceLocator locator = ServiceLocator.getInstance();
		atualizarAnotacoesSisbrServicoRemote = (AtualizarAnotacoesSisbrServicoRemote) locator.getObjetoRemoto("capes/api/AtualizarAnotacoesSisbrServico");
	}

	/**
	 * O método Teste incluir anotacao.
	 */
	@Test
	public void testeIncluirAnotacao() {
		Long anotacao = null;
		try {
			AnotacaoSisbrDTO dto = new AnotacaoSisbrDTO();
			dto.setNumPessoa(19);
			dto.setIdProduto(213);
			dto.setIdModalidadeProduto(123);
			dto.setNumeroContrato("123");
			dto.setCodigoTipoAnotacao(320);
			dto.setDataOcorrencia(new Date());
			dto.setDataVencimento(new Date());
			dto.setValor(new BigDecimal(456));
			dto.setIdInstituicao(910);
			dto.setIdUnidadeInst(0);
			dto.setObservacao("teste");
			anotacao = atualizarAnotacoesSisbrServicoRemote.incluirAnotacao(dto);
			Assert.assertNotNull(anotacao);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * O método Teste baixar anotacoes.
	 */
	@Ignore
	public void testeBaixarAnotacoes() {
		List<Long> anotacoes = null;
		try {
			AnotacaoSisbrDTO dto = new AnotacaoSisbrDTO();
			dto.setNumPessoa(9660);
			dto.setIdProduto(213);
			dto.setIdModalidadeProduto(123);
			dto.setNumeroContrato("123");
			dto.setCodigoTipoAnotacao(301);
			dto.setDataOcorrencia(new Date());
			dto.setDataVencimento(new Date());
			dto.setValor(new BigDecimal(456));
			dto.setIdInstituicao(910);
			dto.setIdUnidadeInst(0);
			dto.setObservacao("teste");
			anotacoes = atualizarAnotacoesSisbrServicoRemote.baixarAnotacao(dto);
			Assert.assertNotNull(anotacoes);
		} catch (BancoobException e) {
			Assert.fail(e.getMessage());
		}
	}

}