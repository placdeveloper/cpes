package br.com.sicoob.capes.cadastro.negocio.enums;

public enum GrupoTipoAnotacaoEnum {

	IRREGULARIDADE_CPF((short) 1, "Anotações de irregularidade de CPF"),
	BAIXA_ANUAL_SOCIOAMBIENTAL((short) 2, "Baixa anual de anotações socioambiental");

	private Short codigo;
	private String nome;

	private GrupoTipoAnotacaoEnum(Short codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public Short getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

}