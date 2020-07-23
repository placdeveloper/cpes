/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.ListaItem;
import br.com.sicoob.capes.negocio.entidades.legado.pk.ListaItemPK;

/**
 * Serviço utilizado para os itens das listas.
 * 
 * @author Erico.Junior
 */
public interface ListaItemServico extends EntidadeDominioReplicavelServico<ListaItem> {

	/**
	 * Obter prod lab.
	 *
	 * @param listaItemPK o valor de lista item pk
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @return ListaItem
	 * @throws BancoobException lança a exceção BancoobException
	 */
	ListaItem obterProdLab(ListaItemPK listaItemPK, Integer idCooperativaProdlab) throws BancoobException;

	/**
	 * O método Incluir prod lab.
	 *
	 * @param listaItemReplicacao o valor de lista item replicacao
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void incluirProdLab(ListaItem listaItemReplicacao, Integer idCooperativaProdlab) throws BancoobException;

	/**
	 * O método Alterar prod lab.
	 *
	 * @param listaItemReplicacao o valor de lista item replicacao
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void alterarProdLab(ListaItem listaItemReplicacao, Integer idCooperativaProdlab) throws BancoobException;

}
