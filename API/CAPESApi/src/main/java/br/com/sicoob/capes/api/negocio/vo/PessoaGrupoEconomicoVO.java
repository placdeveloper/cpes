package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * VO com as pessoas de um grupo economico.
 * 
 * @author bruno.carneiro
 * 
 */
public class PessoaGrupoEconomicoVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -1282418493849712217L;

	/** O atributo idPessoa. */
	private Integer idPessoa;
	
	/** O atributo nomePessoa. */
	private String nomePessoa;
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;

	/** O atributo idGrupoEconomico. */
	private Integer idGrupoEconomico;
	
	/** O atributo nomeGrupoEconomico. */
	private String nomeGrupoEconomico;

	/**
	 * Instancia um novo PessoaGrupoEconomicoVO.
	 */
	public PessoaGrupoEconomicoVO() {

	}

	/**
	 * Instancia um novo PessoaGrupoEconomicoVO.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param cpfCnpj o valor de cpf cnpj
	 * @param nomePessoa o valor de nome pessoa
	 * @param idGrupoEconomico o valor de id grupo economico
	 * @param nomeGrupoEconomico o valor de nome grupo economico
	 */
	public PessoaGrupoEconomicoVO(Integer idPessoa, String cpfCnpj, String nomePessoa, Integer idGrupoEconomico, String nomeGrupoEconomico) {
		this.idPessoa = idPessoa;
		this.cpfCnpj = cpfCnpj;
		this.nomePessoa = nomePessoa;
		this.idGrupoEconomico = idGrupoEconomico;
		this.nomeGrupoEconomico = nomeGrupoEconomico;
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
	 * Recupera o valor de nomePessoa.
	 *
	 * @return o valor de nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * Define o valor de nomePessoa.
	 *
	 * @param nomePessoa o novo valor de nomePessoa
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
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
	 * Recupera o valor de idGrupoEconomico.
	 *
	 * @return o valor de idGrupoEconomico
	 */
	public Integer getIdGrupoEconomico() {
		return idGrupoEconomico;
	}

	/**
	 * Define o valor de idGrupoEconomico.
	 *
	 * @param idGrupoEconomico o novo valor de idGrupoEconomico
	 */
	public void setIdGrupoEconomico(Integer idGrupoEconomico) {
		this.idGrupoEconomico = idGrupoEconomico;
	}

	/**
	 * Recupera o valor de nomeGrupoEconomico.
	 *
	 * @return o valor de nomeGrupoEconomico
	 */
	public String getNomeGrupoEconomico() {
		return nomeGrupoEconomico;
	}

	/**
	 * Define o valor de nomeGrupoEconomico.
	 *
	 * @param nomeGrupoEconomico o novo valor de nomeGrupoEconomico
	 */
	public void setNomeGrupoEconomico(String nomeGrupoEconomico) {
		this.nomeGrupoEconomico = nomeGrupoEconomico;
	}

}