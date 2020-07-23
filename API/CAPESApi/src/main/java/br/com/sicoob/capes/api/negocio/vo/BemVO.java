package br.com.sicoob.capes.api.negocio.vo;

import java.math.BigDecimal;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * Classe com as informações do bem.
 * 
 * @author bruno.carneiro
 */
public class BemVO extends BancoobVo {
	private static final long serialVersionUID = -958306403311933259L;

	private Long idBem;
	private Short codigoTipoClassificacaoBem;
	private String descricaoTipoClassificacaoBem;
	private Boolean interno = Boolean.FALSE;
	private String descricao;
	private BigDecimal valor;
	private Boolean valorNaoInformado = Boolean.FALSE;
	private Short mesRenovacaoSeguro;
	private BigDecimal percentualProprietario;
	private Boolean emGarantia = Boolean.FALSE;
	private String idUsuarioAprovacao;

	public Long getIdBem() {
		return idBem;
	}

	public void setIdBem(Long idBem) {
		this.idBem = idBem;
	}

	public Short getCodigoTipoClassificacaoBem() {
		return codigoTipoClassificacaoBem;
	}

	public void setCodigoTipoClassificacaoBem(Short codigoTipoClassificacaoBem) {
		this.codigoTipoClassificacaoBem = codigoTipoClassificacaoBem;
	}

	public String getDescricaoTipoClassificacaoBem() {
		return descricaoTipoClassificacaoBem;
	}

	public void setDescricaoTipoClassificacaoBem(String descricaoTipoClassificacaoBem) {
		this.descricaoTipoClassificacaoBem = descricaoTipoClassificacaoBem;
	}

	public Boolean getInterno() {
		return interno;
	}

	public void setInterno(Boolean interno) {
		this.interno = interno;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Boolean getValorNaoInformado() {
		return valorNaoInformado;
	}

	public void setValorNaoInformado(Boolean valorNaoInformado) {
		this.valorNaoInformado = valorNaoInformado;
	}

	public Short getMesRenovacaoSeguro() {
		return mesRenovacaoSeguro;
	}

	public void setMesRenovacaoSeguro(Short mesRenovacaoSeguro) {
		this.mesRenovacaoSeguro = mesRenovacaoSeguro;
	}

	public BigDecimal getPercentualProprietario() {
		return percentualProprietario;
	}

	public void setPercentualProprietario(BigDecimal percentualProprietario) {
		this.percentualProprietario = percentualProprietario;
	}

	public Boolean getEmGarantia() {
		return emGarantia;
	}

	public void setEmGarantia(Boolean emGarantia) {
		this.emGarantia = emGarantia;
	}
	
	/**
	 * @return the idUsuarioAprovacao
	 */
	public String getIdUsuarioAprovacao() {
		return idUsuarioAprovacao;
	}

	/**
	 * @param idUsuarioAprovacao the idUsuarioAprovacao to set
	 */
	public void setIdUsuarioAprovacao(String idUsuarioAprovacao) {
		this.idUsuarioAprovacao = idUsuarioAprovacao;
	}
	
	/**
	 * Cálculo simples que retorna o valor da posse do proprietário.
	 * 
	 * <p>Valor do bem * percentual de propriedade / 100.</p>
	 * 
	 * @return
	 */
	public BigDecimal getValorParticipacaoPropriedade() {
		if (valor != null && percentualProprietario != null) {
			return valor.multiply(percentualProprietario).divide(new BigDecimal(100));
		}
		return null;
	}

}