package br.com.sicoob.capes.cadastro.negocio.vo;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.vo.BancoobVo;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;

public class DadosListagemBemVO extends BancoobVo {
	private static final long serialVersionUID = -4241250632294360113L;

	// BEM
	private Long idBem;
	private Short codigoTipoClassificacaoBem;
	private String descricaoTipoBem;
	private String descricaoTipoClassificacaoBem;
	private String descricaoBem;
	private String denominacaoBem;
	private BigDecimal valor;
	private BigDecimal valorAvaliacao;
	private Date dataAvaliacao;
	private String status;
	private String bloqueadoPor;
	private String idRegistroControlado;
	private Date dataHoraInicio;
	private Integer idInstituicaoAtualizacao;
	private Boolean bemInterno;
	private Short codigoSituacaoAprovacao;
	private SituacaoRegistroEnum situacaoAprovacao;

	private Long idBemPessoaCompartilhamento;
	private BigDecimal percentualProprietario;
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

	public String getDescricaoTipoBem() {
		return descricaoTipoBem;
	}

	public void setDescricaoTipoBem(String descricaoTipoBem) {
		this.descricaoTipoBem = descricaoTipoBem;
	}

	public String getDescricaoTipoClassificacaoBem() {
		return descricaoTipoClassificacaoBem;
	}

	public void setDescricaoTipoClassificacaoBem(String descricaoTipoClassificacaoBem) {
		this.descricaoTipoClassificacaoBem = descricaoTipoClassificacaoBem;
	}

	public String getDescricaoBem() {
		return descricaoBem;
	}

	public void setDescricaoBem(String descricaoBem) {
		this.descricaoBem = descricaoBem;
	}
	
	public String getDenominacaoBem() {
		return denominacaoBem;
	}

	public void setDenominacaoBem(String denominacaoBem) {
		this.denominacaoBem = denominacaoBem;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorAvaliacao() {
		return valorAvaliacao;
	}

	public void setValorAvaliacao(BigDecimal valorAvaliacao) {
		this.valorAvaliacao = valorAvaliacao;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getBloqueadoPor() {
		return bloqueadoPor;
	}

	public void setBloqueadoPor(String bloqueadoPor) {
		this.bloqueadoPor = bloqueadoPor;
	}

	public String getIdRegistroControlado() {
		return idRegistroControlado;
	}

	public void setIdRegistroControlado(String idRegistroControlado) {
		this.idRegistroControlado = idRegistroControlado;
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Integer getIdInstituicaoAtualizacao() {
		return idInstituicaoAtualizacao;
	}

	public void setIdInstituicaoAtualizacao(Integer idInstituicaoAtualizacao) {
		this.idInstituicaoAtualizacao = idInstituicaoAtualizacao;
	}

	public Boolean getBemInterno() {
		return bemInterno;
	}

	public void setBemInterno(Boolean bemInterno) {
		this.bemInterno = bemInterno;
	}

	public Short getCodigoSituacaoAprovacao() {
		return codigoSituacaoAprovacao;
	}

	public void setCodigoSituacaoAprovacao(Short codigoSituacaoAprovacao) {
		this.codigoSituacaoAprovacao = codigoSituacaoAprovacao;
	}

	public SituacaoRegistroEnum getSituacaoAprovacao() {
		return situacaoAprovacao;
	}

	public void setSituacaoAprovacao(SituacaoRegistroEnum situacaoAprovacao) {
		this.situacaoAprovacao = situacaoAprovacao;
	}

	public Long getIdBemPessoaCompartilhamento() {
		return idBemPessoaCompartilhamento;
	}

	public void setIdBemPessoaCompartilhamento(Long idBemPessoaCompartilhamento) {
		this.idBemPessoaCompartilhamento = idBemPessoaCompartilhamento;
	}

	public BigDecimal getPercentualProprietario() {
		return percentualProprietario;
	}

	public void setPercentualProprietario(BigDecimal percentualProprietario) {
		this.percentualProprietario = percentualProprietario;
	}

	public String getIdUsuarioAprovacao() {
		return idUsuarioAprovacao;
	}

	public void setIdUsuarioAprovacao(String idUsuarioAprovacao) {
		this.idUsuarioAprovacao = idUsuarioAprovacao;
	}

}