/*
 * SICOOB
 * 
 * DataUltimaAtualizacaoVO.java(br.com.sicoob.capes.comum.negocio.vo.DataUltimaAtualizacaoVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import java.util.Date;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * The Class DataUltimaAtualizacaoVO.
 */
public class DataUltimaAtualizacaoVO extends BancoobVo {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 2647758591002560266L;
	
	/** O atributo id pessoa. */
	private Integer idPessoa;
	
	/** O atributo cpf cnpj. */
	private String cpfCnpj;
	
	/** O atributo nome pessoa. */
	private String nomePessoa;
	
	/** O atributo data ultima atualizacao. */
	private Date dataUltimaAtualizacao;

	/**
	 * Cria uma nova instância de data ultima atualizacao vo.
	 */
	public DataUltimaAtualizacaoVO() {
	}
	
	/**
	 * Cria uma nova instância de data ultima atualizacao vo.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param nomePessoa
	 *            the nome pessoa
	 * @param dataUltimaAtualizacao
	 *            the data ultima atualizacao
	 */
	public DataUltimaAtualizacaoVO(Integer idPessoa, String cpfCnpj, String nomePessoa, Date dataUltimaAtualizacao) {

		super();
		this.idPessoa = idPessoa;
		this.cpfCnpj = cpfCnpj;
		this.nomePessoa = nomePessoa;
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
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

	/**
	 * Recupera data ultima atualizacao.
	 * 
	 * @return data ultima atualizacao
	 */
	public Date getDataUltimaAtualizacao() {
	
		return dataUltimaAtualizacao;
	}

	/**
	 * Preenche data ultima atualizacao.
	 * 
	 * @param dataUltimaAtualizacao
	 *            o novo data ultima atualizacao
	 */
	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
	
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}
	
}
