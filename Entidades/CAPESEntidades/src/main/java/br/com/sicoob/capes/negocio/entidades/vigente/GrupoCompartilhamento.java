/*
 * SICOOB
 * 
 * GrupoCompartilhamento.java(br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento)
 */
package br.com.sicoob.capes.negocio.entidades.vigente;


import static javax.persistence.InheritanceType.JOINED;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import br.com.sicoob.capes.negocio.entidades.GrupoCompartilhamentoBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Vigente;


/**
 * The Class GrupoCompartilhamento.
 */
@Entity
@Table(schema= "CLI", name="GRUPOCOMPARTILHAMENTO")
@Inheritance(strategy = JOINED)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class GrupoCompartilhamento extends GrupoCompartilhamentoBase implements Vigente {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -6091783000742450291L;
	
	/** O atributo id grupo compartilhamento. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDGRUPOCOMPARTILHAMENTO")
	private Integer idGrupoCompartilhamento;
	
	/** O atributo integracao srf. */
	@Column(name = "BOLINTEGRACAOSRF", nullable = false)
	private Boolean integracaoSrf = Boolean.FALSE;
	
	/** O atributo gravar historico. */
	@Transient
	private Boolean gravarHistorico = Boolean.TRUE;

	/**
	 * Recupera id grupo compartilhamento.
	 * 
	 * @return id grupo compartilhamento
	 */
	public Integer getIdGrupoCompartilhamento() {
		return idGrupoCompartilhamento;
	}

	/**
	 * Preenche id grupo compartilhamento.
	 * 
	 * @param idGrupoCompartilhamento
	 *            o novo id grupo compartilhamento
	 */
	public void setIdGrupoCompartilhamento(Integer idGrupoCompartilhamento) {
		this.idGrupoCompartilhamento = idGrupoCompartilhamento;
	}

	/**
	 * Recupera integracao srf.
	 * 
	 * @return integracao srf
	 */
	public Boolean getIntegracaoSrf() {
		return integracaoSrf;
	}

	/**
	 * Preenche integracao srf.
	 * 
	 * @param integracaoSrf
	 *            o novo integracao srf
	 */
	public void setIntegracaoSrf(Boolean integracaoSrf) {
		this.integracaoSrf = integracaoSrf;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return idGrupoCompartilhamento;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		this.idGrupoCompartilhamento = id;
	}

	/** 
	 * {@inheritDoc}
	 */
	public Boolean getGravarHistorico() {
		return gravarHistorico;
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setGravarHistorico(Boolean gravarHistorico) {
		this.gravarHistorico = gravarHistorico;
	}
	
}