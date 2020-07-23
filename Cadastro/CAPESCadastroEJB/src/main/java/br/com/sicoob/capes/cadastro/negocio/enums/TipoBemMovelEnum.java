package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * O {@code Enum} que representa um tipo de bem m�vel.
 * 
 * @author Bruno.Carneiro
 */
public enum TipoBemMovelEnum {

	VEICULO				((short) 1, "VE�CULO"), 
	EMBARCACAO			((short) 2, "EMBARCA��O"), 
	AERONAVE			((short) 3, "AERONAVE"), 
	MAQUINA_EQUIPAMENTO	((short) 4, "M�QUINA/EQUIPAMENTO", Boolean.FALSE), 
	SEMOVENTE			((short) 5, "SEMOVENTE", Boolean.FALSE);

	private Short codigo;
	private String descricao;
	private Boolean possuiDadosAvancados;

	private TipoBemMovelEnum(Short codigo, String descricao) {
		this(codigo, descricao, Boolean.TRUE);
	}
	
	private TipoBemMovelEnum(Short codigo, String descricao, boolean possuiDadosAvancados) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.possuiDadosAvancados = possuiDadosAvancados;
	}

	public Short getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Boolean getPossuiDadosAvancados() {
		return possuiDadosAvancados;
	}

	public static TipoBemMovelEnum obterPorCodigo(Short codigo) {
		for (TipoBemMovelEnum tipoBemMovel : values()) {
			if (tipoBemMovel.codigo.equals(codigo)) {
				return tipoBemMovel;
			}
		}
		return null;
	}

}