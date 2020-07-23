/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum para os estados civis.
 * @author erico.junior
 */
public enum EstadoCivilEnum {

	/** O atributo SOLTEIRO. */
	SOLTEIRO((short)1, "SOLTEIRO(A)"),
	
	/** O atributo CASADO. */
	CASADO((short)2, "CASADO(A)"),
	
	/** O atributo VIUVO. */
	VIUVO((short)3, "VIÚVO(A)"),
	
	/** O atributo DIVORCIADO. */
	DIVORCIADO((short)5, "DIVORCIADO(A)"),
	
	/** O atributo SEPARADO. */
	SEPARADO((short)6, "SEPARADO(A)"),
	
	/** O atributo UNIAO_ESTAVEL. */
	UNIAO_ESTAVEL((short)7 ,"UNIÃO ESTÁVEL");

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Construtor do Enum.
	 * @param codigo O código do estado civil.
	 * @param descricao A descrição do estado civil.
	 */
	private EstadoCivilEnum(Short codigo, String descricao) {
		this.descricao = descricao;
		this.codigo = codigo;
	}
	
	/**
	 * Verifica se é Conjoge ou UniaoEstave.
	 * 
	 * @param codigo
	 *            the codigo
	 * @return true, se for um dos dois
	 */
	public static boolean isConjugeUniaoEstavel(Short codigo) {
		return CASADO.getCodigo().equals(codigo) || UNIAO_ESTAVEL.getCodigo().equals(codigo);
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
}
