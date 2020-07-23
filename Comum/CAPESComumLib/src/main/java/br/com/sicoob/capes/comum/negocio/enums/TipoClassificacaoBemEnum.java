package br.com.sicoob.capes.comum.negocio.enums;

/**
 * Enum que representa o tipo de classificação do bem. (Imóvel ou móvel)
 * 
 * @author Bruno.Carneiro
 */
public enum TipoClassificacaoBemEnum {

	/** O atributo BEM_IMOVEL. */
	BEM_IMOVEL((short) 1, "Bem Imóvel"),

	/** O atributo BEM_MOVEL. */
	BEM_MOVEL((short) 2, "Bem Móvel");

	/** O atributo codigo. */
	private Short codigo;

	/** O atributo descricao. */
	private String descricao;

	/**
	 * Método Construtor.
	 *
	 * @param codigo
	 *            o valor de codigo
	 * @param descricao
	 *            o valor de descricao
	 */
	private TipoClassificacaoBemEnum(Short codigo, String descricao) {
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
	 * @return TipoClassificacaoBemEnum
	 */
	public static TipoClassificacaoBemEnum obterPorCodigo(Short codigo) {
		for (TipoClassificacaoBemEnum tipoClassificacao : values()) {
			if (tipoClassificacao.codigo.equals(codigo)) {
				return tipoClassificacao;
			}
		}
		return null;
	}

	/**
	 * Verifica se o código informado é do tipo imóvel.
	 *
	 * @param codigo
	 *            o valor de codigo
	 * @return {@code true}, se for imovel
	 */
	public static boolean isImovel(Short codigo) {
		return BEM_IMOVEL.getCodigo().equals(codigo);
	}

}