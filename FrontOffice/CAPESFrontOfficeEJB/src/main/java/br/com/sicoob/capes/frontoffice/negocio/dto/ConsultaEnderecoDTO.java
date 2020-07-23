package br.com.sicoob.capes.frontoffice.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

/**
 * A Classe EnderecoDTO.
 * 
 * @author bruno.carneiro
 */
public class ConsultaEnderecoDTO extends BancoobDto {

	
	private static final long serialVersionUID = -3683368538622930290L;

	/** O atributo idEndereco. */
	@AtributoRetorno(posicao = 1)
	private Long idEndereco;

	/** O atributo codigoTipoEndereco. */
	@AtributoRetorno(posicao = 2)
	private Short codigoTipoEndereco;

	/** O atributo codigoTipoLogradouro. */
	@AtributoRetorno(posicao = 3)
	private Integer codigoTipoLogradouro;

	/** O atributo codigoLocalidade. */
	@AtributoRetorno(posicao = 4)
	private Integer codigoLocalidade;

	/** O atributo cep. */
	@AtributoRetorno(posicao = 5)
	private String cep;

	/** O atributo descricao. */
	@AtributoRetorno(posicao = 6)
	private String descricao;

	/** O atributo numero. */
	@AtributoRetorno(posicao = 7)
	private String numero;

	/** O atributo complemento. */
	@AtributoRetorno(posicao = 8)
	private String complemento;

	/** O atributo bairro. */
	@AtributoRetorno(posicao = 9)
	private String bairro;
	
	@AtributoRetorno(posicao = 10)
	private String nomeLocalidade;
	
	@AtributoRetorno(posicao = 11)
	private Integer idUf;
	
	@AtributoRetorno(posicao = 12)
	private String siglaUf;

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

	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	public Integer getIdUf() {
		return idUf;
	}

	public void setIdUf(Integer idUf) {
		this.idUf = idUf;
	}

	public String getSiglaUf() {
		return siglaUf;
	}

	public void setSiglaUf(String siglaUf) {
		this.siglaUf = siglaUf;
	}

}