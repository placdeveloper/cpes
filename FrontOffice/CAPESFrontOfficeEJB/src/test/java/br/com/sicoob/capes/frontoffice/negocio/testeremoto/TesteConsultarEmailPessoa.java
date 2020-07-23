package br.com.sicoob.capes.frontoffice.negocio.testeremoto;


import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

public class TesteConsultarEmailPessoa extends CAPESFrontofficeTesteAbstrato {

	public TesteConsultarEmailPessoa() {
		super("ConsultaEmailPessoaServico");
	}

	@Override
	protected String obterRetornoEsperado() {
		return "1\\t241768\\tesDQ93@HEcLcCR2.0TW.9z\\t0\\t\\n1\\t3501348\\tbatata@suculenta.org\\t0\\t\\n1\\t3501389\\tsolomon@canais.com\\t2\\t\\n";
	}

	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_PESSOA, 12688));
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_COOP_REMETENTE, 3353));
		listaParametros.add(obterParametro(ParametroEnum.TIPO_PESSOA, 0));
	}

}
