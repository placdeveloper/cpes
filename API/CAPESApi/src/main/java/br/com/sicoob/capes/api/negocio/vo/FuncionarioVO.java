/*
 * SICOOB
 * 
 * FuncionarioVO.java(br.com.sicoob.capes.api.negocio.vo.FuncionarioVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * @author Lucas.Borges
 */
public class FuncionarioVO extends BancoobDto{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 8667050917842675796L;

	// FUNCIONARIO
	/** O atributo id funcionario. */
	private Integer idFuncionario;

	/** O atributo matricula. */
	private String matricula;
	
	/** O atributo data admissao. */
	private Date dataAdmissao;

	/** O atributo data desligamento. */
	private Date dataDesligamento;
	
	/** O atributo id unidade inst. */
	private Integer idUnidadeInst;
	
	// FUNCAO
	/** O atributo id funcao. */
	private Short idFuncao;
	
	/** O atributo descricao funcao. */
	private String descricaoFuncao;
	
	// NUCLEO
	/** O atributo id nucleo. */
	private Integer idNucleo;
	
	/** O atributo descricao nucleo. */
	private String descricaoNucleo;
	
	/** O atributo num nucleo. */
	private Integer numNucleo;
	
	// PESSOA
	/** O atributo cpf cnpj pessoa. */
	private String cpfCnpjPessoa;
	
	/** O atributo cod tipo pessoa. */
	private Short codTipoPessoa;
	
	/** O atributo nome pessoa. */
	private String nomePessoa;
	
	/**
	 * Cria uma nova instância de funcionario vo.
	 */
	public FuncionarioVO() {

	}

	/**
	 * Cria uma nova instância de funcionario vo.
	 * 
	 * @param idFuncionario
	 *            the id funcionario
	 * @param matricula
	 *            the matricula
	 * @param dataAdmissao
	 *            the data admissao
	 * @param dataDesligamento
	 *            the data desligamento
	 * @param idUnidadeInst
	 *            the id unidade inst
	 * @param idFuncao
	 *            the id funcao
	 * @param descricaoFuncao
	 *            the descricao funcao
	 * @param idNucleo
	 *            the id nucleo
	 * @param descricaoNucleo
	 *            the descricao nucleo
	 * @param numNucleo
	 *            the num nucleo
	 * @param cpfCnpjPessoa
	 *            the cpf cnpj pessoa
	 * @param codTipoPessoa
	 *            the cod tipo pessoa
	 * @param nomePessoa
	 *            the nome pessoa
	 */
	public FuncionarioVO(Integer idFuncionario, String matricula,
			Date dataAdmissao, Date dataDesligamento, Integer idUnidadeInst,
			Short idFuncao, String descricaoFuncao, Integer idNucleo,
			String descricaoNucleo, Integer numNucleo, String cpfCnpjPessoa,
			Short codTipoPessoa, String nomePessoa) {
		this.idFuncionario = idFuncionario;
		this.matricula = matricula;
		this.dataAdmissao = dataAdmissao;
		this.dataDesligamento = dataDesligamento;
		this.idUnidadeInst = idUnidadeInst;
		this.idFuncao = idFuncao;
		this.descricaoFuncao = descricaoFuncao;
		this.idNucleo = idNucleo;
		this.descricaoNucleo = descricaoNucleo;
		this.numNucleo = numNucleo;
		this.cpfCnpjPessoa = cpfCnpjPessoa;
		this.codTipoPessoa = codTipoPessoa;
		this.nomePessoa = nomePessoa;
	}

