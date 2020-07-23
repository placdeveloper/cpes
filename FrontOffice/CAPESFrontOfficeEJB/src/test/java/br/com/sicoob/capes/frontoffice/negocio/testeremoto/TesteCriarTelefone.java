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
public class TesteCriarTelefone extends CAPESFrontofficeTesteAbstrato {

	/**
	 * Construtor
	 */
	public TesteCriarTelefone() {
		super("CriarTelefonePessoaServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_PESSOA, 19));
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_COOP_REMETENTE, 3353));
		listaParametros.add(obterParametro(ParametroEnum.CODIGO_TIPO_TELEFONE, 2));
		listaParametros.add(obterParametro(ParametroEnum.DDD, 61));
		listaParametros.add(obterParametro(ParametroEnum.TELEFONE, 51651651));		
		listaParametros.add(obterParametro(ParametroEnum.RAMAL, 5548));
		listaParametros.add(obterParametro(ParametroEnum.DESC_OBSERVACAO, "NÃO LIGAR"));		
		listaParametros.add(obterParametro(ParametroEnum.ID_USUARIO, "BRUNO.CARNEIRO"));

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
