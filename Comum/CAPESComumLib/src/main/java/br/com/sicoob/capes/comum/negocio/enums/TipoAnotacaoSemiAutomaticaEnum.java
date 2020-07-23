/*
 * SICOOB
 * 
 * TipoAnotacaoSemiAutomaticaEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoSemiAutomaticaEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * @author Erico.Junior
 *
 */
public enum TipoAnotacaoSemiAutomaticaEnum {

	/** O atributo SISBR_FRUSTRACAO_SAFRA. */
	SISBR_FRUSTRACAO_SAFRA(111, "Frustra��o de Safra", OrigemInformacaoEnum.SISBR);
	
	/** O atributo cod tipo anotacao. */
	private Integer codTipoAnotacao;
	
	/** O atributo descricao. */
	private String descricao;
	
	/** O atributo nada consta. */
	private boolean nadaConsta; 
	
	/** O atributo origem. */
	private OrigemInformacaoEnum origem; 
	
	/**
	 * Construtor do Enum.
	 * @param codTipoAnotacao O identificador do tipo de anota��o.
	 * @param descricao A descri��o do tipo de anota��o.
	 * @param origem A origem da informa��o.
	 */
	private TipoAnotacaoSemiAutomaticaEnum(Integer codTipoAnotacao, String descricao, 
			OrigemInformacaoEnum origem) {
		this(codTipoAnotacao, descricao, false, origem);
	}

	/**
	 * Construtor do Enum.
	 * @param codTipoAnotacao O identificador do tipo de anota��o.
	 * @param descricao A descri��o do tipo de anota��o.
	 * @param nadaConsta indica se a anota��o � de nada consta.
	 * @param origem A origem da informa��o.
	 */
	private TipoAnotacaoSemiAutomaticaEnum(Integer codTipoAnotacao, String descricao, 
			boolean nadaConsta, OrigemInformacaoEnum origem) {
		this.descricao = descricao;
		this.codTipoAnotacao = codTipoAnotacao;
		this.nadaConsta = nadaConsta;
		this.origem = origem;
	}
	
	/**
	 * @return the codTipoAnotacao
	 */
	public Integer getCodTipoAnotacao() {
		return codTipoAnotacao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return the nadaConsta
	 */
	public boolean isNadaConsta() {
		return nadaConsta;
	}
	
	/**
	 * @return the origem
	 */
	public OrigemInformacaoEnum getOrigem() {
		return origem;
	}

	/**
	 * Recupera o enum do tipo de anota��o autom�tica a partir do c�digo informado.
	 * @param codigo O c�digo do tipo de anota��o.
	 * @return o enum do tipo de anota��o autom�tica a partir do c�digo informado.
	 */
	public static TipoAnotacaoSemiAutomaticaEnum 
			recuperarTipoAnotacaoAutomaticaEnumPorCodigo(Integer codigo){
		
		TipoAnotacaoSemiAutomaticaEnum[] tipos = TipoAnotacaoSemiAutomaticaEnum.values();
		TipoAnotacaoSemiAutomaticaEnum encontrada = null;
		
		for (TipoAnotacaoSemiAutomaticaEnum tipoAnotacaoAutomaticaEnum : tipos) {
			if(tipoAnotacaoAutomaticaEnum.getCodTipoAnotacao().equals(codigo)) {
				encontrada = tipoAnotacaoAutomaticaEnum;
				break;
			}
		}
		
		return encontrada;
	}
}
