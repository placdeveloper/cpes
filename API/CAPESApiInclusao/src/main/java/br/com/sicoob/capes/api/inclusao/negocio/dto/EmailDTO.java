package br.com.sicoob.capes.api.inclusao.negocio.dto;

/**
 * A Classe EmailDTO.
 * 
 * @author bruno.carneiro
 */
public class EmailDTO extends RegistroInclusaoDTO<Long> {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 3889698098225996395L;

	/** O atributo idEmail. */
	private Long idEmail;

	/** O atributo codigoTipoEmail. */
	private Short codigoTipoEmail;

	/** O atributo descricao. */
	private String descricao;

	/**
	 * Recupera o valor de idEmail.
	 * 
	 * @return o valor de idEmail
	 */
	public Long getIdEmail() {
		return idEmail;
	}

	/**
	 * Define o valor de idEmail.
	 * 
	 * @param idEmail
	 *            o novo valor de idEmail
	 */
	public void setIdEmail(Long idEmail) {
		this.idEmail = idEmail;
	}

	/**
	 * Recupera o valor de codigoTipoEmail.
	 * 
	 * @return o valor de codigoTipoEmail
	 */
	public Short getCodigoTipoEmail() {
		return codigoTipoEmail;
	}

	/**
	 * Define o valor de codigoTipoEmail.
	 * 
	 * @param codigoTipoEmail
	 *            o novo valor de codigoTipoEmail
	 */
	public void setCodigoTipoEmail(Short codigoTipoEmail) {
		this.codigoTipoEmail = codigoTipoEmail;
	}

	/**
	 * Recupera o valor de descricao.
	 * 
	 * @return o valor de descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Define o valor de descricao.
	 * 
	 * @param descricao
	 *            o novo valor de descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public Long getId() {
		return getIdEmail();
	}

	@Override
	public void setId(Long id) {
		setIdEmail(id);
	}

}