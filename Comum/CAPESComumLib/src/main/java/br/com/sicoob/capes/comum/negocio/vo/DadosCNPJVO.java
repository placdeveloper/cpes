/*
 * SICOOB
 * 
 * DadosCNPJVO.java(br.com.sicoob.capes.comum.negocio.vo.DadosCNPJVO)
 */
package br.com.sicoob.capes.comum.negocio.vo;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * TODO javadoc
 * 
 * 
 * @author Rodrigo.Chaves
 */
public class DadosCNPJVO extends DadosReceitaFederalVO {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -6493112181765498552L;
	
	/** O atributo cod estabelecimento. */
	private Integer codEstabelecimento;
	
	/** O atributo nome empresarial. */
	private String nomeEmpresarial;
	
	/** O atributo nome fantasia. */
	private String nomeFantasia;
	
	/** O atributo cidade exterior. */
	private String cidadeExterior;
	
	/** O atributo codigo pais. */
	private String codigoPais;
	
	/** O atributo nome pais. */
	private String nomePais;
	
	/** O atributo natureza juridica. */
	private String naturezaJuridica;
	
	/** O atributo data abertura. */
	private Date dataAbertura;
	
	/** O atributo cnae principal. */
	private String cnaePrincipal;
	
	/** O atributo tipo logradouro. */
	private String tipoLogradouro;
	
	/** O atributo logradouro. */
	private String logradouro;
	
	/** O atributo numero logradouro. */
	private String numeroLogradouro;
	
	/** O atributo complemento. */
	private String complemento;
	
	/** O atributo bairro. */
	private String bairro;
	
	/** O atributo cep. */
	private Integer cep;
	
	/** O atributo uf. */
	private String uf;
	
	/** O atributo codigo municipio. */
	private Integer codigoMunicipio;
	
	/** O atributo nome municipio. */
	private String nomeMunicipio;
	
	/** O atributo ddd1. */
	private String ddd1;
	
	/** O atributo telefone1. */
	private String telefone1;
	
	/** O atributo ddd2. */
	private String ddd2;
	
	/** O atributo telefone2. */
	private String telefone2;
	
	/** O atributo email. */
	private String email;
	
	/** O atributo cnpj. */
	private String cnpj;

	/**
	 * Recupera cod estabelecimento.
	 * 
	 * @return cod estabelecimento
	 */
	public Integer getCodEstabelecimento() {
		return codEstabelecimento;
	}

	/**
	 * Preenche cod estabelecimento.
	 * 
	 * @param codEstabelecimento
	 *            o novo cod estabelecimento
	 */
	public void setCodEstabelecimento(Integer codEstabelecimento) {
		this.codEstabelecimento = codEstabelecimento;
	}

	/**
	 * Recupera nome empresarial.
	 * 
	 * @return nome empresarial
	 */
	public String getNomeEmpresarial() {
		return nomeEmpresarial;
	}

	/**
	 * Preenche nome empresarial.
	 * 
	 * @param nomeEmpresarial
	 *            o novo nome empresarial
	 */
	public void setNomeEmpresarial(String nomeEmpresarial) {
		this.nomeEmpresarial = nomeEmpresarial;
	}

	/**
	 * Recupera nome fantasia.
	 * 
	 * @return nome fantasia
	 */
	public String getNomeFantasia() {
		return nomeFantasia;
	}

	/**
	 * Preenche nome fantasia.
	 * 
	 * @param nomeFantasia
	 *            o novo nome fantasia
	 */
	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	/**
	 * Recupera cidade exterior.
	 * 
	 * @return cidade exterior
	 */
	public String getCidadeExterior() {
		return cidadeExterior;
	}

	/**
	 * Preenche cidade exterior.
	 * 
	 * @param cidadeExterior
	 *            o novo cidade exterior
	 */
	public void setCidadeExterior(String cidadeExterior) {
		this.cidadeExterior = cidadeExterior;
	}

	/**
	 * Recupera codigo pais.
	 * 
	 * @return codigo pais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * Preenche codigo pais.
	 * 
	 * @param codigoPais
	 *            o novo codigo pais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * Recupera nome pais.
	 * 
	 * @return nome pais
	 */
	public String getNomePais() {
		return nomePais;
	}

	/**
	 * Preenche nome pais.
	 * 
	 * @param nomePais
	 *            o novo nome pais
	 */
	public void setNomePais(String nomePais) {
		this.nomePais = nomePais;
	}

	/**
	 * Recupera natureza juridica.
	 * 
	 * @return natureza juridica
	 */
	public String getNaturezaJuridica() {
		return naturezaJuridica;
	}

	/**
	 * Preenche natureza juridica.
	 * 
	 * @param naturezaJuridica
	 *            o novo natureza juridica
	 */
	public void setNaturezaJuridica(String naturezaJuridica) {
		this.naturezaJuridica = naturezaJuridica;
	}

	/**
	 * Recupera data abertura.
	 * 
	 * @return data abertura
	 */
	public Date getDataAbertura() {
		return dataAbertura;
	}

	/**
	 * Preenche data abertura.
	 * 
	 * @param dataAbertura
	 *            o novo data abertura
	 */
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	/**
	 * Recupera cnae principal.
	 * 
	 * @return cnae principal
	 */
	public String getCnaePrincipal() {
		return cnaePrincipal;
	}

