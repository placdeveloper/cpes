/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * @author Erico.Junior
 *
 */
public enum NacionalidadeEnum {

	/** O atributo BRASILEIRA. */
	BRASILEIRA((short)10, "Brasileira"),
	
	/** O atributo OUTRAS. */
	OUTRAS((short)50, "Outras");

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo NacionalidadeEnum.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private NacionalidadeEnum(Short codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Verifica se eh brasileira.
	 *
	 * @param codigo o valor de codigo
	 * @return {@code true}, se for brasileira
	 */
	public static boolean isBrasileira(Short codigo) {
		return BRASILEIRA.getCodigo().equals(codigo);
	}
}