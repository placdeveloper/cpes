// 09/08/2013
package br.com.sicoob.capes.replicacao.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.replicacao.negocio.servicos.AtualizarCadastroClienteServico;

/**
 * A Interface AtualizarCadastroClienteServicoLocal.
 *
 * @param <R> o tipo generico
 * @param <E> o tipo generico
 */
public interface AtualizarCadastroClienteServicoLocal<R extends EntidadeReplicavel<?>, E extends Replicavel>
		extends AtualizarCadastroClienteServico<R, E> {

	/**
	 * Verifica o compartilhamento dos relacionamentos.
	 * 
	 * @param entidadeCadastro
	 * @param instituicao
	 * @throws BancoobException
	 */
	void verificarRelacionamentos(E entidadeCadastro, Instituicao instituicao) throws BancoobException;
}