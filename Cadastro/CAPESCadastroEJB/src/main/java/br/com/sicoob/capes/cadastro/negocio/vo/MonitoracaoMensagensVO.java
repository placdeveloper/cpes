package br.com.sicoob.capes.cadastro.negocio.vo;

import java.util.Date;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe MonitoracaoMensagensVO.
 */
public class MonitoracaoMensagensVO extends BancoobVo {

	/** Serial UID */
	private static final long serialVersionUID = 4610295568300877727L;

	/** O atributo quantidadeMensagens. */
	private Integer quantidadeMensagens;
	
	/** O atributo dataPrimeira. */
	private Date dataPrimeira;
	
	/** O atributo dataUltima. */
	private Date dataUltima;

	/**
	 * Instancia um novo MonitoracaoMensagensVO.
	 */
	public MonitoracaoMensagensVO() {
	}

	/**
	 * Instancia um novo MonitoracaoMensagensVO.
	 *
	 * @param quantidadeMensagens o valor de quantidade mensagens
	 * @param dataPrimeira o valor de data primeira
	 * @param dataUltima o valor de data ultima
	 */
	public MonitoracaoMensagensVO(Integer quantidadeMensagens, Date dataPrimeira, Date dataUltima) {
		this.quantidadeMensagens = quantidadeMensagens;
		this.dataPrimeira = dataPrimeira;
		this.dataUltima = dataUltima;
	}

	/**
	 * Recupera o valor de quantidadeMensagens.
	 *
	 * @return o valor de quantidadeMensagens
	 */
	public Integer getQuantidadeMensagens() {
		return quantidadeMensagens;
	}

	/**
	 * Define o valor de quantidadeMensagens.
	 *
	 * @param quantidadeMensagens o novo valor de quantidadeMensagens
	 */
	public void setQuantidadeMensagens(Integer quantidadeMensagens) {
		this.quantidadeMensagens = quantidadeMensagens;
	}

	/**
	 * Recupera o valor de dataPrimeira.
	 *
	 * @return o valor de dataPrimeira
	 */
	public Date getDataPrimeira() {
		return dataPrimeira;
	}

	/**
	 * Define o valor de dataPrimeira.
	 *
	 * @param dataPrimeira o novo valor de dataPrimeira
	 */
	public void setDataPrimeira(Date dataPrimeira) {
		this.dataPrimeira = dataPrimeira;
	}

	/**
	 * Recupera o valor de dataUltima.
	 *
	 * @return o valor de dataUltima
	 */
	public Date getDataUltima() {
		return dataUltima;
	}

	/**
	 * Define o valor de dataUltima.
	 *
	 * @param dataUltima o novo valor de dataUltima
	 */
	public void setDataUltima(Date dataUltima) {
		this.dataUltima = dataUltima;
	}

}
