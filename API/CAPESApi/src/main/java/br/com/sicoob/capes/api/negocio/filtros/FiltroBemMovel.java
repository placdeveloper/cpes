package br.com.sicoob.capes.api.negocio.filtros;


public class FiltroBemMovel extends FiltroBem {
	private static final long serialVersionUID = 6207161456694318166L;

	private String numeroChassi;
	private String renavam;
	private String placa;
	private String uf;
	private String inscricaoEmbarcacao;
	private String matriculaAeronave;
	
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

	public String getInscricaoEmbarcacao() {
		return inscricaoEmbarcacao;
	}

	public void setInscricaoEmbarcacao(String inscricaoEmbarcacao) {
		this.inscricaoEmbarcacao = inscricaoEmbarcacao;
	}

	public String getMatriculaAeronave() {
		return matriculaAeronave;
	}

	public void setMatriculaAeronave(String matriculaAeronave) {
		this.matriculaAeronave = matriculaAeronave;
	}

}