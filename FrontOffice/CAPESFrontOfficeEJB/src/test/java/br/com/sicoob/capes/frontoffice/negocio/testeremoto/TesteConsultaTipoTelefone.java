package br.com.sicoob.capes.frontoffice.negocio.testeremoto;

import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;

/**
 * Teste para os serviços de tipo de telefone
 * 
 * @author Bruno.Carneiro
 */
public class TesteConsultaTipoTelefone extends CAPESFrontofficeTesteAbstrato {

	/**
	 * Construtor
	 */
	public TesteConsultaTipoTelefone() {
		super("ConsultaTipoTelefoneServico");
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
		return "1\\t0\\tPARTICULAR\\t\\n1\\t1\\tCOMERCIAL\\t\\n1\\t2\\tOUTROS\\t\\n1\\t3\\tINTERNACIONAL\\t\\n1\\t4\\tRECADO\\t\\n1\\t5\\tFAX\\t\\n1\\t6\\tCELULAR\\t\\n1\\t7\\tTESTE 1\\t\\n1\\t8\\tAAAAAAA\\t\\n1\\t9\\tTESTE\\t\\n";
	}

}