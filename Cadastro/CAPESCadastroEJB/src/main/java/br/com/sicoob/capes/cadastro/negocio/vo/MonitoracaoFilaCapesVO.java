/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * VO utilizado para monitoração das filas utilizadas pelo Capes.
 * @author erico.junior
 */
public class MonitoracaoFilaCapesVO implements Serializable {

	/** Serial UID.*/
	private static final long serialVersionUID = 5061654924738742803L;
	
	/** O atributo nomeFila. */
	private String nomeFila;
	
	/** O atributo quantidadeMensagens. */
	private int quantidadeMensagens;
	
	/** O atributo dataPrimeira. */
	private Date dataPrimeira;
	
	/** O atributo dataUltima. */
	private Date dataUltima;
	
	/** O atributo nivelAlerta. */
	private int nivelAlerta;
	
	/** O atributo mensagens. */
	private List<MensagemVO> mensagens;
	
	/**
	 * @return the nomeFila
	 */
	public String getNomeFila() {
		return nomeFila;
	}
	/**
	 * @param nomeFila the nomeFila to set
	 */
	public void setNomeFila(String nomeFila) {
		this.nomeFila = nomeFila;
	}
	/**
	 * @return the quantidadeMensagens
	 */
	public int getQuantidadeMensagens() {
		return quantidadeMensagens;
	}
	/**
	 * @param quantidadeMensagens the quantidadeMensagens to set
	 */
	public void setQuantidadeMensagens(int quantidadeMensagens) {
		this.quantidadeMensagens = quantidadeMensagens;
	}
	/**
	 * @return the dataPrimeira
	 */
	public Date getDataPrimeira() {
		return dataPrimeira;
	}
	/**
	 * @param dataPrimeira the dataPrimeira to set
	 */
	public void setDataPrimeira(Date dataPrimeira) {
		this.dataPrimeira = dataPrimeira;
	}
	/**
	 * @return the dataUltima
	 */
	public Date getDataUltima() {
		return dataUltima;
	}
	/**
	 * @param dataUltima the dataUltima to set
	 */
	public void setDataUltima(Date dataUltima) {
		this.dataUltima = dataUltima;
	}
	/**
	 * @return the nivelAlerta
	 */
	public int getNivelAlerta() {
		return nivelAlerta;
	}
	/**
	 * @param nivelAlerta the nivelAlerta to set
	 */
	public void setNivelAlerta(int nivelAlerta) {
		this.nivelAlerta = nivelAlerta;
	}
	/**
	 * @return the mensagens
	 */
	public List<MensagemVO> getMensagens() {
		return mensagens;
	}
	/**
	 * @param mensagens the mensagens to set
	 */
	public void setMensagens(List<MensagemVO> mensagens) {
		this.mensagens = mensagens;
	}
}
