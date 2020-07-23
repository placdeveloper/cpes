package br.com.sicoob.capes.cadastro.negocio.dto;

public class FuncionarioDTO {

	private Integer idPessoaLegado;
	private String nomePessoaLegado;

	public FuncionarioDTO(Integer idPessoaLegado, String nomePessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
		this.nomePessoaLegado = nomePessoaLegado;
	}

	public FuncionarioDTO() {
	}

	/**
	 * @return the idPessoaLegado
	 */
	public Integer getIdPessoaLegado() {
		return idPessoaLegado;
	}

	/**
	 * @param idPessoaLegado the idPessoaLegado to set
	 */
	public void setIdPessoaLegado(Integer idPessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
	}

	/**
	 * @return the nomePessoaLegado
	 */
	public String getNomePessoaLegado() {
		return nomePessoaLegado;
	}

	/**
	 * @param nomePessoaLegado the nomePessoaLegado to set
	 */
	public void setNomePessoaLegado(String nomePessoaLegado) {
		this.nomePessoaLegado = nomePessoaLegado;
	}
}
