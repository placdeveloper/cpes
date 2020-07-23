package br.com.sicoob.capes.api.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * Classe que representa o tipo da classificação do bem.
 * 
 * @author Bruno.Carneiro
 */
public class TipoClassificacaoBemVO extends BancoobVo {
	private static final long serialVersionUID = -926209297083998419L;

	private Short codigo;
	private String descricao;

	/**
	 * Construtor.
	 */
	public TipoClassificacaoBemVO() {

	}

	/**
	 * Construtor
	 * 
	 * @param codigo
	 * @param descricao
	 */
	public TipoClassificacaoBemVO(Short codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
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
}