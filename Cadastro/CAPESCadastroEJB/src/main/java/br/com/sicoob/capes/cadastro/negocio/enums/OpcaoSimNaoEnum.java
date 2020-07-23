package br.com.sicoob.capes.cadastro.negocio.enums;

public enum OpcaoSimNaoEnum {

	SIM(true, "Sim", 0),
	
	NAO(false, "Não", 1);
	
	
	private Boolean codigo;
	
	private String descricao;
	
	private Integer cod;
	
	
	private OpcaoSimNaoEnum(Boolean codigo, String descricao, Integer cod) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.cod = cod;
	}

	public Boolean getCodigo() {
		return codigo;
	}
	
	public Integer getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
}
