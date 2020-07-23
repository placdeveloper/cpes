package br.com.sicoob.capes.cadastro.negocio.vo;

import java.io.Serializable;

import br.com.sicoob.capes.negocio.entidades.Autorizacao;

/**
 * @author Rodrigo.Chaves
 */
public class ItemAutorizacaoVO implements Serializable {

	/** Serial UID */
	private static final long serialVersionUID = -5238322547860300558L;
	
	/** O atributo nomeInstituicao. */
	private String nomeInstituicao;
	
	/** O atributo numeroCooperativa. */
	private Integer numeroCooperativa;
	
	/** O atributo autorizacao. */
	private Autorizacao autorizacao;
	
	/** O atributo atividadeAtual. */
	private String atividadeAtual;
	
	/** O atributo idAtividade. */
	private Integer idAtividade;
	
	/** O atributo idOcorrenciaAtividade. */
	private Integer idOcorrenciaAtividade;
	
	/**
	 * Instancia um novo ItemAutorizacaoVO.
	 */
	public ItemAutorizacaoVO() {
	}
	
	/**
	 * Instancia um novo ItemAutorizacaoVO.
	 *
	 * @param autorizacao o valor de autorizacao
	 */
	public ItemAutorizacaoVO(Autorizacao autorizacao) {
		this.autorizacao = autorizacao;
	}

	/**
	 * Recupera o valor de nomeInstituicao.
	 *
	 * @return o valor de nomeInstituicao
	 */
	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	/**
	 * Define o valor de nomeInstituicao.
	 *
	 * @param nomeInstituicao o novo valor de nomeInstituicao
	 */
	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	/**
	 * Recupera o valor de numeroCooperativa.
	 *
	 * @return o valor de numeroCooperativa
	 */
	public Integer getNumeroCooperativa() {
		return numeroCooperativa;
	}

	/**
	 * Define o valor de numeroCooperativa.
	 *
	 * @param numeroCooperativa o novo valor de numeroCooperativa
	 */
	public void setNumeroCooperativa(Integer numeroCooperativa) {
		this.numeroCooperativa = numeroCooperativa;
	}

	/**
	 * Recupera o valor de autorizacao.
	 *
	 * @return o valor de autorizacao
	 */
	public Autorizacao getAutorizacao() {
		return autorizacao;
	}

	/**
	 * Define o valor de autorizacao.
	 *
	 * @param autorizacao o novo valor de autorizacao
	 */
	public void setAutorizacao(Autorizacao autorizacao) {
		this.autorizacao = autorizacao;
	}

	/**
	 * Recupera o valor de atividadeAtual.
	 *
	 * @return o valor de atividadeAtual
	 */
	public String getAtividadeAtual() {
		return atividadeAtual;
	}

	/**
	 * Define o valor de atividadeAtual.
	 *
	 * @param atividadeAtual o novo valor de atividadeAtual
	 */
	public void setAtividadeAtual(String atividadeAtual) {
		this.atividadeAtual = atividadeAtual;
	}

	/**
	 * @return o valor de idAtividadeAtual
	 */
	public Integer getIdAtividade() {
		return idAtividade;
	}

	/**
	 * @param idAtividade o valor de idAtividadeAtual
	 */
	public void setIdAtividade(Integer idAtividade) {
		this.idAtividade = idAtividade;
	}

	/**
	 * @return o valor de idOcorrenciaAtividade
	 */
	public Integer getIdOcorrenciaAtividade() {
		return idOcorrenciaAtividade;
	}

	/**
	 * @param idOcorrenciaAtividade o valor de idOcorrenciaAtividade
	 */
	public void setIdOcorrenciaAtividade(Integer idOcorrenciaAtividade) {
		this.idOcorrenciaAtividade = idOcorrenciaAtividade;
	}

}
