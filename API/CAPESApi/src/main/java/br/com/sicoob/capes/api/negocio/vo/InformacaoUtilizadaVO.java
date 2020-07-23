package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe InformacaoUtilizadaVO.
 */
public class InformacaoUtilizadaVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -1080443019099257566L;

	/** O atributo codigoInformacao. */
	private Long codigoInformacao;
	
	/** O atributo codigoUtilizaInformacao. */
	private Short codigoUtilizaInformacao;
	
	/** O atributo codigoTipoInformacao. */
	private Short codigoTipoInformacao;

	/**
	 * Recupera o valor de codigoInformacao.
	 *
	 * @return o valor de codigoInformacao
	 */
	public Long getCodigoInformacao() {
		return codigoInformacao;
	}

	/**
	 * Define o valor de codigoInformacao.
	 *
	 * @param codigoInformacao o novo valor de codigoInformacao
	 */
	public void setCodigoInformacao(Long codigoInformacao) {
		this.codigoInformacao = codigoInformacao;
	}

	/**
	 * Recupera o valor de codigoUtilizaInformacao.
	 *
	 * @return o valor de codigoUtilizaInformacao
	 */
	public Short getCodigoUtilizaInformacao() {
		return codigoUtilizaInformacao;
	}

	/**
	 * Define o valor de codigoUtilizaInformacao.
	 *
	 * @param codigoUtilizaInformacao o novo valor de codigoUtilizaInformacao
	 */
	public void setCodigoUtilizaInformacao(Short codigoUtilizaInformacao) {
		this.codigoUtilizaInformacao = codigoUtilizaInformacao;
	}

	/**
	 * Recupera o valor de codigoTipoInformacao.
	 *
	 * @return o valor de codigoTipoInformacao
	 */
	public Short getCodigoTipoInformacao() {
		return codigoTipoInformacao;
	}

	/**
	 * Define o valor de codigoTipoInformacao.
	 *
	 * @param codigoTipoInformacao o novo valor de codigoTipoInformacao
	 */
	public void setCodigoTipoInformacao(Short codigoTipoInformacao) {
		this.codigoTipoInformacao = codigoTipoInformacao;
	}
}