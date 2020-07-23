/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.enums;

/**
 * Enum para as atividades econômicas
 * 
 * @author Erico.Junior
 */
public enum AtividadeEconomicaEnum {

	/** O atributo PESSOA_FISICA. */
	PESSOA_FISICA((short) 55, "Pessoa Física"), 
	
	/** O atributo PESSOA_JURIDICA. */
	PESSOA_JURIDICA((short) 54, "Setor Privado - Outros Serviços");

	/** O atributo codigo. */
	private Short codigo;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Construtor do Enum.
	 * 
	 * @param codigo
	 *            O código da atividade econômica.
	 * @param descricao
	 *            A descrição da atividade econômica.
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
