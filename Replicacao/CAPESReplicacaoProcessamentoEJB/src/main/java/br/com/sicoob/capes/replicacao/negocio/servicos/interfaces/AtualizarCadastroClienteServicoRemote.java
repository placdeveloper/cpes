// 09/08/2013
package br.com.sicoob.capes.replicacao.negocio.servicos.interfaces;

import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.legado.EntidadeReplicavel;
import br.com.sicoob.capes.replicacao.negocio.servicos.AtualizarCadastroClienteServico;

/**
 * A Interface AtualizarCadastroClienteServicoRemote.
 *
 * @param <R> o tipo generico
 * @param <E> o tipo generico
 */
public interface AtualizarCadastroClienteServicoRemote<R extends EntidadeReplicavel<?>, E extends Replicavel>
		extends AtualizarCadastroClienteServico<R, E> {

}