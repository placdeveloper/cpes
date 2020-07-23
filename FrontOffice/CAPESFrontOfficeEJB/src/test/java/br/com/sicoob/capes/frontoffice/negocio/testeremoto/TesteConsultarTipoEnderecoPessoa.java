package br.com.sicoob.capes.frontoffice.negocio.testeremoto;

import java.util.List;

import br.com.bancoob.srtb.dto.Parametro;

/**
 * Teste para os serviços de tipo de telefone
 * 
 * @author Bruno.Carneiro
 */
public class TesteConsultarTipoEnderecoPessoa extends CAPESFrontofficeTesteAbstrato {

	/**
	 * Construtor
	 */
	public TesteConsultarTipoEnderecoPessoa() {
		super("ConsultarTipoEnderecoPessoaServico");
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
		return "1\\t0\\tRESIDENCIAL\\t0\\t\\n1\\t1\\tCOMERCIAL\\t1\\t\\n1\\t2\\tOUTROS\\t2\\t\\n1\\t3\\tCAIXA POSTAL\\t0\\t\\n1\\t4\\tZONA RURAL\\t0\\t\\n";
	}

}