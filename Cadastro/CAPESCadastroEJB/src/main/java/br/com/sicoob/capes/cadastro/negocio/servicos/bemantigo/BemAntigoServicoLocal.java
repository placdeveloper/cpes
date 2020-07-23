// 09/08/2013
package br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.bemantigo.Bem;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * A Interface BemServicoLocal.
 */
public interface BemAntigoServicoLocal extends BemAntigoServico {

	/**
	 * 
	 * @param pessoaCompartilhamento
	 * @param id
	 * @return
	 * @throws BancoobException
	 */
	List<Bem> obterPorIdBemNovo(PessoaCompartilhamento pessoaCompartilhamento, Long idBem) throws BancoobException;

	/**
	 * Exclui a entidade sem passar por aprovação
	 * 
	 * @param objeto
	 * @throws BancoobException
	 */
	void excluirEntidadeSemAutorizacao(Bem objeto) throws BancoobException;

}