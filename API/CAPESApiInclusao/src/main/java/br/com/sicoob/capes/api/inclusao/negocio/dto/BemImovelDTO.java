package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.sicoob.capes.comum.negocio.enums.TipoClassificacaoBemEnum;

/**
 * Classe contendo as informações do bem imóvel
 * 
 * @author Bruno.Carneiro
 */
public class BemImovelDTO extends BemDTO {
	private static final long serialVersionUID = -5704158568386412633L;

	private String denominacao;
	private String numero;
	private String complemento;
	private Integer idLogradouro;
	private String descricaoRoteiro;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private BigDecimal areaTotal;
	private Integer idPessoaCartorio;
	private Integer idInstituicaoPessoaCartorio;
	private String matricula;
	private String nirf;
	private String incra;

	private Short codigoTipoBem;
	private Short codigoUnidadeMedida;
	private Short codigoTipoLocalizacaoBem;
	private Short codigoTipoUsoBem;

	private Short codigoTipoImplantacaoBemImovel;
	private Short codigoTipoEstadoConservacao;
	private Short codigoTipoPadraoAcabamentoBemImovel;
	private Short codigoTipoServicoCondominialBemImovel;

	private BigDecimal areaPrivativa;
	private BigDecimal areaTerreno;
	private BigDecimal areaTestada;
	private Short quantidadeDormitorios;
	private Short quantidadeVagasGaragem;

	private String benfeitoria;
	private BigDecimal valorBenfeitoria;

	private BigDecimal valorCompraVenda;
	private Date dataCompraVenda;
	private BigDecimal valorAvaliacao;
	private Date dataAvaliacao;
	private Boolean processoAquisicao = Boolean.FALSE;
	private Integer idPessoaAvaliador;
	private Integer idInstituicaoAvaliador;

	public BemImovelDTO() {
		super(TipoClassificacaoBemEnum.BEM_IMOVEL.getCodigo());
	}

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getIdLogradouro() {
		return idLogradouro;
	}

	public void setIdLogradouro(Integer idLogradouro) {
		this.idLogradouro = idLogradouro;
	}

	public String getDescricaoRoteiro() {
		return descricaoRoteiro;
	}

	public void setDescricaoRoteiro(String descricaoRoteiro) {
		this.descricaoRoteiro = descricaoRoteiro;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(BigDecimal areaTotal) {
		this.areaTotal = areaTotal;
	}

	public Integer getIdPessoaCartorio() {
		return idPessoaCartorio;
	}

	public void setIdPessoaCartorio(Integer idPessoaCartorio) {
		this.idPessoaCartorio = idPessoaCartorio;
	}

	public Integer getIdInstituicaoPessoaCartorio() {
		return idInstituicaoPessoaCartorio;
	}

	public void setIdInstituicaoPessoaCartorio(Integer idInstituicaoPessoaCartorio) {
		this.idInstituicaoPessoaCartorio = idInstituicaoPessoaCartorio;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNirf() {
		return nirf;
	}

	public void setNirf(String nirf) {
		this.nirf = nirf;
	}

	public String getIncra() {
		return incra;
	}

	public void setIncra(String incra) {
		this.incra = incra;
	}

	public Short getCodigoTipoBem() {
		return codigoTipoBem;
	}

	public void setCodigoTipoBem(Short codigoTipoBem) {
		this.codigoTipoBem = codigoTipoBem;
	}

	public Short getCodigoUnidadeMedida() {
		return codigoUnidadeMedida;
	}

	public void setCodigoUnidadeMedida(Short codigoUnidadeMedida) {
		this.codigoUnidadeMedida = codigoUnidadeMedida;
	}

	public Short getCodigoTipoLocalizacaoBem() {
		return codigoTipoLocalizacaoBem;
	}

	public void setCodigoTipoLocalizacaoBem(Short codigoTipoLocalizacaoBem) {
		this.codigoTipoLocalizacaoBem = codigoTipoLocalizacaoBem;
	}

	public Short getCodigoTipoUsoBem() {
		return codigoTipoUsoBem;
	}

	public void setCodigoTipoUsoBem(Short codigoTipoUsoBem) {
		this.codigoTipoUsoBem = codigoTipoUsoBem;
	}

	public Short getCodigoTipoImplantacaoBemImovel() {
		return codigoTipoImplantacaoBemImovel;
	}

	public void setCodigoTipoImplantacaoBemImovel(Short codigoTipoImplantacaoBemImovel) {
		this.codigoTipoImplantacaoBemImovel = codigoTipoImplantacaoBemImovel;
	}

	public Short getCodigoTipoEstadoConservacao() {
		return codigoTipoEstadoConservacao;
	}

	public void setCodigoTipoEstadoConservacao(Short codigoTipoEstadoConservacao) {
		this.codigoTipoEstadoConservacao = codigoTipoEstadoConservacao;
	}

	public Short getCodigoTipoPadraoAcabamentoBemImovel() {
		return codigoTipoPadraoAcabamentoBemImovel;
	}

	public void setCodigoTipoPadraoAcabamentoBemImovel(Short codigoTipoPadraoAcabamentoBemImovel) {
		this.codigoTipoPadraoAcabamentoBemImovel = codigoTipoPadraoAcabamentoBemImovel;
	}

	public Short getCodigoTipoServicoCondominialBemImovel() {
		return codigoTipoServicoCondominialBemImovel;
	}

	public void setCodigoTipoServicoCondominialBemImovel(Short codigoTipoServicoCondominialBemImovel) {
		this.codigoTipoServicoCondominialBemImovel = codigoTipoServicoCondominialBemImovel;
	}

	public BigDecimal getAreaPrivativa() {
		return areaPrivativa;
	}

	public void setAreaPrivativa(BigDecimal areaPrivativa) {
		this.areaPrivativa = areaPrivativa;
	}

	public BigDecimal getAreaTerreno() {
		return areaTerreno;
	}

	public void setAreaTerreno(BigDecimal areaTerreno) {
		this.areaTerreno = areaTerreno;
	}

	public BigDecimal getAreaTestada() {
		return areaTestada;
	}

	public void setAreaTestada(BigDecimal areaTestada) {
		this.areaTestada = areaTestada;
	}

	public Short getQuantidadeDormitorios() {
		return quantidadeDormitorios;
	}

	public void setQuantidadeDormitorios(Short quantidadeDormitorios) {
		this.quantidadeDormitorios = quantidadeDormitorios;
	}

	public Short getQuantidadeVagasGaragem() {
		return quantidadeVagasGaragem;
	}

	public void setQuantidadeVagasGaragem(Short quantidadeVagasGaragem) {
		this.quantidadeVagasGaragem = quantidadeVagasGaragem;
	}

	public String getBenfeitoria() {
		return benfeitoria;
	}

	public void setBenfeitoria(String benfeitoria) {
		this.benfeitoria = benfeitoria;
	}

	public BigDecimal getValorBenfeitoria() {
		return valorBenfeitoria;
	}

	public void setValorBenfeitoria(BigDecimal valorBenfeitoria) {
		this.valorBenfeitoria = valorBenfeitoria;
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

}