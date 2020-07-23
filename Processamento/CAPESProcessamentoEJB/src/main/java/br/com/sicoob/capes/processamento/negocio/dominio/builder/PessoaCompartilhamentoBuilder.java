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
	 * O método Criar atividade economica.
	 */
	void criarAtividadeEconomica();
	
	/**
	 * O método Criar observacao pessoa.
	 */
	void criarObservacaoPessoa();
	
	/**
	 * O método Criar apelido.
	 */
	void criarApelido();
	
	/**
	 * O método Criar dados especificos.
	 */
	void criarDadosEspecificos();
	
	/**
	 * Recupera o valor de pessoaCompartilhamento.
	 *
	 * @return o valor de pessoaCompartilhamento
	 */
	T getPessoaCompartilhamento();
}