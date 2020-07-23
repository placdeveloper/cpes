package br.com.sicoob.capes.comum.negocio.enums;

/**
 * O Enum TipoFonteRendaEnum.
 *
 * @author Bruno.Carneiro
 */
public enum TipoFonteRendaEnum {

	/** O atributo SALARIO. */
	SALARIO((short) 0, "SALÁRIO"),

	/** O atributo PRO_LABORE. */
	PRO_LABORE((short) 1, "PRO-LABORE"),

	/** O atributo PENSAO_ALIMENTICIA. */
	PENSAO_ALIMENTICIA((short) 2, "PENSÃO ALIMENTÍCIA"),

	/** O atributo APOSENTADORIA. */
	APOSENTADORIA((short) 3, "APOSENTADORIA"),

	/** O atributo PRESTACAO_SERVICO. */
	PRESTACAO_SERVICO((short) 4, "PRESTAÇÃO DE SERVIÇO"),

	/** O atributo CONSULTORIA. */
	CONSULTORIA((short) 5, "CONSULTORIA"),

	/** O atributo OUTROS. */
	OUTROS((short) 6, "OUTROS"),

	/** O atributo FATURAMENTO. */
	FATURAMENTO((short) 7, "FATURAMENTO"),

	/** O atributo RENDIMENTO_NAO_COMPROVADO. */
	RENDIMENTO_NAO_COMPROVADO((short) 8, "RENDIMENTO NÃO COMPROVADO"),

	/** O atributo AGROPECUARIA. */
	AGROPECUARIA((short) 9, "AGROPECUÁRIA"),

	/** O atributo APLICACAO. */
	APLICACAO((short) 10, "APLICAÇÃO"),

	/** O atributo BOLSAS_DO_GOVERNO. */
	BOLSAS_DO_GOVERNO((short) 11, "BOLSAS DO GOVERNO"),

	/** O atributo NAO_POSSUI_RENDA. */
	NAO_POSSUI_RENDA((short) 12, "NÃO POSSUI RENDA"),

	/** O atributo MESADA. */
	MESADA((short) 13, "MESADA");

	/** O atributo codigo. */
	private Short codigo;

	/** O atributo descricao. */
	private String descricao;

	/**
	 * Instancia um novo TipoFonteRendaEnum.
	 *
	 * @param codigo
	 *            o valor de codigo
	 * @param descricao
	 *            o valor de descricao
	 */
	private TipoFonteRendaEnum(Short codigo, String descricao) {
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
	 * Obtém o enum correto a partir do código informado.
	 *
	 * @param codigo
	 *            o valor de codigo
	 * @return TipoFonteRendaEnum
	 */
	public static TipoFonteRendaEnum obterPorCodigo(Short codigo) {
		for (TipoFonteRendaEnum tipoFonteRenda : values()) {
			if (tipoFonteRenda.codigo.equals(codigo)) {
				return tipoFonteRenda;
			}
		}
		return null;
	}

}
