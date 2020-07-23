/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.sicoob.capes.cadastro.negocio.vo.MonitoracaoFilaCapesVO;

/**
 * @author erico.junior
 * 
 */
public class MonitoracaoFilasCapesDTO implements Serializable {

	/** Serial UID. */
	private static final long serialVersionUID = 3897692868781852259L;

	/** O atributo filaConsultasExternas. */
	private MonitoracaoFilaCapesVO filaConsultasExternas;
	
	/** O atributo dlqConsultasExternas. */
	private MonitoracaoFilaCapesVO dlqConsultasExternas;
	
	/** O atributo filaReplicacao. */
	private MonitoracaoFilaCapesVO filaReplicacao;
	
	/** O atributo dlqReplicacao. */
	private MonitoracaoFilaCapesVO dlqReplicacao;
	
	/** O atributo mensagensNaoEnviadas. */
	private MonitoracaoFilaCapesVO mensagensNaoEnviadas;
	
	/** O atributo mensagensNaoProcessadas. */
	private MonitoracaoFilaCapesVO mensagensNaoProcessadas;
	
	/** O atributo dataConsulta. */
	private Date dataConsulta;

	/**
	 * @return the filaConsultasExternas
	 */
	public MonitoracaoFilaCapesVO getFilaConsultasExternas() {
		return filaConsultasExternas;
	}

	/**
	 * @param filaConsultasExternas
	 *            the filaConsultasExternas to set
	 */
	public void setFilaConsultasExternas(MonitoracaoFilaCapesVO filaConsultasExternas) {
		this.filaConsultasExternas = filaConsultasExternas;
	}

	/**
	 * @return the dlqConsultasExternas
	 */
	public MonitoracaoFilaCapesVO getDlqConsultasExternas() {
		return dlqConsultasExternas;
	}

	/**
	 * @param dlqConsultasExternas
	 *            the dlqConsultasExternas to set
	 */
	public void setDlqConsultasExternas(MonitoracaoFilaCapesVO dlqConsultasExternas) {
		this.dlqConsultasExternas = dlqConsultasExternas;
	}

	/**
	 * @return the filaReplicacao
	 */
	public MonitoracaoFilaCapesVO getFilaReplicacao() {
		return filaReplicacao;
	}

	/**
	 * @param filaReplicacao
	 *            the filaReplicacao to set
	 */
	public void setFilaReplicacao(MonitoracaoFilaCapesVO filaReplicacao) {
		this.filaReplicacao = filaReplicacao;
	}

	/**
	 * @return the dlqReplicacao
	 */
	public MonitoracaoFilaCapesVO getDlqReplicacao() {
		return dlqReplicacao;
	}

	/**
	 * @param dlqReplicacao
	 *            the dlqReplicacao to set
	 */
	public void setDlqReplicacao(MonitoracaoFilaCapesVO dlqReplicacao) {
		this.dlqReplicacao = dlqReplicacao;
	}

	/**
	 * @return
	 */
	public MonitoracaoFilaCapesVO getMensagensNaoEnviadas() {
		return mensagensNaoEnviadas;
	}

	/**
	 * @param mensagensNaoEnviadas
	 */
	public void setMensagensNaoEnviadas(MonitoracaoFilaCapesVO mensagensNaoEnviadas) {
		this.mensagensNaoEnviadas = mensagensNaoEnviadas;
	}

	/**
	 * @return
	 */
	public MonitoracaoFilaCapesVO getMensagensNaoProcessadas() {
		return mensagensNaoProcessadas;
	}

	/**
	 * @param mensagensNaoProcessadas
	 */
	public void setMensagensNaoProcessadas(MonitoracaoFilaCapesVO mensagensNaoProcessadas) {
		this.mensagensNaoProcessadas = mensagensNaoProcessadas;
	}

	/**
	 * @return the dataConsulta
	 */
	public Date getDataConsulta() {
		return dataConsulta;
	}

	/**
	 * @param dataConsulta
	 *            the dataConsulta to set
	 */
	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
}
