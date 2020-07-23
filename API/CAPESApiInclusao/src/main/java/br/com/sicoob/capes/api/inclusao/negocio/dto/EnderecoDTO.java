package br.com.sicoob.capes.api.inclusao.negocio.dto;

/**
 * A Classe EnderecoDTO.
 * 
 * @author bruno.carneiro
 */
public class EnderecoDTO extends RegistroInclusaoDTO<Long> {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 8138829774778761693L;

	/** O atributo idEndereco. */
	private Long idEndereco;

	/** O atributo codigoTipoEndereco. */
	private Short codigoTipoEndereco;

	/** O atributo codigoTipoLogradouro. */
	private Integer codigoTipoLogradouro;

	/** O atributo codigoLocalidade. */
	private Integer codigoLocalidade;

	/** O atributo cep. */
	private String cep;

	/** O atributo descricao. */
	private String descricao;

	/** O atributo numero. */
	private String numero;

	/** O atributo complemento. */
	private String complemento;

	/** O atributo bairro. */
	private String bairro;

	/**
	 * Recupera o valor de idEndereco.
	 * 
	 * @return o valor de idEndereco
	 */
	public Long getIdEndereco() {
		return idEndereco;
	}

	/**
	 * Define o valor de idEndereco.
	 * 
	 * @param idEndereco
	 *            o novo valor de idEndereco
	 */
	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	/**
	 * Recupera o valor de codigoTipoEndereco.
	 * 
	 * @return o valor de codigoTipoEndereco
	 */
	public Short getCodigoTipoEndereco() {
		return codigoTipoEndereco;
	}

	/**
	 * Define o valor de codigoTipoEndereco.
	 * 
	 * @param codigoTipoEndereco
	 *            o novo valor de codigoTipoEndereco
	 */
	public void setCodigoTipoEndereco(Short codigoTipoEndereco) {
		this.codigoTipoEndereco = codigoTipoEndereco;
	}

	/**
	 * Recupera o valor de codigoTipoLogradouro.
	 * 
	 * @return o valor de codigoTipoLogradouro
	 */
	public Integer getCodigoTipoLogradouro() {
		return codigoTipoLogradouro;
	}

	/**
	 * Define o valor de codigoTipoLogradouro.
	 * 
	 * @param codigoTipoLogradouro
	 *            o novo valor de codigoTipoLogradouro
	 */
	public void setCodigoTipoLogradouro(Integer codigoTipoLogradouro) {
		this.codigoTipoLogradouro = codigoTipoLogradouro;
	}

	/**
	 * Recupera o valor de codigoLocalidade.
	 * 
	 * @return o valor de codigoLocalidade
	 */
	public Integer getCodigoLocalidade() {
		return codigoLocalidade;
	}

	/**
	 * Define o valor de codigoLocalidade.
	 * 
	 * @param codigoLocalidade
	 *            o novo valor de codigoLocalidade
	 */
	public void setCodigoLocalidade(Integer codigoLocalidade) {
		this.codigoLocalidade = codigoLocalidade;
	}

	/**
	 * Recupera o valor de cep.
	 * 
	 * @return o valor de cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Define o valor de cep.
	 * 
	 * @param cep
	 *            o novo valor de cep
	 */
	public void setCep(String cep) {
		this.cep = cep;
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

	/**
	 * Recupera o valor de numero.
	 * 
	 * @return o valor de numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Define o valor de numero.
	 * 
	 * @param numero
	 *            o novo valor de numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Recupera o valor de complemento.
	 * 
	 * @return o valor de complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Define o valor de complemento.
	 * 
	 * @param complemento
	 *            o novo valor de complemento
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * Recupera o valor de bairro.
	 * 
	 * @return o valor de bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * Define o valor de bairro.
	 * 
	 * @param bairro
	 *            o novo valor de bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Override
	public Long getId() {
		return getIdEndereco();
	}

	@Override
	public void setId(Long id) {
		setIdEndereco(id);
	}

}