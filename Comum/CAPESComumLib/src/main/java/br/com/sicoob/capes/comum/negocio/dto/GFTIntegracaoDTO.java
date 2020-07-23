/*
 * SICOOB
 * 
 * GFTIntegracaoDTO.java(br.com.sicoob.capes.comum.negocio.dto.GFTIntegracaoDTO)
 */
package br.com.sicoob.capes.comum.negocio.dto;

/**
 * The Class GFTIntegracaoDTO.
 */
public class GFTIntegracaoDTO {

	/** O atributo id autorizacao. */
	private Long idAutorizacao;
	
	/** O atributo sigla processo. */
	private String siglaProcesso;
	
	/** O atributo id registro controlado. */
	private String idRegistroControlado;
	
	/** O atributo id instituicao origem. */
	private Integer idInstituicaoOrigem;
	
	/** O atributo id unidade inst origem. */
	private Integer idUnidadeInstOrigem;
	
	/** O atributo id instituicao destino. */
	private Integer idInstituicaoDestino;
	
	/** O atributo id unidade inst destino. */
	private Integer idUnidadeInstDestino;

	/**
	 * Cria uma nova instância de GFT integracao dto.
	 */
	public GFTIntegracaoDTO() {

	}

	/**
	 * Recupera id autorizacao.
	 * 
	 * @return id autorizacao
	 */
	public Long getIdAutorizacao() {

		return this.idAutorizacao;
	}

	/**
	 * Preenche id autorizacao.
	 * 
	 * @param idAutorizacao
	 *            o novo id autorizacao
	 */
	public void setIdAutorizacao(Long idAutorizacao) {

		this.idAutorizacao = idAutorizacao;
	}

	/**
	 * Recupera sigla processo.
	 * 
	 * @return sigla processo
	 */
	public String getSiglaProcesso() {

		return this.siglaProcesso;
	}

	/**
	 * Preenche sigla processo.
	 * 
	 * @param siglaProcesso
	 *            o novo sigla processo
	 */
	public void setSiglaProcesso(String siglaProcesso) {

		this.siglaProcesso = siglaProcesso;
	}

	/**
	 * Recupera id registro controlado.
	 * 
	 * @return id registro controlado
	 */
	public String getIdRegistroControlado() {

		return this.idRegistroControlado;
	}

	/**
	 * Preenche id registro controlado.
	 * 
	 * @param idRegistroControlado
	 *            o novo id registro controlado
	 */
	public void setIdRegistroControlado(String idRegistroControlado) {

		this.idRegistroControlado = idRegistroControlado;
	}

	/**
	 * Recupera id instituicao origem.
	 * 
	 * @return id instituicao origem
	 */
	public Integer getIdInstituicaoOrigem() {

		return this.idInstituicaoOrigem;
	}

	/**
	 * Preenche id instituicao origem.
	 * 
	 * @param idInstituicaoOrigem
	 *            o novo id instituicao origem
	 */
	public void setIdInstituicaoOrigem(Integer idInstituicaoOrigem) {

		this.idInstituicaoOrigem = idInstituicaoOrigem;
	}

	/**
	 * Recupera id unidade inst origem.
	 * 
	 * @return id unidade inst origem
	 */
	public Integer getIdUnidadeInstOrigem() {

		return this.idUnidadeInstOrigem;
	}

	/**
	 * Preenche id unidade inst origem.
	 * 
	 * @param idUnidadeInstOrigem
	 *            o novo id unidade inst origem
	 */
	public void setIdUnidadeInstOrigem(Integer idUnidadeInstOrigem) {

		this.idUnidadeInstOrigem = idUnidadeInstOrigem;
	}

	/**
	 * Recupera id instituicao destino.
	 * 
	 * @return id instituicao destino
	 */
	public Integer getIdInstituicaoDestino() {

		return this.idInstituicaoDestino;
	}

	/**
	 * Preenche id instituicao destino.
	 * 
	 * @param idInstituicaoDestino
	 *            o novo id instituicao destino
	 */
	public void setIdInstituicaoDestino(Integer idInstituicaoDestino) {

		this.idInstituicaoDestino = idInstituicaoDestino;
	}

	/**
	 * Recupera id unidade inst destino.
	 * 
	 * @return id unidade inst destino
	 */
	public Integer getIdUnidadeInstDestino() {

		return this.idUnidadeInstDestino;
	}

	/**
	 * Preenche id unidade inst destino.
	 * 
	 * @param idUnidadeInstDestino
	 *            o novo id unidade inst destino
	 */
	public void setIdUnidadeInstDestino(Integer idUnidadeInstDestino) {

		this.idUnidadeInstDestino = idUnidadeInstDestino;
	}
}