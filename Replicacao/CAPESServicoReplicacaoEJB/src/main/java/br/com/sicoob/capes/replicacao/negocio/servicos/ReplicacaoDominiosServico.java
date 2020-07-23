/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;

/**
 * Serviço utilizado para replicar as tabelas do cadastro único que são domínios no SQL (legado).
 * @author Erico.Junior
 */
public interface ReplicacaoDominiosServico extends CAPESServicoReplicacaoServico {

	/**
	 * Replica os domínios do cadastro único no SQL (legado).
	 * 
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	void replicar() throws BancoobException;

}
