/*
 * SICOOB
 * 
 * TributacaoPessoaVO.java(br.com.sicoob.capes.api.negocio.vo.TributacaoPessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;


/**
 * @author Lucas.Borges
 */
public class TributacaoPessoaVO extends BancoobDto{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -6791777875983643293L;

	/** O atributo data hora inicio. */
	private Date dataHoraInicio;
	
	/** O atributo cobrar ir. */
	private Boolean cobrarIR;
	
	/** O atributo cobrar iof. */
	private Boolean cobrarIOF;
	
	/** O atributo cobrar cpmf. */
	private Boolean cobrarCPMF;
	
	/**
	 * Cria uma nova instância de tributacao pessoa vo.
	 */
	public TributacaoPessoaVO() {

	}

	/**
	 * Cria uma nova instância de tributacao pessoa vo.
	 * 
	 * @param dataHoraInicio
	 *            the data hora inicio
	 * @param cobrarIR
	 *            the cobrar ir
	 * @param cobrarIOF
	 *            the cobrar iof
	 * @param cobrarCPMF
	 *            the cobrar cpmf
	 */
	public TributacaoPessoaVO(Date dataHoraInicio, Boolean cobrarIR, Boolean cobrarIOF, Boolean cobrarCPMF) {
		this.dataHoraInicio = dataHoraInicio;
		this.cobrarIR = cobrarIR;
		this.cobrarIOF = cobrarIOF;
		this.cobrarCPMF = cobrarCPMF;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * Recupera cobrar ir.
	 * 
	 * @return cobrar ir
	 */
	public Boolean getCobrarIR() {
		return cobrarIR;
	}

	/**
	 * Preenche cobrar ir.
	 * 
	 * @param cobrarIR
	 *            o novo cobrar ir
	 */
	public void setCobrarIR(Boolean cobrarIR) {
		this.cobrarIR = cobrarIR;
	}

	/**
	 * Recupera cobrar iof.
	 * 
	 * @return cobrar iof
	 */
	public Boolean getCobrarIOF() {
		return cobrarIOF;
	}

	/**
	 * Preenche cobrar iof.
	 * 
	 * @param cobrarIOF
	 *            o novo cobrar iof
	 */
	public void setCobrarIOF(Boolean cobrarIOF) {
		this.cobrarIOF = cobrarIOF;
	}

	/**
	 * Recupera cobrar cpmf.
	 * 
	 * @return cobrar cpmf
	 */
	public Boolean getCobrarCPMF() {
		return cobrarCPMF;
	}

	/**
	 * Preenche cobrar cpmf.
	 * 
	 * @param cobrarCPMF
	 *            o novo cobrar cpmf
	 */
	public void setCobrarCPMF(Boolean cobrarCPMF) {
		this.cobrarCPMF = cobrarCPMF;
	}
	
}
