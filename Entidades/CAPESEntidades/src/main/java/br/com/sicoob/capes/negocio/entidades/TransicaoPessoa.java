/*
 * SICOOB
 * 
 * TransicaoPessoa.java(br.com.sicoob.capes.negocio.entidades.TransicaoPessoa)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Classe que representa a transição da pessoa do ambiente legado para o
 * cadastro único.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "TRANSICAOPESSOA", schema = "CLI")
public class TransicaoPessoa extends CAPESEntidade<Integer> {

	/** Serial UID. */
	private static final long serialVersionUID = -8289355762008968304L;

	/** O atributo id transicao pessoa. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTransicaoPessoa;
	
	/** O atributo instituicao. */
	@Embedded
	private Instituicao instituicao;
	
	/** O atributo id pessoa legado. */
	private Integer idPessoaLegado;
	
	/** O atributo data hora integracao. */
	private Date dataHoraIntegracao;
	
	/** O atributo nome pessoa legado. */
	private String nomePessoaLegado;
	
	/** O atributo pessoa compartilhamento. */
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO", referencedColumnName = "IDPESSOACOMPARTILHAMENTO")
	@ManyToOne
	private PessoaCompartilhamento pessoaCompartilhamento;

	/**
	 * @return the idTransicaoPessoa
	 */
	public Integer getIdTransicaoPessoa() {
		return idTransicaoPessoa;
	}

	/**
	 * @param idTransicaoPessoa the idTransicaoPessoa to set
	 */
	public void setIdTransicaoPessoa(Integer idTransicaoPessoa) {
		this.idTransicaoPessoa = idTransicaoPessoa;
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
	 * @return the dataHoraIntegracao
	 */
	public Date getDataHoraIntegracao() {
		return dataHoraIntegracao;
	}

	/**
	 * @param dataHoraIntegracao the dataHoraIntegracao to set
	 */
	public void setDataHoraIntegracao(Date dataHoraIntegracao) {
		this.dataHoraIntegracao = dataHoraIntegracao;
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
	public void setPessoaCompartilhamento(
			PessoaCompartilhamento pessoaCompartilhamento) {
		this.pessoaCompartilhamento = pessoaCompartilhamento;
	}

	/**
	 * @return the instituicao
	 */
	public Instituicao getInstituicao() {
		return instituicao;
	}

	/**
	 * @param instituicao the instituicao to set
	 */
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	/**
	 * @return the nomePessoaLegado
	 */
	public String getNomePessoaLegado() {
		return nomePessoaLegado;
	}

	/**
	 * @param nomePessoaLegado the nomePessoaLegado to set
	 */
	public void setNomePessoaLegado(String nomePessoaLegado) {
		this.nomePessoaLegado = nomePessoaLegado;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	@Transient
	public Integer getId() {
		return getIdTransicaoPessoa();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdTransicaoPessoa(id);
	}
}
