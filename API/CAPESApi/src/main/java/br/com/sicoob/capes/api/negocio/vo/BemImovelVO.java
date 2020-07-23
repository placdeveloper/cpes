package br.com.sicoob.capes.api.negocio.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Classe com as informações do bem imóvel.
 *
 * @author bruno.carneiro
 */
public class BemImovelVO extends BemVO {
	private static final long serialVersionUID = -3741553833855344738L;

	private String informacoesGerais;
	private String denominacao;
	private String numero;
	private String complemento;
	private Integer idLocalidade;
	private Integer idLogradouro;
	private String descricaoRoteiro;
	private BigDecimal latitude;
	private BigDecimal longitude;
	private BigDecimal areaTotal;
	private Long idPessoaCartorio;
	private String matricula;
	private String nirf;
	private String incra;

	private Short codigoTipoBem;
	private String descricaoTipoBem;
	private Short codigoUnidadeMedida;
	private String descricaoUnidadeMedida;
	private Short codigoTipoLocalizacaoBem;
	private String descricaoTipoLocalizacaoBem;
	private Short codigoTipoUsoBem;
	private String descricaoTipoUsoBem;

	private Short codigoTipoImplantacaoBemImovel;
	private String descricaoTipoImplantacaoBemImovel;
	private Short codigoTipoEstadoConservacao;
	private String descricaoTipoEstadoConservacao;
	private Short codigoTipoPadraoAcabamentoBemImovel;
	private String descricaoTipoPadraoAcabamentoBemImovel;
	private Short codigoTipoServicoCondominialBemImovel;
	private String descricaoTipoServicoCondominialBemImovel;

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
	
	public String getInformacoesGerais() {
		return informacoesGerais;
	}

