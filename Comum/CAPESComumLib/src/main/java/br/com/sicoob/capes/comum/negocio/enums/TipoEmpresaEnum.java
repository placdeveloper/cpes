/*
 * SICOOB
 * 
 * TipoEmailEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoEmailEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;


/**
 * Enum dos tipos de empresa. São eles:
 *	<pre>
 *	1 - MICRO_EMPRESA
 *	2 - MICRO_EMPRESA_NAO_SIMPLES
 *	3 - PEQUENA_EMPRESA
 *	4 - MEDIA_EMPRESA
 *	5 - GRANDE_EMPRESA
 *	6 - PEQUENA_EMPRESA_NAO_SIMPLES
 *  </pre>
 * 
 * @author kelisson.leite
 */
public enum TipoEmpresaEnum {

	/** O atributo MICRO_EMPRESA. */
	MICRO_EMPRESA((short) 1, "MICRO-EMPRESA OPTANTE PELO SIMPLES"),
	
	/** O atributo MICRO_EMPRESA_NAO_SIMPLES. */
	MICRO_EMPRESA_NAO_SIMPLES((short) 2, "MICRO-EMPRESA NÃO OPTANTE PELO SIMPLES"),
	
	/** O atributo PEQUENA_EMPRESA. */
	PEQUENA_EMPRESA((short) 3, "PEQUENA EMPRESA OPTANTE PELO SIMPLES"),
	
	/** O atributo MEDIA_EMPRESA. */
	MEDIA_EMPRESA((short) 4, "MÉDIA EMPRESA"),

	/** O atributo GRANDE_EMPRESA. */
	GRANDE_EMPRESA((short) 5, "GRANDE EMPRESA"),
	
	/** O atributo PEQUENA_EMPRESA_NAO_SIMPLES. */
	PEQUENA_EMPRESA_NAO_SIMPLES((short) 6, "PEQUENA EMPRESA NÃO OPTANTE PELO SIMPLES");
	
	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;
	
	
	private TipoEmpresaEnum(Short codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * Recupera codigo.
	 * 
	 * @return codigo
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
	 * Value of.
	 * 
	 * @param codigo
	 *            the codigo
	 * @return tipo pessoa enum
	 */
	public static TipoEmpresaEnum valueOf(Short codigo) {
		TipoEmpresaEnum[] tipos = TipoEmpresaEnum.values();
		for (TipoEmpresaEnum tipoEmpresaEnum : tipos) {
			if(tipoEmpresaEnum.getCodigo().equals(codigo)) {
				return tipoEmpresaEnum;
			}
		}
		throw new IllegalArgumentException("Nao existe enum com o codigo informado: " + codigo);
	}
	
}
