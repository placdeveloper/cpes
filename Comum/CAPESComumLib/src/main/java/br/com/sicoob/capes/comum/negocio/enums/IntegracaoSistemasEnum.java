/*
 * SICOOB
 * 
 * IntegracaoSistemasEnum.java(br.com.sicoob.capes.comum.negocio.enums.IntegracaoSistemasEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * TODO javadoc
 * 
 * 
 * @author Rodrigo.Chaves
 */
public enum IntegracaoSistemasEnum {
	
	/** O atributo GED. */
	GED("Gestão Eletrônica de Documentos"),
	
	/** O atributo GFT. */
	GFT("Gestão de Fluxo de Trabalho"),
	
	/** Antigo "Consultas Externas" */
	ITX("Integrações Externas"), 
	
	/** O atributo LOC. */
	LOC("Localidade"),
	
	/** O atributo SCI. */
	SCI("SCI");
	
	/** O atributo nome sistema. */
	private String nomeSistema;
	
	/** O atributo sigla sistema. */
	private String siglaSistema;
	
	/**
	 * Construtor
	 * 
	 * @param nomeSistema
	 */
	private IntegracaoSistemasEnum(String nomeSistema) {
		this.nomeSistema = nomeSistema;
		this.siglaSistema = this.name();
	}

	/**
	 * Recupera nome sistema.
	 * 
	 * @return nome sistema
	 */
	public String getNomeSistema() {
		return nomeSistema;
	}

	/**
	 * Recupera sigla sistema.
	 * 
	 * @return sigla sistema
	 */
	public String getSiglaSistema() {
		return siglaSistema;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getSiglaSistema() + " - " + getNomeSistema();
	}
	
}
