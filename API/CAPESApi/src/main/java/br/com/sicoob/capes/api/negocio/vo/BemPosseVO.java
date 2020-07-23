/*
 * SICOOB
 * 
 * BemPosseVO.java(br.com.sicoob.capes.api.negocio.vo.BemPosseVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class BemPosseVO.
 */
public class BemPosseVO implements Serializable {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -2697813531997798735L;

	/** O atributo id bem posse. */
	private Integer idBemPosse;
	
	/** O atributo id bem. */
	private Long idBem;
	
	/** O atributo codigo tipo posse. */
	private Short codigoTipoPosse;
	
	/** O atributo descricao tipo posse. */
	private String descricaoTipoPosse;
	
	/** O atributo area. */
	private BigDecimal area;
	
	/**
	 * Instancia um novo BemPosseVO.
	 */
	public BemPosseVO(){
		
	}
	
	/**
	 * Instancia um novo BemPosseVO.
	 *
	 * @param idBemPosse o valor de id bem posse
	 * @param idBem o valor de id bem
	 * @param codigoTipoPosse o valor de codigo tipo posse
	 * @param descricaoTipoPosse o valor de descricao tipo posse
	 * @param area o valor de area
	 */
	public BemPosseVO(Integer idBemPosse, Long idBem, Short codigoTipoPosse, String descricaoTipoPosse, BigDecimal area) {
		this.idBemPosse = idBemPosse;
		this.idBem = idBem;
		this.codigoTipoPosse = codigoTipoPosse;
		this.descricaoTipoPosse = descricaoTipoPosse;
		this.area = area;
	}



	/**
	 * Recupera id bem posse.
	 * 
	 * @return id bem posse
	 */
	public Integer getIdBemPosse() {
		return idBemPosse;
	}

	/**
	 * Preenche id bem posse.
	 * 
	 * @param idBemPosse
	 *            o novo id bem posse
	 */
	public void setIdBemPosse(Integer idBemPosse) {
		this.idBemPosse = idBemPosse;
	}

	/**
	 * Recupera id bem.
	 * 
	 * @return id bem
	 */
	public Long getIdBem() {
		return idBem;
	}

	/**
	 * Preenche id bem.
	 * 
	 * @param idBem
	 *            o novo id bem
	 */
	public void setIdBem(Long idBem) {
		this.idBem = idBem;
	}

	/**
	 * Recupera codigo tipo posse.
	 * 
	 * @return codigo tipo posse
	 */
	public Short getCodigoTipoPosse() {
		return codigoTipoPosse;
	}

	/**
	 * Preenche codigo tipo posse.
	 * 
	 * @param codigoTipoPosse
	 *            o novo codigo tipo posse
	 */
	public void setCodigoTipoPosse(Short codigoTipoPosse) {
		this.codigoTipoPosse = codigoTipoPosse;
	}

	/**
	 * Recupera descricao tipo posse.
	 * 
	 * @return descricao tipo posse
	 */
	public String getDescricaoTipoPosse() {
		return descricaoTipoPosse;
	}

	/**
	 * Preenche descricao tipo posse.
	 * 
	 * @param descricaoTipoPosse
	 *            o novo descricao tipo posse
	 */
	public void setDescricaoTipoPosse(String descricaoTipoPosse) {
		this.descricaoTipoPosse = descricaoTipoPosse;
	}

	/**
	 * Recupera area.
	 * 
	 * @return area
	 */
	public BigDecimal getArea() {
		return area;
	}

	/**
	 * Preenche area.
	 * 
	 * @param area
	 *            o novo area
	 */
	public void setArea(BigDecimal area) {
		this.area = area;
	}

}