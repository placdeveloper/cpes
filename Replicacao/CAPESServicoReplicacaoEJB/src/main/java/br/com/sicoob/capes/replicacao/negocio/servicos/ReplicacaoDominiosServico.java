/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * Servi�o utilizado para replicar as tabelas do cadastro �nico que s�o dom�nios no SQL (legado).
 * @author Erico.Junior
 */
public interface ReplicacaoDominiosServico extends CAPESServicoReplicacaoServico {

	/**
	 * Replica os dom�nios do cadastro �nico no SQL (legado).
	 * 
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	void replicar() throws BancoobException;

}
