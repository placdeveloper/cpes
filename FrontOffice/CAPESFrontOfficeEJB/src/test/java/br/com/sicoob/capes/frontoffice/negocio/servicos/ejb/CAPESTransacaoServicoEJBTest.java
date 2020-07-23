package br.com.sicoob.capes.frontoffice.negocio.servicos.ejb;

import org.junit.Assert;
import org.junit.Test;

import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.bancoob.srtb.excecao.ExcecaoTransacao;
import br.com.sicoob.capes.frontoffice.negocio.servicos.CAPESTransacaoServico;

/**
 * A Classe CAPESTransacaoServicoEJBTest.
 */
public abstract class CAPESTransacaoServicoEJBTest {

	/**
	 * O método Test executar transacao.
	 */
	@Test
	public void testExecutarTransacao() {
		CAPESTransacaoServico ejb = criarServico();
		try {
			RetornoMensagem retornoMensagem = ejb.executarTransacao(criarMensagem());
			Assert.assertNotNull(retornoMensagem);
			Assert.assertNotNull(retornoMensagem.getCodRetorno());
			Assert.assertNotNull(retornoMensagem.getConteudoRetorno());
			Assert.assertNotNull(retornoMensagem.isSucesso());
			realizarAssert(retornoMensagem);
		} catch (ExcecaoTransacao e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Criar servico.
	 *
	 * @return CAPESTransacaoServico
	 */
	protected abstract CAPESTransacaoServico criarServico();
	
	/**
	 * Criar mensagem.
	 *
	 * @return Mensagem
	 */
	protected abstract Mensagem criarMensagem();
	
	/**
	 * O método Realizar assert.
	 *
	 * @param retornoMensagem o valor de retorno mensagem
	 */
	protected abstract void realizarAssert(RetornoMensagem retornoMensagem);
}
