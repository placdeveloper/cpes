package br.com.sicoob.capes.api.inclusao.negocio.dto;

/**
 * A Classe TelefoneDTO.
 * 
 * @author bruno.carneiro
 */
public class TelefoneDTO extends RegistroInclusaoDTO<Long> {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -8096928442352433308L;

	/** O atributo idTelefonePessoa. */
	private Long idTelefonePessoa;

	/** O atributo codigoTipoTelefone. */
	private Short codigoTipoTelefone;

	/** O atributo ddd. */
	private String ddd;

	/** O atributo telefone. */
	private String telefone;

	/** O atributo ramal. */
	private String ramal;

	/** O atributo observacao. */
	private String observacao;

	/**
	 * Recupera o valor de idTelefonePessoa.
	 * 
	 * @return o valor de idTelefonePessoa
	 */
	public Long getIdTelefonePessoa() {
		return idTelefonePessoa;
	}

	/**
	 * Define o valor de idTelefonePessoa.
	 * 
	 * @param idTelefonePessoa
	 *            o novo valor de idTelefonePessoa
	 */
	public void setIdTelefonePessoa(Long idTelefonePessoa) {
		this.idTelefonePessoa = idTelefonePessoa;
	}

	/**
	 * Recupera o valor de codigoTipoTelefone.
	 * 
	 * @return o valor de codigoTipoTelefone
	 */
	public Short getCodigoTipoTelefone() {
		return codigoTipoTelefone;
	}

	/**
	 * Define o valor de codigoTipoTelefone.
	 * 
	 * @param codigoTipoTelefone
	 *            o novo valor de codigoTipoTelefone
	 */
	public void setCodigoTipoTelefone(Short codigoTipoTelefone) {
		this.codigoTipoTelefone = codigoTipoTelefone;
	}

	/**
	 * Recupera o valor de ddd.
	 * 
	 * @return o valor de ddd
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * Define o valor de ddd.
	 * 
	 * @param ddd
	 *            o novo valor de ddd
	 */
	public void setDdd(String ddd) {
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
	 * Recupera o valor de ramal.
	 * 
	 * @return o valor de ramal
	 */
	public String getRamal() {
		return ramal;
	}

	/**
	 * Define o valor de ramal.
	 * 
	 * @param ramal
	 *            o novo valor de ramal
	 */
	public void setRamal(String ramal) {
		this.ramal = ramal;
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

	@Override
	public Long getId() {
		return getIdTelefonePessoa();
	}

	@Override
	public void setId(Long id) {
		setIdTelefonePessoa(id);
	}

}