package br.com.sicoob.capes.frontoffice.negocio.testeremoto;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

public class TesteAlterarEnderecoPessoa extends CAPESFrontofficeTesteAbstrato {

	public TesteAlterarEnderecoPessoa() {
		super("AlterarEnderecoPessoaServico");
	}

	@Override
	protected String obterRetornoEsperado() {
		return "1\\t10830104\\t2\\t33\\t5665\\t64600124\\tOSVALDO CRUZ\\tS/N\\tQEWQEWEWQ\\tFÁTIMA\\t\\n";
	}

	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_PESSOA, 12688));
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_COOP_REMETENTE, 3353));
		listaParametros.add(obterParametro(ParametroEnum.ID_ENDERECO, 3501389));
		listaParametros.add(obterParametro(ParametroEnum.COD_TIPO_ENDERECO, 2));
		listaParametros.add(obterParametro(ParametroEnum.COD_TIPO_LOGRADOURO, 2));
		listaParametros.add(obterParametro(ParametroEnum.DESC_CEP, "70680550"));
		listaParametros.add(obterParametro(ParametroEnum.DESC_ENDERECO, "teste aaa"));
		listaParametros.add(obterParametro(ParametroEnum.DESC_SEU_NUMERO, "668456"));
		listaParametros.add(obterParametro(ParametroEnum.DESC_NOME_BAIRRO, "Bairro"));
		listaParametros.add(obterParametro(ParametroEnum.ID_LOCALIDADE, 1));
		listaParametros.add(obterParametro(ParametroEnum.ID_USUARIO, "SicoobNET"));
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

}
