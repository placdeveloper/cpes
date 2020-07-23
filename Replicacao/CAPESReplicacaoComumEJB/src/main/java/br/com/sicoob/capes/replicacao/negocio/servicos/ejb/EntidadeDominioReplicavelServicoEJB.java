/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeDominioReplicavel;

/**
 * Serviçi para as entidades de dominío replicável.
 * @author Erico.Junior
 */
public abstract class EntidadeDominioReplicavelServicoEJB<T extends EntidadeDominioReplicavel<?>>
		extends CAPESReplicacaoComumCrudServicoEJB<T> {

	/**
	 * {@inheritDoc}
	 */
	public void atualizar(List<T> itensInclusao, List<T> itensAlteracao,
			Integer idInstituicao) throws BancoobException {
	
		if (itensAlteracao != null && !itensAlteracao.isEmpty()) {
			for (T item : itensAlteracao) {
				super.alterar(item, idInstituicao);
			}
		}

		if (itensInclusao != null && !itensInclusao.isEmpty()) {
			for (T item : itensInclusao) {
				super.incluir(item, idInstituicao);
			}
		}
	}

}
