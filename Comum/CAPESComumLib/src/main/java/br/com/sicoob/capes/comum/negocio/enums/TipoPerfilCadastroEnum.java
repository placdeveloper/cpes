package br.com.sicoob.capes.comum.negocio.enums;

public enum TipoPerfilCadastroEnum {

	SIMPLES((short) 1, "SIMPLES"), 
	AVANCADO((short) 2, "AVANCADO");

	private Short codigo;
	private String descricao;

	private TipoPerfilCadastroEnum(Short codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Short getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

}