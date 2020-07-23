/*
 * SICOOB
 * 
 * ListaLegadoEnum.java(br.com.sicoob.capes.comum.negocio.enums.ListaLegadoEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * The Enum ListaLegadoEnum.
 */
public enum ListaLegadoEnum {
	
	/** O atributo TIPO_ENDERECO. */
	TIPO_ENDERECO(1),
	
	/** O atributo TIPO_POSSE_BEM. */
	TIPO_POSSE_BEM(2),
	
	/** O atributo TIPO_REFERENCIA. */
	TIPO_REFERENCIA(16),
	
	/** O atributo SITUACAO_BEM. */
	SITUACAO_BEM(187),
	
	/** O atributo TIPO_FONTE_RENDA. */
	TIPO_FONTE_RENDA(195),
	
	/** O atributo NACIONALIDADE. */
	NACIONALIDADE(585);
	
	/** O atributo id lista. */
	private Integer idLista;

	/**
	 * Cria uma nova instância de lista legado enum.
	 * 
	 * @param idLista
	 *            the id lista
	 */
	private ListaLegadoEnum(Integer idLista) {
		this.idLista = idLista;
	}

	/**
	 * Recupera id lista.
	 * 
	 * @return id lista
	 */
	public Integer getIdLista() {
		return idLista;
	}

	/**
	 * Preenche id lista.
	 * 
	 * @param idLista
	 *            o novo id lista
	 */
	public void setIdLista(Integer idLista) {
		this.idLista = idLista;
	}

}
