package br.com.sicoob.capes.relatorio.negocio.dto;

import java.io.Serializable;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe RelCompartilhamentoDTO.
 */
public class RelatorioCadastroCompartilhadoDTO extends BancoobDto implements Serializable {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** O atributo cpfCnpj. */
	private String cpfCnpj;
	
	/** O atributo numCentral. */
	private Integer numCentral;
	
	/** O atributo numSingular. */
	private Integer numSingular;
	
	/** O atributo numCoopLogada. */
	private Integer idInstituicao;
	
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
	 * Recupera o valor de numCentral.
	 *
	 * @return o valor de numCentral
	 */
	public Integer getNumCentral() {
		return numCentral;
	}
	
	/**
	 * Define o valor de numCentral.
	 *
	 * @param numCentral o novo valor de numCentral
	 */
	public void setNumCentral(Integer numCentral) {
		this.numCentral = numCentral;
	}
	
	/**
	 * Recupera o valor de numSingular.
	 *
	 * @return o valor de numSingular
	 */
	public Integer getNumSingular() {
		return numSingular;
	}
	
	/**
	 * Define o valor de numSingular.
	 *
	 * @param numSingular o novo valor de numSingular
	 */
	public void setNumSingular(Integer numSingular) {
		this.numSingular = numSingular;
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
}
