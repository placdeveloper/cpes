/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.replicacao.negocio.servicos.EntidadeReplicavelServico;

/**
 * @author erico.junior
 * 
 */
public abstract class EntidadeReplicavelDelegate<
		T extends EntidadeReplicavel<?>, S extends EntidadeReplicavelServico<T>>
		extends CAPESReplicacaoComumCrudDelegate<T, S> {
	
	/**
	 * Obter por id d b2.
	 *
	 * @param entidade o valor de entidade
	 * @param idInstituicao o valor de id instituicao
	 * @return T
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public T obterPorIdDB2(T entidade, Integer idInstituicao) throws BancoobException {
		return getServico().obterPorIdDB2(entidade, idInstituicao);
	}
	
	/**
	 * Obter por ids d b2.
	 *
	 * @param entidade o valor de entidade
	 * @param idInstituicao o valor de id instituicao
	 * @return T
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<T> obterPorIdsDB2(T entidade, Integer idInstituicao) throws BancoobException {
		return getServico().obterPorIdsDB2(entidade, idInstituicao);
	}
}
