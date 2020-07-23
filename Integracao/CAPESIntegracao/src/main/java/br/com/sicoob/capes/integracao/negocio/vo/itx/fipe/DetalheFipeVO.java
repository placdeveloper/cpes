package br.com.sicoob.capes.integracao.negocio.vo.itx.fipe;

import com.google.gson.annotations.SerializedName;

public class DetalheFipeVO extends FipeVO {
	private static final long serialVersionUID = -4639426681214069096L;

	@SerializedName("fipe_codigo")
	private String codigoFipe;

	@SerializedName("combustivel")
	private String combustivel;

	@SerializedName("marca")
	private String marca;

	@SerializedName("ano_modelo")
	private Integer anoModelo;

	@SerializedName("veiculo")
	private String veiculo;

	@SerializedName("referencia")
	private String referencia;

	@SerializedName("preco")
	private String preco;

	public String getCodigoFipe() {
		return codigoFipe;
	}

	public void setCodigoFipe(String codigoFipe) {
		this.codigoFipe = codigoFipe;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getAnoModelo() {
		return anoModelo;
	}

	public void setAnoModelo(Integer anoModelo) {
		this.anoModelo = anoModelo;
	}

	public String getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

}