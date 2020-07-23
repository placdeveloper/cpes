package br.com.sicoob.capes.processamento.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe CooperativaVO.
 */
public class CooperativaVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -5720983378263427123L;

	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo numeroCooperativa. */
	private Integer numeroCooperativa;
	
	/** O atributo idUnidadeInst. */
	private Integer idUnidadeInst;
	
	/** O atributo numeroPac. */
	private Integer numeroPac;

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

	/**
	 * Recupera o valor de idUnidadeInst.
	 *
	 * @return o valor de idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * Define o valor de idUnidadeInst.
	 *
	 * @param idUnidadeInst o novo valor de idUnidadeInst
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * Recupera o valor de numeroPac.
	 *
	 * @return o valor de numeroPac
	 */
	public Integer getNumeroPac() {
		return numeroPac;
	}

	/**
	 * Define o valor de numeroPac.
	 *
	 * @param pac o novo valor de numeroPac
	 */
	public void setNumeroPac(Integer pac) {
		this.numeroPac = pac;
	}

}