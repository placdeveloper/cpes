package br.com.sicoob.capes.integracao.negocio.vo.itx.fipe;

import com.google.gson.annotations.SerializedName;

public class MarcaFipeVO extends FipeVO {
	private static final long serialVersionUID = -4900238292331850815L;

	@SerializedName("order")
	private Integer ordem;

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

}