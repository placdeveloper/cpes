package br.com.sicoob.capes.frontoffice.negocio.testeremoto;

import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

public class TesteAlterarTelefonePessoa extends CAPESFrontofficeTesteAbstrato {

	private String EMPTY_STRING = "";
	
	public TesteAlterarTelefonePessoa() {
		super("AlterarTelefonePessoaServico");
	}

	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_PESSOA, 12688));
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_COOP_REMETENTE, 3353));
		listaParametros.add(obterParametro(ParametroEnum.CODIGO_TIPO_TELEFONE, 2));
		listaParametros.add(obterParametro(ParametroEnum.ID_TELEFONE, 8316403));
		listaParametros.add(obterParametro(ParametroEnum.DDD, 61));
		listaParametros.add(obterParametro(ParametroEnum.TELEFONE, 51651651));
		listaParametros.add(obterParametro(ParametroEnum.RAMAL, 5548));
		listaParametros.add(obterParametro(ParametroEnum.DESC_OBSERVACAO, "NÃO LIGAR"));
		listaParametros.add(obterParametro(ParametroEnum.ID_USUARIO, "SicoobNET"));
	}

	@Override
	protected String obterRetornoEsperado() {
		return EMPTY_STRING;
	}

}
