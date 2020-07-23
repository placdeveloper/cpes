/*
 * SICOOB
 * 
 * BemImovel.java(br.com.sicoob.capes.negocio.entidades.legado.BemImovel)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Entidade que representa a tabela BemPessoaImovel.
 * 
 * @author erico.junior
 */
@Entity
@Table(name = "BemPessoaImovel")
@PrimaryKeyJoinColumn(referencedColumnName = "UIDBemPessoa")
public class BemImovel extends Bem {

	/** Serial UID. */
	private static final long serialVersionUID = 2729256577375594652L;

	/** O atributo unidade medida. */
	@ManyToOne
	@JoinColumn(name = "IDUnidadeMedida", referencedColumnName = "IDUnidadeMedida")
	private UnidadeMedida unidadeMedida;

	/** O atributo area. */
	@Column(name = "qtdAreaImovel")
	private BigDecimal area;

	/** O atributo municipio. */
	@Column(name = "descMunicipioImovel")
	private String municipio;

	/** O atributo uf. */
	@Column(name = "descUF")
	private String uf;

	/** O atributo uf comarca. */
	@Column(name = "descUFComarca")
	private String ufComarca;

	/** O atributo denominacao. */
	@Column(name = "descDenominacaoImovel")
	private String denominacao;

	/** O atributo comarca. */
	@Column(name = "descComarcaImovel")
	private String comarca;

	/** O atributo benfeitorias. */
	@Column(name = "descBenfeitorias")
	private String benfeitorias;
	
	/** O atributo valor benfeitorias. */
	@Column(name = "ValorBenfeitorias")
	private BigDecimal valorBenfeitorias;

	/** O atributo id tipo localizacao. */
	private Short idTipoLocalizacao;
	
	/**
	 * @return the valorBenfeitorias
	 */
	public BigDecimal getValorBenfeitorias() {
		return valorBenfeitorias;
	}

	/**
	 * @param valorBenfeitorias the valorBenfeitorias to set
	 */
	public void setValorBenfeitorias(BigDecimal valorBenfeitorias) {
		this.valorBenfeitorias = valorBenfeitorias;
	}

	/**
	 * @return the unidadeMedida
	 */
	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	/**
	 * @param unidadeMedida
	 *            the unidadeMedida to set
	 */
	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	/**
	 * @return the area
	 */
	public BigDecimal getArea() {
		return area;
	}

	/**
	 * @param area
	 *            the area to set
	 */
	public void setArea(BigDecimal area) {
		this.area = area;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio
	 *            the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * @param uf
	 *            the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * @return the ufComarca
	 */
	public String getUfComarca() {
		return ufComarca;
	}

	/**
	 * @param ufComarca
	 *            the ufComarca to set
	 */
	public void setUfComarca(String ufComarca) {
		this.ufComarca = ufComarca;
	}

	/**
	 * @return the denominacao
	 */
	public String getDenominacao() {
		return denominacao;
	}

	/**
	 * @param denominacao
	 *            the denominacao to set
	 */
	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	/**
	 * @return the comarca
	 */
	public String getComarca() {
		return comarca;
	}

	/**
	 * @param comarca
	 *            the comarca to set
	 */
	public void setComarca(String comarca) {
		this.comarca = comarca;
	}

	/**
	 * @return the benfeitorias
	 */
	public String getBenfeitorias() {
		return benfeitorias;
	}

	/**
	 * @param benfeitorias
	 *            the benfeitorias to set
	 */
	public void setBenfeitorias(String benfeitorias) {
		this.benfeitorias = benfeitorias;
	}

	/**
	 * @return the idTipoLocalizacao
	 */
	public Short getIdTipoLocalizacao() {
		return idTipoLocalizacao;
	}

	/**
	 * @param idTipoLocalizacao the idTipoLocalizacao to set
	 */
	public void setIdTipoLocalizacao(Short idTipoLocalizacao) {
		this.idTipoLocalizacao = idTipoLocalizacao;
	}

}
