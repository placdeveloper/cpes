/*
 * SICOOB
 * 
 * TributacaoBase.java(br.com.sicoob.capes.negocio.entidades.TributacaoBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * The Class TributacaoBase.
 */
@MappedSuperclass
public abstract class TributacaoBase extends EntidadeCadastroBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5076464006270247541L;
	
	/** O atributo cobrar ir. */
	@Column(name="BOLCOBRARIR")
	private Boolean cobrarIR = Boolean.TRUE;
	
	/** O atributo cobrar iof. */
	@Column(name="BOLCOBRARIOF")
	private Boolean cobrarIOF = Boolean.TRUE;

	/** O atributo cobrar cpmf. */
	@Column(name="BOLCOBRARCPMF")
	private Boolean cobrarCPMF = Boolean.TRUE;
	
	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;
	
	/**
	 * Recupera id pessoa compartilhamento.
	 * 
	 * @return id pessoa compartilhamento
	 */
	public abstract Long getIdPessoaCompartilhamento();

	/**
	 * Preenche id pessoa compartilhamento.
	 * 
	 * @param idPessoaCompartilhamento
	 *            o novo id pessoa compartilhamento
	 */
	public abstract void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento);

	/**
	 * @return the cobrarIR
	 */
	public Boolean getCobrarIR() {
		return cobrarIR;
	}

	/**
	 * @param cobrarIR the cobrarIR to set
	 */
	public void setCobrarIR(Boolean cobrarIR) {
		this.cobrarIR = cobrarIR;
	}

	/**
	 * @return the cobrarIOF
	 */
	public Boolean getCobrarIOF() {
		return cobrarIOF;
	}

	/**
	 * @param cobrarIOF the cobrarIOF to set
	 */
	public void setCobrarIOF(Boolean cobrarIOF) {
		this.cobrarIOF = cobrarIOF;
	}

	/**
	 * @return the cobrarCPMF
	 */
	public Boolean getCobrarCPMF() {
		return cobrarCPMF;
	}

	/**
	 * @param cobrarCPMF the cobrarCPMF to set
	 */
	public void setCobrarCPMF(Boolean cobrarCPMF) {
		this.cobrarCPMF = cobrarCPMF;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
}