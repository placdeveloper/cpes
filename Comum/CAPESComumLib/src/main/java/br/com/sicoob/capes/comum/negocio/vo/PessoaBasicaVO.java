package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe PessoaBasicaVO.
 */
public class PessoaBasicaVO extends BancoobVo {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 7935426054110163494L;

	/** O atributo id pessoa. */
	private Integer idPessoa;
	
	/** O atributo cpf cnpj. */
	private String cpfCnpj;
	
	/** O atributo nome pessoa. */
	private String nomePessoa;

	/**
	 * Instancia um novo PessoaBasicaVO.
	 */
	public PessoaBasicaVO() {
		super();
	}
	
	/**
	 * Instancia um novo PessoaBasicaVO.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param cpfCnpj o valor de cpf cnpj
	 * @param nomePessoa o valor de nome pessoa
	 */
	public PessoaBasicaVO(Integer idPessoa, String cpfCnpj, String nomePessoa) {
		super();
		this.idPessoa = idPessoa;
		this.cpfCnpj = cpfCnpj;
		this.nomePessoa = nomePessoa;
	}
	
	/**
	 * Recupera id pessoa.
	 * 
	 * @return id pessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Preenche id pessoa.
	 * 
	 * @param idPessoa
	 *            o novo id pessoa
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	
	/**
	 * Recupera cpf cnpj.
	 * 
	 * @return cpf cnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * Preenche cpf cnpj.
	 * 
	 * @param cpfCnpj
	 *            o novo cpf cnpj
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}
	
	/**
	 * Recupera nome pessoa.
	 * 
	 * @return nome pessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * Preenche nome pessoa.
	 * 
	 * @param nomePessoa
	 *            o novo nome pessoa
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

}