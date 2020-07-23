/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum para o tipo de aplicação da anotação.
 * As opções são: Pessoa Física, Pessoa Jurídica e Ambas.
 * @author Erico.Junior
 */
public enum AplicacaoAnotacaoEnum {

	/** O atributo PESSOA_FISICA. */
	PESSOA_FISICA((short)1, "Pessoa Física"),
	
	/** O atributo PESSOA_JURIDICA. */
	PESSOA_JURIDICA((short)2, "Pessoa Jurídica"),
	
	/** O atributo AMBAS. */
	AMBAS((short)3, "Ambas");

	/** O atributo idAplicacao. */
	private Short idAplicacao;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Construtor da Enum.
	 * @param idAplicacao O identificador da aplicação.
	 * @param descricao A descrição da aplicação.
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
