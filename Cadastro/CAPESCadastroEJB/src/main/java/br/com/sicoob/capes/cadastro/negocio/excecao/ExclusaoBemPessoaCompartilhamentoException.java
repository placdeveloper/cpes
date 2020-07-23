package br.com.sicoob.capes.cadastro.negocio.excecao;

import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;

/**
 * Exce��o para a exclus�o de um bemPessoaCompartilhamento.
 * 
 * � lan�ada durante a tentativa de uma exclus�o de um
 * {@link BemPessoaCompartilhamento} na tela principal. Nesse momento, a
 * exclus�o do compartilhamento dever� ser feita na tela de edi��o, pois o
 * percentual de propriedade do bem dever� ser ajustado.
 * 
 * @author Bruno.Carneiro
 * 
 */
public class ExclusaoBemPessoaCompartilhamentoException extends CAPESCadastroNegocioException {
	private static final long serialVersionUID = 6246379534737026534L;

	private static final String CHAVE_MSG = "MN211";

	public ExclusaoBemPessoaCompartilhamentoException() {
		super(CHAVE_MSG);
	}

}