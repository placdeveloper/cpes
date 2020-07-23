/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.AvaliacaoFinanceira;

/**
 * @author Erico.Junior
 *
 */
public interface AvaliacaoFinanceiraDAO extends EntidadeReplicavelDaoIF<AvaliacaoFinanceira> {

	/**
	 * O m�todo Atualizar historico.
	 *
	 * @param numPessoa o valor de num pessoa
	 * @param numeroCooperativa o valor de numero cooperativa
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void atualizarHistorico(Integer numPessoa, Integer numeroCooperativa) throws BancoobException;

}
