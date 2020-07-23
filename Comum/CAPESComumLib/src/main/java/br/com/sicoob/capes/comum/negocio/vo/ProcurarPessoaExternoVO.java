package br.com.sicoob.capes.comum.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * Classe utilizada pelo componente "Procurar Pessoas Externo CAPES"
 */
public class ProcurarPessoaExternoVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 8081667210081542092L;

	/** O atributo idPessoa. */
	private Integer idPessoa;
	
	/** O atributo idPessoaLegado. */
	private Integer idPessoaLegado;
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo nome. */
	private String nome;
	
	/** O atributo apelido. */
	private String apelido;
	
	/** O atributo idTipoPessoa. */
	private Short idTipoPessoa;

	// INFORMAÇÕES DE FILTRO PARA USO NAS CONSULTAS
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo somenteClientes. */
	private Boolean somenteClientes;
	
	/** O atributo procurarBancoob. */
	private Boolean procurarBancoob = Boolean.FALSE;
	
	/** O atributo numeroCooperativa. */
	private Integer numeroCooperativa;

	/**
	 * Instancia um novo ProcurarPessoaExternoVO.
	 */
	public ProcurarPessoaExternoVO() {

	}

	/**
	 * Instancia um novo ProcurarPessoaExternoVO.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param cpfCnpj o valor de cpf cnpj
	 * @param nome o valor de nome
	 * @param apelido o valor de apelido
	 * @param idPessoaLegado o valor de id pessoa legado
	 * @param idTipoPessoa o valor de id tipo pessoa
	 */
	public ProcurarPessoaExternoVO(Integer idPessoa, String cpfCnpj, String nome, String apelido, Integer idPessoaLegado, Short idTipoPessoa) {
		this.idPessoa = idPessoa;
		this.cpfCnpj = cpfCnpj;
		this.nome = nome;
		this.apelido = apelido;
		this.idPessoaLegado = idPessoaLegado;
		this.idTipoPessoa = idTipoPessoa;
	}

	/**
	 * Recupera o valor de idPessoa.
	 *
	 * @return o valor de idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Define o valor de idPessoa.
	 *
	 * @param idPessoa o novo valor de idPessoa
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * Recupera o valor de idPessoaLegado.
	 *
	 * @return o valor de idPessoaLegado
	 */
	public Integer getIdPessoaLegado() {
		return idPessoaLegado;
	}

	/**
	 * Define o valor de idPessoaLegado.
	 *
	 * @param idPessoaLegado o novo valor de idPessoaLegado
	 */
	public void setIdPessoaLegado(Integer idPessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
	}

	/**
	 * Recupera o valor de cpfCnpj.
	 *
	 * @return o valor de cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * Define o valor de cpfCnpj.
	 *
	 * @param cpfCnpj o novo valor de cpfCnpj
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Recupera o valor de nome.
	 *
	 * @return o valor de nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Define o valor de nome.
	 *
	 * @param nome o novo valor de nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Recupera o valor de apelido.
	 *
	 * @return o valor de apelido
	 */
	public String getApelido() {
		return apelido;
	}

	/**
	 * Define o valor de apelido.
	 *
	 * @param apelido o novo valor de apelido
	 */
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	/**
	 * Recupera o valor de idTipoPessoa.
	 *
	 * @return o valor de idTipoPessoa
	 */
	public Short getIdTipoPessoa() {
		return idTipoPessoa;
	}

	/**
	 * Define o valor de idTipoPessoa.
	 *
	 * @param idTipoPessoa o novo valor de idTipoPessoa
	 */
	public void setIdTipoPessoa(Short idTipoPessoa) {
		this.idTipoPessoa = idTipoPessoa;
	}

	/**
	 * Recupera o valor de somenteClientes.
	 *
	 * @return o valor de somenteClientes
	 */
	public Boolean getSomenteClientes() {
		return somenteClientes;
	}

	/**
	 * Define o valor de somenteClientes.
	 *
	 * @param somenteClientes o novo valor de somenteClientes
	 */
	public void setSomenteClientes(Boolean somenteClientes) {
		this.somenteClientes = somenteClientes;
	}

	/**
	 * Recupera o valor de procurarBancoob.
	 *
	 * @return o valor de procurarBancoob
	 */
	public Boolean getProcurarBancoob() {
		return procurarBancoob;
	}

	/**
	 * Define o valor de procurarBancoob.
	 *
	 * @param procurarBancoob o novo valor de procurarBancoob
	 */
	public void setProcurarBancoob(Boolean procurarBancoob) {
		this.procurarBancoob = procurarBancoob;
	}

	/**
	 * Recupera o valor de idInstituicao.
	 *
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Define o valor de idInstituicao.
	 *
	 * @param idInstituicao o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera o valor de numeroCooperativa.
	 *
	 * @return o valor de numeroCooperativa
	 */
	public Integer getNumeroCooperativa() {
		return numeroCooperativa;
	}

	/**
	 * Define o valor de numeroCooperativa.
	 *
	 * @param numeroCooperativa o novo valor de numeroCooperativa
	 */
	public void setNumeroCooperativa(Integer numeroCooperativa) {
		this.numeroCooperativa = numeroCooperativa;
	}

}