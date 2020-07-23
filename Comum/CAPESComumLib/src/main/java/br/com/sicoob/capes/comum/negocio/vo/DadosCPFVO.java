/*
 * SICOOB
 * 
 * DadosCPFVO.java(br.com.sicoob.capes.comum.negocio.vo.DadosCPFVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import java.util.Date;

import br.com.sicoob.capes.comum.negocio.enums.TipoSexoEnum;

/**
 * TODO javadoc
 * 
 * 
 * @author Rodrigo.Chaves
 */
public class DadosCPFVO extends DadosReceitaFederalVO {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 3316932360468873169L;
	
	/** O atributo cod residente exterior. */
	private Integer codResidenteExterior;
	
	/** O atributo nome mae. */
	private String nomeMae;
	
	/** O atributo data nascimento. */
	private Date dataNascimento;
	
	/** O atributo cod sexo. */
	private Integer codSexo;
	
	/** O atributo num titulo eleitor. */
	private String numTituloEleitor;
	
	/** O atributo ano obito. */
	private Integer anoObito;
	
	/** O atributo desc situacao cadastral. */
	private String descSituacaoCadastral;
	
	/** O atributo desc residente exterior. */
	private String descResidenteExterior;
	
	/** O atributo desc sexo. */
	private String descSexo;
	
	/** O atributo nome. */
	private String nome;
	
	/** O atributo cpf. */
	private String cpf;

	/**
	 * Recupera cod residente exterior.
	 * 
	 * @return cod residente exterior
	 */
	public Integer getCodResidenteExterior() {
		return codResidenteExterior;
	}

	/**
	 * Preenche cod residente exterior.
	 * 
	 * @param codResidenteExterior
	 *            o novo cod residente exterior
	 */
	public void setCodResidenteExterior(Integer codResidenteExterior) {
		this.codResidenteExterior = codResidenteExterior;
	}

	/**
	 * Recupera nome mae.
	 * 
	 * @return nome mae
	 */
	public String getNomeMae() {
		return nomeMae;
	}

	/**
	 * Preenche nome mae.
	 * 
	 * @param nomeMae
	 *            o novo nome mae
	 */
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	/**
	 * Recupera data nascimento.
	 * 
	 * @return data nascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Preenche data nascimento.
	 * 
	 * @param dataNascimento
	 *            o novo data nascimento
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Recupera cod sexo.
	 * 
	 * @return cod sexo
	 */
	public Integer getCodSexo() {
		return codSexo;
	}

	/**
	 * Preenche cod sexo.
	 * 
	 * @param codSexo
	 *            o novo cod sexo
	 */
	public void setCodSexo(Integer codSexo) {
		this.codSexo = codSexo;
	}

	/**
	 * Recupera num titulo eleitor.
	 * 
	 * @return num titulo eleitor
	 */
	public String getNumTituloEleitor() {
		return numTituloEleitor;
	}

	/**
	 * Preenche num titulo eleitor.
	 * 
	 * @param numTituloEleitor
	 *            o novo num titulo eleitor
	 */
	public void setNumTituloEleitor(String numTituloEleitor) {
		this.numTituloEleitor = numTituloEleitor;
	}

	/**
	 * Recupera ano obito.
	 * 
	 * @return ano obito
	 */
	public Integer getAnoObito() {
		return anoObito;
	}

	/**
	 * Preenche ano obito.
	 * 
	 * @param anoObito
	 *            o novo ano obito
	 */
	public void setAnoObito(Integer anoObito) {
		this.anoObito = anoObito;
	}

	/**
	 * Recupera desc situacao cadastral.
	 * 
	 * @return desc situacao cadastral
	 */
	public String getDescSituacaoCadastral() {
		return descSituacaoCadastral;
	}

	/**
	 * Preenche desc situacao cadastral.
	 * 
	 * @param descSituacaoCadastral
	 *            o novo desc situacao cadastral
	 */
	public void setDescSituacaoCadastral(String descSituacaoCadastral) {
		this.descSituacaoCadastral = descSituacaoCadastral;
	}

	/**
	 * Recupera desc residente exterior.
	 * 
	 * @return desc residente exterior
	 */
	public String getDescResidenteExterior() {
		return descResidenteExterior;
	}

	/**
	 * Preenche desc residente exterior.
	 * 
	 * @param descResidenteExterior
	 *            o novo desc residente exterior
	 */
	public void setDescResidenteExterior(String descResidenteExterior) {
		this.descResidenteExterior = descResidenteExterior;
	}

	/**
	 * Recupera desc sexo.
	 * 
	 * @return desc sexo
	 */
	public String getDescSexo() {
		return descSexo;
	}

	/**
	 * Preenche desc sexo.
	 * 
	 * @param descSexo
	 *            o novo desc sexo
	 */
	public void setDescSexo(String descSexo) {
		this.descSexo = descSexo;
	}

	/** 
	 * {@inheritDoc}
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Preenche nome.
	 * 
	 * @param nome
	 *            o novo nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Preenche cpf.
	 * 
	 * @param cpf
	 *            o novo cpf
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	/**
	 * Recupera cpf.
	 * 
	 * @return cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getNumeroInscricao() {
		return getCpf();
	}

	/**
	 * Recupera tipo sexo.
	 * 
	 * @return tipo sexo
	 */
	public TipoSexoEnum getTipoSexo() {
		return TipoSexoEnum.recuperarPorCodigoRFB(getCodSexo());
	}

}
