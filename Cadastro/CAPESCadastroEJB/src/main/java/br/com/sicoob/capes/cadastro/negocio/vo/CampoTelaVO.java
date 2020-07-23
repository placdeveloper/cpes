package br.com.sicoob.capes.cadastro.negocio.vo;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * A Classe CampoTelaVO.
 */
public class CampoTelaVO implements Serializable {

	/** Seria UID */
	private static final long serialVersionUID = 4703366024369194084L;
	
	/** O atributo label. */
	private String label;
	
	/** O atributo valorAntigo. */
	private String valorAntigo;
	
	/** O atributo valorNovo. */
	private String valorNovo;
	
	/** O atributo alterado. */
	private Boolean alterado;
	
	/** O atributo ordenacao. */
	private int ordenacao;

	/**
	 * Recupera o valor de label.
	 *
	 * @return o valor de label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Define o valor de label.
	 *
	 * @param label o novo valor de label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Recupera o valor de valorAntigo.
	 *
	 * @return o valor de valorAntigo
	 */
	public String getValorAntigo() {
		return valorAntigo;
	}

	/**
	 * Define o valor de valorAntigo.
	 *
	 * @param valor o novo valor de valorAntigo
	 */
	public void setValorAntigo(String valor) {
		this.valorAntigo = valor;
	}

	/**
	 * Recupera o valor de valorNovo.
	 *
	 * @return o valor de valorNovo
	 */
	public String getValorNovo() {
		return valorNovo;
	}

	/**
	 * Define o valor de valorNovo.
	 *
	 * @param valor o novo valor de valorNovo
	 */
	public void setValorNovo(String valor) {
		this.valorNovo = valor;
	}

	/**
	 * Recupera o valor de alterado.
	 *
	 * @return o valor de alterado
	 */
	public Boolean getAlterado() {
		return alterado;
	}

	/**
	 * Define o valor de alterado.
	 *
	 * @param alterado o novo valor de alterado
	 */
	public void setAlterado(Boolean alterado) {
		this.alterado = alterado;
	}

	/**
	 * O método Comparar.
	 */
	public void comparar() {
		this.alterado = !StringUtils.equalsIgnoreCase(this.valorAntigo, this.valorNovo);
	}

	/**
	 * Retorna o valor de ordenacao
	 * @return
	 */
	public int getOrdenacao() {
		return ordenacao;
	}

	/**
	 * Define o valor de ordenacao
	 * @param ordenacao
	 */
	public void setOrdenacao(int ordenacao) {
		this.ordenacao = ordenacao;
	}

}
