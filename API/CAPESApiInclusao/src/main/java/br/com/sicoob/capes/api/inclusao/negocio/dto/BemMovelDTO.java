package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.sicoob.capes.comum.negocio.enums.TipoClassificacaoBemEnum;

/**
 * Classe contendo as informações do bem móvel.
 *
 * @author Bruno.Carneiro
 */
public class BemMovelDTO extends BemDTO {
	private static final long serialVersionUID = -2893121801361312948L;

	private Short codigoTipoBem;
	private Short codigoTipoChassi;
	private String numeroChassi;
	private String renavam;
	private String placa;
	private String uf;
	private Short anoFabricacaoAutomovel;
	private Short anoModeloAutomovel;
	private String inscricaoEmbarcacao;
	private Short anoConstrucaoEmbarcacao;
	private String matriculaAeronave;
	private Short anoConstrucaoAeronave;
	private Short codigoTipoCorAutomovel;
	private BigDecimal valorCompraVenda;
	private Date dataCompraVenda;
	private BigDecimal valorAvaliacao;
	private Date dataAvaliacao;
	private Boolean processoAquisicao = Boolean.FALSE;
	private Integer idPessoaAvaliador;
	private Integer idInstituicaoAvaliador;
	private Short codigoTipoEstadoConservacao;
	
	public BemMovelDTO() {
		super(TipoClassificacaoBemEnum.BEM_MOVEL.getCodigo());
	}

	public Short getCodigoTipoBem() {
		return codigoTipoBem;
	}

	public void setCodigoTipoBem(Short codigoTipoBem) {
		this.codigoTipoBem = codigoTipoBem;
	}

	public Short getCodigoTipoChassi() {
		return codigoTipoChassi;
	}

	public void setCodigoTipoChassi(Short codigoTipoChassi) {
		this.codigoTipoChassi = codigoTipoChassi;
	}

	public String getNumeroChassi() {
		return numeroChassi;
	}

	public void setNumeroChassi(String numeroChassi) {
		this.numeroChassi = numeroChassi;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Short getAnoFabricacaoAutomovel() {
		return anoFabricacaoAutomovel;
	}

	public void setAnoFabricacaoAutomovel(Short anoFabricacaoAutomovel) {
		this.anoFabricacaoAutomovel = anoFabricacaoAutomovel;
	}

	public Short getAnoModeloAutomovel() {
		return anoModeloAutomovel;
	}

	public void setAnoModeloAutomovel(Short anoModeloAutomovel) {
		this.anoModeloAutomovel = anoModeloAutomovel;
	}

	public String getInscricaoEmbarcacao() {
		return inscricaoEmbarcacao;
	}

	public void setInscricaoEmbarcacao(String inscricaoEmbarcacao) {
		this.inscricaoEmbarcacao = inscricaoEmbarcacao;
	}

	public Short getAnoConstrucaoEmbarcacao() {
		return anoConstrucaoEmbarcacao;
	}

	public void setAnoConstrucaoEmbarcacao(Short anoConstrucaoEmbarcacao) {
		this.anoConstrucaoEmbarcacao = anoConstrucaoEmbarcacao;
	}

	public String getMatriculaAeronave() {
		return matriculaAeronave;
	}

	public void setMatriculaAeronave(String matriculaAeronave) {
		this.matriculaAeronave = matriculaAeronave;
	}

	public Short getAnoConstrucaoAeronave() {
		return anoConstrucaoAeronave;
	}

	public void setAnoConstrucaoAeronave(Short anoConstrucaoAeronave) {
		this.anoConstrucaoAeronave = anoConstrucaoAeronave;
	}

	public Short getCodigoTipoCorAutomovel() {
		return codigoTipoCorAutomovel;
	}

	public void setCodigoTipoCorAutomovel(Short codigoTipoCorAutomovel) {
		this.codigoTipoCorAutomovel = codigoTipoCorAutomovel;
	}

	public BigDecimal getValorCompraVenda() {
		return valorCompraVenda;
	}

	public void setValorCompraVenda(BigDecimal valorCompraVenda) {
		this.valorCompraVenda = valorCompraVenda;
	}

	public Date getDataCompraVenda() {
		return dataCompraVenda;
	}

	public void setDataCompraVenda(Date dataCompraVenda) {
		this.dataCompraVenda = dataCompraVenda;
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

	public Boolean getProcessoAquisicao() {
		return processoAquisicao;
	}

	public void setProcessoAquisicao(Boolean processoAquisicao) {
		this.processoAquisicao = processoAquisicao;
	}

	public Integer getIdPessoaAvaliador() {
		return idPessoaAvaliador;
	}

	public void setIdPessoaAvaliador(Integer idPessoaAvaliador) {
		this.idPessoaAvaliador = idPessoaAvaliador;
	}

	public Integer getIdInstituicaoAvaliador() {
		return idInstituicaoAvaliador;
	}

	public void setIdInstituicaoAvaliador(Integer idInstituicaoAvaliador) {
		this.idInstituicaoAvaliador = idInstituicaoAvaliador;
	}

	public Short getCodigoTipoEstadoConservacao() {
		return codigoTipoEstadoConservacao;
	}

	public void setCodigoTipoEstadoConservacao(Short codigoTipoEstadoConservacao) {
		this.codigoTipoEstadoConservacao = codigoTipoEstadoConservacao;
	}

}