package br.com.sicoob.capes.api.negocio.filtros;

public class FiltroEndereco extends FiltroConsultaAPIAbstrato {
	private static final long serialVersionUID = 9131292759517448171L;

	private String cep;
	private String descricao;
	private String numero;
	private String bairro;
	private Integer idLocalidade;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

}