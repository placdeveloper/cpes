package br.com.sicoob.capes.frontoffice.negocio.testeremoto;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.bancoob.srtb.dto.Mensagem;
import br.com.bancoob.srtb.dto.Parametro;
import br.com.bancoob.srtb.dto.RetornoMensagem;
import br.com.bancoob.srtb.dto.TipoParametro;
import br.com.bancoob.srtb.servico.Transacao;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

/**
 * Classe para os testes de serviço para uso local.
 * 
 * @author Bruno.Carneiro
 */
public abstract class CAPESFrontofficeTesteAbstrato {

	private String nomeServico;
	private Transacao servico;

	/**
	 * Construtor.
	 * 
	 * @param nomeServico
	 *            o nome do serviço a ser chamado. <br/>
	 *            Ex: ConsultarTipoEmpresaServico
	 */
	public CAPESFrontofficeTesteAbstrato(String nomeServico) {
		this.nomeServico = nomeServico;
	}

	/**
	 * Método executado antes do principal
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		ServiceLocator locator = ServiceLocator.getInstance();
		servico = (Transacao) locator.getObjetoRemoto("capes/frontoffice/" + nomeServico);
	}

	/**
	 * Método para teste das transações
	 */
	@Test
	public void executarTransacao() {
		try {
			ArrayList<Parametro> parametros = new ArrayList<Parametro>();
			preencherParametros(parametros);
			Mensagem mensagem = new Mensagem();
			mensagem.setParametros(parametros);
			mensagem.setIdInstituicao(910);
			mensagem.setCodigoCanal((short) 4);
			RetornoMensagem retorno = servico.executarTransacao(mensagem);
			
			assertNotNull(retorno);
			if(retorno.isSucesso()) {
				assertNotNull(retorno.getConteudoRetorno());
				realizarAfirmacao(retorno.getConteudoRetorno());
			} else {
				fail(retorno.getMensagem());
			}
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Cria um parâmetro de entrada
	 * 
	 * @param nome
	 * @param valor
	 * @return
	 */
	protected Parametro obterParametro(ParametroEnum parametro, Object valor) {
		return new Parametro(parametro.getRotulo(), TipoParametro.ENTRADA, valor, parametro.getTipoDadoPersistencia());
	}

	/**
	 * Preenche a lista com os parâmetros
	 */
	protected abstract void preencherParametros(List<Parametro> listaParametros);

	/**
	 * Realiza o teste de validação do retorno da mensagem
	 * 
	 * @param retorno
	 */
	protected void realizarAfirmacao(String codigoRetorno) {
		isRetornoIgual(obterRetornoEsperado(), codigoRetorno);
	}

	/**
	 * Obtém o valor esperado do retorno da transação
	 */
	protected abstract String obterRetornoEsperado();

	/**
	 * Valida a informação de retorno
	 * 
	 * @param valor
	 * @param retorno
	 */
	protected void isRetornoIgual(String valor, String retorno) {
		Assert.assertEquals(valor, retorno);
	}

}