package br.com.sicoob.capes.api.negocio.vo;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * VO pra identificação do participante do bem imóvel
 * 
 * @author Bruno.Carneiro
 */
public class BemImovelParticipanteVO extends BancoobVo {
	private static final long serialVersionUID = -3898108501171199655L;

	private Short codigoTipoRelacionamento;
	private String descricaoTipoRelacionamento;
	private Integer idPessoa;
	private Date dataInicioRelacionamento;
	private Date dataFimRelacionamento;
	private BigDecimal areaPosse;

	public BemImovelParticipanteVO() {
		super();
	}

	public BemImovelParticipanteVO(Short codigoTipoRelacionamento, String descricaoTipoRelacionamento, Integer idPessoa,
			Date dataInicioRelacionamento, Date dataFimRelacionamento, BigDecimal areaPosse) {
		super();
		this.codigoTipoRelacionamento = codigoTipoRelacionamento;
		this.descricaoTipoRelacionamento = descricaoTipoRelacionamento;
		this.idPessoa = idPessoa;
		this.dataInicioRelacionamento = dataInicioRelacionamento;
		this.dataFimRelacionamento = dataFimRelacionamento;
		this.areaPosse = areaPosse;
	}

	public Short getCodigoTipoRelacionamento() {
		return codigoTipoRelacionamento;
	}

	public void setCodigoTipoRelacionamento(Short codigoTipoRelacionamento) {
		this.codigoTipoRelacionamento = codigoTipoRelacionamento;
	}

	public String getDescricaoTipoRelacionamento() {
		return descricaoTipoRelacionamento;
	}

	public void setDescricaoTipoRelacionamento(String descricaoTipoRelacionamento) {
		this.descricaoTipoRelacionamento = descricaoTipoRelacionamento;
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Date getDataInicioRelacionamento() {
		return dataInicioRelacionamento;
	}

	public void setDataInicioRelacionamento(Date dataInicioRelacionamento) {
		this.dataInicioRelacionamento = dataInicioRelacionamento;
	}

	public Date getDataFimRelacionamento() {
		return dataFimRelacionamento;
	}

	public void setDataFimRelacionamento(Date dataFimRelacionamento) {
		this.dataFimRelacionamento = dataFimRelacionamento;
	}

	public BigDecimal getAreaPosse() {
		return areaPosse;
	}

	public void setAreaPosse(BigDecimal areaPosse) {
		this.areaPosse = areaPosse;
	}

}