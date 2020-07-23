package br.com.sicoob.capes.relatorio.negocio.dto;

import java.io.Serializable;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * A Classe RelCompartilhamentoDTO.
 */
public class RelCompartilhamentoDTO extends BancoobDto implements Serializable {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** O atributo compartilhamento. */
	private Integer compartilhamento;
	
	/** O atributo dataInicio. */
	private DateTime dataInicio;
	
	/** O atributo dataFim. */
	private DateTime dataFim;
	
	/** O atributo numCentral. */
	private Integer numCentral;
	
	/** O atributo numSingular. */
	private Integer numSingular;
	
	/** O atributo numCoopLogada. */
	private Integer numCoopLogada;
	
	/**
	 * Recupera o valor de numCoopLogada.
	 *
	 * @return o valor de numCoopLogada
	 */
	public Integer getNumCoopLogada() {
		return numCoopLogada;
	}
	
	/**
	 * Define o valor de numCoopLogada.
	 *
	 * @param numCoopLogada o novo valor de numCoopLogada
	 */
	public void setNumCoopLogada(Integer numCoopLogada) {
		this.numCoopLogada = numCoopLogada;
	}
	
	/**
	 * Recupera o valor de compartilhamento.
	 *
	 * @return o valor de compartilhamento
	 */
	public Integer getCompartilhamento() {
		return compartilhamento;
	}
	
	/**
	 * Define o valor de compartilhamento.
	 *
	 * @param compartilhamento o novo valor de compartilhamento
	 */
	public void setCompartilhamento(Integer compartilhamento) {
		this.compartilhamento = compartilhamento;
	}
	
	/**
	 * Recupera o valor de dataInicio.
	 *
	 * @return o valor de dataInicio
	 */
	public DateTime getDataInicio() {
		return dataInicio;
	}
	
	/**
	 * Define o valor de dataInicio.
	 *
	 * @param dataInicio o novo valor de dataInicio
	 */
	public void setDataInicio(DateTime dataInicio) {
		this.dataInicio = dataInicio;
	}
	
	/**
	 * Recupera o valor de dataFim.
	 *
	 * @return o valor de dataFim
	 */
	public DateTime  getDataFim() {
		return dataFim;
	}
	
	/**
	 * Define o valor de dataFim.
	 *
	 * @param dataFim o novo valor de dataFim
	 */
	public void setDataFim(DateTime  dataFim) {
		this.dataFim = dataFim;
	}
	
	/**
	 * Recupera o valor de numCentral.
	 *
	 * @return o valor de numCentral
	 */
	public Integer getNumCentral() {
		return numCentral;
	}
	
	/**
	 * Define o valor de numCentral.
	 *
	 * @param numCentral o novo valor de numCentral
	 */
	public void setNumCentral(Integer numCentral) {
		this.numCentral = numCentral;
	}
	
	/**
	 * Recupera o valor de numSingular.
	 *
	 * @return o valor de numSingular
	 */
	public Integer getNumSingular() {
		return numSingular;
	}
	
	/**
	 * Define o valor de numSingular.
	 *
	 * @param numSingular o novo valor de numSingular
	 */
	public void setNumSingular(Integer numSingular) {
		this.numSingular = numSingular;
	}
}
