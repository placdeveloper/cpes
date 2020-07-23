/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum para o tipo de aplica��o da anota��o.
 * As op��es s�o: Pessoa F�sica, Pessoa Jur�dica e Ambas.
 * @author Erico.Junior
 */
public enum AplicacaoAnotacaoEnum {

	/** O atributo PESSOA_FISICA. */
	PESSOA_FISICA((short)1, "Pessoa F�sica"),
	
	/** O atributo PESSOA_JURIDICA. */
	PESSOA_JURIDICA((short)2, "Pessoa Jur�dica"),
	
	/** O atributo AMBAS. */
	AMBAS((short)3, "Ambas");

	/** O atributo idAplicacao. */
	private Short idAplicacao;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Construtor da Enum.
	 * @param idAplicacao O identificador da aplica��o.
	 * @param descricao A descri��o da aplica��o.
	 */
	private AplicacaoAnotacaoEnum(Short idAplicacao, String descricao) {
		this.descricao = descricao;
		this.idAplicacao = idAplicacao;
	}

	/**
	 * @return the idAplicacao
	 */
	public Short getIdAplicacao() {
		return idAplicacao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

}
