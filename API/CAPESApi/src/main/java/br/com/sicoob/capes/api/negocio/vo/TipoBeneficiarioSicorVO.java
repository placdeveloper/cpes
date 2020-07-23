package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

public class TipoBeneficiarioSicorVO extends BancoobVo {
	private static final long serialVersionUID = -1884822388270557873L;

	private Short codigo;
	private String descricao;

	public TipoBeneficiarioSicorVO() {

	}

	public TipoBeneficiarioSicorVO(Short codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Short getCodigo() {
		return codigo;
	}

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}