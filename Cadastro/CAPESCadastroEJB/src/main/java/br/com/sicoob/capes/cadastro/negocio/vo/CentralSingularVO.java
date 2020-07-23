package br.com.sicoob.capes.cadastro.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe CentralSingularVO.
 */
public class CentralSingularVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 8685011145926158109L;

	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo numeroCooperativa. */
	private Integer numeroCooperativa;

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
	 * Recupera o valor de numeroCooperativa.
	 *
	 * @return o valor de numeroCooperativa
	 */
	public Integer getNumeroCooperativa() {
		return numeroCooperativa;
	}

	/**
	 * Define o valor de numeroCooperativa.
	 *
	 * @param numeroCooperativa o novo valor de numeroCooperativa
	 */
	public void setNumeroCooperativa(Integer numeroCooperativa) {
		this.numeroCooperativa = numeroCooperativa;
	}

}