	/**
	 * Recupera id funcionario.
	 * 
	 * @return id funcionario
	 */
	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	/**
	 * Preenche id funcionario.
	 * 
	 * @param idFuncionario
	 *            o novo id funcionario
	 */
	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	/**
	 * Recupera matricula.
	 * 
	 * @return matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Preenche matricula.
	 * 
	 * @param matricula
	 *            o novo matricula
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Recupera data admissao.
	 * 
	 * @return data admissao
	 */
	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	/**
	 * Preenche data admissao.
	 * 
	 * @param dataAdmissao
	 *            o novo data admissao
	 */
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	/**
	 * Recupera data desligamento.
	 * 
	 * @return data desligamento
	 */
	public Date getDataDesligamento() {
		return dataDesligamento;
	}

	/**
	 * Preenche data desligamento.
	 * 
	 * @param dataDesligamento
	 *            o novo data desligamento
	 */
	public void setDataDesligamento(Date dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	/**
	 * Recupera id unidade inst.
	 * 
	 * @return id unidade inst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * Preenche id unidade inst.
	 * 
	 * @param idUnidadeInst
	 *            o novo id unidade inst
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * Recupera id funcao.
	 * 
	 * @return id funcao
	 */
	public Short getIdFuncao() {
		return idFuncao;
	}

	/**
	 * Preenche id funcao.
	 * 
	 * @param idFuncao
	 *            o novo id funcao
	 */
	public void setIdFuncao(Short idFuncao) {
		this.idFuncao = idFuncao;
	}

	/**
	 * Recupera descricao funcao.
	 * 
	 * @return descricao funcao
	 */
	public String getDescricaoFuncao() {
		return descricaoFuncao;
	}

	/**
	 * Preenche descricao funcao.
	 * 
	 * @param descricaoFuncao
	 *            o novo descricao funcao
	 */
	public void setDescricaoFuncao(String descricaoFuncao) {
		this.descricaoFuncao = descricaoFuncao;
	}

	/**
	 * Recupera id nucleo.
	 * 
	 * @return id nucleo
	 */
	public Integer getIdNucleo() {
		return idNucleo;
	}

	/**
	 * Preenche id nucleo.
	 * 
	 * @param idNucleo
	 *            o novo id nucleo
	 */
	public void setIdNucleo(Integer idNucleo) {
		this.idNucleo = idNucleo;
	}

	/**
	 * Recupera descricao nucleo.
	 * 
	 * @return descricao nucleo
	 */
	public String getDescricaoNucleo() {
		return descricaoNucleo;
	}

	/**
	 * Preenche descricao nucleo.
	 * 
	 * @param descricaoNucleo
	 *            o novo descricao nucleo
	 */
	public void setDescricaoNucleo(String descricaoNucleo) {
		this.descricaoNucleo = descricaoNucleo;
	}

	/**
	 * Recupera num nucleo.
	 * 
	 * @return num nucleo
	 */
	public Integer getNumNucleo() {
		return numNucleo;
	}

	/**
	 * Preenche num nucleo.
	 * 
	 * @param numNucleo
	 *            o novo num nucleo
	 */
	public void setNumNucleo(Integer numNucleo) {
		this.numNucleo = numNucleo;
	}

	/**
	 * Recupera cpf cnpj pessoa.
	 * 
	 * @return cpf cnpj pessoa
	 */
	public String getCpfCnpjPessoa() {
		return cpfCnpjPessoa;
	}

	/**
	 * Preenche cpf cnpj pessoa.
	 * 
	 * @param cpfCnpjPessoa
	 *            o novo cpf cnpj pessoa
	 */
	public void setCpfCnpjPessoa(String cpfCnpjPessoa) {
		this.cpfCnpjPessoa = cpfCnpjPessoa;
	}

	/**
	 * Recupera cod tipo pessoa.
	 * 
	 * @return cod tipo pessoa
	 */
	public Short getCodTipoPessoa() {
		return codTipoPessoa;
	}

	/**
	 * Preenche cod tipo pessoa.
	 * 
	 * @param codTipoPessoa
	 *            o novo cod tipo pessoa
	 */
	public void setCodTipoPessoa(Short codTipoPessoa) {
		this.codTipoPessoa = codTipoPessoa;
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