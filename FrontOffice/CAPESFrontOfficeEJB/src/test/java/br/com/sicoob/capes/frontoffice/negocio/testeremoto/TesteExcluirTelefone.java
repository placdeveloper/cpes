package br.com.sicoob.capes.frontoffice.negocio.testeremoto;

import java.util.List;

import org.junit.Assert;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

/**
 * Classe para o teste de criação de telefone.
 *
 * @author Bruno.Carneiro
 */
public class TesteExcluirTelefone extends CAPESFrontofficeTesteAbstrato {

	/**
	 * Construtor
	 */
	public TesteExcluirTelefone() {
		super("ExcluirTelefonePessoaServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_PESSOA, 19));
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_COOP_REMETENTE, 3353));
		listaParametros.add(obterParametro(ParametroEnum.ID_TELEFONE, 243520));		

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void realizarAfirmacao(String codigoRetorno) {
		Assert.assertNotNull(codigoRetorno);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterRetornoEsperado() {
		return null;
	}
}
