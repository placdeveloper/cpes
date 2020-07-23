package br.com.sicoob.capes.frontoffice.negocio.testeremoto;


import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

public class TesteConsultarEnderecoPessoa extends CAPESFrontofficeTesteAbstrato {

	public TesteConsultarEnderecoPessoa() {
		super("ConsultarEnderecoPessoaServico");
	}

	@Override
	protected String obterRetornoEsperado() {
		return "1\\t10830104\\t2\\t33\\t5665\\t64600124\\tOSVALDO CRUZ\\tS/N\\tQEWQEWEWQ\\tFÁTIMA\\t\\n";
	}

	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_PESSOA, 12688));
		listaParametros.add(obterParametro(ParametroEnum.NUMERO_COOP_REMETENTE, 3353));
		listaParametros.add(obterParametro(ParametroEnum.TIPO_PESSOA, 0));
	}

}
