/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dto;

import java.math.BigDecimal;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.tipos.DateTime;

/**
 * @author erico.junior
 *
 */
public class ProdutividadeDTO extends BancoobDto {
	
	/** Serial UID.*/
	private static final long serialVersionUID = -6560522099505236949L;

	/** O atributo idProdutividade. */
	private Long idProdutividade;

	/** O atributo houveFrustracao. */
	private Boolean houveFrustracao = Boolean.FALSE;

	/** O atributo dataOcorrencia. */
	private DateTime dataOcorrencia;
	
	/** O atributo percentualFrustracao. */
	private BigDecimal percentualFrustracao;

	/**
	 * Recupera o valor de idProdutividade.
	 *
	 * @return o valor de idProdutividade
	 */
	public Long getIdProdutividade() {
		return idProdutividade;
	}

	/**
	 * Define o valor de idProdutividade.
	 *
	 * @param idProdutividade o novo valor de idProdutividade
	 */
	public void setIdProdutividade(Long idProdutividade) {
		this.idProdutividade = idProdutividade;
	}

	/**
	 * Recupera o valor de houveFrustracao.
	 *
	 * @return o valor de houveFrustracao
	 */
	public Boolean getHouveFrustracao() {
		return houveFrustracao;
	}

	/**
	 * Define o valor de houveFrustracao.
	 *
	 * @param houveFrustracao o novo valor de houveFrustracao
	 */
	public void setHouveFrustracao(Boolean houveFrustracao) {
		this.houveFrustracao = houveFrustracao;
	}

	/**
	 * Recupera o valor de percentualFrustracao.
	 *
	 * @return o valor de percentualFrustracao
	 */
	public BigDecimal getPercentualFrustracao() {
		return percentualFrustracao;
	}

	/**
	 * Define o valor de percentualFrustracao.
	 *
	 * @param percentualFrustracao o novo valor de percentualFrustracao
	 */
	public void setPercentualFrustracao(BigDecimal percentualFrustracao) {
		this.percentualFrustracao = percentualFrustracao;
	}

	/**
	 * Recupera o valor de dataOcorrencia.
	 *
	 * @return o valor de dataOcorrencia
	 */
	public DateTime getDataOcorrencia() {
		return dataOcorrencia;
	}

	/**
	 * Define o valor de dataOcorrencia.
	 *
	 * @param dataOcorrencia o novo valor de dataOcorrencia
	 */
	public void setDataOcorrencia(DateTime dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}
	
}
