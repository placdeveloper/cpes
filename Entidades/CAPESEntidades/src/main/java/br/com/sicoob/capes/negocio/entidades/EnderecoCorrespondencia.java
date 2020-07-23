/*
 * SICOOB
 * 
 * EnderecoCorrespondencia.java(br.com.sicoob.capes.negocio.entidades.EnderecoCorrespondencia)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;
import br.com.sicoob.capes.negocio.entidades.vigente.Endereco;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Endereco de correspondência.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "ENDERECOCORRESPONDENCIA", schema = "CLI")
@EntityListeners( { ReplicacaoListener.class })
public class EnderecoCorrespondencia extends CAPESEntidade<Integer> implements
		Replicavel {

	/** Serial UID.*/
	private static final long serialVersionUID = -8517411244178311644L;

	/** O atributo id endereco correspondencia. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDENDERECOCORRESPONDENCIA")
	private Integer idEnderecoCorrespondencia;

	/** O atributo endereco. */
	@JoinColumn(name = "IDENDERECOPESSOA")
	@ManyToOne
	private Endereco endereco;

	/** O atributo id instituicao. */
	private Integer idInstituicao;

	/** O atributo pessoa compartilhamento. */
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	@ManyToOne
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	/**
	 * @return the idEnderecoCorrespondencia
	 */
	public Integer getIdEnderecoCorrespondencia() {
		return idEnderecoCorrespondencia;
	}

	/**
	 * @param idEnderecoCorrespondencia the idEnderecoCorrespondencia to set
	 */
	public void setIdEnderecoCorrespondencia(Integer idEnderecoCorrespondencia) {
		this.idEnderecoCorrespondencia = idEnderecoCorrespondencia;
	}

	/**
	 * @return the endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
	 * @return the pessoaCompartilhamento
	 */
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}

	/**
	 * @param pessoaCompartilhamento the pessoaCompartilhamento to set
	 */
	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoa) {
		this.pessoaCompartilhamento = pessoa;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdEnderecoCorrespondencia();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdEnderecoCorrespondencia(id);
	}

}
