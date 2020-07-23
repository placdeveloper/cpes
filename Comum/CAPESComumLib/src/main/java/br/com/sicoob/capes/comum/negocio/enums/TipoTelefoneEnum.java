/*
 * SICOOB
 * 
 * TipoTelefoneEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;


/**
 * @author Erico.Junior
 *
 */
public enum TipoTelefoneEnum {

	/**
	 * Utilizar RESIDENCIAL
	 */
	@Deprecated
	PARTICULAR((short)0, "Particular"),
	
	/** O atributo RESIDENCIAL. */
	RESIDENCIAL((short)0, "Residencial"),
	
	/** O atributo COMERCIAL. */
	COMERCIAL((short)1, "Comercial"),
	
	/** O atributo OUTROS. */
	OUTROS((short)2, "Outros"),
	
	/** O atributo INTERNACIONAL. */
	INTERNACIONAL((short)3, "Internacional"),
	
	/** O atributo RECADO. */
	RECADO((short)4, "Recado"),
	
	/** O atributo FAX. */
	FAX((short)5, "Fax"),
	
	/** O atributo CELULAR. */
	CELULAR((short)6, "Celular");

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	/**
	 * Construtor do Enum.
	 * @param codigo O identificador do tipo de telefone.
	 * @param descricao A descrição do tipo de telefone.
	 */
	private TipoTelefoneEnum(Short codigo, String descricao) {
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
	 * @param codigo O código do tipo de telefone.
	 * @return o enum a partir do código informado.
	 */
	public static TipoTelefoneEnum valueOf(Short codigo){
		
		TipoTelefoneEnum[] tipos = TipoTelefoneEnum.values();
		
		for (TipoTelefoneEnum tipoTelefone : tipos) {
			if(tipoTelefone.getCodigo().equals(codigo)) {
				return tipoTelefone;
			}
		}
		throw new IllegalArgumentException("Nao existe enum com o codigo informado: " + codigo);
	}	
	
}
