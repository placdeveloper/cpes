package br.com.sicoob.capes.frontoffice.negocio.testeremoto;


import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

public class TesteConsultarFonteRendaPessoa extends CAPESFrontofficeTesteAbstrato {

	public TesteConsultarFonteRendaPessoa() {
		super("ConsultarFonteRendaServico");
	}

	@Override
	protected String obterRetornoEsperado() {
		return "1\\t838097\\t0\\t1\\t\\t816.0600\\tRENDA COMPROVADO ATRAVES DO CONTRA CHEQUE 01/2012.\\t5427343\\t0\\t0\\t\\n1\\t3407442\\t0\\t1\\t2019-08-02\\t2331.0000\\tFREELA\\t3209116\\t0\\t0\\t\\n";
	}

	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_PESSOA, 12688));
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_COOP_REMETENTE, 3353));
		listaParametros.add(obterParametro(ParametroEnum.TIPO_PESSOA, 0));
	}

}
