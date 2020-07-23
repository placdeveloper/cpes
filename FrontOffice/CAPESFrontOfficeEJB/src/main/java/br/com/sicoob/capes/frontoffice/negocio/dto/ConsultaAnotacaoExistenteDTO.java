/*
 * SICOOB
 * 
 * ConsultaAnotacaoExistenteDTO.java(br.com.sicoob.capes.frontoffice.negocio.dto.ConsultaAnotacaoExistenteDTO)
 */
package br.com.sicoob.capes.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

/**
 * The Class ConsultaAnotacaoExistenteDTO.
 */
public class ConsultaAnotacaoExistenteDTO extends BancoobDto {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 5378382778684355069L;

	/**
	 * <pre>
	 * Atributo booleano:
	 * 0 -> {@code false} 
	 * 1 -> {@code true}
	 * </pre>
	 */
	@AtributoRetorno(posicao = 1)
	private Integer anotacaoExistente = 0;

	/**
	 * Cria uma nova instância de consulta anotacao existente dto.
	 * 
	 * @param anotacaoExistente
	 *            the anotacao existente
	 */
	public ConsultaAnotacaoExistenteDTO(boolean anotacaoExistente) {
		setAnotacaoExistente(anotacaoExistente);
	}

	/**
	 * Recupera anotacao existente.
	 * 
	 * @return anotacao existente
	 */
	public Integer getAnotacaoExistente() {

		return anotacaoExistente;
	}
	
	/**
	 * Verifica se é anotacao existente.
	 * 
	 * @return true, se for anotacao existente
	 */
	public boolean isAnotacaoExistente() {
		
		return (anotacaoExistente != null) && anotacaoExistente.equals(1);
	}

	/**
	 * Preenche anotacao existente.
	 * 
	 * @param anotacaoExistente
	 *            o novo anotacao existente
	 */
	public void setAnotacaoExistente(Integer anotacaoExistente) {

		this.anotacaoExistente = anotacaoExistente;
	}

	/**
	 * Preenche anotacao existente.
	 * 
	 * @param anotacaoExistente
	 *            o novo anotacao existente
	 */
	public void setAnotacaoExistente(boolean anotacaoExistente) {

		this.anotacaoExistente = anotacaoExistente ? 1 : 0;
	}

}
