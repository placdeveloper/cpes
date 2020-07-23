/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeDominioReplicavel;
import br.com.sicoob.capes.replicacao.negocio.servicos.EntidadeDominioReplicavelServico;

/**
 * Superclasse das Delegates para as entidades que são de domínio replicável.
 * @author Erico.Junior
 */
public abstract class EntidadeDominioReplicavelDelegate
		<T extends EntidadeDominioReplicavel<?>, S extends EntidadeDominioReplicavelServico<T>>
		extends CAPESReplicacaoComumCrudDelegate<T, S> {

	/**
	 * Atualiza o domínio. Inclui e ou altera os registros informados.
	 * 
	 * @param itensInclusao
	 *            Os itens a serem incluídos.
	 * @param itensAlteracao
	 *            Os itens a serem alterados.            
	 * @param instituicao
	 *            A instituição para qual será estabelecida a conexão.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public void atualizar(List<T> itensInclusao, List<T> itensAlteracao, Integer idInstituicao) 
			throws BancoobException {
		getServico().atualizar(itensInclusao, itensAlteracao, idInstituicao);
	}
}
