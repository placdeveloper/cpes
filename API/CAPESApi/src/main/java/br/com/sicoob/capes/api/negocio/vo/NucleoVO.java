package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe NucleoVO.
 */
public class NucleoVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -7108221804662066143L;

	/** O atributo id. */
	private Integer id;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo numero. */
	private Integer numero;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Instancia um novo NucleoVO.
	 */
	public NucleoVO() {

	}

	/**
	 * Instancia um novo NucleoVO.
	 *
	 * @param id o valor de id
	 * @param idInstituicao o valor de id instituicao
	 * @param numero o valor de numero
	 * @param descricao o valor de descricao
	 */
	public NucleoVO(Integer id, Integer idInstituicao, Integer numero, String descricao) {
		this.id = id;
		this.idInstituicao = idInstituicao;
		this.numero = numero;
		this.descricao = descricao;
	}

	/**
	 * Recupera o valor de id.
	 *
	 * @return o valor de id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Define o valor de id.
	 *
	 * @param id o novo valor de id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera o valor de numero.
	 *
	 * @return o valor de numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * Define o valor de numero.
	 *
	 * @param numero o novo valor de numero
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
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

}