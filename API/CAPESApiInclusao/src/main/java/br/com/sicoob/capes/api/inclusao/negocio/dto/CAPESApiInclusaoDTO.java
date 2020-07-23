package br.com.sicoob.capes.api.inclusao.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;

/**
 * A Classe base dos DTO's do projeto 'CAPES-API-INCLUSÃO'.
 * 
 * @author bruno.carneiro
 */
public abstract class CAPESApiInclusaoDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 3345073596210583577L;
	
	/** O atributo idPessoa. */
	private Integer idPessoa;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;

	/** O atributo idUnidadeInst. */
	private Integer idUnidadeInst;

	/** O atributo idUsuarioAprovacao. */
	private String idUsuarioAprovacao;
	
	/**
	 * Recupera o valor de idPessoa.
	 * 
	 * @return o valor de idPessoa
	 */
	public Integer getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Define o valor de idPessoa.
	 * 
	 * @param idPessoa
	 *            o novo valor de idPessoa
	 */
	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * Recupera o valor de idInstituicao.
	 * 
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Define o valor de idInstituicao.
	 * 
	 * @param idInstituicao
	 *            o novo valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Recupera o valor de idUnidadeInst.
	 * 
	 * @return o valor de idUnidadeInst
	 */
	public Integer getIdUnidadeInst() {
		return idUnidadeInst;
	}

	/**
	 * Define o valor de idUnidadeInst.
	 * 
	 * @param idUnidadeInst
	 *            o novo valor de idUnidadeInst
	 */
	public void setIdUnidadeInst(Integer idUnidadeInst) {
		this.idUnidadeInst = idUnidadeInst;
	}

	/**
	 * Recupera o valor de idUsuarioAprovacao.
	 * 
	 * @return o valor de idUsuarioAprovacao
	 */
	public String getIdUsuarioAprovacao() {
		return idUsuarioAprovacao;
	}

	/**
	 * Define o valor de idUsuarioAprovacao.
	 * 
	 * @param idUsuarioAprovacao
	 *            o novo valor de idUsuarioAprovacao
	 */
	public void setIdUsuarioAprovacao(String idUsuarioAprovacao) {
		this.idUsuarioAprovacao = idUsuarioAprovacao;
	}

}