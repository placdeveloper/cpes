package br.com.sicoob.capes.cadastro.negocio.excecao;

import br.com.sicoob.capes.negocio.entidades.bem.BemPessoaCompartilhamento;

/**
 * Exceção para a exclusão de um bemPessoaCompartilhamento.
 * 
 * É lançada durante a tentativa de uma exclusão de um
 * {@link BemPessoaCompartilhamento} na tela principal. Nesse momento, a
 * exclusão do compartilhamento deverá ser feita na tela de edição, pois o
 * percentual de propriedade do bem deverá ser ajustado.
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