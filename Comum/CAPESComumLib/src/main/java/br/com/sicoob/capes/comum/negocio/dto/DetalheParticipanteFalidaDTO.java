/*
 * SICOOB
 * 
 * DetalheParticipanteFalidaDTO.java(br.com.sicoob.capes.comum.negocio.dto.DetalheParticipanteFalidaDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

/**
 * DTO que representa o detalhe de Participante de empresa falida.
 * @author erico.junior
 *
 */
public class DetalheParticipanteFalidaDTO extends DetalheAnotacaoDTO {

	/** Serial UID.*/
	private static final long serialVersionUID = -8734541944513385521L;

	/** Natureza da ação. */
	private String natureza;
	
	/** Número da vara civil. */
	private Integer varaCivil;
	
	/** A qualificação da pessoa na empresa. */
	private String qualificacao;
	
	/** O Cnpj da empresa falida. */
	private String cnpjEmpresa;

	/** O nome da empresa falida. */
	private String nomeEmpresa;

	/**
	 * @return the natureza
	 */
	public String getNatureza() {
		return natureza;
	}

	/**
	 * @param natureza the natureza to set
	 */
	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	/**
	 * @return the varaCivil
	 */
	public Integer getVaraCivil() {
		return varaCivil;
	}

	/**
	 * @param varaCivil the varaCivil to set
	 */
	public void setVaraCivil(Integer varaCivil) {
		this.varaCivil = varaCivil;
	}

	/**
	 * @return the qualificacao
	 */
	public String getQualificacao() {
		return qualificacao;
	}

	/**
	 * @param qualificacao the qualificacaoo to set
	 */
	public void setQualificacao(String qualificacao) {
		this.qualificacao = qualificacao;
	}

	/**
	 * @return the cnpjEmpresa
	 */
	public String getCnpjEmpresa() {
		return cnpjEmpresa;
	}

	/**
	 * @param cnpjEmpresa the cnpjEmpresa to set
	 */
	public void setCnpjEmpresa(String cnpjEmpresa) {
		this.cnpjEmpresa = cnpjEmpresa;
	}

	/**
	 * @return the nomeEmpresa
	 */
	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	/**
	 * @param nomeEmpresa the nomeEmpresa to set
	 */
	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

}
