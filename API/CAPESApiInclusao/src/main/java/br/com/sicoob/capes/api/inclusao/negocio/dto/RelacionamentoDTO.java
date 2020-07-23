package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * A classe RelacionamentoDTO
 * 
 * @author Bruno.Carneiro
 */
public class RelacionamentoDTO extends RegistroInclusaoDTO<Long> {
	private static final long serialVersionUID = -3295700297470975368L;

	private Long idRelacionamento;
	private Short codigoTipoRelacionamento;
	private BigDecimal percentualCapitalEmpresa = BigDecimal.ZERO;
	private Date dataInicioRelacionamento;
	private Date dataInicioMandato;
	private Date dataFimMandato;
	private Integer idPessoaRelacionada;

	public Long getIdRelacionamento() {
		return idRelacionamento;
	}

	public void setIdRelacionamento(Long idRelacionamento) {
		this.idRelacionamento = idRelacionamento;
	}

	public Short getCodigoTipoRelacionamento() {
		return codigoTipoRelacionamento;
	}

	public void setCodigoTipoRelacionamento(Short codigoTipoRelacionamento) {
		this.codigoTipoRelacionamento = codigoTipoRelacionamento;
	}

	public BigDecimal getPercentualCapitalEmpresa() {
		return percentualCapitalEmpresa;
	}

	public void setPercentualCapitalEmpresa(BigDecimal percentualCapitalEmpresa) {
		this.percentualCapitalEmpresa = percentualCapitalEmpresa;
	}

	public Date getDataInicioRelacionamento() {
		return dataInicioRelacionamento;
	}

	public void setDataInicioRelacionamento(Date dataInicioRelacionamento) {
		this.dataInicioRelacionamento = dataInicioRelacionamento;
	}

	public Date getDataInicioMandato() {
		return dataInicioMandato;
	}

	public void setDataInicioMandato(Date dataInicioMandato) {
		this.dataInicioMandato = dataInicioMandato;
	}

	public Date getDataFimMandato() {
		return dataFimMandato;
	}

	public void setDataFimMandato(Date dataFimMandato) {
		this.dataFimMandato = dataFimMandato;
	}

	public Integer getIdPessoaRelacionada() {
		return idPessoaRelacionada;
	}

	public void setIdPessoaRelacionada(Integer idPessoaRelacionada) {
		this.idPessoaRelacionada = idPessoaRelacionada;
	}

	@Override
	public Long getId() {
		return getIdRelacionamento();
	}

	@Override
	public void setId(Long id) {
		setIdRelacionamento(id);
	}

}