/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Este VO representa uma mensagem (JMS) utilizada no CAPES para atualizações cadastrais e de anotações.
 * @author Erico.Junior
 */
public class MensagemVO implements Serializable {

	/** Serial UID.*/
	private static final long serialVersionUID = 727455533017431501L;

	/** O atributo dataHora. */
	private Date dataHora;
	
	/** O atributo conteudo. */
	private String conteudo;

	/**
	 * @return the dataHora
	 */
	public Date getDataHora() {
		return dataHora;
	}

	/**
	 * @param dataHora the dataHora to set
	 */
	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	/**
	 * @return the conteudo
	 */
	public String getConteudo() {
		return conteudo;
	}

	/**
	 * @param conteudo the conteudo to set
	 */
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
		
}
