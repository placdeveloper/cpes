/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * @author erico.junior
 *
 */
public enum DestinoExibicaoMensagemEnum {

	/** O atributo INTERNA. */
	INTERNA((short)0, "Interna"),
	
	/** O atributo CONSULTAS_CLIENTE. */
	CONSULTAS_CLIENTE((short)1, "Nas Consultas Efetuadas pelo Cliente"),
	
	/** O atributo AMBAS. */
	AMBAS((short)2, "Ambas");
	
	/** O atributo codigoDestino. */
	private Short codigoDestino;
	
	/** O atributo descricaoDestino. */
	private String descricaoDestino;
	
	/**
	 * Instancia um novo DestinoExibicaoMensagemEnum.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private DestinoExibicaoMensagemEnum(Short codigo, String descricao) {
		this.descricaoDestino = descricao;
		this.codigoDestino = codigo;
	}

	/**
	 * Recupera o valor de codigoDestino.
	 *
	 * @return o valor de codigoDestino
	 */
	public Short getCodigoDestino() {
		return codigoDestino;
	}

	/**
	 * Recupera o valor de descricaoDestino.
	 *
	 * @return o valor de descricaoDestino
	 */
	public String getDescricaoDestino() {
		return descricaoDestino;
	}	
	
	/**
	 * Value of destino exibicao.
	 *
	 * @param codigo o valor de codigo
	 * @return DestinoExibicaoMensagemEnum
	 */
	public static DestinoExibicaoMensagemEnum valueOfDestinoExibicao(short codigo) {
		for (DestinoExibicaoMensagemEnum value : values()) {
			if (value.codigoDestino.equals(codigo)) {
				return value;
			}
		}
		throw new IllegalArgumentException("Codigo destino exibição inválido [" + codigo + "]");
	}
}
