package br.com.sicoob.capes.frontoffice.negocio.testeremoto;

import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;

/**
 * Teste para os serviços de tipo de telefone
 * 
 * @author Bruno.Carneiro
 */
public class TesteConsultarTipoEmail extends CAPESFrontofficeTesteAbstrato {

	/**
	 * Construtor
	 */
	public TesteConsultarTipoEmail() {
		super("ConsultaTipoEmailServico");
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
		return "1\\t0\\tPARTICULAR\\t\\n1\\t1\\tCOMERCIAL\\t\\n1\\t2\\tOUTROS\\t\\n";
	}

}