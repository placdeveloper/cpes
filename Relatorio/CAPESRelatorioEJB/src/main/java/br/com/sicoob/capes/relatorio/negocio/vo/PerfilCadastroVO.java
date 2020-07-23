package br.com.sicoob.capes.relatorio.negocio.vo;


public class PerfilCadastroVO {
	
	private Short codigo;
	
	private String descricao;

	private Short ordem;

	public Short getCodigo() {
		return codigo;
	}

	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getOrdem() {
		return ordem;
	}

	public void setOrdem(Short ordem) {
		this.ordem = ordem;
	}
}
