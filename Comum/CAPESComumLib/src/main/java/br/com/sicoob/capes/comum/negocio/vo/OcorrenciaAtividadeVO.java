/*
 * SICOOB
 * 
 * OcorrenciaAtividadeVO.java(br.com.sicoob.capes.comum.negocio.vo.OcorrenciaAtividadeVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 18/02/2011
 * 
 * @author Rodrigo.Chaves
 */
public class OcorrenciaAtividadeVO implements Serializable {

	/** Serial UID */
	private static final long serialVersionUID = 272579353282257118L;

	/** O atributo data. */
	private Date data;

	/** O atributo id ocorrencia atividade. */
	private Integer idOcorrenciaAtividade;

	/** O atributo id ocorrencia processo. */
	private Integer idOcorrenciaProcesso;

	/** O atributo id usuario. */
	private String idUsuario;

	/** O atributo id Procedimento. */
	private Integer idProcedimento;

	/** O atributo nome procedimento. */
	private String nomeProcedimento;

	/** O atributo justificativa. */
	private String justificativa;

	/**
	 * Construtor
	 */
	public OcorrenciaAtividadeVO() {
	}

	/**
	 * @return o valor de dataHoraInicioAtividade
	 */
	public Date getData() {
		return data;
	}

	/**
	 * @param data
	 *            o valor de dataHoraInicioAtividade
	 */
	public void setData(Date data) {
		this.data = data;
	}

	/**
	 * @return o valor de idUsuario
	 */
	public String getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario
	 *            o valor de idUsuario
	 */
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Integer getIdProcedimento() {
		return idProcedimento;
	}

	public void setIdProcedimento(Integer idProcedimento) {
		this.idProcedimento = idProcedimento;
	}

	/**
	 * @return o valor de nomeProcedimento
	 */
	public String getNomeProcedimento() {
		return nomeProcedimento;
	}

	/**
	 * @param nomeProcedimento
	 *            o valor de nomeProcedimento
	 */
	public void setNomeProcedimento(String nomeProcedimento) {
		this.nomeProcedimento = nomeProcedimento;
	}

	/**
	 * @return o valor de justificativa
	 */
	public String getJustificativa() {
		return justificativa;
	}

	/**
	 * @param justificativa
	 *            o valor de justificativa
	 */
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	/**
	 * Recupera id ocorrencia atividade.
	 * 
	 * @return id ocorrencia atividade
	 */
	public Integer getIdOcorrenciaAtividade() {
		return idOcorrenciaAtividade;
	}

	/**
	 * Preenche id ocorrencia atividade.
	 * 
	 * @param idOcorrenciaAtividade
	 *            o novo id ocorrencia atividade
	 */
	public void setIdOcorrenciaAtividade(Integer idOcorrenciaAtividade) {
		this.idOcorrenciaAtividade = idOcorrenciaAtividade;
	}

	/**
	 * Recupera id ocorrencia processo.
	 * 
	 * @return id ocorrencia processo
	 */
	public Integer getIdOcorrenciaProcesso() {
		return idOcorrenciaProcesso;
	}

	/**
	 * Preenche id ocorrencia processo.
	 * 
	 * @param idOcorrenciaProcesso
	 *            o novo id ocorrencia processo
	 */
	public void setIdOcorrenciaProcesso(Integer idOcorrenciaProcesso) {
		this.idOcorrenciaProcesso = idOcorrenciaProcesso;
	}

}