/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dominio.builder;

import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Interface que define o Builder para pessoas compartilhamento.
 * @author Erico.Junior
 */
public interface PessoaCompartilhamentoBuilder<T extends PessoaCompartilhamento> {

	/**
	 * O m�todo Criar atividade economica.
	 */
	void criarAtividadeEconomica();
	
	/**
	 * O m�todo Criar observacao pessoa.
	 */
	void criarObservacaoPessoa();
	
	/**
	 * O m�todo Criar apelido.
	 */
	void criarApelido();
	
	/**
	 * O m�todo Criar dados especificos.
	 */
	void criarDadosEspecificos();
	
	/**
	 * Recupera o valor de pessoaCompartilhamento.
	 *
	 * @return o valor de pessoaCompartilhamento
	 */
	T getPessoaCompartilhamento();
}