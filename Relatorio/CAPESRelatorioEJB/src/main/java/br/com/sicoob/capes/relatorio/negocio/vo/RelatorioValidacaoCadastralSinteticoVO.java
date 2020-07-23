package br.com.sicoob.capes.relatorio.negocio.vo;

/**
 * A Classe RelatorioValidacaoCadastralSinteticoVO.
 */
public class RelatorioValidacaoCadastralSinteticoVO extends RelatorioValidacaoCadastralVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -7498883988772198337L;

	/** O atributo central. */
	private String central;
	
	/** O atributo singular. */
	private String singular;
	
	/** O atributo unidade. */
	private String unidade;
	
	/** O atributo nomeUnidade. */
	private String nomeUnidade;
	
	/** O atributo quantidade. */
	private Long quantidade;

	/**
	 * Recupera o valor de central.
	 *
	 * @return o valor de central
	 */
	public String getCentral() {
		return central;
	}

	/**
	 * Define o valor de central.
	 *
	 * @param central o novo valor de central
	 */
	public void setCentral(String central) {
		this.central = central;
	}

	/**
	 * Recupera o valor de singular.
	 *
	 * @return o valor de singular
	 */
	public String getSingular() {
		return singular;
	}

	/**
	 * Define o valor de singular.
	 *
	 * @param singular o novo valor de singular
	 */
	public void setSingular(String singular) {
		this.singular = singular;
	}

	/**
	 * Recupera o valor de unidade.
	 *
	 * @return o valor de unidade
	 */
	public String getUnidade() {
		return unidade;
	}

	/**
	 * Define o valor de unidade.
	 *
	 * @param unidade o novo valor de unidade
	 */
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	/**
	 * Recupera o valor de nomeUnidade.
	 *
	 * @return o valor de nomeUnidade
	 */
	public String getNomeUnidade() {
		return nomeUnidade;
	}

	/**
	 * Define o valor de nomeUnidade.
	 *
	 * @param nomeUnidade o novo valor de nomeUnidade
	 */
	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	/**
	 * Recupera o valor de quantidade.
	 *
	 * @return o valor de quantidade
	 */
	public Long getQuantidade() {
		return quantidade;
	}

	/**
	 * Define o valor de quantidade.
	 *
	 * @param quantidade o novo valor de quantidade
	 */
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

}