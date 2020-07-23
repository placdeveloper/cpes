/*
 * SICOOB
 * 
 * FuncionalidadeValidacaoCadastralEnum.java(br.com.sicoob.capes.comum.negocio.enums.FuncionalidadeValidacaoCadastralEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * The Enum FuncionalidadeValidacaoCadastralEnum.
 */
public enum FuncionalidadeValidacaoCadastralEnum {
	
	/** O atributo BEM. */
	BEM("Bem"),
	
	/** O atributo CERTIDAO. */
	CERTIDAO("Certidão"),
	
	/** O atributo EMAIL. */
	EMAIL("E-Mail"),
	
	/** O atributo ENDERECO. */
	ENDERECO("Endereço"),
	
	/** O atributo FONTE_RENDA. */
	FONTE_RENDA("Fonte de Renda"),
	
	/** O atributo INFO_PROFISSIONAL. */
	INFO_PROFISSIONAL("Informação Profissional"),
	
	/** O atributo PESSOA. */
	PESSOA("Pessoa"),
	
	/** O atributo PESSOA_INSTITUICAO. */
	PESSOA_INSTITUICAO("Dados do Cliente"),
	
	/** O atributo PRODUTOR. */
	PRODUTOR("Produtor"),
	
	/** O atributo REFERENCIA. */
	REFERENCIA("Referência"),
	
	/** O atributo RELACIONAMENTO. */
	RELACIONAMENTO("Relacionamento"),
	
	/** O atributo TELEFONE. */
	TELEFONE("Telefone"),
	
	/** O atributo TRIBUTACAO. */
	TRIBUTACAO("Tributação");

	/** O atributo descricao. */
	private String descricao;

	/**
	 * Cria uma nova instância de funcionalidade validacao cadastral enum.
	 * 
	 * @param descricao
	 *            the descricao
	 */
	private FuncionalidadeValidacaoCadastralEnum(String descricao) {

		this.descricao = descricao;
	}

	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {

		return descricao;
	}

}
