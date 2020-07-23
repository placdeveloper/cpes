package br.com.sicoob.capes.frontoffice.negocio.testeremoto;

import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;
import br.com.sicoob.capes.frontoffice.negocio.enums.ParametroEnum;

/**
 * Teste para os servi�os de tipo de telefone
 * 
 * @author Bruno.Carneiro
 */
public class TesteConsultarTipoFonteRenda extends CAPESFrontofficeTesteAbstrato {

	/**
	 * Construtor
	 */
	public TesteConsultarTipoFonteRenda() {
		super("ConsultarTipoFonteRendaServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		listaParametros.add(obterParametro(ParametroEnum.TIPO_PESSOA, 0));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterRetornoEsperado() {
		return "1\\t0\\tSAL�RIO\\t0\\t\\n1\\t1\\tPRO-LABORE\\t0\\t\\n1\\t2\\tPENS�O ALIMENT�CIA\\t0\\t\\n1\\t3\\tAPOSENTADORIA\\t0\\t\\n1\\t4\\tPRESTA��O DE SERVI�O\\t0\\t\\n1\\t5\\tCONSULTORIA\\t0\\t\\n1\\t6\\tOUTROS\\t0\\t\\n1\\t7\\tFATURAMENTO\\t1\\t\\n1\\t8\\tRENDIMENTO N�O COMPROVADO\\t0\\t\\n1\\t9\\tAGROPECU�RIA\\t0\\t\\n1\\t10\\tAPLICA��O\\t0\\t\\n1\\t12\\tTESTE OBRIGAT�RIO\\t0\\t\\n1\\t13\\tMESADA\\t0\\t\\n1\\t14\\tTESTE ELISMAEL\\t0\\t\\n1\\t15\\tOUTROS\\t0\\t\\n";
	}

}