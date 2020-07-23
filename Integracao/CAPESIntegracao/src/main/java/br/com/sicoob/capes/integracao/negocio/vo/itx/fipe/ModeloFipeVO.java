package br.com.sicoob.capes.integracao.negocio.vo.itx.fipe;

import com.google.gson.annotations.SerializedName;

public class ModeloFipeVO extends FipeVO {
	private static final long serialVersionUID = -8809441551674182781L;

	@SerializedName("marca")
	private String marca;

	@SerializedName("veiculo")
	private String veiculo;

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

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
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