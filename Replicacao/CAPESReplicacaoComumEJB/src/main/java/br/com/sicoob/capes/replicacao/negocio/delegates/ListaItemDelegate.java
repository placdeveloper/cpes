/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.ListaItem;
import br.com.sicoob.capes.negocio.entidades.legado.pk.ListaItemPK;
import br.com.sicoob.capes.replicacao.negocio.servicos.ListaItemServico;
import br.com.sicoob.capes.replicacao.negocio.servicos.locator.CAPESReplicacaoComumServiceLocator;

/**
 * Delegate para os itens das listas.
 * @author Erico.Junior
 */
public class ListaItemDelegate extends EntidadeDominioReplicavelDelegate<ListaItem, ListaItemServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ListaItemServico localizarServico() {
		return CAPESReplicacaoComumServiceLocator.getInstance().localizarListaItemServico();
	}

	/**
	 * Obter prod lab.
	 *
	 * @param listaItemPK o valor de lista item pk
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @return ListaItem
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public ListaItem obterProdLab(ListaItemPK listaItemPK, Integer idCooperativaProdlab) throws BancoobException {
		return getServico().obterProdLab(listaItemPK, idCooperativaProdlab);
	}

	/**
	 * O método Incluir prod lab.
	 *
	 * @param listaItemReplicacao o valor de lista item replicacao
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void incluirProdLab(ListaItem listaItemReplicacao, Integer idCooperativaProdlab) throws BancoobException {
		getServico().incluirProdLab(listaItemReplicacao, idCooperativaProdlab);
	}

	/**
	 * O método Alterar prod lab.
	 *
	 * @param listaItemReplicacao o valor de lista item replicacao
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void alterarProdLab(ListaItem listaItemReplicacao, Integer idCooperativaProdlab) throws BancoobException {
		getServico().alterarProdLab(listaItemReplicacao, idCooperativaProdlab);
	}
	
}