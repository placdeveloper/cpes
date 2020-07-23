/*
 * SICOOB
 * 
 * InstituicaoResponsavelVO.java(br.com.sicoob.capes.api.negocio.vo.InstituicaoResponsavelVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.io.Serializable;

/**
 * @author Marcelo.Onofre
 */
public class InstituicaoResponsavelVO implements Serializable {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -254316355316554276L;

	/** O atributo id pessoa. */
	private Integer idPessoa;

	/** O atributo id instituicao. */
	private Integer idInstituicao;

	/** O atributo cpf cnpj. */
	private String cpfCnpj;

	/** O atributo cod compartilhamento cadastro. */
	private Short codCompartilhamentoCadastro;

	/**
	 * Cria uma nova instância de instituicao responsavel vo.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 */
	public InstituicaoResponsavelVO(Integer idPessoa, Integer idInstituicao) {
		this.idPessoa = idPessoa;
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Cria uma nova instância de instituicao responsavel vo.
	 */
	public InstituicaoResponsavelVO() {

	}

	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Preenche id instituicao.
	 * 
	 * @param idInstituicao
	 *            o novo id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
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
	 * Recupera cod compartilhamento cadastro.
	 * 
	 * @return cod compartilhamento cadastro
	 */
	public Short getCodCompartilhamentoCadastro() {
		return codCompartilhamentoCadastro;
	}

	/**
	 * Preenche cod compartilhamento cadastro.
	 * 
	 * @param codCompartilhamentoCadastro
	 *            o novo cod compartilhamento cadastro
	 */
	public void setCodCompartilhamentoCadastro(Short codCompartilhamentoCadastro) {
		this.codCompartilhamentoCadastro = codCompartilhamentoCadastro;
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

}