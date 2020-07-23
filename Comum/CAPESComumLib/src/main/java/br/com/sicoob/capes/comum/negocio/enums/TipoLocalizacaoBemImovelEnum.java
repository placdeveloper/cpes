package br.com.sicoob.capes.comum.negocio.enums;

/**
 * Enum que representa os tipos de localização do bem imóvel.
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
	 * Método construtor.
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
	 * Obtém o valor do código.
	 *
	 * @return o valor de codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * Obtém o valor da descrição.
	 *
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Obtém o enum correto a partir do código informado.
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
	 * Verifica se o código informado é o correspondente do Tipo URBANO.
	 *
	 * @param codigo
	 *            o valor de codigo
	 * @return {@code true}, se for urbano
	 */
	public static boolean isUrbano(Short codigo) {
		return URBANO.getCodigo().equals(codigo);
	}

}