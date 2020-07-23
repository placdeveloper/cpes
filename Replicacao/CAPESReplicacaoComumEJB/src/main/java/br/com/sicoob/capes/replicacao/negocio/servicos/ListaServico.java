/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Lista;

/**
 * Serviço utilizado nas listas.
 * 
 * @author Erico.Junior
 */
public interface ListaServico extends CAPESReplicacaoComumCrudServico<Lista> {

	/**
	 * Obter prod lab.
	 *
	 * @param idLista o valor de id lista
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @return Lista
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Lista obterProdLab(Integer idLista, Integer idCooperativaProdlab) throws BancoobException;

	
}
