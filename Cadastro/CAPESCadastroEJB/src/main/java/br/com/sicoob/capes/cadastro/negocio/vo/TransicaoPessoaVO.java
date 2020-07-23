/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.vo;

import java.io.Serializable;

/**
 * @author erico.junior
 *
 */
public class TransicaoPessoaVO implements Serializable {

	/** Serial UID.*/
	private static final long serialVersionUID = -4013937796420187710L;

	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo idUnidadeInst. */
	private Integer idUnidadeInst;
	
	/** O atributo idPessoaLegado. */
	private Integer idPessoaLegado;
	
	/** O atributo nomePessoaLegado. */
	private String nomePessoaLegado;

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
	public void setIdUnidadeInst(Integer idUnidadeInstituicao) {
		this.idUnidadeInst = idUnidadeInstituicao;
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
