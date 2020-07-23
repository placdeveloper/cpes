package br.com.sicoob.capes.comum.negocio.enums;

/**
 * O Enum TipoInformacaoEnum.
 */
public enum TipoInformacaoEnum {
	
	/** O atributo INFORMACAO_PROFISSIONAL. */
	INFORMACAO_PROFISSIONAL ((short)1, "INFORMAÇÕES PROFISSIONAIS"),
	
	/** O atributo ENDERECO. */
	ENDERECO				((short)2, "ENDEREÇO"),
	
	/** O atributo TELEFONE. */
	TELEFONE				((short)3, "TELEFONE"),
	
	/** O atributo EMAIL. */
	EMAIL					((short)4, "EMAIL"),
	
	/** O atributo FONTE_RENDA. */
	FONTE_RENDA				((short)5, "RENDA"),
	
	/** O atributo BEM. */
	BEM						((short)6, "BEM"),
	
	/** O atributo PAC. */
	PAC						((short)7, "PAC"),
	
	/** O atributo PESSOA. */
	PESSOA					((short)8, "PESSOA");
	
	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Instancia um novo TipoInformacaoEnum.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private TipoInformacaoEnum(Short codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * Recupera o valor de codigo.
	 *
	 * @return o valor de codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * Recupera o valor de descricao.
	 *
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Value of.
	 *
	 * @param codigo o valor de codigo
	 * @return TipoInformacaoEnum
	 */
	public static TipoInformacaoEnum valueOf(Short codigo) {
		TipoInformacaoEnum retorno = null;
		for (TipoInformacaoEnum tipoInformacao : values()) {
			if (tipoInformacao.getCodigo().equals(codigo)) {
				return tipoInformacao;
			}
		}
		return retorno;
	}
}