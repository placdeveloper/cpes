package br.com.sicoob.capes.processamento.negocio.dominio.exportacaoarquivo.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo.AlinhamentoCampo;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public class RegistroEnderecoVO extends RegistroDadosArquivoBaseVO {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 2612847892333403642L;

	/** A constante CODIGO_REGISTRO. */
	public static final String CODIGO_REGISTRO = "03";

	/** O atributo tipoEndereco. */
	@CampoArquivo(inicio = 21, tamanho = 3, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Short tipoEndereco;

	/** O atributo tipoLogradouro. */
	@CampoArquivo(inicio = 24, tamanho = 5, alinhamento = AlinhamentoCampo.DIREITA, complemento = '0')
	private Integer tipoLogradouro;

	/** O atributo logradouro. */
	@CampoArquivo(inicio = 29, tamanho = 80)
	private String logradouro;

	/** O atributo numero. */
	@CampoArquivo(inicio = 109, tamanho = 12)
	private String numero;

	/** O atributo complemento. */
	@CampoArquivo(inicio = 121, tamanho = 40)
	private String complemento;

	/** O atributo bairro. */
	@CampoArquivo(inicio = 161, tamanho = 60)
	private String bairro;

	/** O atributo municipio. */
	@CampoArquivo(inicio = 221, tamanho = 130)
	private String municipio;
	
	/** O atributo uf. */
	@CampoArquivo(inicio = 351 , tamanho = 2)
	private String uf;

	/** O atributo cep. */
	@CampoArquivo(inicio = 353, tamanho = 8)
	private String cep;

	/** O atributo filler. */
	@CampoArquivo(inicio = 361, tamanho = 628)
	private String filler;

	/**
	 * Instancia um novo RegistroEnderecoVO.
	 */
	public RegistroEnderecoVO() {
		super(CODIGO_REGISTRO);
	}

	/**
     * @param tipoEndereco
     * @param tipoLogradouro
     * @param logradouro
     * @param numero
     * @param complemento
     * @param bairro
     * @param municipio
     * @param cep
     */
	public RegistroEnderecoVO(Long idPessoaCompartilhamento, Short tipoEndereco, Integer tipoLogradouro, String logradouro, String numero, String complemento,
			String bairro, String municipio, String uf, String cep) {
		super(CODIGO_REGISTRO, idPessoaCompartilhamento);
		this.tipoEndereco = tipoEndereco;
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.municipio = municipio;
		this.uf = uf;
		this.cep = cep;
	}

	/**
	 * @return the tipoEndereco
	 */
	public Short getTipoEndereco() {
		return tipoEndereco;
	}

	/**
	 * @param tipoEndereco
	 *            the tipoEndereco to set
	 */
	public void setTipoEndereco(Short tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	/**
	 * @return the tipoLogradouro
	 */
	public Integer getTipoLogradouro() {
		return tipoLogradouro;
	}

	/**
	 * @param tipoLogradouro
	 *            the tipoLogradouro to set
	 */
	public void setTipoLogradouro(Integer tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	/**
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * @param logradouro
	 *            the logradouro to set
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @param complemento
	 *            the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro
	 *            the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return the municipio
	 */
	public String getMunicipio() {
		return municipio;
	}

	/**
	 * @param municipio
	 *            the municipio to set
	 */
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	
	/**
	 * Recupera o valor de uf.
	 *
	 * @return o valor de uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * Define o valor de uf.
	 *
	 * @param uf o novo valor de uf
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @param cep
	 *            the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	/**
	 * @return the filler
	 */
	public String getFiller() {
		return filler;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
	    return ToStringBuilder.reflectionToString(this);
	}

}