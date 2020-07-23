/*
 * SICOOB
 * 
 * TipoAutorizacaoEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;


/**
 * The Enum TipoAutorizacaoEnum.
 */
public enum TipoAutorizacaoEnum {

	/** O atributo BEM_PESSOACOMPARTILHAMENTO. */
	BEM_NOVO("Bem (NOVO)"),
	
	/** O atributo BEM. */
	BEM("Bens"),
	
	/** O atributo CERTIDAO. */
	CERTIDAO("Certid�o"),
	
	/** O atributo ENDERECO. */
	ENDERECO("Endere�o"),
	
	/** O atributo FONTE_RENDA. */
	FONTE_RENDA("Fonte de Renda"),
	
	/** O atributo TRIBUTACAO. */
	TRIBUTACAO("Perfil Tribut�rio", true),
	
	/** O atributo PESSOA. */
	PESSOA("Pessoa", true),
	
	/** O atributo PRODUTIVIDADE. */
	PRODUTIVIDADE("Produtividade"),
	
	/** O atributo PRODUTOR. */
	PRODUTOR("Produtor", true),
	
	/** O atributo RELACIONAMENTO. */
	RELACIONAMENTO("Relacionamento", false),
	
	/** O atributo RESPONSAVEL. */
	RESPONSAVEL("Respons�vel pelo Cadastro");

	/** O atributo descricao. */
	private String descricao;
	
	/** O atributo responsavel. */
	private boolean responsavel;
	
	/** indica se este tipo de autorizacao utiliza json */
	private boolean utilizaJSON;

	/**
	 * Cria uma nova inst�ncia de tipo autorizacao enum.
	 * 
	 * @param descricao
	 *            the descricao
	 */
	private TipoAutorizacaoEnum(String descricao) {
		this.descricao = descricao.toUpperCase();
	}

	/**
	 * Cria uma nova inst�ncia de tipo autorizacao enum.
	 * 
	 * @param descricao
	 *            the descricao
	 * @param responsavel
	 *            the responsavel
	 */
	private TipoAutorizacaoEnum(String descricao, boolean utilizaJSON) {
		this(descricao);
	    this.utilizaJSON = utilizaJSON;
	}

	/**
	 * Cria uma nova inst�ncia de tipo autorizacao enum.
	 * 
     * @param descricao
     * @param utilizaJSON
     * @param responsavel
     */
    private TipoAutorizacaoEnum(String descricao, boolean utilizaJSON, boolean responsavel) {
	    this(descricao, utilizaJSON);
	    this.responsavel = responsavel;
    }

	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Indica se o tipo de autoriza��o � respons�vel pelo pr�prio cadastro
	 *
	 * @return <code>true</code> se for o respons�vel
	 */
	public boolean isResponsavel() {
		return this.responsavel;
	}

	/**
     * @return the utilizaJSON
     */
    public boolean isUtilizaJSON() {
    	return utilizaJSON;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + "=" + name();
	}
}
