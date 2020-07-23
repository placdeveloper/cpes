/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.ListaItem;
import br.com.sicoob.capes.negocio.entidades.legado.pk.ListaItemPK;

/**
 * Servi�o utilizado para os itens das listas.
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
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	ListaItem obterProdLab(ListaItemPK listaItemPK, Integer idCooperativaProdlab) throws BancoobException;

	/**
	 * O m�todo Incluir prod lab.
	 *
	 * @param listaItemReplicacao o valor de lista item replicacao
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void incluirProdLab(ListaItem listaItemReplicacao, Integer idCooperativaProdlab) throws BancoobException;

	/**
	 * O m�todo Alterar prod lab.
	 *
	 * @param listaItemReplicacao o valor de lista item replicacao
	 * @param idCooperativaProdlab o valor de id cooperativa prodlab
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void alterarProdLab(ListaItem listaItemReplicacao, Integer idCooperativaProdlab) throws BancoobException;

}
