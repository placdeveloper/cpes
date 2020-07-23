package br.com.sicoob.capes.cadastro.negocio.enums;

public enum TipoRelacionamentoBemImovelEnum {
	
	USUFRATARIO ((short)1,	"USUFRUTUÁRIO"),
	ARRENDATARIO((short)2,	"ARRENDATÁRIO"),
	COMODATARIO	((short)3,	"COMODATÁRIO"),
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