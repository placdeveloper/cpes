/*
 * SICOOB
 * 
 * ReferenciaPessoaVO.java(br.com.sicoob.capes.api.negocio.vo.ReferenciaPessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;


/**
 * @author Erico.Junior
 *
 */
public class ReferenciaPessoaVO extends AbstractPessoaVO {

	/** Serial UID.*/
	private static final long serialVersionUID = -7068483555677527754L;
	
	/** O atributo id referencia pessoa. */
	private Long idReferenciaPessoa;
	
	/** O atributo id pessoa. */
	private Integer idPessoa;
	
	/** O atributo ddd. */
	private Short ddd;
	
	/** O atributo telefone. */
	private String telefone;
	
	/** O atributo observacao. */
	private String observacao;
	
	// Pessoa de referência
	/** O atributo id pessoa referencia. */
	private Integer idPessoaReferencia;
	
	/** O atributo nome pessoa referencia. */
	private String nomePessoaReferencia;
	
	/** O atributo cpf cnpj. */
	private String cpfCnpj;
	
	// Tipo de Referência
	/** O atributo codigo tipo referencia. */
	private Short codigoTipoReferencia;
	
	/** O atributo descricao tipo referencia. */
	private String descricaoTipoReferencia;
	
	// Referência Bancária
	/** O atributo numero banco. */
	private String numeroBanco;
	
	/** O atributo numero agencia. */
	private String numeroAgencia;
	
	/** O atributo numero conta. */
	private String numeroConta;
	
	/**
	 * Cria uma nova instância de referencia pessoa vo.
	 */
	public ReferenciaPessoaVO() {
		super();
	}
	
	/**
	 * Cria uma nova instância de referencia pessoa vo.
	 * 
	 * @param idReferenciaPessoa
	 *            the id referencia pessoa
	 * @param idPessoa
	 *            the id pessoa
	 * @param ddd
	 *            the ddd
	 * @param telefone
	 *            the telefone
	 * @param observacao
	 *            the observacao
	 * @param idPessoaReferencia
	 *            the id pessoa referencia
	 * @param nomePessoaReferencia
	 *            the nome pessoa referencia
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param codigoTipoReferencia
	 *            the codigo tipo referencia
	 * @param descricaoTipoReferencia
	 *            the descricao tipo referencia
	 * @param numeroBanco
	 *            the numero banco
	 * @param numeroAgencia
	 *            the numero agencia
	 * @param numeroConta
	 *            the numero conta
	 */
	public ReferenciaPessoaVO(Long idReferenciaPessoa, Integer idPessoa,
			Short ddd, String telefone, String observacao,
			Integer idPessoaReferencia, String nomePessoaReferencia,
			String cpfCnpj, Short codigoTipoReferencia,
			String descricaoTipoReferencia, String numeroBanco,
			String numeroAgencia, String numeroConta) {
		super();
		this.idReferenciaPessoa = idReferenciaPessoa;
		this.idPessoa = idPessoa;
		this.ddd = ddd;
		this.telefone = telefone;
		this.observacao = observacao;
		this.idPessoaReferencia = idPessoaReferencia;
		this.nomePessoaReferencia = nomePessoaReferencia;
		this.cpfCnpj = cpfCnpj;
		this.codigoTipoReferencia = codigoTipoReferencia;
		this.descricaoTipoReferencia = descricaoTipoReferencia;
		this.numeroBanco = numeroBanco;
		this.numeroAgencia = numeroAgencia;
		this.numeroConta = numeroConta;
	}
	/**
	 * @return the idReferenciaPessoa
	 */
	public Long getIdReferenciaPessoa() {
		return idReferenciaPessoa;
	}
	/**
	 * @param idReferenciaPessoa the idReferenciaPessoa to set
	 */
	public void setIdReferenciaPessoa(Long idReferenciaPessoa) {
		this.idReferenciaPessoa = idReferenciaPessoa;
	}
	/**
	 * @return the ddd
	 */
	public Short getDdd() {
		return ddd;
	}
	/**
	 * @param ddd the ddd to set
	 */
	public void setDdd(Short ddd) {
		this.ddd = ddd;
	}
	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}
	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	/**
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}
	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	/**
	 * @return the idPessoaReferencia
	 */
	public Integer getIdPessoaReferencia() {
		return idPessoaReferencia;
	}
	/**
	 * @param idPessoaReferencia the idPessoaReferencia to set
	 */
	public void setIdPessoaReferencia(Integer idPessoaReferencia) {
		this.idPessoaReferencia = idPessoaReferencia;
	}
	/**
	 * @return the nomePessoaReferencia
	 */
	public String getNomePessoaReferencia() {
		return nomePessoaReferencia;
	}
	/**
	 * @param nomePessoaReferencia the nomePessoaReferencia to set
	 */
	public void setNomePessoaReferencia(String nomePessoaReferencia) {
		this.nomePessoaReferencia = nomePessoaReferencia;
	}
	/**
	 * @return the codigoTipoReferencia
	 */
	public Short getCodigoTipoReferencia() {
		return codigoTipoReferencia;
	}
	/**
	 * @param codigoTipoReferencia the codigoTipoReferencia to set
	 */
	public void setCodigoTipoReferencia(Short codigoTipoReferencia) {
		this.codigoTipoReferencia = codigoTipoReferencia;
	}
	/**
	 * @return the descricaoTipoReferencia
	 */
	public String getDescricaoTipoReferencia() {
		return descricaoTipoReferencia;
	}
	/**
	 * @param descricaoTipoReferencia the descricaoTipoReferencia to set
	 */
	public void setDescricaoTipoReferencia(String descricaoTipoReferencia) {
		this.descricaoTipoReferencia = descricaoTipoReferencia;
	}
	/**
	 * @return the numeroBanco
	 */
	public String getNumeroBanco() {
		return numeroBanco;
	}
	/**
	 * @param numeroBanco the numeroBanco to set
	 */
	public void setNumeroBanco(String numeroBanco) {
		this.numeroBanco = numeroBanco;
	}
	/**
	 * @return the numeroAgencia
	 */
	public String getNumeroAgencia() {
		return numeroAgencia;
	}
	/**
	 * @param numeroAgencia the numeroAgencia to set
	 */
	public void setNumeroAgencia(String numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
	/**
	 * @return the numeroConta
	 */
	public String getNumeroConta() {
		return numeroConta;
	}
	/**
	 * @param numeroConta the numeroConta to set
	 */
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	/**
	 * @return the idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}
	/**
	 * @param idPessoa the idPessoa to set
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}
	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}
	/**
	 * @param cpfCnpj the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

}