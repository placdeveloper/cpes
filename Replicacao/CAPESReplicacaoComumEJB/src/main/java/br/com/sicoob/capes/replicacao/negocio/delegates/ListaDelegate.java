/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Lista;
import br.com.sicoob.capes.replicacao.negocio.servicos.ListaServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para as listas
 * 
 * @author Erico.Junior
 */
public class ListaDelegate extends CAPESReplicacaoComumCrudDelegate<Lista, ListaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ListaServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarListaServico();
	}

	/**
	 * Obter prod lab.
	 *
	 * @param idLista o valor de id lista
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @return Lista
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Lista obterProdLab(Integer idLista, Integer idCooperativaProdlab) throws BancoobException {
		return getServico().obterProdLab(idLista, idCooperativaProdlab);
	}
	
}
