/* 
 * Sicoob
 * TipoOperacaoVO.java 
 * Criado em: 31/01/2011
 */
package br.com.sicoob.capes.cadastro.negocio.vo;

import br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum;

/**
 * 31/01/2011
 * 
 * @author rodrigo.chaves
 */
public class TipoOperacaoVO {

	/** O atributo codigo. */
	private Character codigo;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Construtor
	 */
	public TipoOperacaoVO(TipoAtualizacaoCadastralEnum tipoOperacao) {
		
		codigo = tipoOperacao.getTipoOperacao();
		descricao = tipoOperacao.getDescricao();
	}

	/**
	 * Recupera o valor de codigo.
	 *
	 * @return o valor de codigo
	 */
	public Character getCodigo() {
		return codigo;
	}

	/**
	 * Define o valor de codigo.
	 *
	 * @param codigo o novo valor de codigo
	 */
	public void setCodigo(Character codigo) {
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
