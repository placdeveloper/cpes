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
	SISBR_FRUSTRACAO_SAFRA(111, "Frustração de Safra", OrigemInformacaoEnum.SISBR);
	
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
	 * @param codTipoAnotacao O identificador do tipo de anotação.
	 * @param descricao A descrição do tipo de anotação.
	 * @param origem A origem da informação.
	 */
	private TipoAnotacaoSemiAutomaticaEnum(Integer codTipoAnotacao, String descricao, 
			OrigemInformacaoEnum origem) {
		this(codTipoAnotacao, descricao, false, origem);
	}

	/**
	 * Construtor do Enum.
	 * @param codTipoAnotacao O identificador do tipo de anotação.
	 * @param descricao A descrição do tipo de anotação.
	 * @param nadaConsta indica se a anotação é de nada consta.
	 * @param origem A origem da informação.
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
	 * Recupera o enum do tipo de anotação automática a partir do código informado.
	 * @param codigo O código do tipo de anotação.
	 * @return o enum do tipo de anotação automática a partir do código informado.
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
