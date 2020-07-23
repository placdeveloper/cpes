/*
 * SICOOB
 * 
 * EnderecoPessoaVO.java(br.com.sicoob.capes.api.negocio.vo.EnderecoPessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Lucas.Borges
 */
public class EnderecoPessoaVO extends AbstractPessoaVO {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -6820440130459867825L;

	/** O atributo id endereco. */
	private Long idEndereco;
	
	/** O atributo data hora inicio. */
	private Date dataHoraInicio;
	
	/** O atributo descricao. */
	private String descricao;
	
	/** O atributo numero. */
	private String numero;
	
	/** O atributo complemento. */
	private String complemento;
	
	/** O atributo bairro. */
	private String bairro;
	
	/** O atributo cep. */
	private String cep;
	
	/** O atributo codigo tipo endereco. */
	private Short codigoTipoEndereco;
	
	/** O atributo descricao tipo endereco. */
	private String descricaoTipoEndereco;
	
	/** O atributo id tipo logradouro. */
	private Short idTipoLogradouro;
	
	/** O atributo id localidade. */
	private Integer idLocalidade;

	/**
	 * Cria uma nova instância de endereco pessoa vo.
	 */
	public EnderecoPessoaVO() {

	}

	/**
	 * Cria uma nova instância de endereco pessoa vo.
	 * 
	 * @param idEndereco
	 *            the id endereco
	 * @param dataHoraInicio
	 *            the data hora inicio
	 * @param descricao
	 *            the descricao
	 * @param numero
	 *            the numero
	 * @param complemento
	 *            the complemento
	 * @param bairro
	 *            the bairro
	 * @param cep
	 *            the cep
	 * @param codigoTipoEndereco
	 *            the codigo tipo endereco
	 * @param descricaoTipoEndereco
	 *            the descricao tipo endereco
	 * @param idTipoLogradouro
	 *            the id tipo logradouro
	 * @param idLocalidade
	 *            the id localidade
	 */
	public EnderecoPessoaVO(Long idEndereco, Date dataHoraInicio, String descricao, String numero, String complemento,
			String bairro, String cep, Short codigoTipoEndereco, String descricaoTipoEndereco, Integer idTipoLogradouro,
			Integer idLocalidade) {
		this.idEndereco = idEndereco;
		this.dataHoraInicio = dataHoraInicio;
		this.descricao = descricao;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.codigoTipoEndereco = codigoTipoEndereco;
		this.descricaoTipoEndereco = descricaoTipoEndereco;
		this.idTipoLogradouro = idTipoLogradouro != null ? idTipoLogradouro.shortValue() : null;
		this.idLocalidade = idLocalidade;
	}

	/**
	 * Recupera id endereco.
	 * 
	 * @return id endereco
	 */
	public Long getIdEndereco() {

		return idEndereco;
	}

	/**
	 * Preenche id endereco.
	 * 
	 * @param idEndereco
	 *            o novo id endereco
	 */
	public void setIdEndereco(Long idEndereco) {

		this.idEndereco = idEndereco;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public Date getDataHoraInicio() {

		return dataHoraInicio;
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(Date dataHoraInicio) {

		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {

		return descricao;
	}

	/**
	 * Preenche descricao.
	 * 
	 * @param descricao
	 *            o novo descricao
	 */
	public void setDescricao(String descricao) {

		this.descricao = descricao;
	}

	/**
	 * Recupera numero.
	 * 
	 * @return numero
	 */
	public String getNumero() {

		return numero;
	}

	/**
	 * Preenche numero.
	 * 
	 * @param numero
	 *            o novo numero
	 */
	public void setNumero(String numero) {

		this.numero = numero;
	}

	/**
	 * Recupera complemento.
	 * 
	 * @return complemento
	 */
	public String getComplemento() {

		return complemento;
	}

	/**
	 * Preenche complemento.
	 * 
	 * @param complemento
	 *            o novo complemento
	 */
	public void setComplemento(String complemento) {

		this.complemento = complemento;
	}

	/**
	 * Recupera bairro.
	 * 
	 * @return bairro
	 */
	public String getBairro() {

		return bairro;
	}

	/**
	 * Preenche bairro.
	 * 
	 * @param bairro
	 *            o novo bairro
	 */
	public void setBairro(String bairro) {

		this.bairro = bairro;
	}

	/**
	 * Recupera cep.
	 * 
	 * @return cep
	 */
	public String getCep() {

		return cep;
	}

	/**
	 * Preenche cep.
	 * 
	 * @param cep
	 *            o novo cep
	 */
	public void setCep(String cep) {

		this.cep = cep;
	}

	/**
	 * Recupera codigo tipo endereco.
	 * 
	 * @return codigo tipo endereco
	 */
	public Short getCodigoTipoEndereco() {

		return codigoTipoEndereco;
	}

	/**
	 * Preenche codigo tipo endereco.
	 * 
	 * @param codigoTipoEndereco
	 *            o novo codigo tipo endereco
	 */
	public void setCodigoTipoEndereco(Short codigoTipoEndereco) {

		this.codigoTipoEndereco = codigoTipoEndereco;
	}

	/**
	 * Recupera descricao tipo endereco.
	 * 
	 * @return descricao tipo endereco
	 */
	public String getDescricaoTipoEndereco() {

		return descricaoTipoEndereco;
	}

	/**
	 * Preenche descricao tipo endereco.
	 * 
	 * @param descricaoTipoEndereco
	 *            o novo descricao tipo endereco
	 */
	public void setDescricaoTipoEndereco(String descricaoTipoEndereco) {

		this.descricaoTipoEndereco = descricaoTipoEndereco;
	}

	/**
	 * Recupera id tipo logradouro.
	 * 
	 * @return id tipo logradouro
	 */
	public Short getIdTipoLogradouro() {

		return idTipoLogradouro;
	}

	/**
	 * Preenche id tipo logradouro.
	 * 
	 * @param idTipoLogradouro
	 *            o novo id tipo logradouro
	 */
	public void setIdTipoLogradouro(Short idTipoLogradouro) {

		this.idTipoLogradouro = idTipoLogradouro;
	}

	/**
	 * Recupera id localidade.
	 * 
	 * @return id localidade
	 */
	public Integer getIdLocalidade() {

		return idLocalidade;
	}

	/**
	 * Preenche id localidade.
	 * 
	 * @param idLocalidade
	 *            o novo id localidade
	 */
	public void setIdLocalidade(Integer idLocalidade) {

		this.idLocalidade = idLocalidade;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		String endereco = this.descricao;
		if (StringUtils.isNotBlank(this.numero)) {
			endereco += " " + this.numero;
		}
		if (StringUtils.isNotBlank(this.complemento)) {
			endereco += " " + this.complemento;
		}
		endereco += ", " + this.bairro;
		return endereco;
	}
}
