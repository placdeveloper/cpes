package br.com.sicoob.capes.api.negocio.filtros;

import br.com.bancoob.negocio.vo.BancoobVo;

public abstract class FiltroConsultaAPIAbstrato extends BancoobVo {
	private static final long serialVersionUID = -4261337547021060485L;

	private Integer idPessoa;
	private Integer idInstituicao;

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

}