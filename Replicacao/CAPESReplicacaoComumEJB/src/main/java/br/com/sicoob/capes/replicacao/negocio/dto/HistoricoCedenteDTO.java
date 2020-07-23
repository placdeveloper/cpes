/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author erico.junior
 *
 */
public class HistoricoCedenteDTO extends BancoobDto {

	/** Serial UID.*/
	private static final long serialVersionUID = 4200728350202579947L;
	
	/** O atributo numCliente. */
	private Integer numCliente;

	/**
	 * @return the numCliente
	 */
	public Integer getNumCliente() {
		return numCliente;
	}
	/**
	 * @param numCliente the numCliente to set
	 */
	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}
}
