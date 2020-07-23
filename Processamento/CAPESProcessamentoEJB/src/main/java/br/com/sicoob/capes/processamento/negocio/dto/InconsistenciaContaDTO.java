/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * @author Erico.Junior
 *
 */
public class InconsistenciaContaDTO extends BancoobDto {

	/** Serial UID.*/
	private static final long serialVersionUID = 7534232744672908914L;
	
	/** O atributo numErro. */
	private Integer numErro;
	
	/** O atributo dataProcessamento. */
	private DateTime dataProcessamento;
	
	/** O atributo numBeneficiario. */
	private String numBeneficiario;
	
	/** O atributo numTrabalhador. */
	private String numTrabalhador;
	
	/** O atributo codLoteOrigem. */
	private Integer codLoteOrigem;
	/**
	 * @return the numErro
	 */
	public Integer getNumErro() {
		return numErro;
	}
	/**
	 * @param numErro the numErro to set
	 */
	public void setNumErro(Integer numErro) {
		this.numErro = numErro;
	}
	/**
	 * @return the dataProcessamento
	 */
	public DateTime getDataProcessamento() {
		return dataProcessamento;
	}
	/**
	 * @param dataProcessamento the dataProcessamento to set
	 */
	public void setDataProcessamento(DateTime dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	/**
	 * @return the numBeneficiario
	 */
	public String getNumBeneficiario() {
		return numBeneficiario;
	}
	/**
	 * @param numBeneficiario the numBeneficiario to set
	 */
	public void setNumBeneficiario(String numBeneficiario) {
		this.numBeneficiario = numBeneficiario;
	}
	/**
	 * @return the numTrabalhador
	 */
	public String getNumTrabalhador() {
		return numTrabalhador;
	}
	/**
	 * @param numTrabalhador the numTrabalhador to set
	 */
	public void setNumTrabalhador(String numTrabalhador) {
		this.numTrabalhador = numTrabalhador;
	}
	/**
	 * @return the codLoteOrigem
	 */
	public Integer getCodLoteOrigem() {
		return codLoteOrigem;
	}
	/**
	 * @param codLoteOrigem the codLoteOrigem to set
	 */
	public void setCodLoteOrigem(Integer codLoteOrigem) {
		this.codLoteOrigem = codLoteOrigem;
	}
	
}
