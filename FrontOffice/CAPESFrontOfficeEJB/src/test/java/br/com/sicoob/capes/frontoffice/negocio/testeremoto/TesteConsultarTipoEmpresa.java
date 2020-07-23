package br.com.sicoob.capes.frontoffice.negocio.testeremoto;

import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;

/**
 * Classe para o serviço de consultar empresa.
 * 
 * @author Bruno.Carneiro
 */
public class TesteConsultarTipoEmpresa extends CAPESFrontofficeTesteAbstrato {

	/**
	 * Construtor.
	 */
	public TesteConsultarTipoEmpresa() {
		super("ConsultarTipoEmpresaServico");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void preencherParametros(List<Parametro> listaParametros) {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String obterRetornoEsperado() {
		return "1\\t1\\tMICRO-EMPRESA OPTANTE PELO SIMPLES\\t0\\t1\\t0.00\\t360000.00\\t\\n1\\t2\\tMICRO-EMPRESA NÃO OPTANTE PELO SIMPLES\\t0\\t0\\t0.00\\t360000.00\\t\\n1\\t3\\tPEQUENA EMPRESA NÃO OPTANTE PELO SIMPLES\\t0\\t0\\t360000.01\\t3600000.00\\t\\n1\\t4\\tMÉDIA EMPRESA\\t1\\t0\\t3600000.01\\t300000000.00\\t\\n1\\t5\\tGRANDE EMPRESA\\t1\\t0\\t300000000.00\\t9999999999.99\\t\\n1\\t6\\tPEQUENA EMPRESA OPTANTE PELO SIMPLES\\t0\\t1\\t360000.01\\t3600000.00\\t\\n";
	}

}