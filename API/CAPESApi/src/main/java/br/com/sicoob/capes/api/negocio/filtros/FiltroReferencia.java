package br.com.sicoob.capes.api.negocio.filtros;

public class FiltroReferencia extends FiltroConsultaAPIAbstrato {
	private static final long serialVersionUID = -6159369155157790969L;

	private Short ddd;
	private String telefone;
	private String observacao;
	private Integer idPessoaReferencia;
	private String nomePessoaReferencia;
	private String cpfCnpjPessoaReferencia;
	private Short codigoTipoReferencia;

	public Short getDdd() {
		return ddd;
	}

	public void setDdd(Short ddd) {
		this.ddd = ddd;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getIdPessoaReferencia() {
		return idPessoaReferencia;
	}

	public void setIdPessoaReferencia(Integer idPessoaReferencia) {
		this.idPessoaReferencia = idPessoaReferencia;
	}

	public String getNomePessoaReferencia() {
		return nomePessoaReferencia;
	}

	public void setNomePessoaReferencia(String nomePessoaReferencia) {
		this.nomePessoaReferencia = nomePessoaReferencia;
	}

	public String getCpfCnpjPessoaReferencia() {
		return cpfCnpjPessoaReferencia;
	}

	public void setCpfCnpjPessoaReferencia(String cpfCnpjPessoaReferencia) {
		this.cpfCnpjPessoaReferencia = cpfCnpjPessoaReferencia;
	}

	public Short getCodigoTipoReferencia() {
		return codigoTipoReferencia;
	}

	public void setCodigoTipoReferencia(Short codigoTipoReferencia) {
		this.codigoTipoReferencia = codigoTipoReferencia;
	}

}