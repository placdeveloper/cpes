/*
 * SICOOB
 * 
 * DetalheAnotacaoDTO.java(br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * Classe que representa os detalhes das anotações.
 * @author Erico.Junior
 */
public abstract class DetalheAnotacaoDTO extends BancoobDto {

	/** Serial UID. */
	private static final long serialVersionUID = -6576614697496847831L;
	
	/** O atributo data ocorrencia. */
	private DateTime dataOcorrencia;
	
	/** O atributo valor ocorrencia. */
	private BigDecimal valorOcorrencia;

	/**
	 * @return the dataOcorrencia
	 */
	public DateTime getDataOcorrencia() {
		return dataOcorrencia;
	}

	/**
	 * @param dataOcorrencia the dataOcorrencia to set
	 */
	public void setDataOcorrencia(DateTime dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

	/**
	 * @return the valorOcorrencia
	 */
	public BigDecimal getValorOcorrencia() {
		return valorOcorrencia;
	}

	/**
	 * @param valorOcorrencia the valorOcorrencia to set
	 */
	public void setValorOcorrencia(BigDecimal valorOcorrencia) {
		this.valorOcorrencia = valorOcorrencia;
	}

}
