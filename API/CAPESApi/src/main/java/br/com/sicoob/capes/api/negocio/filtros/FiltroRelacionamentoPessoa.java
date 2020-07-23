package br.com.sicoob.capes.api.negocio.filtros;

import java.math.BigDecimal;
import java.util.Date;

public class FiltroRelacionamentoPessoa extends FiltroConsultaAPIAbstrato {
	private static final long serialVersionUID = -7539216084632690952L;

	private Integer idPessoaRelacionada;
	private BigDecimal percentualCapitalEmpresa;
	private Date dataInicioRelacionamento;
	private Date dataFimMandato;
	private Integer idInstituicao;
	private Short codigoTipoRelacionamento;
	private Boolean habilitaDadosRegistro;
	private Boolean habilitaPoderes;

	public Integer getIdPessoaRelacionada() {
		return idPessoaRelacionada;
	}

	public void setIdPessoaRelacionada(Integer idPessoaRelacionada) {
		this.idPessoaRelacionada = idPessoaRelacionada;
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

	public Date getDataFimMandato() {
		return dataFimMandato;
	}

	public void setDataFimMandato(Date dataFimMandato) {
		this.dataFimMandato = dataFimMandato;
	}

	@Override
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	@Override
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Short getCodigoTipoRelacionamento() {
		return codigoTipoRelacionamento;
	}

	public void setCodigoTipoRelacionamento(Short codigoTipoRelacionamento) {
		this.codigoTipoRelacionamento = codigoTipoRelacionamento;
	}

	public Boolean getHabilitaDadosRegistro() {
		return habilitaDadosRegistro;
	}

	public void setHabilitaDadosRegistro(Boolean habilitaDadosRegistro) {
		this.habilitaDadosRegistro = habilitaDadosRegistro;
	}

	public Boolean getHabilitaPoderes() {
		return habilitaPoderes;
	}

	public void setHabilitaPoderes(Boolean habilitaPoderes) {
		this.habilitaPoderes = habilitaPoderes;
	}

}