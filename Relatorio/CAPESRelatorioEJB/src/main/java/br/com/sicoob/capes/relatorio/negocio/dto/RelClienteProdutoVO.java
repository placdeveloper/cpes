package br.com.sicoob.capes.relatorio.negocio.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

public class RelClienteProdutoVO extends BancoobDto implements Serializable {

	private static final long serialVersionUID = -1820394876196703568L;

	private Integer cooperativa;
	private String nomeCoopPac;
	private Integer pac;
	private Integer nucleo;
	private String nomeNucleo;
	private Integer grupoEconomico;
	private String nomeGrupoEconomico;
	private Integer gerente;
	private String nomeGerente;
	private Integer tipoPessoa;

	private Boolean contaCapital;
	private Boolean contaCorrente;
	private Boolean aplicacoes;
	private Boolean poupanca;
	private Boolean opCredito;
	private Boolean cartaoCredito;
	private Boolean debitoAutomatico;

	private double rendaMin;
	private double rendaMax;
	private Date dtNascInicio;
	private Date dtNascFim;
	private Integer dependente;
	private Integer sexo;
	private Integer municipio;
	private String nomeMunicipio;
	private Integer categoriaProdutor;
	private String nomeCategoriaProdutor;
	private Integer ordenacao;

	public Integer getCooperativa() {
		return cooperativa;
	}

	public void setCooperativa(Integer cooperativa) {
		this.cooperativa = cooperativa;
	}

	public String getNomeCoopPac() {
		return nomeCoopPac;
	}

	public void setNomeCoopPac(String nomeCoopPac) {
		this.nomeCoopPac = nomeCoopPac;
	}

	public Integer getPac() {
		return pac;
	}

	public void setPac(Integer pac) {
		this.pac = pac;
	}

	public Integer getNucleo() {
		return nucleo;
	}

	public void setNucleo(Integer nucleo) {
		this.nucleo = nucleo;
	}

	public String getNomeNucleo() {
		return nomeNucleo;
	}

	public void setNomeNucleo(String nomeNucleo) {
		this.nomeNucleo = nomeNucleo;
	}

	public Integer getGrupoEconomico() {
		return grupoEconomico;
	}

	public void setGrupoEconomico(Integer grupoEconomico) {
		this.grupoEconomico = grupoEconomico;
	}

	public String getNomeGrupoEconomico() {
		return nomeGrupoEconomico;
	}

	public void setNomeGrupoEconomico(String nomeGrupoEconomico) {
		this.nomeGrupoEconomico = nomeGrupoEconomico;
	}

	public Integer getGerente() {
		return gerente;
	}

	public void setGerente(Integer gerente) {
		this.gerente = gerente;
	}

	public String getNomeGerente() {
		return nomeGerente;
	}

	public void setNomeGerente(String nomeGerente) {
		this.nomeGerente = nomeGerente;
	}

	public Integer getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(Integer tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public Boolean getContaCapital() {
		return contaCapital;
	}

	public void setContaCapital(Boolean contaCapital) {
		this.contaCapital = contaCapital;
	}

	public Boolean getContaCorrente() {
		return contaCorrente;
	}

	public void setContaCorrente(Boolean contaCorrente) {
		this.contaCorrente = contaCorrente;
	}

	public Boolean getAplicacoes() {
		return aplicacoes;
	}

	public void setAplicacoes(Boolean aplicacoes) {
		this.aplicacoes = aplicacoes;
	}

	public Boolean getPoupanca() {
		return poupanca;
	}

	public void setPoupanca(Boolean poupanca) {
		this.poupanca = poupanca;
	}

	public Boolean getOpCredito() {
		return opCredito;
	}

	public void setOpCredito(Boolean opCredito) {
		this.opCredito = opCredito;
	}

	public Boolean getCartaoCredito() {
		return cartaoCredito;
	}

	public void setCartaoCredito(Boolean cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public Boolean getDebitoAutomatico() {
		return debitoAutomatico;
	}

	public void setDebitoAutomatico(Boolean debitoAutomatico) {
		this.debitoAutomatico = debitoAutomatico;
	}

	public Date getDtNascInicio() {
		return dtNascInicio;
	}

	public void setDtNascInicio(Date dtNascInicio) {
		this.dtNascInicio = dtNascInicio;
	}

	public Date getDtNascFim() {
		return dtNascFim;
	}

	public void setDtNascFim(Date dtNascFim) {
		this.dtNascFim = dtNascFim;
	}

	public double getRendaMin() {
		return rendaMin;
	}

	public void setRendaMin(double rendaMin) {
		this.rendaMin = rendaMin;
	}

	public double getRendaMax() {
		return rendaMax;
	}

	public void setRendaMax(double rendaMax) {
		this.rendaMax = rendaMax;
	}

	public Integer getDependente() {
		return dependente;
	}

	public void setDependente(Integer dependente) {
		this.dependente = dependente;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	public Integer getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Integer municipio) {
		this.municipio = municipio;
	}

	public Integer getCategoriaProdutor() {
		return categoriaProdutor;
	}

	public void setCategoriaProdutor(Integer categoriaProdutor) {
		this.categoriaProdutor = categoriaProdutor;
	}

	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	public String getNomeCategoriaProdutor() {
		return nomeCategoriaProdutor;
	}

	public void setNomeCategoriaProdutor(String nomeCategoriaProdutor) {
		this.nomeCategoriaProdutor = nomeCategoriaProdutor;
	}

	public Integer getOrdenacao() {
		return ordenacao;
	}

	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}

}
