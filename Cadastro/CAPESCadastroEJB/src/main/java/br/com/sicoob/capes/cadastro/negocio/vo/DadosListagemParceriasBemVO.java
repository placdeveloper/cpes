package br.com.sicoob.capes.cadastro.negocio.vo;

import java.math.BigDecimal;

public class DadosListagemParceriasBemVO extends DadosListagemBemVO {
	private static final long serialVersionUID = -4241250632294360113L;

	private BigDecimal areaTotal;
	private BigDecimal areaPosse;
	private String municipio;
	private String matricula;
	private String nirf;
	private String incra;

	public BigDecimal getAreaTotal() {
		return areaTotal;
	}

	public void setAreaTotal(BigDecimal areaTotal) {
		this.areaTotal = areaTotal;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
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

	public BigDecimal getAreaPosse() {
		return areaPosse;
	}

	public void setAreaPosse(BigDecimal areaPosse) {
		this.areaPosse = areaPosse;
	}

}