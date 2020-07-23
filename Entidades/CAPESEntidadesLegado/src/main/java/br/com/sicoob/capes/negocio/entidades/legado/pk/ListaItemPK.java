/*
 * SICOOB
 * 
 * ListaItemPK.java(br.com.sicoob.capes.negocio.entidades.legado.pk.ListaItemPK)
 */
package br.com.sicoob.capes.negocio.entidades.legado.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.legado.Lista;

/**
 * Chave primária do item da lista.
 *  
 * @author Erico.Junior
 */
@Embeddable
public class ListaItemPK extends BancoobChavePrimaria {

	/** Serial UID.*/
	private static final long serialVersionUID = 4764363817541655203L;

	/** O atributo lista. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "IdLista", referencedColumnName = "IdLista")
	private Lista lista;
	
	/** O atributo codigo item. */
	@Column (name = "CodListaItem")
	private String codigoItem;

	/**
	 * @return the lista
	 */
	public Lista getLista() {
		return lista;
	}

	/**
	 * @param lista the lista to set
	 */
	public void setLista(Lista lista) {
		this.lista = lista;
	}

	/**
	 * @return the codigoItem
	 */
	public String getCodigoItem() {
		return codigoItem;
	}

	/**
	 * @param codigoItem the codigoItem to set
	 */
	public void setCodigoItem(String codigoItem) {
		this.codigoItem = codigoItem;
	}
	
	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof ListaItemPK){
			ListaItemPK outro = (ListaItemPK) obj;
			String codigoOutro = outro.getCodigoItem();
			Integer idListaOutro = outro.getLista().getIdLista();
			Integer idLista = getLista().getIdLista();
			
			return getCodigoItem().equals(codigoOutro) && idLista.equals(idListaOutro); 
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codigoItem", "lista");
	}
}
