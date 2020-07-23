package br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.ListaLegadoEnum;
import br.com.sicoob.capes.comum.util.Constantes;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;
import br.com.sicoob.capes.negocio.entidades.legado.Lista;
import br.com.sicoob.capes.negocio.entidades.legado.ListaItem;
import br.com.sicoob.capes.negocio.entidades.legado.pk.ListaItemPK;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.ListaDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.ListaItemDelegate;

/**
 * A Classe NacionalidadeFachada.
 */
public class NacionalidadeFachada {

	private transient ListaItemDelegate listaItemDelegate = CAPESReplicacaoComumFabricaDelegates.getInstance().criarListaItemDelegate();

	private transient ListaDelegate listaDelegate = CAPESReplicacaoComumFabricaDelegates.getInstance().criarListaDelegate();

	public void replicarNacionalidade(Nacionalidade nacionalidade) throws BancoobException {
		Lista lista = listaDelegate.obterProdLab(ListaLegadoEnum.NACIONALIDADE.getIdLista(), Constantes.Comum.ID_COOPERATIVA_PRODLAB);

		ListaItemPK listaItemPK = new ListaItemPK();
		listaItemPK.setLista(lista);
		listaItemPK.setCodigoItem(nacionalidade.getCodigoListaItem());

		ListaItem listaItemAlteracao = listaItemDelegate.obterProdLab(listaItemPK, Constantes.Comum.ID_COOPERATIVA_PRODLAB);

		ListaItem listaItemReplicacao = criarListaItemReplicacao(nacionalidade, listaItemAlteracao, lista);

		if (listaItemAlteracao == null) {
			listaItemDelegate.incluirProdLab(listaItemReplicacao, Constantes.Comum.ID_COOPERATIVA_PRODLAB);
		} else {
			listaItemDelegate.alterarProdLab(listaItemReplicacao, Constantes.Comum.ID_COOPERATIVA_PRODLAB);
		}

	}

	private ListaItem criarListaItemReplicacao(Nacionalidade nacionalidade, ListaItem listaItemAlteracao, Lista lista) {

		ListaItem listaItemReplicacao = listaItemAlteracao;
		if (listaItemAlteracao == null) {
			listaItemReplicacao = new ListaItem();
			ListaItemPK pk = new ListaItemPK();
			pk.setCodigoItem(nacionalidade.getCodigoListaItem());
			pk.setLista(lista);
			listaItemReplicacao.setPk(pk);
		}
		listaItemReplicacao.setDescricao(nacionalidade.getDescricao());
		return listaItemReplicacao;
	}

}
