/*
 * SICOOB
 * 
 * DadosEtiquetaVO.java(br.com.sicoob.capes.api.negocio.vo.DadosEtiquetaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.util.Date;

/**
 * @author erico.junior
 *
 */
public class DadosEtiquetaVO extends AbstractPessoaVO {

	/** Serial UID.*/
	private static final long serialVersionUID = -7784561146464864737L;

	/** O atributo id pessoa legado. */
	private Integer idPessoaLegado;

	/** O atributo id instituicao. */
	private Integer idInstituicao;
	
	/** O atributo nome pessoa. */
	private String nomePessoa;

	/** O atributo desc endereco. */
	private String descEndereco;

	/** O atributo desc numero. */
	private String descNumero;

	/** O atributo desc complemento. */
	private String descComplemento;

	/** O atributo nome bairro. */
	private String nomeBairro;

	/** O atributo num cep. */
	private String numCep;

	/** O atributo id tipo logradouro. */
	private Short idTipoLogradouro; 

	/** O atributo id localidade. */
	private Integer idLocalidade;

	/** O atributo cod tipo endereco. */
	private Short codTipoEndereco;
	
	/** O atributo desc tipo endereco. */
	private String descTipoEndereco;
	
	/** O atributo codTipoPessoa. */
	private Short codTipoPessoa;
	
	/** O atributo numCpfCnpj. */
	private String numCpfCnpj;
	
	/** O atributo codTipoSexo. */
	private String codTipoSexo;
	
	/** O atributo dataNascimento. */
	private Date dataNascimento;
	

	/**
	 * Cria uma nova instância de dados etiqueta vo.
	 */
	public DadosEtiquetaVO() {
	}
	
	/**
	 * Cria uma nova instância de dados etiqueta vo.
	 * 
	 * @param idPessoaLegado
	 *            the id pessoa legado
	 * @param idInstituicao
	 *            the id instituicao
	 * @param nomePessoa
	 *            the nome pessoa
	 * @param descEndereco
	 *            the desc endereco
	 * @param descNumero
	 *            the desc numero
	 * @param descComplemento
	 *            the desc complemento
	 * @param nomeBairro
	 *            the nome bairro
	 * @param numCep
	 *            the num cep
	 * @param idTipoLogradouro
	 *            the id tipo logradouro
	 * @param idLocalidade
	 *            the id localidade
	 * @param codTipoEndereco
	 *            the cod tipo endereco
	 * @param descTipoEndereco
	 *            the desc tipo endereco
	 */
	public DadosEtiquetaVO(Integer idPessoaLegado, Integer idInstituicao,
			String nomePessoa, String descEndereco, String descNumero,
			String descComplemento, String nomeBairro, String numCep,
			Short idTipoLogradouro, Integer idLocalidade,
			Short codTipoEndereco, String descTipoEndereco) {
		
		super();
		this.idPessoaLegado = idPessoaLegado;
		this.idInstituicao = idInstituicao;
		this.nomePessoa = nomePessoa;
		this.descEndereco = descEndereco;
		this.descNumero = descNumero;
		this.descComplemento = descComplemento;
		this.nomeBairro = nomeBairro;
		this.numCep = numCep;
		this.idTipoLogradouro = idTipoLogradouro;
		this.idLocalidade = idLocalidade;
		this.codTipoEndereco = codTipoEndereco;
		this.descTipoEndereco = descTipoEndereco;
	}	
	
	/**
	 * @return the idPessoaLegado
	 */
	public Integer getIdPessoaLegado() {
		return idPessoaLegado;
	}

	/**
	 * @param idPessoaLegado the idPessoaLegado to set
	 */
	public void setIdPessoaLegado(Integer idPessoaLegado) {
		this.idPessoaLegado = idPessoaLegado;
	}

	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * @return the nomePessoa
	 */
	public String getNomePessoa() {
		return nomePessoa;
	}

	/**
	 * @param nomePessoa the nomePessoa to set
	 */
	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	/**
	 * @return the descEndereco
	 */
	public String getDescEndereco() {
		return descEndereco;
	}

