package br.com.sicoob.capes.api.inclusao.negocio.dto;

/**
 * A Classe ReferenciaDTO.
 * 
 * @author bruno.carneiro
 */
public class ReferenciaDTO extends RegistroInclusaoDTO<Long> {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 6608845603074261043L;

	/** O atributo idReferenciaPessoa. */
	private Long idReferenciaPessoa;

	/** O atributo codigoTipoReferencia. */
	private Short codigoTipoReferencia;

	/** O atributo ddd. */
	private Short ddd;

	/** O atributo telefone. */
	private String telefone;

	/** O atributo observacao. */
	private String observacao;

	/** O atributo idPessoaReferencia. */
	private Integer idPessoaReferencia;

	/**
	 * Recupera o valor de idReferenciaPessoa.
	 * 
	 * @return o valor de idReferenciaPessoa
	 */
	public Long getIdReferenciaPessoa() {
		return idReferenciaPessoa;
	}

	/**
	 * Define o valor de idReferenciaPessoa.
	 * 
	 * @param idReferenciaPessoa
	 *            o novo valor de idReferenciaPessoa
	 */
	public void setIdReferenciaPessoa(Long idReferenciaPessoa) {
		this.idReferenciaPessoa = idReferenciaPessoa;
	}

	/**
	 * Recupera o valor de ddd.
	 * 
	 * @return o valor de ddd
	 */
	public Short getDdd() {
		return ddd;
	}

	/**
	 * Define o valor de ddd.
	 * 
	 * @param ddd
	 *            o novo valor de ddd
	 */
	public void setDdd(Short ddd) {
		this.ddd = ddd;
	}

	/**
	 * Recupera o valor de telefone.
	 * 
	 * @return o valor de telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * Define o valor de telefone.
	 * 
	 * @param telefone
	 *            o novo valor de telefone
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * Recupera o valor de observacao.
	 * 
	 * @return o valor de observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * Define o valor de observacao.
	 * 
	 * @param observacao
	 *            o novo valor de observacao
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * Recupera o valor de codigoTipoReferencia.
	 * 
	 * @return o valor de codigoTipoReferencia
	 */
	public Short getCodigoTipoReferencia() {
		return codigoTipoReferencia;
	}

	/**
	 * Define o valor de codigoTipoReferencia.
	 * 
	 * @param codigoTipoReferencia
	 *            o novo valor de codigoTipoReferencia
	 */
	public void setCodigoTipoReferencia(Short codigoTipoReferencia) {
		this.codigoTipoReferencia = codigoTipoReferencia;
	}

	/**
	 * Recupera o valor de idPessoaReferencia.
	 * 
	 * @return o valor de idPessoaReferencia
	 */
	public Integer getIdPessoaReferencia() {
		return idPessoaReferencia;
	}
	
	/**
	 * Define o valor de idPessoaReferencia.
	 * 
	 * @param idPessoaReferencia
	 *            o novo valor de idPessoaReferencia
	 */
	public void setIdPessoaReferencia(Integer idPessoaReferencia) {
		this.idPessoaReferencia = idPessoaReferencia;
	}

	/**
	 * Define o valor de idPessoaReferencia.
	 * 
	 * @param idPessoaReferencia
	 *            o novo valor de idPessoaReferencia
	 * 
	 * @deprecated Utilize o setIdPessoaReferencia
	 */
	@Deprecated
	public void setIdPessoaCompartilhamentoReferencia(Integer idPessoaCompartilhamentoReferencia) {
		this.idPessoaReferencia = idPessoaCompartilhamentoReferencia;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdReferenciaPessoa();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
		setIdReferenciaPessoa(id);
	}

}