/*
 * SICOOB
 * 
 * ReferenciaBase.java(br.com.sicoob.capes.negocio.entidades.ReferenciaBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Entidade que representa a tabela responsável por armazenar as referências de uma pessoa.
 * 
 * @author Erico.Junior
 */
@MappedSuperclass
public abstract class ReferenciaBase extends EntidadeCadastroBase {
		
	/** Serial UID. */	
	private static final long serialVersionUID = 7046501708229421244L;

	/** O atributo pessoa. */
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	@ManyToOne
	private PessoaCompartilhamento pessoa;

	/** O atributo ddd. */
	@Column(name = "NUMDDD")
	private Short ddd;
	
	/** O atributo telefone. */
	@Column(name = "NUMTELEFONE")
	private String telefone;
	
	/** O atributo observacao. */
	@Column(name = "DESCOBSERVACAO")
	private String observacao;
	
	/** O atributo tipo referencia. */
	@JoinColumn( name = "CODTIPOREFERENCIAPESSOA", referencedColumnName = "CODTIPOREFERENCIAPESSOA")
	@ManyToOne	
	private TipoReferencia tipoReferencia;
	
	/** O atributo pessoa referencia. */
	@JoinColumn( name = "IDPESSOACOMPARTILHAMENTOREFERENCIA", 
		referencedColumnName = "IDPESSOACOMPARTILHAMENTO" )
	@ManyToOne
	private PessoaCompartilhamento pessoaReferencia;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;
	
	/**
	 * @return the pessoaReferencia
	 */
	public PessoaCompartilhamento getPessoaReferencia() {
		return pessoaReferencia;
	}

	/**
	 * @param pessoaReferencia the pessoaReferencia to set
	 */
	public void setPessoaReferencia(PessoaCompartilhamento pessoaReferencia) {
		this.pessoaReferencia = pessoaReferencia;
	}
	
	/**
	 * @return the ddd
	 */
	public Short getDdd() {
		return ddd;
	}

	/**
	 * @param ddd the ddd to set
	 */
	public void setDdd(Short ddd) {
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
	 * @return the observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * @return the tipoReferencia
	 */
	public TipoReferencia getTipoReferencia() {
		return tipoReferencia;
	}

	/**
	 * @param tipoReferencia the tipoReferencia to set
	 */
	public void setTipoReferencia(TipoReferencia tipoReferencia) {
		this.tipoReferencia = tipoReferencia;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return getPessoa();
	}

	/**
	 * Recupera pessoa.
	 * 
	 * @return pessoa
	 */
	public PessoaCompartilhamento getPessoa() {
		return pessoa;
	}

	/**
	 * Preenche pessoa.
	 * 
	 * @param pessoa
	 *            o novo pessoa
	 */
	public void setPessoa(PessoaCompartilhamento pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
}
