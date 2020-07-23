/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;

/**
 * @author erico.junior
 * 
 */
public interface EntidadeReplicavelServico<T extends EntidadeReplicavel<? extends Serializable>>
		extends CAPESReplicacaoComumCrudServico<T> {
	
	/**
	 * Obter por id d b2.
	 *
	 * @param entidade o valor de entidade
	 * @param idInstituicao o valor de id instituicao
	 * @return T
	 * @throws BancoobException lança a exceção BancoobException
	 */
	T obterPorIdDB2(T entidade, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por ids d b2.
	 *
	 * @param entidade o valor de entidade
	 * @param idInstituicao o valor de id instituicao
	 * @return T
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<T> obterPorIdsDB2(T entidade, Integer idInstituicao) throws BancoobException;
}