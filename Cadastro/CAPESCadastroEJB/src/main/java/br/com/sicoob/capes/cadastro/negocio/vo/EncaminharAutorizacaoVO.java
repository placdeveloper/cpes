package br.com.sicoob.capes.cadastro.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * A Classe EncaminharAutorizacaoVO.
 */
public class EncaminharAutorizacaoVO extends BancoobVo {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -4397639600392856975L;
	
	/** O atributo idAutorizacao. */
	private Long idAutorizacao;
	
	/** O atributo dataHoraCadastro. */
	private String dataHoraCadastro;
	
	/** O atributo tipoAutorizacao. */
	private String tipoAutorizacao;
	
	/** O atributo tipoAutorizacaoLabel. */
	private String tipoAutorizacaoLabel;
	
	/** O atributo tipoOperacao. */
	private String tipoOperacao;
	
	/** O atributo cooperativaDestino. */
	private String cooperativaDestino;
	
	/** O atributo unidadeDestino. */
	private Integer unidadeDestino;
	
	/** O atributo cooperativaOrigem. */
	private String cooperativaOrigem;
	
	/** O atributo unidadeOrigem. */
	private Integer unidadeOrigem;
	
	/** O atributo registroControlado. */
	private String registroControlado;
	
	/** O atributo possuiDocumento. */
	private String possuiDocumento;
	
	/** O atributo idInstituicaoOrigem. */
	private Integer idInstituicaoOrigem;
	
	/**
	 * Recupera o valor de dataHoraCadastro.
	 *
	 * @return o valor de dataHoraCadastro
	 */
	public String getDataHoraCadastro() {
		return dataHoraCadastro;
	}
	
	/**
	 * Define o valor de dataHoraCadastro.
	 *
	 * @param dataHoraCadastro o novo valor de dataHoraCadastro
	 */
	public void setDataHoraCadastro(String dataHoraCadastro) {
		this.dataHoraCadastro = dataHoraCadastro;
	}
	
	/**
	 * Recupera o valor de tipoAutorizacao.
	 *
	 * @return o valor de tipoAutorizacao
	 */
	public String getTipoAutorizacao() {
		return tipoAutorizacao;
	}
	
	/**
	 * Define o valor de tipoAutorizacao.
	 *
	 * @param tipoAutorizacao o novo valor de tipoAutorizacao
	 */
	public void setTipoAutorizacao(String tipoAutorizacao) {
		this.tipoAutorizacao = tipoAutorizacao;
	}
	
	/**
	 * Recupera o valor de tipoAutorizacaoLabel.
	 *
	 * @return o valor de tipoAutorizacaoLabel
	 */
	public String getTipoAutorizacaoLabel() {
		return tipoAutorizacaoLabel;
	}
	
	/**
	 * Define o valor de tipoAutorizacaoLabel.
	 *
	 * @param tipoAutorizacaoLabel o novo valor de tipoAutorizacaoLabel
	 */
	public void setTipoAutorizacaoLabel(String tipoAutorizacaoLabel) {
		this.tipoAutorizacaoLabel = tipoAutorizacaoLabel;
	}
	
	/**
	 * Recupera o valor de tipoOperacao.
	 *
	 * @return o valor de tipoOperacao
	 */
	public String getTipoOperacao() {
		return tipoOperacao;
	}
	
	/**
	 * Define o valor de tipoOperacao.
	 *
	 * @param tipoOperacao o novo valor de tipoOperacao
	 */
	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}
	
	/**
	 * Recupera o valor de unidadeDestino.
	 *
	 * @return o valor de unidadeDestino
	 */
	public Integer getUnidadeDestino() {
		return unidadeDestino;
	}
	
	/**
	 * Define o valor de unidadeDestino.
	 *
	 * @param unidadeDestino o novo valor de unidadeDestino
	 */
	public void setUnidadeDestino(Integer unidadeDestino) {
		this.unidadeDestino = unidadeDestino;
	}
	
	/**
	 * Recupera o valor de registroControlado.
	 *
	 * @return o valor de registroControlado
	 */
	public String getRegistroControlado() {
		return registroControlado;
	}
	
	/**
	 * Define o valor de registroControlado.
	 *
	 * @param registroControlado o novo valor de registroControlado
	 */
	public void setRegistroControlado(String registroControlado) {
		this.registroControlado = registroControlado;
	}
	
	/**
	 * Recupera o valor de possuiDocumento.
	 *
	 * @return o valor de possuiDocumento
	 */
	public String getPossuiDocumento() {
		return possuiDocumento;
	}
	
	/**
	 * Define o valor de possuiDocumento.
	 *
	 * @param possuiDocumento o novo valor de possuiDocumento
	 */
	public void setPossuiDocumento(String possuiDocumento) {
		this.possuiDocumento = possuiDocumento;
	}
	
	/**
	 * Recupera o valor de unidadeOrigem.
	 *
	 * @return o valor de unidadeOrigem
	 */
	public Integer getUnidadeOrigem() {
		return unidadeOrigem;
	}
	
	/**
	 * Define o valor de unidadeOrigem.
	 *
	 * @param unidadeOrigem o novo valor de unidadeOrigem
	 */
	public void setUnidadeOrigem(Integer unidadeOrigem) {
		this.unidadeOrigem = unidadeOrigem;
	}
	
	/**
	 * Recupera o valor de idAutorizacao.
	 *
	 * @return o valor de idAutorizacao
	 */
	public Long getIdAutorizacao() {
		return idAutorizacao;
	}
	
	/**
	 * Define o valor de idAutorizacao.
	 *
	 * @param idAutorizacao o novo valor de idAutorizacao
	 */
	public void setIdAutorizacao(Long idAutorizacao) {
		this.idAutorizacao = idAutorizacao;
	}
	
	/**
	 * Recupera o valor de cooperativaDestino.
	 *
	 * @return o valor de cooperativaDestino
	 */
	public String getCooperativaDestino() {
		return cooperativaDestino;
	}
	
	/**
	 * Define o valor de cooperativaDestino.
	 *
	 * @param cooperativaDestino o novo valor de cooperativaDestino
	 */
	public void setCooperativaDestino(String cooperativaDestino) {
		this.cooperativaDestino = cooperativaDestino;
	}
	
	/**
	 * Recupera o valor de cooperativaOrigem.
	 *
	 * @return o valor de cooperativaOrigem
	 */
	public String getCooperativaOrigem() {
		return cooperativaOrigem;
	}
	
	/**
	 * Define o valor de cooperativaOrigem.
	 *
	 * @param cooperativaOrigem o novo valor de cooperativaOrigem
	 */
	public void setCooperativaOrigem(String cooperativaOrigem) {
		this.cooperativaOrigem = cooperativaOrigem;
	}
	
	/**
	 * Recupera o valor de idInstituicaoOrigem.
	 *
	 * @return o valor de idInstituicaoOrigem
	 */
	public Integer getIdInstituicaoOrigem() {
		return idInstituicaoOrigem;
	}
	
	/**
	 * Define o valor de idInstituicaoOrigem.
	 *
	 * @param idInstituicaoOrigem o novo valor de idInstituicaoOrigem
	 */
	public void setIdInstituicaoOrigem(Integer idInstituicaoOrigem) {
		this.idInstituicaoOrigem = idInstituicaoOrigem;
	}	
	
}
