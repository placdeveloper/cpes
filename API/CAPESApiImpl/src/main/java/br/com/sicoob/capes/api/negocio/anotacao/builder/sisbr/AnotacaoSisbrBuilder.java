/*
 * SICOOB
 * 
 * AnotacaoSisbrBuilder.java(br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr.AnotacaoSisbrBuilder)
 */
package br.com.sicoob.capes.api.negocio.anotacao.builder.sisbr;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.math.NumberUtils;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.api.negocio.anotacao.builder.AnotacaoBuilder;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import br.com.sicoob.capes.negocio.entidades.AnotacaoSisbr;
import br.com.sicoob.capes.negocio.entidades.Instituicao;

/**
 * Superclasse para os builders das anotações do SISBR.
 * @author Erico.Junior
 */
public abstract class AnotacaoSisbrBuilder extends AnotacaoBuilder {

	/** O atributo dto. */
	private final transient AnotacaoSisbrDTO dto;

	/**
	 * Construtor do Builder.
	 * 
	 * @param dto
	 *            Os dados referentes a anotação do SISBR.
	 */
	public AnotacaoSisbrBuilder(AnotacaoSisbrDTO dto) {
		super();
		this.dto = dto;
	}

	/**
	 * Cria a anotação .
	 * @param dataAnotacao A data da anotação.
	 * @return a Anotação com os dados preenchidos.
	 */
	@Override
	public Anotacao criarAnotacao(Date dataAnotacao) {

		AnotacaoSisbr anotacao = new AnotacaoSisbr();
		
		// Dados do detalhe.
		anotacao.setDescObservacao(dto.getObservacao());
		anotacao.setInstituicaoModalidadeProduto(obterInstituicao(dto));
		anotacao.setIdProduto(dto.getIdProduto());
		anotacao.setIdModalidadeProduto(dto.getIdModalidadeProduto());
		anotacao.setNumeroContrato(dto.getNumeroContrato());
		anotacao.setDataVencimento(new DateTimeDB(dto.getDataVencimento().getTime()));
		return super.popularDadosBasicos(anotacao, dataAnotacao);
	}

	/**
	 * Obter instituicao.
	 * 
	 * @param dto
	 *            the dto
	 * @return instituicao
	 */
	protected Instituicao obterInstituicao(AnotacaoSisbrDTO dto) {
		
		Instituicao instituicao = new Instituicao();
		instituicao.setIdInstituicao(dto.getIdInstituicao());
		instituicao.setIdUnidadeInst(dto.getIdUnidadeInst());
		return instituicao;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected Date obterDataOcorrencia() {
		return dto.getDataOcorrencia();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected Integer obterQuantidade() {
		return NumberUtils.INTEGER_ZERO;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public BigDecimal obterValorAnotacao() {
		return dto.getValor();
	}

}
