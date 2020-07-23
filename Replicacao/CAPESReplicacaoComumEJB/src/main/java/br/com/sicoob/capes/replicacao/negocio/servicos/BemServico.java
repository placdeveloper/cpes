/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;

/**
 * Serviço utilizado para os bens.
 * 
 * @author Erico.Junior
 */
public interface BemServico extends EntidadeReplicavelServico<Bem> {

	/**
	 * {@inheritDoc}
	 */
	Bem obterPorIdDB2(Bem bem, Integer idInstituicao) throws BancoobException;
}
