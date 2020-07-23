/*
 * SICOOB
 * 
 * AtualizacaoRiscoClienteDTO.java(br.com.sicoob.capes.comum.negocio.dto.AtualizacaoRiscoClienteDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Rodrigo.Chaves
 */
public class AtualizacaoRiscoClienteDTO extends BancoobDto {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -3023672813640170718L;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	/** O atributo num cliente. */
	private Integer numCliente;
	
	/** O atributo motivo risco. */
	private String motivoRisco;
	
	/** O atributo id nivel risco. */
	private String idNivelRisco;

	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Preenche id instituicao.
	 * 
	 * @param idInstituicao
	 *            o novo id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera num cliente.
	 * 
	 * @return num cliente
	 */
	public Integer getNumCliente() {
		return numCliente;
	}

	/**
	 * Preenche num cliente.
	 * 
	 * @param numCliente
	 *            o novo num cliente
	 */
	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}

	/**
	 * Recupera motivo risco.
	 * 
	 * @return motivo risco
	 */
	public String getMotivoRisco() {
		return motivoRisco;
	}

	/**
	 * Preenche motivo risco.
	 * 
	 * @param motivoRisco
	 *            o novo motivo risco
	 */
	public void setMotivoRisco(String motivoRisco) {
		this.motivoRisco = motivoRisco;
	}

	/**
	 * Recupera id nivel risco.
	 * 
	 * @return id nivel risco
	 */
	public String getIdNivelRisco() {
		return idNivelRisco;
	}

	/**
	 * Preenche id nivel risco.
	 * 
	 * @param idNivelRisco
	 *            o novo id nivel risco
	 */
	public void setIdNivelRisco(String idNivelRisco) {
		this.idNivelRisco = idNivelRisco;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "[idNivelRisco=" + getIdNivelRisco() + "], [motivoRisco="
				+ getMotivoRisco() + "], [idInstituicao=" + getIdInstituicao()
				+ "], [numCliente=" + getNumCliente() + "]";
	}
	
	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		System.out.println(new AtualizacaoRiscoClienteDTO());
	}
}
