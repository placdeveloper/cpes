package br.com.sicoob.capes.frontoffice.negocio.testeremoto;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

/**
 * Teste do serviço de criar email.
 * 
 * @author Bruno.Carneiro
 */
public class TesteCriarFonteRenda extends CAPESFrontofficeTesteAbstrato {

	/**
	 * construtor
	 */
	public TesteCriarFonteRenda() {
		super("CriarFonteRendaServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_PESSOA, 19));
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_COOP_REMETENTE, 3353));		
		listaParametros.add(obterParametro(ParametroEnum.COD_TIPO_RENDA, 0));
		listaParametros.add(obterParametro(ParametroEnum.VALOR_TRANSACAO, 1024));
		listaParametros.add(obterParametro(ParametroEnum.DOCUMENTO_COMPROBATORIO, converterImagemParaBase64("C:\\temp\\imagens\\1.jpg")));
		listaParametros.add(obterParametro(ParametroEnum.NOME_ARQUIVO_DOCUMENTO_COMPROBATORIO, "1"));
		listaParametros.add(obterParametro(ParametroEnum.EXTENSAO_ARQUIVO_DOCUMENTO_COMPROBATORIO, "jpg"));
	}
	
	private String converterImagemParaBase64(String nomeArquivo) {
		try {
			return String.valueOf(Base64.encodeBase64(FileUtils.readFileToByteArray(new File(nomeArquivo))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
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