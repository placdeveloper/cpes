/*
 * SICOOB
 * 
 * BemImovelBase.java(br.com.sicoob.capes.negocio.entidades.BemImovelBase)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.sicoob.capes.negocio.entidades.UnidadeMedida;

/**
 * 
 * @author juan.damasceno
 *
 */
@MappedSuperclass
public abstract class BemImovelBase extends Bem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo unidade medida. */
	@ManyToOne
	@JoinColumn(name="CODUNIDADEMEDIDA")
	private UnidadeMedida unidadeMedida;
	
	/** O atributo area. */
	@Column(name="QTDAREAIMOVEL", precision = 13, scale = 6)
	private BigDecimal area;
	
	/** O atributo denominacao. */
	@Column(name="DESCDENOMINACAO", length = 50)
	private String denominacao;
	
	/** O atributo benfeitoria. */
	@Column(name="DESCBENFEITORIA", length = 400)
	private String benfeitoria;
	
	/** O atributo valor benfeitoria. */
	@Column(name="VALORBENFEITORIA", precision = 12, scale = 2)
	private BigDecimal valorBenfeitoria;
	
	/** O atributo municipio. */
	@Column(name="DESCMUNICIPIOIMOVEL", updatable=false, insertable=false, length = 40)
	private String municipio;
	
	/** O atributo sigla uf municipio. */
	@Column(name="SIGLAUFMUNICIPIOIMOVEL", updatable=false, insertable=false, length = 2)
	private String siglaUFMunicipio;
	
	/** O atributo comarca. */
	@Column(name="DESCCOMARCA", updatable=false, insertable=false, length = 40)
	private String comarca;
	
	/** O atributo sigla uf comarca. */
	@Column(name="SIGLAUFCOMARCA", updatable=false, insertable=false, length = 2)
	private String siglaUFComarca;
	
	/** O atributo cod localizacao. */
	@Column(name="CODLOCALIZACAO", length = 1)
	private String codLocalizacao;
	
	/** O atributo id localidade imovel. */
	@Column(name="IDLOCALIDADEIMOVEL")
	private Integer idLocalidadeImovel;
	
	/** O atributo id localidade comarca. */
	@Column(name="IDLOCALIDADECOMARCA")
	private Integer idLocalidadeComarca;
	
	/**
	 * @return the unidadeMedida
	 */
	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}
	/**
	 * @param unidadeMedida the unidadeMedida to set
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
	 * @param area the area to set
	 */
	public void setArea(BigDecimal area) {
		this.area = area;
	}
	/**
	 * @return the denominacao
	 */
	public String getDenominacao() {
		return denominacao;
	}
	/**
	 * @param denominacao the denominacao to set
	 */
	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}
	/**
	 * @return the benfeitoria
	 */
	public String getBenfeitoria() {
		return benfeitoria;
	}
	/**
	 * @param benfeitoria the benfeitoria to set
	 */
	public void setBenfeitoria(String benfeitoria) {
		this.benfeitoria = benfeitoria;
	}

	/**
	 * @return the valorBenfeitoria
	 */
	public BigDecimal getValorBenfeitoria() {
		return valorBenfeitoria;
	}
	/**
	 * @param valorBenfeitoria the valorBenfeitoria to set
	 */
	public void setValorBenfeitoria(BigDecimal valorBenfeitoria) {
		this.valorBenfeitoria = valorBenfeitoria;
	}
	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}
	/**
	 * @param municipio the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	/**
	 * @return the siglaUFMunicipio
	 */
	public String getSiglaUFMunicipio() {
		return siglaUFMunicipio;
	}
	/**
	 * @param siglaUFMunicipio the siglaUFMunicipio to set
	 */
	public void setSiglaUFMunicipio(String siglaUFMunicipio) {
		this.siglaUFMunicipio = siglaUFMunicipio;
	}
	/**
	 * @return the comarca
	 */
	public String getComarca() {
		return comarca;
	}
	/**
	 * @param comarca the comarca to set
	 */
	public void setComarca(String comarca) {
		this.comarca = comarca;
	}
	/**
	 * @return the siglaUFComarca
	 */
	public String getSiglaUFComarca() {
		return siglaUFComarca;
	}
	/**
	 * @param siglaUFComarca the siglaUFComarca to set
	 */
	public void setSiglaUFComarca(String siglaUFComarca) {
		this.siglaUFComarca = siglaUFComarca;
	}
	/**
	 * @return the codLocalizacao
	 */
	public String getCodLocalizacao() {
		return codLocalizacao;
	}
	/**
	 * @param codLocalizacao the codLocalizacao to set
	 */
	public void setCodLocalizacao(String codLocalizacao) {
		this.codLocalizacao = codLocalizacao;
	}
	/**
	 * @return the idLocalidadeImovel
	 */
	public Integer getIdLocalidadeImovel() {
		return idLocalidadeImovel;
	}
	/**
	 * @param idLocalidadeImovel the idLocalidadeImovel to set
	 */
	public void setIdLocalidadeImovel(Integer idLocalidadeImovel) {
		this.idLocalidadeImovel = idLocalidadeImovel;
	}
	/**
	 * @return the idLocalidadeComarca
	 */
	public Integer getIdLocalidadeComarca() {
		return idLocalidadeComarca;
	}
	/**
	 * @param idLocalidadeComarca the idLocalidadeComarca to set
	 */
	public void setIdLocalidadeComarca(Integer idLocalidadeComarca) {
		this.idLocalidadeComarca = idLocalidadeComarca;
	}
}