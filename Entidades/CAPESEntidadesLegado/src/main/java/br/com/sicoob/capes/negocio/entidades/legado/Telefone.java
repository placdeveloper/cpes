/*
 * SICOOB
 * 
 * Telefone.java(br.com.sicoob.capes.negocio.entidades.legado.Telefone)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entidade que mapeia a tabela DB2TelefonePessoa.
 * 
 * @author Erico.Junior
 */
@Entity
@Table (name = "DB2TelefonePessoa")
public class Telefone extends EntidadeReplicavel<Integer> {

	/** Serial UID.*/
	private static final long serialVersionUID = 6323764136071303435L;

	/** O atributo id telefone pessoa. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idTelefonePessoa;
	
	/** O atributo pessoa. */
	@ManyToOne
	@JoinColumn (name = "NumPessoa", referencedColumnName = "NumPessoa")	
	private Pessoa pessoa;	
	
	/** O atributo cod tipo telefone. */
	@Column(name = "CodTipoTelefone")
	private Short codTipoTelefone;

	/** O atributo ddd. */
	@Column (name = "NumDDD")
	private String ddd;
	
	/** O atributo numero. */
	@Column (name = "NumTelefone")
	private String numero;
	
	/** O atributo ramal. */
	@Column (name = "NumRamal")
	private String ramal;

	/** O atributo id telefone pessoa d b2. */
	private Long idTelefonePessoaDB2;

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
	 * @return the codTipoTelefone
	 */
	public Short getCodTipoTelefone() {
		return codTipoTelefone;
	}

	/**
	 * @param codTipoTelefone the codTipoTelefone to set
	 */
	public void setCodTipoTelefone(Short codTipoTelefone) {
		this.codTipoTelefone = codTipoTelefone;
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
	 * @return the ramal
	 */
	public String getRamal() {
		return ramal;
	}

	/**
	 * @param ramal the ramal to set
	 */
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	/**
	 * @return the idTelefonePessoaDB2
	 */
	public Long getIdTelefonePessoaDB2() {
		return idTelefonePessoaDB2;
	}

	/**
	 * @param idTelefonePessoaDB2 the idTelefonePessoaDB2 to set
	 */
	public void setIdTelefonePessoaDB2(Long idTelefonePessoaDB2) {
		this.idTelefonePessoaDB2 = idTelefonePessoaDB2;
	}

	/**
	 * @return the idTelefonePessoa
	 */
	public Integer getIdTelefonePessoa() {
		return idTelefonePessoa;
	}

	/**
	 * @param idTelefonePessoa the idTelefonePessoa to set
	 */
	public void setIdTelefonePessoa(Integer idTelefonePessoa) {
		this.idTelefonePessoa = idTelefonePessoa;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getIdSQL() {
		return getIdTelefonePessoa();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return getIdTelefonePessoaDB2();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof Integer){
			setIdTelefonePessoa((Integer)idSQL);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Long){
			setIdTelefonePessoaDB2((Long) idDB2);
		}
	}
}
