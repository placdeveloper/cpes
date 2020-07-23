package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe AnotacaoPeriodoVO.
 */
public class AnotacaoPeriodoVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -1515485515947142014L;

	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo provaVida. */
	private Boolean provaVida;

	/**
	 * Instancia um novo AnotacaoPeriodoVO.
	 */
	public AnotacaoPeriodoVO() {

	}

	/**
	 * Instancia um novo AnotacaoPeriodoVO.
	 *
	 * @param cpfCnpj o valor de cpf cnpj
	 * @param provaVida o valor de prova vida
	 */
	public AnotacaoPeriodoVO(String cpfCnpj, Boolean provaVida) {
		this.cpfCnpj = cpfCnpj;
		this.provaVida = provaVida;
	}

	/**
	 * Instancia um novo AnotacaoPeriodoVO.
	 *
	 * @param cpfCnpj o valor de cpf cnpj
	 */
	public AnotacaoPeriodoVO(String cpfCnpj) {
		this(cpfCnpj, null);
	}

	/**
	 * Recupera o valor de cpfCnpj.
	 *
	 * @return o valor de cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * Define o valor de cpfCnpj.
	 *
	 * @param cpfCnpj o novo valor de cpfCnpj
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Recupera o valor de provaVida.
	 *
	 * @return o valor de provaVida
	 */
	public Boolean getProvaVida() {
		return provaVida;
	}

	/**
	 * Define o valor de provaVida.
	 *
	 * @param provaVida o novo valor de provaVida
	 */
	public void setProvaVida(Boolean provaVida) {
		this.provaVida = provaVida;
	}

}