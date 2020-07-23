/*
 * SICOOB
 * 
 * BemOnus.java(br.com.sicoob.capes.negocio.entidades.bemantigo.BemOnus)
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
 * The Class BemOnus.
 */
@Entity(name="bemonus")
@Table(name="BEMPESSOAONUS",schema="CLI")
@EntityListeners({ReplicacaoListener.class})
@Filters({
	@Filter(name = "dataVigente"),
	@Filter(name = "dataVigenteAntiga"),
	@Filter(name = "imprimirFichaCadastralPrevia"),
	@Filter(name = "imprimirFichaCadastral")
})
public class BemOnus extends BemOnusBase implements Replicavel, Vigente {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;
	
	/** O atributo id bem onus. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDBEMPESSOAONUS")
	private Integer idBemOnus;

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdBemOnus();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdBemOnus(id);
	}

	/**
	 * @return the idBemOnus
	 */
	public Integer getIdBemOnus() {
		return idBemOnus;
	}

	/**
	 * @param idBemOnus the idBemOnus to set
	 */
	public void setIdBemOnus(Integer idBemOnus) {
		this.idBemOnus = idBemOnus;
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
		return ReflexaoUtils.equals(this, objeto, "idBemOnus");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idBemOnus");
	}
	
}