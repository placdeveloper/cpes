package br.com.sicoob.capes.frontoffice.negocio.testeremoto;


import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

public class TesteConsultarTelefonePessoa extends CAPESFrontofficeTesteAbstrato {

	public TesteConsultarTelefonePessoa() {
		super("ConsultaTelefonePessoaServico");
	}

	@Override
	protected String obterRetornoEsperado() {
		return "1\\t2727585\\t0\\t13\\t3179516\\t\\t\\t\\n1\\t2735033\\t0\\t45\\t3866797\\t\\t\\t\\n1\\t8316403\\t2\\t61\\t51651651\\t5548\\tNÃO LIGAR\\t\\n";
	}

	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_PESSOA, 12688));
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_COOP_REMETENTE, 3353));
		listaParametros.add(obterParametro(ParametroEnum.TIPO_PESSOA, 0));
	}

}
