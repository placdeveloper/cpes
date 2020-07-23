package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * Classe que representa o tipo do bem.
 * 
 * @author Bruno.Carneiro
 */
public class TipoBemVO extends BancoobVo {
	private static final long serialVersionUID = 1L;

	private Short codigo;
	private String descricao;
	private Boolean possuiDadosAvancados = Boolean.TRUE;

	public TipoBemVO() {

	}

	public TipoBemVO(Short codigo, String descricao) {
		this(codigo, descricao, Boolean.TRUE);
	}
	
	public TipoBemVO(Short codigo, String descricao, Boolean possuiDadosAvancados) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.possuiDadosAvancados = possuiDadosAvancados;
	}

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

	public Boolean getPossuiDadosAvancados() {
		return possuiDadosAvancados;
	}

	public void setPossuiDadosAvancados(Boolean possuiDadosAvancados) {
		this.possuiDadosAvancados = possuiDadosAvancados;
	}

}