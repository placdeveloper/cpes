/*
 * SICOOB
 * 
 * Aprovavel.java(br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel)
 */
package br.com.sicoob.capes.negocio.entidades.interfaces;

import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;


/**
 * Interface para as entidades que precisarão de apovação para atualização cadastral.
 * @author Erico.Junior
 */
public interface Aprovavel extends Vigente {

	/** A Constante SEPARADOR_ID_REGISTRO_CONTROLADO. */
	public static final String SEPARADOR_ID_REGISTRO_CONTROLADO = "#";

	/**
	 * Recupera id.
	 * 
	 * @return id
	 */
	Long getId();

	/**
	 * Recupera id instituicao atualizacao.
	 * 
	 * @return id instituicao atualizacao
	 */
	Integer getIdInstituicaoAtualizacao();
	
	/**
	 * Preenche id instituicao atualizacao.
	 * 
	 * @param idInstituicaoAtualizacao
	 *            o novo id instituicao atualizacao
	 */
	void setIdInstituicaoAtualizacao(Integer idInstituicaoAtualizacao);

	/**
	 * Recupera id registro controlado.
	 * 
	 * @return id registro controlado
	 */
	String getIdRegistroControlado();
	
	/**
	 * Preenche id registro controlado.
	 * 
	 * @param idRegistroControlado
	 *            o novo id registro controlado
	 */
	void setIdRegistroControlado(String idRegistroControlado);

	/**
	 * Recupera pessoa compartilhamento.
	 * 
	 * @return pessoa compartilhamento
	 */
	PessoaCompartilhamento getPessoaCompartilhamento();

	/**
	 * Recupera tipo autorizacao.
	 * 
	 * @return tipo autorizacao
	 */
	br.com.sicoob.capes.comum.negocio.enums.TipoAutorizacaoEnum getTipoAutorizacao();

	/**
	 * Preenche verificar autorizacao.
	 * 
	 * @param verificarAutorizacao
	 *            o novo verificar autorizacao
	 */
	void setVerificarAutorizacao(Boolean verificarAutorizacao);
	
	/**
	 * Recupera verificar autorizacao.
	 * 
	 * @return verificar autorizacao
	 */
	Boolean getVerificarAutorizacao();

	/**
	 * Recupera codigo situacao aprovacao.
	 * 
	 * @return codigo situacao aprovacao
	 */
	Short getCodigoSituacaoAprovacao();
	
	/**
	 * Define o valor de codigoSituacaoAprovacao.
	 *
	 * @param codigoSituacaoAprovacao o novo valor de codigoSituacaoAprovacao
	 */
	void setCodigoSituacaoAprovacao(Short codigoSituacaoAprovacao);

	/**
	 * Recupera situacao aprovacao.
	 * 
	 * @return situacao aprovacao
	 */
	SituacaoRegistroEnum getSituacaoAprovacao();

	/**
	 * Preenche situacao aprovacao.
	 * 
	 * @param situacaoAprovacao
	 *            o novo situacao aprovacao
	 */
	void setSituacaoAprovacao(SituacaoRegistroEnum situacaoAprovacao);
	
	/**
	 * Recupera o  IdUsuarioEnvio.
	 * 
	 * @return situacao aprovacao
	 */
	String getIdUsuarioEnvio();

}