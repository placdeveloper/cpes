/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.vo;

import java.io.Serializable;

/**
 * DTO utilizado para consulta na alteração do documento.
 * @author erico.junior
 */
public class ConsultaAlteracaoDocumentoVO  implements Serializable {

	/** Serial UID.*/
	private static final long serialVersionUID = 4684444072306237018L;
	
	/** Pessoa **/
	private Integer idPessoa;
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** Transição **/
	private Integer idInstituicao;
	
	/** O atributo idUnidadeInst. */
	private Integer idUnidadeInst;
	
	/** O atributo nomePessoaLegado. */
	private String nomePessoaLegado;
	
	/** Instituição **/
	private String nomeInstituicao;
	
	/** O atributo numeroInstituicao. */
	private String numeroInstituicao; 
	
	/** Compartilhamento **/
	private Short codigoCompartilhamento;
	
	/** O atributo descricaoCompartilhamento. */
	private String descricaoCompartilhamento;
	
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
	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	/**
	 * @return the idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}
	/**
	 * @param idUnidadeInst the idUnidadeInst to set
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
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
	/**
	 * @return the nomeInstituicao
	 */
	public String getNomeInstituicao() {
		return nomeInstituicao;
	}
	/**
	 * @param nomeInstituicao the nomeInstituicao to set
	 */
	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}
	/**
	 * @return the numeroInstituicao
	 */
	public String getNumeroInstituicao() {
		return numeroInstituicao;
	}
	/**
	 * @param numeroInstituicao the numeroInstituicao to set
	 */
	public void setNumeroInstituicao(String numeroInstituicao) {
		this.numeroInstituicao = numeroInstituicao;
	}
	/**
	 * @return the codigoCompartilhamento
	 */
	public Short getCodigoCompartilhamento() {
		return codigoCompartilhamento;
	}
	/**
	 * @param codigoCompartilhamento the codigoCompartilhamento to set
	 */
	public void setCodigoCompartilhamento(Short codigoCompartilhamento) {
		this.codigoCompartilhamento = codigoCompartilhamento;
	}
	/**
	 * @return the descricaoCompartilhamento
	 */
	public String getDescricaoCompartilhamento() {
		return descricaoCompartilhamento;
	}
	/**
	 * @param descricaoCompartilhamento the descricaoCompartilhamento to set
	 */
	public void setDescricaoCompartilhamento(String descricaoCompartilhamento) {
		this.descricaoCompartilhamento = descricaoCompartilhamento;
	}	
}
