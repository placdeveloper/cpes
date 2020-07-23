package br.com.sicoob.capes.frontoffice.negocio.testeremoto;

import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

public class TesteAlterarEmailPessoa extends CAPESFrontofficeTesteAbstrato {

	private String EMPTY_STRING = "";
	
	public TesteAlterarEmailPessoa() {
		super("AlterarEmailPessoaServico");
	}

	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_PESSOA, 12688));
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_COOP_REMETENTE, 3353));
		listaParametros.add(obterParametro(ParametroEnum.ID_EMAIL, 3501389));
		listaParametros.add(obterParametro(ParametroEnum.CODIGO_TIPO_EMAIL, 2));
		listaParametros.add(obterParametro(ParametroEnum.DESC_EMAIL, "solomon@canais.com"));
		listaParametros.add(obterParametro(ParametroEnum.ID_USUARIO, "SicoobNET"));
	}

	@Override
	protected String obterRetornoEsperado() {
		return EMPTY_STRING;
	}

}
