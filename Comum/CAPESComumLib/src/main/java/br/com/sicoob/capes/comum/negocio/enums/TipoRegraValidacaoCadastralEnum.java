/*
 * SICOOB
 * 
 * TipoRegraValidacaoCadastralEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoRegraValidacaoCadastralEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * The Enum TipoRegraValidacaoCadastralEnum.
 */
public enum TipoRegraValidacaoCadastralEnum {

	/** O atributo I. */
	I("Informativa"),
	
	/** O atributo R. */
	R("Restritiva");
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Cria uma nova instância de tipo regra validacao cadastral enum.
	 * 
	 * @param descricao
	 *            the descricao
	 */
	private TipoRegraValidacaoCadastralEnum(String descricao) {

		this.descricao = descricao;
	}

	/**
	 * Recupera codigo que representa o enum.
	 * 
	 * @return o codigo
	 */
	public Character getCodigo() {
		return name().charAt(0);
	}
	
	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
	
		return descricao;
	}

	/**
	 * Recupera o enum equivalente ao {@code codigo}.
	 * 
	 * @param codigo
	 *            o codigo do enum
	 * @return o enum
	 */
	public static TipoRegraValidacaoCadastralEnum valueOf(Character codigo) {
		
		TipoRegraValidacaoCadastralEnum value = null;
		if (codigo != null) {
			value = TipoRegraValidacaoCadastralEnum.valueOf(String.valueOf(codigo));
		}
		return value;
	}
}
