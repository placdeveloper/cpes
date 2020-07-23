package br.com.sicoob.capes.comum.negocio.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * A Classe RiscoVO.
 *
 * @author Bruno.Carneiro
 */
public class RiscoVO extends BancoobVo {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -4368579292861200023L;

	/** O atributo dataEnquadramentoRisco. */
	private DateTimeDB dataEnquadramentoRisco;

	/** O atributo nivelRisco. */
	private String nivelRisco;

	/** O atributo notaRiscoCRL. */
	private String notaRiscoCRL;

	/** O atributo valorPD. */
	private BigDecimal valorPD;

	/**
	 * Recupera o valor de dataEnquadramentoRisco.
	 *
	 * @return o valor de dataEnquadramentoRisco
	 */
	public DateTimeDB getDataEnquadramentoRisco() {
		return dataEnquadramentoRisco;
	}

	/**
	 * Define o valor de dataEnquadramentoRisco.
	 *
	 * @param dataEnquadramentoRisco
	 *            o novo valor de dataEnquadramentoRisco
	 */
	public void setDataEnquadramentoRisco(DateTimeDB dataEnquadramentoRisco) {
		this.dataEnquadramentoRisco = dataEnquadramentoRisco;
	}

	/**
	 * Recupera o valor de nivelRisco.
	 *
	 * @return o valor de nivelRisco
	 */
	public String getNivelRisco() {
		return nivelRisco;
	}

	/**
	 * Define o valor de nivelRisco.
	 *
	 * @param nivelRisco
	 *            o novo valor de nivelRisco
	 */
	public void setNivelRisco(String nivelRisco) {
		this.nivelRisco = nivelRisco;
	}

	/**
	 * Recupera o valor de notaRiscoCRL.
	 *
	 * @return o valor de notaRiscoCRL
	 */
	public String getNotaRiscoCRL() {
		return notaRiscoCRL;
	}

	/**
	 * Define o valor de notaRiscoCRL.
	 *
	 * @param notaRiscoCRL
	 *            o novo valor de notaRiscoCRL
	 */
	public void setNotaRiscoCRL(String notaRiscoCRL) {
		this.notaRiscoCRL = notaRiscoCRL;
	}

	/**
	 * Recupera o valor de valorPD.
	 *
	 * @return o valor de valorPD
	 */
	public BigDecimal getValorPD() {
		return valorPD;
	}

	/**
	 * Define o valor de valorPD.
	 *
	 * @param valorPD
	 *            o novo valor de valorPD
	 */
	public void setValorPD(BigDecimal valorPD) {
		this.valorPD = valorPD;
	}

}