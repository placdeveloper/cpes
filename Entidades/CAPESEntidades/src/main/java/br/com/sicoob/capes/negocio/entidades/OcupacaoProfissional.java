/*
 * SICOOB
 * 
 * OcupacaoProfissional.java(br.com.sicoob.capes.negocio.entidades.OcupacaoProfissional)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



/**
 * Entidade que representa ocupação profissional.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "OCUPACAOPROFISSIONAL", schema = "CLI")
public class OcupacaoProfissional extends CAPESEntidade<Integer> {

	/** Serial UID. */
	private static final long serialVersionUID = 6334479135185831173L;
	
	/** O atributo id ocupacao profissional. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idOcupacaoProfissional;
	
	/** O atributo codigo. */
	@Column(name = "CODOCUPACAO")
	private String codigo;

	/** O atributo codigo pai. */
	@Column(name = "CODOCUPACAOPAI")
	private String codigoPai;

	/** O atributo descricao. */
	@Column(name = "DESCOCUPACAO")
	private String descricao;

	/** O atributo codigo tipo ocupacao. */
	@Column(name = "CODTIPOOCUPACAO")
	private Short codigoTipoOcupacao;

	/** O atributo ativo. */
	@Column(name = "BOLATIVO")
	private Boolean ativo;

	/**
	 * @return the idOcupacaoProfissional
	 */
	public Integer getIdOcupacaoProfissional() {
		return idOcupacaoProfissional;
	}

	/**
	 * @param idOcupacaoProfissional the idOcupacaoProfissional to set
	 */
	public void setIdOcupacaoProfissional(Integer idOcupacaoProfissional) {
		this.idOcupacaoProfissional = idOcupacaoProfissional;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the codigoPai
	 */
	public String getCodigoPai() {
		return codigoPai;
	}

	/**
	 * @param codigoPai the codigoPai to set
	 */
	public void setCodigoPai(String codigoPai) {
		this.codigoPai = codigoPai;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the codigoTipoOcupacao
	 */
	public Short getCodigoTipoOcupacao() {
		return codigoTipoOcupacao;
	}

	/**
	 * @param codigoTipoOcupacao the codigoTipoOcupacao to set
	 */
	public void setCodigoTipoOcupacao(Short codigoTipoOcupacao) {
		this.codigoTipoOcupacao = codigoTipoOcupacao;
	}

	/**
	 * @return the ativo
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo the ativo to set
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdOcupacaoProfissional();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdOcupacaoProfissional(id);
	}

}
