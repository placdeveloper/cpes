/*
 * SICOOB
 * 
 * TipoAtualizacaoCadastralEnum.java(br.com.sicoob.capes.comum.negocio.enums.TipoAtualizacaoCadastralEnum)
 */
package br.com.sicoob.capes.comum.negocio.enums;

import static br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum.A;
import static br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum.E;
import static br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum.I;

import java.io.Serializable;

/**
 * Enum para os tipos de atualizações cadastrais.
 * 
 * @author Erico.Junior
 */
public enum TipoAtualizacaoCadastralEnum implements Serializable {

	/** O atributo OPERACAO_ALTERACAO. */
	OPERACAO_ALTERACAO(A.getCodigo(), A.getDescricao(), I.getCodigo(), A),
	
	/** O atributo OPERACAO_EXCLUSAO. */
	OPERACAO_EXCLUSAO(E.getCodigo(), E.getDescricao(), E),
	
	/** O atributo OPERACAO_INCLUSAO. */
	OPERACAO_INCLUSAO(I.getCodigo(), I.getDescricao(), I),

	/** REPLICA AS OPERAÇÕES DAS ENTIDADES: TRIBUTACAO E PESSOAINSTITUICAO **/
	OPERACAO_ALTERACAO_CLIENTE('C', "Alteração Cliente", 'D', A),
	
	/** O atributo OPERACAO_INCLUSAO_CLIENTE. */
	OPERACAO_INCLUSAO_CLIENTE('D', "Inclusão Cliente", I),

	/** REPLICA as alterações de endereço de correspondência. **/
	OPERACAO_ALTERACAO_ENDERECO_CORRESPONDENCIA('T', "Alteração de endereço de correspondência", A),

	/** O atributo OPERACAO_ALTERACAO_EMAIL_PESSOA. */
	OPERACAO_ALTERACAO_EMAIL_PESSOA('H', "Alteração do e-mail de uma pessoa", 'J', A),
	
	/** O atributo OPERACAO_INCLUSAO_EMAIL_PESSOA. */
	OPERACAO_INCLUSAO_EMAIL_PESSOA('J', "Inclusão do e-mail de uma pessoa", I),
	
	/** O atributo OPERACAO_ALTERACAO_AVAL_FINANCEIRA. */
	OPERACAO_ALTERACAO_AVAL_FINANCEIRA('V', "Alteração Aval Financeira da pessoa compartilhamento", A);

	/** O atributo tipo operacao. */
	private Character tipoOperacao;
	
	/** O atributo descricao. */
	private String descricao;
	
	/** O atributo tipo inclusao. */
	private Character tipoInclusao;
	
	/** O atributo operacao base. */
	private TipoOperacaoEnum operacaoBase;

	/**
	 * Construtor
	 * 
	 * @param tipoOperacao
	 * @param descricao
	 * @param operacaoBase
	 */
	private TipoAtualizacaoCadastralEnum(Character tipoOperacao, String descricao,
			TipoOperacaoEnum operacaoBase) {
		this.tipoOperacao = tipoOperacao;
		this.descricao = descricao;
		this.operacaoBase = operacaoBase;
	}

	/**
	 * Construtor
	 * 
	 * @param tipoOperacao
	 * @param descricao
	 * @param tipoInclusao
	 * @param operacaoBase
	 */
	private TipoAtualizacaoCadastralEnum(Character tipoOperacao, String descricao,
			Character tipoInclusao, TipoOperacaoEnum operacaoBase) {

		this.tipoOperacao = tipoOperacao;
		this.descricao = descricao;
		this.tipoInclusao = tipoInclusao;
		this.operacaoBase = operacaoBase;
	}

	/**
	 * @return the tipoOperacao
	 */
	public Character getTipoOperacao() {
		return tipoOperacao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return the tipoInclusao
	 */
	public Character getTipoInclusao() {
		return tipoInclusao;
	}

	/**
	 * @return the operacaoBase
	 */
	public TipoOperacaoEnum getOperacaoBase() {
		return operacaoBase;
	}

	/**
	 * Value of.
	 * 
	 * @param tipoOperacao
	 *            the tipo operacao
	 * @return tipo atualizacao cadastral enum
	 */
	public static TipoAtualizacaoCadastralEnum valueOf(Character tipoOperacao) {
		for (TipoAtualizacaoCadastralEnum tipoAtualizacao : values()) {
			if (tipoAtualizacao.getTipoOperacao().equals(tipoOperacao)) {
				return tipoAtualizacao;
			}
		}
		throw new IllegalArgumentException(String.valueOf(tipoOperacao));
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return getTipoOperacao() + " - " + getDescricao();
	}
}