package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

import br.com.bancoob.negocio.vo.BancoobVo;

public class GrupoCompartilhamentoVO extends BancoobVo {
	private static final long serialVersionUID = -2582826890292857125L;

	private Integer idGrupoCompartilhamento;
	private Integer idInstituicao;
	private Short codigoCompartilhamentoCadastro;
	private Date dataHoraInicio;
	private Boolean integracaoReceitaFederal;

	public GrupoCompartilhamentoVO() {
		super();
	}

	public GrupoCompartilhamentoVO(Integer idGrupoCompartilhamento, Integer idInstituicao, Short codigoCompartilhamentoCadastro, Date dataHoraInicio, Boolean integracaoReceitaFederal) {
		super();
		this.idGrupoCompartilhamento = idGrupoCompartilhamento;
		this.idInstituicao = idInstituicao;
		this.codigoCompartilhamentoCadastro = codigoCompartilhamentoCadastro;
		this.dataHoraInicio = dataHoraInicio;
		this.integracaoReceitaFederal = integracaoReceitaFederal;
	}

	public Integer getIdGrupoCompartilhamento() {
		return idGrupoCompartilhamento;
	}

	public void setIdGrupoCompartilhamento(Integer idGrupoCompartilhamento) {
		this.idGrupoCompartilhamento = idGrupoCompartilhamento;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Short getCodigoCompartilhamentoCadastro() {
		return codigoCompartilhamentoCadastro;
	}

	public void setCodigoCompartilhamentoCadastro(Short codigoCompartilhamentoCadastro) {
		this.codigoCompartilhamentoCadastro = codigoCompartilhamentoCadastro;
	}

	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public Boolean getIntegracaoReceitaFederal() {
		return integracaoReceitaFederal;
	}

	public void setIntegracaoReceitaFederal(Boolean integracaoReceitaFederal) {
		this.integracaoReceitaFederal = integracaoReceitaFederal;
	}

}
