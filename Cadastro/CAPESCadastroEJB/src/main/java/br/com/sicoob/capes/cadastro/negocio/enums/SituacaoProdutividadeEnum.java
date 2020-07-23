/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Situa��es da produtividade.
 * @author Erico.Junior
 */
public enum SituacaoProdutividadeEnum {

	/** O atributo EM_ABERTO. */
	EM_ABERTO((short)1, "Em Aberto"),
	
	/** O atributo FINALIZADO_SUCESSO. */
	FINALIZADO_SUCESSO((short)2, "Finalizado com Sucesso"),
	
	/** O atributo FINALIZADO_FRUSTRACAO. */
	FINALIZADO_FRUSTRACAO((short)3, "Finalizado com Frusta��o");

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Instancia um novo SituacaoProdutividadeEnum.
	 *
	 * @param codigo o valor de codigo
	 * @param descricao o valor de descricao
	 */
	private SituacaoProdutividadeEnum(Short codigo, String descricao) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	/**
	 * Recupera o valor de codigo.
	 *
	 * @return o valor de codigo
	 */
	public Short getCodigo() {
		return codigo;
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
	 * Recupera a descri��o da situa��o a partir do c�digo informado.
	 * 
	 * @param codigo
	 *            O c�digo da situa��o.
	 * @return a descri��o da situa��o a partir do c�digo informado.
	 */
	public static String recuperarDescricao(Short codigo){
		
		SituacaoProdutividadeEnum[] situacoes = SituacaoProdutividadeEnum.values();
		String descricao = "";
		for (SituacaoProdutividadeEnum situacao : situacoes) {
			if(situacao.getCodigo().equals(codigo)) {
				descricao = situacao.getDescricao();
				break;
			}
		}
		return descricao;
	}
}
