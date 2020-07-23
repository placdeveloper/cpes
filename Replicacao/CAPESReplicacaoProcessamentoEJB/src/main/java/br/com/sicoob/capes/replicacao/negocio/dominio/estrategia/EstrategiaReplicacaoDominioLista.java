/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.dominio.estrategia;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Instituicao;
import br.com.sicoob.capes.negocio.entidades.interfaces.DominioReplicavelLista;
import br.com.sicoob.capes.negocio.entidades.legado.Lista;
import br.com.sicoob.capes.negocio.entidades.legado.ListaItem;
import br.com.sicoob.capes.negocio.entidades.legado.pk.ListaItemPK;
import br.com.sicoob.capes.replicacao.negocio.delegates.CAPESReplicacaoComumFabricaDelegates;
import br.com.sicoob.capes.replicacao.negocio.delegates.EntidadeDominioReplicavelDelegate;
import br.com.sicoob.capes.replicacao.negocio.delegates.ListaDelegate;
import br.com.sicoob.capes.replicacao.negocio.enums.DominioReplicavelEnum;


/**
 * Estratégia para replicação da tabela do Cadastro único em Lista no sistema legado.
 * 
 * @author Erico.Junior
 */
public class EstrategiaReplicacaoDominioLista<T extends DominioReplicavelLista> extends
		EstrategiaReplicacaoDominioAbstract<T, ListaItem> {

	/** O atributo idLista. */
	private Integer idLista = null;
	
	/**
	 * Instancia um novo EstrategiaReplicacaoDominioLista.
	 *
	 * @param tabela o valor de tabela
	 */
	public EstrategiaReplicacaoDominioLista(DominioReplicavelEnum tabela) {
		super(tabela);
		idLista = tabela.getIdLista();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EntidadeDominioReplicavelDelegate<ListaItem, ?> obterDelegate() {
		return CAPESReplicacaoComumFabricaDelegates.getInstance().criarListaItemDelegate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ListaItem converter(T entidadeCadastro) {
		Lista lista = new Lista();
		lista.setIdLista(idLista);

		ListaItemPK pk = new ListaItemPK();
		pk.setCodigoItem(entidadeCadastro.getCodigoListaItem());
		pk.setLista(lista);

		ListaItem item = new ListaItem();
		item.setDescricao(entidadeCadastro.getDescricao());
		item.setPk(pk);

		return item;
	}

	/**
	 * {@inheritDoc}
	 */
	protected Map<Serializable, ListaItem> recuperarMapaLegado() throws BancoobException {

		Instituicao instituicao = obterInstituicaoProdLab();
		ListaDelegate delegate = 
				CAPESReplicacaoComumFabricaDelegates.getInstance().criarListaDelegate();

		Lista lista = delegate.obter(this.idLista, instituicao.getIdInstituicao());
		List<ListaItem> itens = lista.getItens();

		Map<Serializable, ListaItem> mapa = new HashMap<Serializable, ListaItem>();
		for (ListaItem item : itens) {
			mapa.put(item.getPk(), item);
		}
		
		return mapa;
	}
	
}
