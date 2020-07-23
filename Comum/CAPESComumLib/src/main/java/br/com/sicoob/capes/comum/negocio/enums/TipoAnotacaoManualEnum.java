package br.com.sicoob.capes.comum.negocio.enums;

/**
 * Enum representando os tipos de anotação manual.
 *
 * @author Bruno.Carneiro
 */
public enum TipoAnotacaoManualEnum {

	NEGACAO_FORNECIMENTO_INFORMACOES_CADASTRAIS(115, "NEGAÇÃO OU RESISTÊNCIA AO FORNECIMENTO DE INFORMAÇÕES CADASTRAIS"), 
	SUSPEITA_FRAUDE(319, "SUSPEITA DE FRAUDE");

	/** O atributo cod tipo anotacao. */
	private Integer codTipoAnotacao;

	/** O atributo descricao. */
	private String descricao;

	/**
	 * Construtor do Enum.
	 * 
	 * @param codTipoAnotacao
	 *            O identificador do tipo de anotação.
	 * @param descricao
	 *            A descrição do tipo de anotação.
	 */
	private TipoAnotacaoManualEnum(Integer codTipoAnotacao, String descricao) {
		this.descricao = descricao;
		this.codTipoAnotacao = codTipoAnotacao;
	}

	/**
	 * @return the codTipoAnotacao
	 */
	public Integer getCodTipoAnotacao() {
		return codTipoAnotacao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

}