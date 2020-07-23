/*
 * SICOOB
 * 
 * AnotacaoInformacaoDTO.java(br.com.sicoob.capes.comum.negocio.dto.AnotacaoInformacaoDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * The Class AnotacaoInformacaoDTO.
 */
public class AnotacaoInformacaoDTO extends BancoobDto {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1871117865134650490L;

	/** O atributo codigo tipo origem informacao. */
	private String codigoTipoOrigemInformacao;
	
	/** O atributo quantidade. */
	private Integer quantidade;
	
	/** O atributo data ocorrencia. */
	private Date dataOcorrencia;
	
	/** O atributo valor. */
	private BigDecimal valor;

	/**
	 * Recupera codigo tipo origem informacao.
	 * 
	 * @return codigo tipo origem informacao
	 */
	public String getCodigoTipoOrigemInformacao() {
		return codigoTipoOrigemInformacao;
	}

	/**
	 * Preenche codigo tipo origem informacao.
	 * 
	 * @param codigoTipoOrigemInformacao
	 *            o novo codigo tipo origem informacao
	 */
	public void setCodigoTipoOrigemInformacao(String codigoTipoOrigemInformacao) {
		this.codigoTipoOrigemInformacao = codigoTipoOrigemInformacao;
	}

	/**
	 * Recupera quantidade.
	 * 
	 * @return quantidade
	 */
	public Integer getQuantidade() {
		return quantidade;
	}

	/**
	 * Preenche quantidade.
	 * 
	 * @param quantidade
	 *            o novo quantidade
	 */
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Recupera valor.
	 * 
	 * @return valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * Preenche valor.
	 * 
	 * @param valor
	 *            o novo valor
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * Recupera data ocorrencia.
	 * 
	 * @return data ocorrencia
	 */
	public Date getDataOcorrencia() {
		return dataOcorrencia;
	}

	/**
	 * Preenche data ocorrencia.
	 * 
	 * @param dataOcorrencia
	 *            o novo data ocorrencia
	 */
	public void setDataOcorrencia(Date dataOcorrencia) {
		this.dataOcorrencia = dataOcorrencia;
	}

}