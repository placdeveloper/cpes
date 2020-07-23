/*
 * SICOOB
 * 
 * AbstractEntidadeIncluirVO.java(br.com.sicoob.capes.api.negocio.vo.AbstractEntidadeIncluirVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

/**
 * Classe abstrata que deve ser herdada caso a entidade necessite incluir 
 * um registro
 * 
 * @author Marcelo.Onofre
 */
public abstract class AbstractEntidadeIncluirVO extends AbstractPessoaVO {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1126670732720790474L;

	/** O atributo cpf cnpj. */
	private String cpfCnpj;

	/** O atributo id instituicao. */
	private Integer idInstituicao;

	/**
	 * Obtém o CPF/CNPJ da pessoa responsável. OBS: Atributo não retorna na
	 * consulta. Utilizado apenas na inclusão da entidade
	 * 
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * Seta o CPF/CNPJ da pessoa responsável. OBS: Atributo não retorna na
	 * consulta. Utilizado apenas na inclusão da entidade
	 * 
	 * @param cpfCnpj
	 *            the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * Obtém o ID da instituição da pessoa responsável. OBS: Atributo não
	 * retorna na consulta. Utilizado apenas na inclusão da entidade
	 * 
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Seta o ID da instituição da pessoa responsável. OBS: Atributo não retorna
	 * na consulta. Utilizado apenas na inclusão da entidade
	 * 
	 * @param idInstituicao
	 *            the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
}
