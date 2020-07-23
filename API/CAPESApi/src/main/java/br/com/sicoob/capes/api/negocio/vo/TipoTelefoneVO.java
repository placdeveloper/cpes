package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

public class TipoTelefoneVO extends BancoobVo {

	private static final long serialVersionUID = 4265540516121181334L;

	private Short codTipoTelefone;

	private String descTipoTelefone;

	public Short getCodTipoTelefone() {
		return codTipoTelefone;
	}

	public void setCodTipoTelefone(Short codTipoTelefone) {
		this.codTipoTelefone = codTipoTelefone;
	}

	public String getDescTipoTelefone() {
		return descTipoTelefone;
	}

	public void setDescTipoTelefone(String descTipoTelefone) {
		this.descTipoTelefone = descTipoTelefone;
	}

}
