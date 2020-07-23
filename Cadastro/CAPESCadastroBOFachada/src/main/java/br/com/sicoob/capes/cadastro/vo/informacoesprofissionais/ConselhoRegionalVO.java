/**
 * 
 */
package br.com.sicoob.capes.cadastro.vo.informacoesprofissionais;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * @author Erico.Junior
 *
 */
public class ConselhoRegionalVO extends BancoobVo {

	/** Serial UID.*/
	private static final long serialVersionUID = -8608118072808065252L;

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Recupera o valor de codigo.
	 *
	 * @return o valor de codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * Define o valor de codigo.
	 *
	 * @param codigo o novo valor de codigo
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
	}

	/**
	 * Recupera o valor de descricao.
	 *
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Define o valor de descricao.
	 *
	 * @param descricao o novo valor de descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