	public void setInformacoesGerais(String informacoesGerais) {
		this.informacoesGerais = informacoesGerais;
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
	
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
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

	public Long getIdPessoaCartorio() {
		return idPessoaCartorio;
	}

	public void setIdPessoaCartorio(Long idPessoaCartorio) {
		this.idPessoaCartorio = idPessoaCartorio;
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

	public String getDescricaoTipoBem() {
		return descricaoTipoBem;
	}

	public void setDescricaoTipoBem(String descricaoTipoBem) {
		this.descricaoTipoBem = descricaoTipoBem;
	}

	public Short getCodigoUnidadeMedida() {
		return codigoUnidadeMedida;
	}

	public void setCodigoUnidadeMedida(Short codigoUnidadeMedida) {
		this.codigoUnidadeMedida = codigoUnidadeMedida;
	}

	public String getDescricaoUnidadeMedida() {
		return descricaoUnidadeMedida;
	}

	public void setDescricaoUnidadeMedida(String descricaoUnidadeMedida) {
		this.descricaoUnidadeMedida = descricaoUnidadeMedida;
	}

	public Short getCodigoTipoLocalizacaoBem() {
		return codigoTipoLocalizacaoBem;
	}

	public void setCodigoTipoLocalizacaoBem(Short codigoTipoLocalizacaoBem) {
		this.codigoTipoLocalizacaoBem = codigoTipoLocalizacaoBem;
	}

	public String getDescricaoTipoLocalizacaoBem() {
		return descricaoTipoLocalizacaoBem;
	}

	public void setDescricaoTipoLocalizacaoBem(String descricaoTipoLocalizacaoBem) {
		this.descricaoTipoLocalizacaoBem = descricaoTipoLocalizacaoBem;
	}

	public Short getCodigoTipoUsoBem() {
		return codigoTipoUsoBem;
	}

	public void setCodigoTipoUsoBem(Short codigoTipoUsoBem) {
		this.codigoTipoUsoBem = codigoTipoUsoBem;
	}

	public String getDescricaoTipoUsoBem() {
		return descricaoTipoUsoBem;
	}

	public void setDescricaoTipoUsoBem(String descricaoTipoUsoBem) {
		this.descricaoTipoUsoBem = descricaoTipoUsoBem;
	}

	public Short getCodigoTipoImplantacaoBemImovel() {
		return codigoTipoImplantacaoBemImovel;
	}

	public void setCodigoTipoImplantacaoBemImovel(Short codigoTipoImplantacaoBemImovel) {
		this.codigoTipoImplantacaoBemImovel = codigoTipoImplantacaoBemImovel;
	}

	public String getDescricaoTipoImplantacaoBemImovel() {
		return descricaoTipoImplantacaoBemImovel;
	}

	public void setDescricaoTipoImplantacaoBemImovel(String descricaoTipoImplantacaoBemImovel) {
		this.descricaoTipoImplantacaoBemImovel = descricaoTipoImplantacaoBemImovel;
	}

	public Short getCodigoTipoEstadoConservacao() {
		return codigoTipoEstadoConservacao;
	}

	public void setCodigoTipoEstadoConservacao(Short codigoTipoEstadoConservacao) {
		this.codigoTipoEstadoConservacao = codigoTipoEstadoConservacao;
	}

	public String getDescricaoTipoEstadoConservacao() {
		return descricaoTipoEstadoConservacao;
	}

	public void setDescricaoTipoEstadoConservacao(String descricaoTipoEstadoConservacao) {
		this.descricaoTipoEstadoConservacao = descricaoTipoEstadoConservacao;
	}

	public Short getCodigoTipoPadraoAcabamentoBemImovel() {
		return codigoTipoPadraoAcabamentoBemImovel;
	}

	public void setCodigoTipoPadraoAcabamentoBemImovel(Short codigoTipoPadraoAcabamentoBemImovel) {
		this.codigoTipoPadraoAcabamentoBemImovel = codigoTipoPadraoAcabamentoBemImovel;
	}

	public String getDescricaoTipoPadraoAcabamentoBemImovel() {
		return descricaoTipoPadraoAcabamentoBemImovel;
	}

	public void setDescricaoTipoPadraoAcabamentoBemImovel(String descricaoTipoPadraoAcabamentoBemImovel) {
		this.descricaoTipoPadraoAcabamentoBemImovel = descricaoTipoPadraoAcabamentoBemImovel;
	}

	public Short getCodigoTipoServicoCondominialBemImovel() {
		return codigoTipoServicoCondominialBemImovel;
	}

	public void setCodigoTipoServicoCondominialBemImovel(Short codigoTipoServicoCondominialBemImovel) {
		this.codigoTipoServicoCondominialBemImovel = codigoTipoServicoCondominialBemImovel;
	}

	public String getDescricaoTipoServicoCondominialBemImovel() {
		return descricaoTipoServicoCondominialBemImovel;
	}

	public void setDescricaoTipoServicoCondominialBemImovel(String descricaoTipoServicoCondominialBemImovel) {
		this.descricaoTipoServicoCondominialBemImovel = descricaoTipoServicoCondominialBemImovel;
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
	
	public BemImovelVO(){
		
	}

	public BemImovelVO(String informacoesGerais, String denominacao, String numero, String complemento, Integer idLocalidade, Integer idLogradouro,
			String descricaoRoteiro, BigDecimal latitude, BigDecimal longitude, BigDecimal areaTotal, Long idPessoaCartorio, String matricula, String nirf,
			String incra, Short codigoTipoBem, String descricaoTipoBem, Short codigoUnidadeMedida, String descricaoUnidadeMedida,
			Short codigoTipoLocalizacaoBem, String descricaoTipoLocalizacaoBem, Short codigoTipoUsoBem, String descricaoTipoUsoBem,
			Short codigoTipoImplantacaoBemImovel, String descricaoTipoImplantacaoBemImovel, Short codigoTipoEstadoConservacao,
			String descricaoTipoEstadoConservacao, Short codigoTipoPadraoAcabamentoBemImovel, String descricaoTipoPadraoAcabamentoBemImovel,
			Short codigoTipoServicoCondominialBemImovel, String descricaoTipoServicoCondominialBemImovel, BigDecimal areaPrivativa, BigDecimal areaTerreno,
			BigDecimal areaTestada, Short quantidadeDormitorios, Short quantidadeVagasGaragem, String benfeitoria, BigDecimal valorBenfeitoria,
			BigDecimal valorCompraVenda, Date dataCompraVenda, BigDecimal valorAvaliacao, Date dataAvaliacao, Boolean processoAquisicao,
			Integer idPessoaAvaliador) {
		super();
		this.informacoesGerais = informacoesGerais;
		this.denominacao = denominacao;
		this.numero = numero;
		this.complemento = complemento;
		this.idLocalidade = idLocalidade;
		this.idLogradouro = idLogradouro;
		this.descricaoRoteiro = descricaoRoteiro;
		this.latitude = latitude;
		this.longitude = longitude;
		this.areaTotal = areaTotal;
		this.idPessoaCartorio = idPessoaCartorio;
		this.matricula = matricula;
		this.nirf = nirf;
		this.incra = incra;
		this.codigoTipoBem = codigoTipoBem;
		this.descricaoTipoBem = descricaoTipoBem;
		this.codigoUnidadeMedida = codigoUnidadeMedida;
		this.descricaoUnidadeMedida = descricaoUnidadeMedida;
		this.codigoTipoLocalizacaoBem = codigoTipoLocalizacaoBem;
		this.descricaoTipoLocalizacaoBem = descricaoTipoLocalizacaoBem;
		this.codigoTipoUsoBem = codigoTipoUsoBem;
		this.descricaoTipoUsoBem = descricaoTipoUsoBem;
		this.codigoTipoImplantacaoBemImovel = codigoTipoImplantacaoBemImovel;
		this.descricaoTipoImplantacaoBemImovel = descricaoTipoImplantacaoBemImovel;
		this.codigoTipoEstadoConservacao = codigoTipoEstadoConservacao;
		this.descricaoTipoEstadoConservacao = descricaoTipoEstadoConservacao;
		this.codigoTipoPadraoAcabamentoBemImovel = codigoTipoPadraoAcabamentoBemImovel;
		this.descricaoTipoPadraoAcabamentoBemImovel = descricaoTipoPadraoAcabamentoBemImovel;
		this.codigoTipoServicoCondominialBemImovel = codigoTipoServicoCondominialBemImovel;
		this.descricaoTipoServicoCondominialBemImovel = descricaoTipoServicoCondominialBemImovel;
		this.areaPrivativa = areaPrivativa;
		this.areaTerreno = areaTerreno;
		this.areaTestada = areaTestada;
		this.quantidadeDormitorios = quantidadeDormitorios;
		this.quantidadeVagasGaragem = quantidadeVagasGaragem;
		this.benfeitoria = benfeitoria;
		this.valorBenfeitoria = valorBenfeitoria;
		this.valorCompraVenda = valorCompraVenda;
		this.dataCompraVenda = dataCompraVenda;
		this.valorAvaliacao = valorAvaliacao;
		this.dataAvaliacao = dataAvaliacao;
		this.processoAquisicao = processoAquisicao;
		this.idPessoaAvaliador = idPessoaAvaliador;
	}

}