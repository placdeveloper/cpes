package br.com.sicoob.capes.integracao.negocio.enums;

public enum TipoVeiculoFipeEnum {

	CARROS("Carros", "carros"), 
	MOTOS("Motos", "motos"), 
	CAMINHOES("Caminhões", "caminhoes");

	private String nome;
	private String valor;

	private TipoVeiculoFipeEnum(String nome, String valor) {
		this.nome = nome;
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public String getValor() {
		return valor;
	}

	@Override
	public String toString() {
		return getNome();
	}

}