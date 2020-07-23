/*
 * SICOOB
 * 
 * Referencia.java(br.com.sicoob.capes.negocio.entidades.legado.Referencia)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Entidade que representa as referências das Pessoas.
 * @author Erico.Junior
 */
@Entity
@Table(name = "ReferenciaPessoa")
public class Referencia extends EntidadeReplicavel<String> {

	/** Serial UID. */
	private static final long serialVersionUID = 8850054146455109365L;

	/** Identificador da referência. */
	@Id
	@Column(name = "UIDRefPessoa")
	@GeneratedValue(generator="geradorGUID")
	@GenericGenerator(name="geradorGUID", 
			strategy = "br.com.sicoob.capes.negocio.entidades.legado.id.GUIDGenerator")    	
	private String id;
	
	/** O atributo data cadastro. */
	private Date dataCadastro;

	/** O atributo descricao. */
	@Column (name = "DescReferenciaCli")
	private String descricao;
	
	/** O atributo ddd. */
	private String ddd;
	
	/** O atributo telefone. */
	@Column (name = "NumTelefone")
	private String telefone;
	
	/** O atributo id referencia pessoa d b2. */
	private Long idReferenciaPessoaDB2;
	
	/** O atributo codigo tipo referencia. */
	@Column (name = "CodTipoReferencia")
	private Short codigoTipoReferencia;

	/** O atributo pessoa. */
	@ManyToOne
	@JoinColumn (name = "NumPessoa", referencedColumnName = "NumPessoa")	
	private Pessoa pessoa;
	
	/** Pessoa referenciada. */
	@ManyToOne
	@JoinColumn (name = "NumPessoaRef", referencedColumnName = "NumPessoa")
	private Pessoa pessoaReferenciada;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the dataCadastro
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro the dataCadastro to set
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
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
	 * @return the ddd
	 */
	public String getDdd() {
		return ddd;
	}

	/**
	 * @param ddd the ddd to set
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the codigoTipoReferencia
	 */
	public Short getCodigoTipoReferencia() {
		return codigoTipoReferencia;
	}

	/**
	 * @param codigoTipoReferencia the codigoTipoReferencia to set
	 */
	public void setCodigoTipoReferencia(Short codigoTipoReferencia) {
		this.codigoTipoReferencia = codigoTipoReferencia;
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
	 * @return the pessoaReferenciada
	 */
	public Pessoa getPessoaReferenciada() {
		return pessoaReferenciada;
	}

	/**
	 * @param pessoaReferenciada the pessoaReferenciada to set
	 */
	public void setPessoaReferenciada(Pessoa pessoaReferenciada) {
		this.pessoaReferenciada = pessoaReferenciada;
	}

	/**
	 * @return the idReferenciaPessoaDB2
	 */
	public Long getIdReferenciaPessoaDB2() {
		return idReferenciaPessoaDB2;
	}

	/**
	 * @param idReferenciaPessoaDB2 the idReferenciaPessoaDB2 to set
	 */
	public void setIdReferenciaPessoaDB2(Long idReferenciaPessoaDB2) {
		this.idReferenciaPessoaDB2 = idReferenciaPessoaDB2;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return getIdReferenciaPessoaDB2();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof String){
			setId((String)idSQL);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public String getIdSQL() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Long){
			setIdReferenciaPessoaDB2((Long) idDB2);
		}
	}
}
