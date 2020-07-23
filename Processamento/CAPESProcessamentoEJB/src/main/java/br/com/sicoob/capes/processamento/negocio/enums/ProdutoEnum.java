/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.enums;

/**
 * @author Erico.Junior
 *
 */
public enum ProdutoEnum {

	/** O atributo PAGAMENTO_INSS. */
	PAGAMENTO_INSS((short)16, "PAGAMENTO DE BENEFÍCIOS DO INSS");

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Construtor do Enum.
	 * @param codigo O identificador do produto.
	 * @param descricao A descrição do produto.
	 */
	private ProdutoEnum(Short codigo, String descricao) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	/**
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

}