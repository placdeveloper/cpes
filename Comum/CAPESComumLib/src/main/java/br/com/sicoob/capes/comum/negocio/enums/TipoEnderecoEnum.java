/*
 * SICOOB
 * 
 * TipoEnderecoEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * Enum para os tipos de endereço. 
 * @author erico.junior
 */
public enum TipoEnderecoEnum {

	/** O atributo RESIDENCIAL. */
	RESIDENCIAL((short)0, "Residencial"),
	
	/** O atributo COMERCIAL. */
	COMERCIAL((short)1, "Comercial"),
	
	/** O atributo OUTROS. */
	OUTROS((short)2, "Outros"),
	
	/** O atributo CAIXA_POSTAL. */
	CAIXA_POSTAL((short)3, "Caixa Postal");

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Construtor do Enum.
	 * @param codigo O identificador do tipo de endereço.
	 * @param descricao A descrição do tipo de endereço.
	 */
	private TipoEnderecoEnum(Short codigo, String descricao) {
		this.descricao = descricao;
		this.codigo = codigo;
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
	 * Recupera o enum a partir do código informado.
	 * 
	 * @param codigo
	 *            O código do tipo de endereço.
	 * @return o enum a partir do código informado.
	 */
	public static TipoEnderecoEnum valueOf(Short codigo){
		
		TipoEnderecoEnum[] tipos = TipoEnderecoEnum.values();
		for (TipoEnderecoEnum tipoEnderecoEnum : tipos) {
			if(tipoEnderecoEnum.getCodigo().equals(codigo)) {
				return tipoEnderecoEnum;
			}
		}
		throw new IllegalArgumentException("Nao existe enum com o codigo informado: " + codigo);
	}	
}
