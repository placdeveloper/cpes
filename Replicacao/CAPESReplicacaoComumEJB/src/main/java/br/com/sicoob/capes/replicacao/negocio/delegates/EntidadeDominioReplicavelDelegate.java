/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeDominioReplicavel;
import br.com.sicoob.capes.replicacao.negocio.servicos.EntidadeDominioReplicavelServico;

/**
 * Superclasse das Delegates para as entidades que s�o de dom�nio replic�vel.
 * @author Erico.Junior
 */
public abstract class EntidadeDominioReplicavelDelegate
		<T extends EntidadeDominioReplicavel<?>, S extends EntidadeDominioReplicavelServico<T>>
		extends CAPESReplicacaoComumCrudDelegate<T, S> {

	/**
	 * Atualiza o dom�nio. Inclui e ou altera os registros informados.
	 * 
	 * @param itensInclusao
	 *            Os itens a serem inclu�dos.
	 * @param itensAlteracao
	 *            Os itens a serem alterados.            
	 * @param instituicao
	 *            A institui��o para qual ser� estabelecida a conex�o.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public void atualizar(List<T> itensInclusao, List<T> itensAlteracao, Integer idInstituicao) 
			throws BancoobException {
		getServico().atualizar(itensInclusao, itensAlteracao, idInstituicao);
	}
}
