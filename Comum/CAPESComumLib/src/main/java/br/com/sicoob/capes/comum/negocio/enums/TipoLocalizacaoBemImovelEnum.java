package br.com.sicoob.capes.comum.negocio.enums;

/**
 * Enum que representa os tipos de localiza��o do bem im�vel.
 * 
 * @author Bruno.Carneiro
 */
public enum TipoLocalizacaoBemImovelEnum {

	/** O atributo URBANO. */
	URBANO((short) 1, "Urbano"),

	/** O atributo RURAL. */
	RURAL((short) 2, "Rural");

	/** O atributo codigo. */
	private Short codigo;

	/** O atributo descricao. */
	private String descricao;

	/**
	 * M�todo construtor.
	 *
	 * @param codigo
	 *            o valor de codigo
	 * @param descricao
	 *            o valor de descricao
	 */
	private TipoLocalizacaoBemImovelEnum(Short codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	/**
	 * Obt�m o valor do c�digo.
	 *
	 * @return o valor de codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * Obt�m o valor da descri��o.
	 *
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Obt�m o enum correto a partir do c�digo informado.
	 *
	 * @param codigo
	 *            o valor de codigo
	 * @return TipoLocalizacaoBemImovelEnum
	 */
	public static TipoLocalizacaoBemImovelEnum obterPorCodigo(Short codigo) {
		for (TipoLocalizacaoBemImovelEnum tipoClassificacao : values()) {
			if (tipoClassificacao.codigo.equals(codigo)) {
				return tipoClassificacao;
			}
		}
		return null;
	}

	/**
	 * Verifica se o c�digo informado � o correspondente do Tipo URBANO.
	 *
	 * @param codigo
	 *            o valor de codigo
	 * @return {@code true}, se for urbano
	 */
	public static boolean isUrbano(Short codigo) {
		return URBANO.getCodigo().equals(codigo);
	}

}