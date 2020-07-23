/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeDominioReplicavel;

/**
 * Interface para os servi�os que s�o de dom�nios replicaveis.
 * 
 * @author Erico.Junior
 */
public interface EntidadeDominioReplicavelServico<T extends EntidadeDominioReplicavel<?>>
		extends CAPESReplicacaoComumCrudServico<T> {

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
	void atualizar(List<T> itensInclusao, List<T> itensAlteracao, Integer idInstituicao) 
			throws BancoobException;
}
