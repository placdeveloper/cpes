/*
 * SICOOB
 * 
 * TelefoneBase.java(br.com.sicoob.capes.negocio.entidades.TelefoneBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Entidade que representa a tabela responsável por armazenar os telefones das pessoas.
 * 
 * @author Erico.Junior
 */
@MappedSuperclass
public abstract class TelefoneBase extends EntidadeCadastroBase {

	/** Serial UID.*/
	private static final long serialVersionUID = -5513804963830077501L;

	/** O atributo tipo telefone. */
	@JoinColumn( name = "CODTIPOTELEFONE", referencedColumnName = "CODTIPOTELEFONE" )
	@ManyToOne
	private TipoTelefone tipoTelefone;

	/** O atributo pessoa compartilhamento. */
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	@ManyToOne
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	/** O atributo ddd. */
	@Column(name = "NUMDDD")
	private String ddd;

	/** O atributo telefone. */
	@Column(name = "NUMTELEFONE")
	private String telefone;

	/** O atributo ramal. */
	@Column(name = "NUMRAMAL")
	private String ramal;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;
	
	/** O atributo ativo. */
	@Column(name="DESCOBSERVACAO")
	private String observacao;

	/**
	 * @return the tipoTelefone
	 */
	public TipoTelefone getTipoTelefone() {
		return tipoTelefone;
	}

	/**
	 * @param tipoTelefone the tipoTelefone to set
	 */
	public void setTipoTelefone(TipoTelefone tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
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
	
	/**
	 * @return the observacao
	 */
	public String getobservacao() {
		return observacao;
	}

	/**
	 * @param observacao the observacao to set
	 */
	public void setobservacao(String observacao) {
		this.observacao = observacao;
	}
}
