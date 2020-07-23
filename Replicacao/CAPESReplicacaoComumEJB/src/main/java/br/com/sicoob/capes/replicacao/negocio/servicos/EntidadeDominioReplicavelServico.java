/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeDominioReplicavel;

/**
 * Interface para os serviços que são de domínios replicaveis.
 * 
 * @author Erico.Junior
 */
public interface EntidadeDominioReplicavelServico<T extends EntidadeDominioReplicavel<?>>
		extends CAPESReplicacaoComumCrudServico<T> {

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
	void atualizar(List<T> itensInclusao, List<T> itensAlteracao, Integer idInstituicao) 
			throws BancoobException;
}
