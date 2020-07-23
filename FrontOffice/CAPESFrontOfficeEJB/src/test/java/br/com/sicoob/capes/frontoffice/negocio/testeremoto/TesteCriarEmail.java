package br.com.sicoob.capes.frontoffice.negocio.testeremoto;

import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.CODIGO_TIPO_EMAIL;
import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.DESC_EMAIL;
import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.ID_USUARIO;
import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.NUMERO_COOP_REMETENTE;
import static br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum.NUMERO_PESSOA;

import java.util.List;

import org.junit.Assert;

import br.com.bancoob.srtb.dto.Parametro;

/**
 * Teste do serviço de criar email.
 * 
 * @author Bruno.Carneiro
 */
public class TesteCriarEmail extends CAPESFrontofficeTesteAbstrato {

	/**
	 * construtor
	 */
	public TesteCriarEmail() {
		super("CriarEmailPessoaServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(NUMERO_PESSOA, 19));
		listaParametros.add(obterParametro(NUMERO_COOP_REMETENTE, 3353));
		listaParametros.add(obterParametro(CODIGO_TIPO_EMAIL, 2));
		listaParametros.add(obterParametro(DESC_EMAIL, "teste@canais.com"));
		listaParametros.add(obterParametro(ID_USUARIO, "BRUNO.CARNEIRO"));
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