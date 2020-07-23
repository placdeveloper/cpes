package br.com.sicoob.capes.frontoffice.negocio.testeremoto;

import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.NUMERO_COOP_REMETENTE;
import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.NUMERO_PESSOA;

import java.util.List;

import org.junit.Assert;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

/**
 * Teste do serviço de criar email.
 * 
 * @author Bruno.Carneiro
 */
public class TesteExcluirEmail extends CAPESFrontofficeTesteAbstrato {

	/**
	 * construtor
	 */
	public TesteExcluirEmail() {
		super("ExcluirEmailPessoaServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(NUMERO_PESSOA, 19));
		listaParametros.add(obterParametro(NUMERO_COOP_REMETENTE, 3353));
		listaParametros.add(obterParametro(ParametroEnum.ID_EMAIL, 894444));
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