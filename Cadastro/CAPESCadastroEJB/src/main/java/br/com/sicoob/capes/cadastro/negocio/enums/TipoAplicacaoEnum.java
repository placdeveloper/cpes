/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum utilizado para a aplica��o dos tipos de anota��es a pessoas f�sicas e ou jur�dicas. 
 * @author erico.junior
 */
public enum TipoAplicacaoEnum {

	/** O atributo PESSOA_FISICA. */
	PESSOA_FISICA((short)1,   "Pessoa F�sica"),
	
	/** O atributo PESSOA_JURIDICA. */
	PESSOA_JURIDICA((short)2, "Pessoa Jur�dica"),
	
	/** O atributo AMBAS. */
	AMBAS((short)3, "Ambas");

	/** O atributo idTipoAplicacao. */
	private Short idTipoAplicacao;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Construtor do Enum.
	 * @param idTipoAplicacao O identificador do tipo de aplica��o.
	 * @param descricao A descri��o do tipo de aplica��o.
	 */
	private TipoAplicacaoEnum(Short idTipoAplicacao, String descricao) {
		this.idTipoAplicacao = idTipoAplicacao;
		this.descricao = descricao;
	}

	/**
	 * @return the idTipoAplicacao
	 */
	public Short getIdTipoAplicacao() {
		return idTipoAplicacao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Value of.
	 *
	 * @param codigo o valor de codigo
	 * @return TipoAplicacaoEnum
	 */
	public static TipoAplicacaoEnum valueOf(Short codigo) {
		for (TipoAplicacaoEnum value : values()) {
	        if (value.getIdTipoAplicacao().equals(codigo)) {
	        	return value;
	        }
        }
		throw new IllegalArgumentException(String.valueOf(codigo));
	}
}
