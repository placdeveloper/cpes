package br.com.sicoob.capes.integracao.negocio.vo.itx.fipe;

import com.google.gson.annotations.SerializedName;

public class VeiculoFipeVO extends FipeVO {
	private static final long serialVersionUID = 1764433241532361305L;

	@SerializedName("marca")
	private String marca;

	@SerializedName("fipe_marca")
	private String marcaFipe;

	@SerializedName("order")
	private Integer ordem;

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getMarcaFipe() {
		return marcaFipe;
	}

	public void setMarcaFipe(String marcaFipe) {
		this.marcaFipe = marcaFipe;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

}