	/**
	 * @param descEndereco the descEndereco to set
	 */
	public void setDescEndereco(String descEndereco) {
		this.descEndereco = descEndereco;
	}

	/**
	 * @return the descNumero
	 */
	public String getDescNumero() {
		return descNumero;
	}

	/**
	 * @param descNumero the descNumero to set
	 */
	public void setDescNumero(String descNumero) {
		this.descNumero = descNumero;
	}

	/**
	 * @return the descComplemento
	 */
	public String getDescComplemento() {
		return descComplemento;
	}

	/**
	 * @param descComplemento the descComplemento to set
	 */
	public void setDescComplemento(String descComplemento) {
		this.descComplemento = descComplemento;
	}

	/**
	 * @return the nomeBairro
	 */
	public String getNomeBairro() {
		return nomeBairro;
	}

	/**
	 * @param nomeBairro the nomeBairro to set
	 */
	public void setNomeBairro(String nomeBairro) {
		this.nomeBairro = nomeBairro;
	}

	/**
	 * @return the numCep
	 */
	public String getNumCep() {
		return numCep;
	}

	/**
	 * @param numCep the numCep to set
	 */
	public void setNumCep(String numCep) {
		this.numCep = numCep;
	}

	/**
	 * @return the idTipoLogradouro
	 */
	public Short getIdTipoLogradouro() {
		return idTipoLogradouro;
	}

	/**
	 * @param idTipoLogradouro the idTipoLogradouro to set
	 */
	public void setIdTipoLogradouro(Short idTipoLogradouro) {
		this.idTipoLogradouro = idTipoLogradouro;
	}

	/**
	 * @return the idLocalidade
	 */
	public Integer getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade the idLocalidade to set
	 */
	public void setIdLocalidade(Integer idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return the codTipoEndereco
	 */
	public Short getCodTipoEndereco() {
		return codTipoEndereco;
	}

	/**
	 * @param codTipoEndereco the codTipoEndereco to set
	 */
	public void setCodTipoEndereco(Short codTipoEndereco) {
		this.codTipoEndereco = codTipoEndereco;
	}

	/**
	 * @return the descTipoEndereco
	 */
	public String getDescTipoEndereco() {
		return descTipoEndereco;
	}

	/**
	 * @param descTipoEndereco the descTipoEndereco to set
	 */
	public void setDescTipoEndereco(String descTipoEndereco) {
		this.descTipoEndereco = descTipoEndereco;
	}
	
	/**
	 * @return the codTipoPessoa
	 */
	public Short getCodTipoPessoa() {
		return codTipoPessoa;
	}

	/**
	 * @param codTipoPessoa the codTipoPessoa to set
	 */
	public void setCodTipoPessoa(Short codTipoPessoa) {
		this.codTipoPessoa = codTipoPessoa;
	}

	/**
	 * @return the numCpfCnpj
	 */
	public String getNumCpfCnpj() {
		return numCpfCnpj;
	}

	/**
	 * @param numCpfCnpj the numCpfCnpj to set
	 */
	public void setNumCpfCnpj(String numCpfCnpj) {
		this.numCpfCnpj = numCpfCnpj;
	}

	/**
	 * @return the codTipoSexo
	 */
	public String getCodTipoSexo() {
		return codTipoSexo;
	}

	/**
	 * @param codTipoSexo the codTipoSexo to set
	 */
	public void setCodTipoSexo(String codTipoSexo) {
		this.codTipoSexo = codTipoSexo;
	}

	/**
	 * @return the dataNascimento
	 */
	public Date getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * @param dataNascimento the dataNascimento to set
	 */
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idInstituicao;
		result = prime * result + idPessoaLegado;
		return result;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		
		boolean iguais = false;
		
		if(obj != null && getClass().equals(obj.getClass())) {
			DadosEtiquetaVO other = (DadosEtiquetaVO) obj;
			iguais = idInstituicao.equals(other.idInstituicao) 
					&& idPessoaLegado.equals(other.idPessoaLegado);
		} 
		
		return iguais;
	}
}
