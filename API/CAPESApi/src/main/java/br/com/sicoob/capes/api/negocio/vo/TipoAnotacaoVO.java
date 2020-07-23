package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe TipoAnotacaoVO.
 */
public class TipoAnotacaoVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -5258711438328720847L;

	/** O atributo codigo. */
	private Integer codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/** O atributo categoria. */
	private String categoria;

	/** O atributo tipoCaptura. */
	private String tipoCaptura;
	
	/** O atributo periocidade. */
	private String periocidade;

	/**
	 * Instancia um novo TipoAnotacaoVO.
	 */
	public TipoAnotacaoVO() {

	}

	/**
	 * Instancia um novo TipoAnotacaoVO.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 * @param categoria o valor de categoria
	 * @param tipoCaptura o valor de tipo captura
	 * @param periocidade o valor de periocidade
	 */
	public TipoAnotacaoVO(Integer codigo, String descricao, String categoria, String tipoCaptura, String periocidade) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.tipoCaptura = tipoCaptura;
		this.periocidade = periocidade;
	}

	/**
	 * Recupera o valor de codigo.
	 *
	 * @return o valor de codigo
	 */
	public Integer getCodigo() {
		return codigo;
	}

	/**
	 * Define o valor de codigo.
	 *
	 * @param codigo o novo valor de codigo
	 */
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	/**
	 * Recupera o valor de descricao.
	 *
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Define o valor de descricao.
	 *
	 * @param descricao o novo valor de descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Recupera o valor de categoria.
	 *
	 * @return o valor de categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Define o valor de categoria.
	 *
	 * @param categoria o novo valor de categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Recupera o valor de tipoCaptura.
	 *
	 * @return o valor de tipoCaptura
	 */
	public String getTipoCaptura() {
		return tipoCaptura;
	}

	/**
	 * Define o valor de tipoCaptura.
	 *
	 * @param tipoCaptura o novo valor de tipoCaptura
	 */
	public void setTipoCaptura(String tipoCaptura) {
		this.tipoCaptura = tipoCaptura;
	}

	/**
	 * Recupera o valor de periocidade.
	 *
	 * @return o valor de periocidade
	 */
	public String getPeriocidade() {
		return periocidade;
	}

	/**
	 * Define o valor de periocidade.
	 *
	 * @param periocidade o novo valor de periocidade
	 */
	public void setPeriocidade(String periocidade) {
		this.periocidade = periocidade;
	}

}