/*
 * SICOOB
 * 
 * Endereco.java(br.com.sicoob.capes.negocio.entidades.legado.Endereco)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidade que mapeia a tabela DB2EnderecoPessoa.
 * 
 * @author Erico.Junior
 */
@Entity
@Table (name = "DB2EnderecoPessoa")
public class Endereco extends EntidadeReplicavel<Integer> {

	/** Serial UID.*/
	private static final long serialVersionUID = -6846255659995375459L;

	/** O atributo id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "IdEnderecoPessoa")
	private Integer id;
	
	/** O atributo pessoa. */
	@ManyToOne
	@JoinColumn (name = "NumPessoa", referencedColumnName = "NumPessoa")	
	private Pessoa pessoa;	

	/** O atributo cod tipo endereco. */
	private Short codTipoEndereco;
	
	/** O atributo id tipo logradouro. */
	private Short idTipoLogradouro;
	
	/** O atributo descricao. */
	@Column (name = "DescEndereco")
	private String descricao;

	/** O atributo numero. */
	@Column (name = "DescNumero")
	private String numero;

	/** O atributo complemento. */
	@Column (name = "DescComplemento")
	private String complemento;

	/** O atributo bairro. */
	@Column (name = "NomeBairro")
	private String bairro;

	/** O atributo cidade. */
	@Column (name = "NomeCidade")
	private String cidade;

	/** O atributo uf. */
	@Column (name = "DescUF")
	private String uf;
	
	/** O atributo id municipio ibge. */
	private Integer idMunicipioIbge;

	/** O atributo origem municipio. */
	private Short origemMunicipio;
	
	/** O atributo cep. */
	@Column (name = "NumCEP")
	private String cep;

	/** O atributo endereco correspondencia. */
	@Column (name = "BolEnvioCorrespondenc")
	private Boolean enderecoCorrespondencia = Boolean.FALSE;

	/** O atributo data ultima atualizacao. */
	private Date dataUltimaAtualizacao;
	
	/** O atributo id endereco pessoa d b2. */
	private Long idEnderecoPessoaDB2;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
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
	 * @param complemento the complemento to set
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
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade the cidade to set
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * @param uf the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * @return the idMunicipioIbge
	 */
	public Integer getIdMunicipioIbge() {
		return idMunicipioIbge;
	}

	/**
	 * @param idMunicipioIbge the idMunicipioIbge to set
	 */
	public void setIdMunicipioIbge(Integer idMunicipioIbge) {
		this.idMunicipioIbge = idMunicipioIbge;
	}

	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * @return the enderecoCorrespondencia
	 */
	public Boolean getEnderecoCorrespondencia() {
		return enderecoCorrespondencia;
	}

	/**
	 * @param enderecoCorrespondencia the enderecoCorrespondencia to set
	 */
	public void setEnderecoCorrespondencia(Boolean enderecoCorrespondencia) {
		this.enderecoCorrespondencia = enderecoCorrespondencia;
	}

	/**
	 * @return the dataUltimaAtualizacao
	 */
	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}

	/**
	 * @param dataUltimaAtualizacao the dataUltimaAtualizacao to set
	 */
	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	/**
	 * @return the idEnderecoPessoaDB2
	 */
	public Long getIdEnderecoPessoaDB2() {
		return idEnderecoPessoaDB2;
	}

	/**
	 * @param idEnderecoPessoaDB2 the idEnderecoPessoaDB2 to set
	 */
	public void setIdEnderecoPessoaDB2(Long idEnderecoPessoaDB2) {
		this.idEnderecoPessoaDB2 = idEnderecoPessoaDB2;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getIdSQL() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return getIdEnderecoPessoaDB2();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof Integer){
			setId((Integer)idSQL);
		}
	}

	/**
	 * @return the origemMunicipio
	 */
	public Short getOrigemMunicipio() {
		return origemMunicipio;
	}

	/**
	 * @param origemMunicipio the origemMunicipio to set
	 */
	public void setOrigemMunicipio(Short origemMunicipio) {
		this.origemMunicipio = origemMunicipio;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Long){
			setIdEnderecoPessoaDB2((Long) idDB2);
		}
	}
}

