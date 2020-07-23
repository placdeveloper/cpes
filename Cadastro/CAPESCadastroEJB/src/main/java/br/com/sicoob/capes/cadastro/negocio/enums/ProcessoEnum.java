// 11/04/2013 - 15:18:39
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * @author Rodrigo.Chaves
 */
public enum ProcessoEnum {
	
	/** O atributo CAPES_AECD. */
	CAPES_AECD("CAPES-AECD", "CAPES - APROVA플O EXTERNA COM DOCUMENTO", true),
	
	/** O atributo CAPES_AESD. */
	CAPES_AESD("CAPES-AESD", "CAPES - APROVA플O EXTERNA SEM DOCUMENTO", false),
	
	/** O atributo CAPES_AICD. */
	CAPES_AICD("CAPES-AICD", "CAPES - APROVA플O INTERNA COM DOCUMENTO", true),
	
	/** O atributo CAPES_AISD. */
	CAPES_AISD("CAPES-AISD", "CAPES - APROVA플O INTERNA SEM DOCUMENTO", false);
	
	/** O atributo sigla. */
	private String sigla;
	
	/** O atributo nome. */
	private String nome;
	
	/** O atributo aceitaDocumento. */
	private Boolean aceitaDocumento;
	
	/**
	 * Construtor
	 * 
	 * @param sigla
	 * @param nome
	 * @param aceitaDocumento
	 */
	private ProcessoEnum(String sigla, String nome, Boolean aceitaDocumento) {
		this.sigla = sigla;
		this.nome = nome;
		this.aceitaDocumento = aceitaDocumento;
	}

	/**
	 * Recupera o valor de sigla.
	 *
	 * @return o valor de sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * Recupera o valor de nome.
	 *
	 * @return o valor de nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Recupera o valor de aceitaDocumento.
	 *
	 * @return o valor de aceitaDocumento
	 */
	public Boolean getAceitaDocumento() {
		return aceitaDocumento;
	}
	
	/**
	 * Value of sigla.
	 *
	 * @param sigla o valor de sigla
	 * @return ProcessoEnum
	 */
	public static ProcessoEnum valueOfSigla(String sigla) {
		for (ProcessoEnum value : values()) {
			if (value.sigla.equals(sigla)) {
				return value;
			}
		}
		throw new IllegalArgumentException("sigla[" + sigla + "]");
	}
}
