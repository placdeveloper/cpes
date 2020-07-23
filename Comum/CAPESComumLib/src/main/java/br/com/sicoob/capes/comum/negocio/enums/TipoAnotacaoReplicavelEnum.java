/*
 * SICOOB
 * 
 * TipoAnotacaoReplicavelEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoAnotacaoReplicavelEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

/**
 * Tipos de anota��o que devem ser replicadas para o legado.
 * 
 * @author Erico.Junior
 */
public enum TipoAnotacaoReplicavelEnum {

	/** O atributo FALECIDO. */
	FALECIDO(505, "Falecido (a)"), 
	
	/** O atributo PESSOA_POLITICAMENTE_EXPOSTA. */
	PESSOA_POLITICAMENTE_EXPOSTA(102, "Pessoa Politicamente Exposta");

	/** O atributo cod tipo anotacao. */
	private Integer codTipoAnotacao;
	
	/** O atributo descricao. */
	private String descricao;

	/**
	 * Construtor do Enum.
	 * 
	 * @param codTipoAnotacao
	 *            O identificador do tipo de anota��o.
	 * @param descricao
	 *            A descri��o do tipo de anota��o.
	 */
	private TipoAnotacaoReplicavelEnum(Integer codTipoAnotacao, String descricao) {
		this.descricao = descricao;
		this.codTipoAnotacao = codTipoAnotacao;
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
	 * Recupera o enum do tipo de anota��o a partir do c�digo informado.
	 * 
	 * @param codigo
	 *            O c�digo do tipo de anota��o.
	 * @return o enum do tipo de anota��o a partir do c�digo informado.
	 */
	public static TipoAnotacaoReplicavelEnum valueOf(Integer codigo) {

		TipoAnotacaoReplicavelEnum encontrada = null;
		TipoAnotacaoReplicavelEnum[] tipos = TipoAnotacaoReplicavelEnum.values();
		for (TipoAnotacaoReplicavelEnum tipoAnotacao : tipos) {
			if (tipoAnotacao.getCodTipoAnotacao().equals(codigo)) {
				encontrada = tipoAnotacao;
				break;
			}
		}
		return encontrada;
	}

}
