/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;
import br.com.sicoob.capes.negocio.entidades.vigente.Tributacao;

/**
 * Interface para o DAO de tributacao.
 * 
 * @author juan.damasceno
 */
public interface TributacaoDAO extends EntidadeCadastroDaoIF<Tributacao> {

	/**
	 * Obter por pessoa.
	 *
	 * @param pessoa o valor de pessoa
	 * @return Tributacao
	 */
	Tributacao obterPorPessoa(PessoaCompartilhamento pessoa);
}