package br.com.sicoob.capes.api.negocio.filtros;

public class FiltroEmail extends FiltroConsultaAPIAbstrato {
	private static final long serialVersionUID = 1164133436898808324L;

	private String descricao;
	private Short codigoTipoEmail;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getCodigoTipoEmail() {
		return codigoTipoEmail;
	}

	public void setCodigoTipoEmail(Short codigoTipoEmail) {
		this.codigoTipoEmail = codigoTipoEmail;
	}

}