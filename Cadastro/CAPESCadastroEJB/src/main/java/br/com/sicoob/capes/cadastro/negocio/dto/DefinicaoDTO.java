/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * DTO utilizado para as definições do detalhe.
 */
public class DefinicaoDTO extends BancoobDto {

	/** Serial UID.*/
	private static final long serialVersionUID = 44346081865295194L;
	
	/** O atributo cabecalho. */
	private String cabecalho;
	
	/** O atributo nomeAtributo. */
	private String nomeAtributo;
	
	/** O atributo largura. */
	private int largura;
	
	/** O atributo tipo. */
	private int tipo;
	
	/**
	 * @return the cabecalho
	 */
	public String getCabecalho() {
		return cabecalho;
	}
	/**
	 * @param cabecalho the cabecalho to set
	 */
	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}
	/**
	 * @return the nomeAtributo
	 */
	public String getNomeAtributo() {
		return nomeAtributo;
	}
	/**
	 * @param nomeAtributo the nomeAtributo to set
	 */
	public void setNomeAtributo(String nomeAtributo) {
		this.nomeAtributo = nomeAtributo;
	}
	/**
	 * @return the largura
	 */
	public int getLargura() {
		return largura;
	}
	/**
	 * @param largura the largura to set
	 */
	public void setLargura(int largura) {
		this.largura = largura;
	}
	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
