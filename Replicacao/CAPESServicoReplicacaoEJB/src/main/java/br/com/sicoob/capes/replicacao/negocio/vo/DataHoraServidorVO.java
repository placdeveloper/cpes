package br.com.sicoob.capes.replicacao.negocio.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * A Classe DataHoraServidorVO.
 */
public class DataHoraServidorVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo dataHoraLocal. */
	private Date dataHoraLocal;
	
	/** O atributo dataHoraRemota. */
	private Date dataHoraRemota;
	
	/**
	 * @return the dataHoraLocal
	 */
	public Date getDataHoraLocal() {
		return dataHoraLocal;
	}
	/**
	 * @param dataHoraLocal the dataHoraLocal to set
	 */
	public void setDataHoraLocal(Date dataHoraLocal) {
		this.dataHoraLocal = dataHoraLocal;
	}
	/**
	 * @return the dataHoraRemota
	 */
	public Date getDataHoraRemota() {
		return dataHoraRemota;
	}
	/**
	 * @param dataHoraRemota the dataHoraRemota to set
	 */
	public void setDataHoraRemota(Date dataHoraRemota) {
		this.dataHoraRemota = dataHoraRemota;
	}
}
