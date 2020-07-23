/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum para as atividades econ�micas
 * 
 * @author Erico.Junior
 */
public enum AtividadeEconomicaEnum {

	/** O atributo PESSOA_FISICA. */
	PESSOA_FISICA((short) 55, "Pessoa F�sica"), 
	
	/** O atributo PESSOA_JURIDICA. */
	PESSOA_JURIDICA((short) 54, "Setor Privado - Outros Servi�os");

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Construtor do Enum.
	 * 
	 * @param codigo
	 *            O c�digo da atividade econ�mica.
	 * @param descricao
	 *            A descri��o da atividade econ�mica.
	 */
	private AtividadeEconomicaEnum(Short codigo, String descricao) {
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
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		return getClass().getSimpleName() + ": " + getCodigo() + " - " + getDescricao();
	}
}
