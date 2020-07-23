/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Erico.Junior
 *
 */
public class CargaAnotacoesConsultasExternasDTO extends BancoobDto {

	/** Serial UID.*/
	private static final long serialVersionUID = -1462191060062193404L;
	
	/** O atributo codigoCompartilhamento. */
	private Short codigoCompartilhamento;

	/**
	 * @return the codigoCompartilhamento
	 */
	public Short getCodigoCompartilhamento() {
		return codigoCompartilhamento;
	}

	/**
	 * @param codigoCompartilhamento the codigoCompartilhamento to set
	 */
	public void setCodigoCompartilhamento(Short codigoCompartilhamento) {
		this.codigoCompartilhamento = codigoCompartilhamento;
	}
	
}
