package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

public class TipoEmailVO extends BancoobVo {

	private static final long serialVersionUID = 4265540516121181334L;

	private Short codTipoEmail;

	private String descTipoEmail;

	public Short getCodTipoEmail() {
		return codTipoEmail;
	}

	public void setCodTipoEmail(Short codTipoEmail) {
		this.codTipoEmail = codTipoEmail;
	}

	public String getDescTipoEmail() {
		return descTipoEmail;
	}

	public void setDescTipoEmail(String descTipoEmail) {
		this.descTipoEmail = descTipoEmail;
	}

}
