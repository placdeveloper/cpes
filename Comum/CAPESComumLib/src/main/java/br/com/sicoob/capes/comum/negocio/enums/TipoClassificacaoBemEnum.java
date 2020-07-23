package br.com.sicoob.capes.comum.negocio.enums;

/**
 * Enum que representa o tipo de classifica��o do bem. (Im�vel ou m�vel)
 * 
 * @author Bruno.Carneiro
 */
public enum TipoClassificacaoBemEnum {

	/** O atributo BEM_IMOVEL. */
	BEM_IMOVEL((short) 1, "Bem Im�vel"),

	/** O atributo BEM_MOVEL. */
	BEM_MOVEL((short) 2, "Bem M�vel");

	/** O atributo codigo. */
	private Short codigo;

	/** O atributo descricao. */
	private String descricao;

	/**
	 * M�todo Construtor.
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
	 * Verifica se o c�digo informado � do tipo im�vel.
	 *
	 * @param codigo
	 *            o valor de codigo
	 * @return {@code true}, se for imovel
	 */
	public static boolean isImovel(Short codigo) {
		return BEM_IMOVEL.getCodigo().equals(codigo);
	}

}