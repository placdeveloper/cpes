/*
 * SICOOB
 * 
 * ProdutoEnum.java(br.com.sicoob.capes.comum.negocio.enums.ProdutoEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * The Enum ProdutoEnum.
 */
public enum ProdutoEnum {

	/** O atributo CCS. */
	CCS((short)41, "CCS");

	/** O atributo id. */
	private Short id;
	
	/** O atributo nome. */
	private String nome;

	/**
	 * Cria uma nova instância de produto enum.
	 * 
	 * @param idProduto
	 *            the id produto
	 * @param nomeProduto
	 *            the nome produto
	 */
	private ProdutoEnum(Short idProduto, String nomeProduto) {
		this.id = idProduto;
		this.nome = nomeProduto;
	}

	/**
	 * Recupera id.
	 * 
	 * @return id
	 */
	public Short getId() {
		return id;
	}

	/**
	 * Recupera nome.
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Value of.
	 * 
	 * @param codigo
	 *            the codigo
	 * @return produto enum
	 */
	public static ProdutoEnum valueOf(Short codigo) {
		for (ProdutoEnum enumm : ProdutoEnum.values()) {
			if (enumm.getId() == codigo.shortValue()) {
				return enumm;
			}
		}
		return null;
	}

}