	/**
	 * Preenche cnae principal.
	 * 
	 * @param cnaePrincipal
	 *            o novo cnae principal
	 */
	public void setCnaePrincipal(String cnaePrincipal) {
		this.cnaePrincipal = cnaePrincipal;
	}

	/**
	 * Recupera tipo logradouro.
	 * 
	 * @return tipo logradouro
	 */
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	/**
	 * Preenche tipo logradouro.
	 * 
	 * @param tipoLogradouro
	 *            o novo tipo logradouro
	 */
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	/**
	 * Recupera logradouro.
	 * 
	 * @return logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * Preenche logradouro.
	 * 
	 * @param logradouro
	 *            o novo logradouro
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * Recupera numero logradouro.
	 * 
	 * @return numero logradouro
	 */
	public String getNumeroLogradouro() {
		return numeroLogradouro;
	}

	/**
	 * Preenche numero logradouro.
	 * 
	 * @param numeroLogradouro
	 *            o novo numero logradouro
	 */
	public void setNumeroLogradouro(String numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
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
	public Integer getCep() {
		return cep;
	}

	/**
	 * Preenche cep.
	 * 
	 * @param cep
	 *            o novo cep
	 */
	public void setCep(Integer cep) {
		this.cep = cep;
	}

	/**
	 * Recupera uf.
	 * 
	 * @return uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * Preenche uf.
	 * 
	 * @param uf
	 *            o novo uf
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * Recupera codigo municipio.
	 * 
	 * @return codigo municipio
	 */
	public Integer getCodigoMunicipio() {
		return codigoMunicipio;
	}

	/**
	 * Preenche codigo municipio.
	 * 
	 * @param codigoMunicipio
	 *            o novo codigo municipio
	 */
	public void setCodigoMunicipio(Integer codigoMunicipio) {
		this.codigoMunicipio = codigoMunicipio;
	}

	/**
	 * Recupera nome municipio.
	 * 
	 * @return nome municipio
	 */
	public String getNomeMunicipio() {
		return nomeMunicipio;
	}

	/**
	 * Preenche nome municipio.
	 * 
	 * @param nomeMunicipio
	 *            o novo nome municipio
	 */
	public void setNomeMunicipio(String nomeMunicipio) {
		this.nomeMunicipio = nomeMunicipio;
	}

	/**
	 * Recupera ddd1.
	 * 
	 * @return ddd1
	 */
	public String getDdd1() {
		return ddd1;
	}

	/**
	 * Preenche ddd1.
	 * 
	 * @param ddd1
	 *            o novo ddd1
	 */
	public void setDdd1(String ddd1) {
		this.ddd1 = ddd1;
	}

	/**
	 * Recupera telefone1.
	 * 
	 * @return telefone1
	 */
	public String getTelefone1() {
		return telefone1;
	}

	/**
	 * Preenche telefone1.
	 * 
	 * @param telefone1
	 *            o novo telefone1
	 */
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	/**
	 * Recupera ddd2.
	 * 
	 * @return ddd2
	 */
	public String getDdd2() {
		return ddd2;
	}

	/**
	 * Preenche ddd2.
	 * 
	 * @param ddd2
	 *            o novo ddd2
	 */
	public void setDdd2(String ddd2) {
		this.ddd2 = ddd2;
	}

	/**
	 * Recupera telefone2.
	 * 
	 * @return telefone2
	 */
	public String getTelefone2() {
		return telefone2;
	}

	/**
	 * Preenche telefone2.
	 * 
	 * @param telefone2
	 *            o novo telefone2
	 */
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	/**
	 * Recupera email.
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Preenche email.
	 * 
	 * @param email
	 *            o novo email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getNome() {
		return getNomeEmpresarial();
	}
	
	/**
	 * Recupera cnpj.
	 * 
	 * @return cnpj
	 */
	public String getCnpj() {
		return cnpj;
	}

	/**
	 * Preenche cnpj.
	 * 
	 * @param cnpj
	 *            o novo cnpj
	 */
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getNumeroInscricao() {
		return getCnpj();
	}

	/**
	 * Recupera codigo forma constituicao.
	 * 
	 * @return codigo forma constituicao
	 */
	public Short getCodigoFormaConstituicao() {
		Short formaConstituicao = null;
		if (StringUtils.isNotBlank(getNaturezaJuridica())) {
			String codigo = StringUtils.left(getNaturezaJuridica(), 3);
			formaConstituicao = Short.valueOf(codigo);
		}
		return formaConstituicao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.getClass().getCanonicalName() + "(nomeEmpresarial=" + nomeEmpresarial
				+ "nomeFantasia=" + nomeFantasia + "cidadeExterior=" + cidadeExterior
				+ "codigoPais=" + codigoPais + "nomePais=" + nomePais + "naturezaJuridica="
				+ naturezaJuridica + "dataAbertura=" + dataAbertura + "cnaePrincipal="
				+ cnaePrincipal + "tipoLogradouro=" + tipoLogradouro + "logradouro=" + logradouro
				+ "numeroLogradouro=" + numeroLogradouro + "complemento=" + complemento + "bairro="
				+ bairro + "cep=" + cep + "uf=" + uf + "codigoMunicipio=" + codigoMunicipio
				+ "nomeMunicipio=" + nomeMunicipio + "ddd1=" + ddd1 + "telefone1=" + telefone1
				+ "ddd2=" + ddd2 + "telefone2=" + telefone2 + "email=" + email + "cnpj=" + cnpj
				+ ")";
	}
}
