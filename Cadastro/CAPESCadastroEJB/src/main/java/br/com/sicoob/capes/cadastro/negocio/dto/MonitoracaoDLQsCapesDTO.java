/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.sicoob.capes.cadastro.negocio.vo.MonitoracaoFilaCapesVO;

/**
 * @author Erico.Junior
 *
 */
public class MonitoracaoDLQsCapesDTO implements Serializable {

	/** Serial UID.*/
	private static final long serialVersionUID = 7113503547192391550L;
	
	/** O atributo dlqConsultasExternas. */
	private MonitoracaoFilaCapesVO dlqConsultasExternas;
	
	/** O atributo dlqReplicacao. */
	private MonitoracaoFilaCapesVO dlqReplicacao;
	
	/** O atributo dataConsulta. */
	private Date dataConsulta;
	/**
	 * @return the dlqConsultasExternas
	 */
	public MonitoracaoFilaCapesVO getDlqConsultasExternas() {
		return dlqConsultasExternas;
	}
	/**
	 * @param dlqConsultasExternas the dlqConsultasExternas to set
	 */
	public void setDlqConsultasExternas(MonitoracaoFilaCapesVO dlqConsultasExternas) {
		this.dlqConsultasExternas = dlqConsultasExternas;
	}
	/**
	 * @return the dlqReplicacao
	 */
	public MonitoracaoFilaCapesVO getDlqReplicacao() {
		return dlqReplicacao;
	}
	/**
	 * @param dlqReplicacao the dlqReplicacao to set
	 */
	public void setDlqReplicacao(MonitoracaoFilaCapesVO dlqReplicacao) {
		this.dlqReplicacao = dlqReplicacao;
	}
	/**
	 * @return the dataConsulta
	 */
	public Date getDataConsulta() {
		return dataConsulta;
	}
	/**
	 * @param dataConsulta the dataConsulta to set
	 */
	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	
}
