package br.com.sicoob.capes.replicacao.negocio.mdb;

import static br.com.sicoob.capes.replicacao.negocio.excecao.MensagemReplicacaoInacessivelException.ERRO_OBTER_MENSAGENS_REPLICACAO;

import javax.jms.JMSException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.excecao.MensagemInvalidaException;
import br.com.sicoob.capes.replicacao.negocio.excecao.LeituraMensagemException;
import br.com.sicoob.capes.replicacao.negocio.excecao.MensagemReplicacaoInacessivelException;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.AtualizarCadastroClienteServicoLocal;

public class AtualizarCadastroClientesMDBTest extends Mockito {

	@SuppressWarnings("rawtypes")
	@Mock
	private AtualizarCadastroClienteServicoLocal servicoAtualizacao;

	@SuppressWarnings("rawtypes")
	@InjectMocks
	private AtualizarCadastroClientesMDB mdb;

	@Before
	public void setUp() throws BancoobException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void mensagemSemTexto() throws JMSException {
		MensagemTeste mensagem = mock(MensagemTeste.class);
		when(mensagem.getText()).thenReturn("");
		mdb.onMessage(mensagem);
		verify(mensagem).getText();
		verifyNoMoreInteractions(mensagem);
		verifyZeroInteractions(servicoAtualizacao);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void mensagemGetTextException() throws JMSException {
		MensagemTeste mensagem = mock(MensagemTeste.class);
		when(mensagem.getText()).thenThrow(JMSException.class);
		try {
			mdb.onMessage(mensagem);
			Assert.fail();
		} catch (LeituraMensagemException e) {

		}
		verify(mensagem).getText();
		verifyNoMoreInteractions(mensagem);
		verifyZeroInteractions(servicoAtualizacao);
	}

	@Test
	public void mensagemQuantidadeParametrosInvalida() throws JMSException {
		MensagemTeste mensagem = mock(MensagemTeste.class);
		when(mensagem.getText()).thenReturn("valorIdentificadorOperacao");
		try {
			mdb.onMessage(mensagem);
			Assert.fail();
		} catch (MensagemInvalidaException e) {
			Assert.assertEquals(e.getMessage(), "msg.erro.replicacao.quantidade.parametros");
		}
		verify(mensagem).getText();
		verifyNoMoreInteractions(mensagem);
		verifyZeroInteractions(servicoAtualizacao);
	}

	@Test
	public void mensagemIdentificadorOperacaoInvalido() throws JMSException {
		MensagemTeste mensagem = mock(MensagemTeste.class);
		when(mensagem.getText()).thenReturn("valorIdentificadorOperacao,1");
		try {
			mdb.onMessage(mensagem);
			Assert.fail();
		} catch (MensagemInvalidaException e) {
			Assert.assertEquals(e.getMessage(), "msg.erro.replicacao.identificador.operacao");
		}
		verify(mensagem).getText();
		verifyNoMoreInteractions(mensagem);
		verifyZeroInteractions(servicoAtualizacao);
	}

	@Test
	public void mensagemIdInstituicaoInvalido() throws JMSException {
		MensagemTeste mensagem = mock(MensagemTeste.class);
		when(mensagem.getText()).thenReturn("9f4d834f-b5e1-4716-a8fc-b56a9b5ca0a8,ID");
		try {
			mdb.onMessage(mensagem);
			Assert.fail();
		} catch (MensagemInvalidaException e) {
			Assert.assertTrue(e.getMessage().startsWith("msg.erro.replicacao.id.instituicao"));
		}
		verify(mensagem).getText();
		verifyNoMoreInteractions(mensagem);
		verifyZeroInteractions(servicoAtualizacao);
	}

	@Test
	public void mensagemErroObterMensagensReplicacao() throws JMSException {
		MensagemTeste mensagem = mock(MensagemTeste.class);
		when(mensagem.getText()).thenReturn("9f4d834f-b5e1-4716-a8fc-b56a9b5ca0a8,1");
		when(servicoAtualizacao.obterMensagensReplicacao("9f4d834f-b5e1-4716-a8fc-b56a9b5ca0a8", 1))
				.thenThrow(new MensagemReplicacaoInacessivelException(new BancoobException(""), ERRO_OBTER_MENSAGENS_REPLICACAO,
						"9f4d834f-b5e1-4716-a8fc-b56a9b5ca0a8"));
		try {
			mdb.onMessage(mensagem);
			Assert.fail();
		} catch (MensagemReplicacaoInacessivelException e) {
			Assert.assertTrue(e.getMessage().startsWith("erro.obter.mensagens.replicacao"));
		}
		verify(mensagem).getText();
		verify(servicoAtualizacao).obterMensagensReplicacao("9f4d834f-b5e1-4716-a8fc-b56a9b5ca0a8", 1);
		verifyNoMoreInteractions(mensagem, servicoAtualizacao);
	}

}
