package br.com.sicoob.capes.frontoffice.negocio.testeremoto;


import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

public class TesteConsultarNomePessoa extends CAPESFrontofficeTesteAbstrato {

	public TesteConsultarNomePessoa() {
		super("ConsultaNomePessoaServico");
	}

	@Override
	protected String obterRetornoEsperado() {
		return "";
	}

	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(ParametroEnum.CPF_CNPJ, "02603166441"));
	}

}
