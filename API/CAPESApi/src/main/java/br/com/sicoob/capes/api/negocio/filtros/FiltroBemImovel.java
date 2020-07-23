package br.com.sicoob.capes.api.negocio.filtros;

public class FiltroBemImovel extends FiltroBem {
	private static final long serialVersionUID = 2181651002632731691L;

	private Short codigoTipoLocalizacaoBem;
	private Short codigoTipoUsoBem;
	private String denominacao;
	private String matricula;
	private String nirf;
	private String incra;

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

	public String getDenominacao() {
		return denominacao;
	}

	public void setDenominacao(String denominacao) {
		this.denominacao = denominacao;
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

}