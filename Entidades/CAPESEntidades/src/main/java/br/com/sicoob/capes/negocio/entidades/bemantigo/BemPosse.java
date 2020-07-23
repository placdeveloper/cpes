/*
 * SICOOB
 * 
 * BemPosse.java(br.com.sicoob.capes.negocio.entidades.bemantigo.BemPosse)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * The Class BemPosse.
 */
@Entity
@Table(name="BEMPESSOAPOSSE",schema="CLI")
@EntityListeners({ReplicacaoListener.class})
@Filters({
	@Filter(name = "dataVigente"),
	@Filter(name = "dataVigenteAntiga"),
	@Filter(name = "imprimirFichaCadastralPrevia"),
	@Filter(name = "imprimirFichaCadastral")
})
public class BemPosse extends BemPosseBase implements Replicavel, Vigente {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo id bem posse. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IDBEMPESSOAPOSSE")
	private Integer idBemPosse;
	
	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdBemPosse();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdBemPosse(id);
	}

	/**
	 * @return the idBemPosse
	 */
	public Integer getIdBemPosse() {
		return idBemPosse;
	}

	/**
	 * @param idBemPosse the idBemPosse to set
	 */
	public void setIdBemPosse(Integer idBemPosse) {
		this.idBemPosse = idBemPosse;
	}

	/**
	 * @return the gravarHistorico
	 */
	@Override
	public Boolean getGravarHistorico() {
		return gravarHistorico;
	}

	/**
	 * @param gravarHistorico the gravarHistorico to set
	 */
	@Override
	public void setGravarHistorico(Boolean gravarHistorico) {
		this.gravarHistorico = gravarHistorico;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		if (getBem() != null && getBem().getPessoaCompartilhamento() != null){
			return getBem().getPessoaCompartilhamento();
		}else{
			return null;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object objeto) {
		return ReflexaoUtils.equals(this, objeto, "idBemPosse");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idBemPosse");
	}
}