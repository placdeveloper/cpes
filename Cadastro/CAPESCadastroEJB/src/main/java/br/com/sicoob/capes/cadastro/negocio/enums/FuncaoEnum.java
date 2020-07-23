/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * @author juan.damasceno
 */
public enum FuncaoEnum {

	/** O atributo GERENTE. */
	GERENTE((short)2, "Gerente");

	/** O atributo idTipoFuncao. */
	private Short idTipoFuncao;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Instancia um novo FuncaoEnum.
	 *
	 * @param idTipoFuncao o valor de id tipo funcao
	 * @param descricao o valor de descricao
	 */
	private FuncaoEnum(Short idTipoFuncao, String descricao) {
		this.idTipoFuncao = idTipoFuncao;
		this.descricao = descricao;
	}

	/**
	 * @return the idTipoAplicacao
	 */
	public Short getIdTipoFuncao() {
		return idTipoFuncao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	
}
