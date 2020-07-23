/*
 * SICOOB
 * 
 * CompartilhamentoCadastro.java(br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.bancoob.persistencia.annotations.OrdenacaoPadrao;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * The Class CompartilhamentoCadastro.
 */
@Entity
@Table(schema = "CLI", name = "COMPARTILHAMENTOCADASTRO")
@OrdenacaoPadrao(colunas = {"descricao"}, descendente = false)
public class CompartilhamentoCadastro extends CAPESEntidade<Short> {

	/** Serial UID.*/
	private static final long serialVersionUID = 2626777085571565241L;

	/** O atributo codigo. */
	@Id
	@Column(name = "CODCOMPARTILHAMENTOCADASTRO")
	private Short codigo;
	
	/** O atributo descricao. */
	@Column(name = "DESCCOMPARTILHAMENTOCADASTRO", nullable = false, length = 100)
	private String descricao;
	
	/** O atributo nome grupo cta. */
	@Column(name = "NOMEGRUPOCTA")
	private String nomeGrupoCta;
	
	/** O atributo utiliza ged gft. */
	@Column(name = "BOLUTILIZAGEDGFT", nullable = false)
	private Boolean utilizaGedGft = Boolean.FALSE;

	/** O atributo nomeGrupoCtaCadastroInstituicao. */
	@Column(name = "NOMEGRPCTACADINST")
	private String nomeGrupoCtaCadastroInstituicao;
	
	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
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
	 * {@inheritDoc}
	 */
	@Override
	public Short getId() {
		return getCodigo();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Short id) {
		setCodigo(id);
	}
	
	/**
	 * Recupera nome grupo cta.
	 * 
	 * @return nome grupo cta
	 */
	public String getNomeGrupoCta() {
		return nomeGrupoCta;
	}
	
	/**
	 * Preenche nome grupo cta.
	 * 
	 * @param nomeGrupoCta
	 *            o novo nome grupo cta
	 */
	public void setNomeGrupoCta(String nomeGrupoCta) {
		this.nomeGrupoCta = nomeGrupoCta;
	}
	
	/**
	 * Recupera utiliza ged gft.
	 * 
	 * @return utiliza ged gft
	 */
	public Boolean getUtilizaGedGft() {
		return utilizaGedGft;
	}
	
	/**
	 * Preenche utiliza ged gft.
	 * 
	 * @param utilizaGedGft
	 *            o novo utiliza ged gft
	 */
	public void setUtilizaGedGft(Boolean utilizaGedGft) {
		this.utilizaGedGft = utilizaGedGft;
	}
	
	/**
	 * Recupera o valor de nomeGrupoCtaCadastroInstituicao.
	 *
	 * @return o valor de nomeGrupoCtaCadastroInstituicao
	 */
	public String getNomeGrupoCtaCadastroInstituicao() {
		return nomeGrupoCtaCadastroInstituicao;
	}

	/**
	 * Define o valor de nomeGrupoCtaCadastroInstituicao.
	 *
	 * @param nomeGrupoCtaCadastroInstituicao o novo valor de nomeGrupoCtaCadastroInstituicao
	 */
	public void setNomeGrupoCtaCadastroInstituicao(String nomeGrupoCtaCadastroInstituicao) {
		this.nomeGrupoCtaCadastroInstituicao = nomeGrupoCtaCadastroInstituicao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codigo");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "codigo");
	}

}