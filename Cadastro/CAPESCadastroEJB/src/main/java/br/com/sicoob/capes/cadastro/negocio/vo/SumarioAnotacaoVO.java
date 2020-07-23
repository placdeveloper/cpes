/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * VO que representa o sumário das anotações de um cliente.
 * 
 * @author Erico.Junior
 */
public class SumarioAnotacaoVO implements Serializable {

	/** Serial UID. */
	private static final long serialVersionUID = -2318783657826970726L;

	/** O atributo idOrigem. */
	private Short idOrigem;
	
	/** O atributo origem. */
	private String origem;
	
	/** O atributo dataUltima. */
	private Date dataUltima;
	
	/** O atributo situacao. */
	private String situacao;
	
	/** O atributo idCategoria. */
	private Short idCategoria;
	
	/** O atributo categoria. */
	private String categoria;
	
	/** O atributo quantidade. */
	private int quantidade;
	
	/** O atributo valor. */
	private BigDecimal valor;

	/**
	 * @return the idOrigem
	 */
	public Short getIdOrigem() {
		return idOrigem;
	}

	/**
	 * @param idOrigem
	 *            the idOrigem to set
	 */
	public void setIdOrigem(Short idOrigem) {
		this.idOrigem = idOrigem;
	}

	/**
	 * @return the origem
	 */
	public String getOrigem() {
		return origem;
	}

	/**
	 * @param origem
	 *            the origem to set
	 */
	public void setOrigem(String origem) {
		this.origem = origem;
	}

	/**
	 * @return the dataUltima
	 */
	public Date getDataUltima() {
		return dataUltima;
	}

	/**
	 * @param dataUltima
	 *            the dataUltima to set
	 */
	public void setDataUltima(Date dataUltima) {
		this.dataUltima = dataUltima;
	}

	/**
	 * @return the descricao
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setSituacao(String descricao) {
		this.situacao = descricao;
	}

	/**
	 * @return the idCategoria
	 */
	public Short getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria
	 *            the idCategoria to set
	 */
	public void setIdCategoria(Short idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria
	 *            the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return the quantidade
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade
	 *            the quantidade to set
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
