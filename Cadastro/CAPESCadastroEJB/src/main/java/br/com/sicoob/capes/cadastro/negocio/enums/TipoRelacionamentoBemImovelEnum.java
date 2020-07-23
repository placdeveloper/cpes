package br.com.sicoob.capes.cadastro.negocio.enums;

public enum TipoRelacionamentoBemImovelEnum {
	
	USUFRATARIO ((short)1,	"USUFRUTU�RIO"),
	ARRENDATARIO((short)2,	"ARRENDAT�RIO"),
	COMODATARIO	((short)3,	"COMODAT�RIO"),
	PARCEIRO	((short)4,	"PARCEIRO");
	
	private Short codigo;
	private String descricao;

	private TipoRelacionamentoBemImovelEnum(Short codigo, String descricao) {
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