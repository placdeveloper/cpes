/*
 * SICOOB
 * 
 * Funcionario.java(br.com.sicoob.capes.negocio.entidades.Funcionario)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Classe responsavel por armazenar o funcionario 
 * @author juan.damasceno
 *
 */
@Entity
@Table(name="FUNCIONARIO", schema="CLI")
public class Funcionario extends CAPESEntidade<Integer> implements Replicavel {
	
	/** Serial UID. */
	private static final long serialVersionUID = 2189948472008936980L;

	/** O atributo id funcionario. */
	@Id
	@Column(name="IDFUNCIONARIO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idFuncionario;

	/** O atributo pessoa. */
	@ManyToOne
	@JoinColumn(name="IDPESSOA")
	private Pessoa pessoa;
	
	/** O atributo nucleo. */
	@ManyToOne
	@JoinColumn(name="IDNUCLEO")
	private Nucleo nucleo;
	
	/** O atributo funcao. */
	@ManyToOne
	@JoinColumn(name="IDFUNCAO")
	private Funcao funcao;
	
	/** O atributo matricula. */
	@Column(name="NUMMATRICULA")
	private String matricula;

	/** O atributo data admissao. */
	private DateTimeDB dataAdmissao;

	/** O atributo data desligamento. */
	private DateTimeDB dataDesligamento;
	
	/** O atributo instituicao. */
	@Embedded	
	private Instituicao instituicao;

	/** 
	 * {@inheritDoc}
	 */
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return getPessoa().getPessoaCompartilhamento();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdFuncionario();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdFuncionario(id);
	}

	/**
	 * @return the idFuncionario
	 */
	public Integer getIdFuncionario() {
		return idFuncionario;
	}

	/**
	 * @param idFuncionario the idFuncionario to set
	 */
	public void setIdFuncionario(Integer idFuncionario) {
		this.idFuncionario = idFuncionario;
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
	 * @return the nucleo
	 */
	public Nucleo getNucleo() {
		return nucleo;
	}

	/**
	 * @param nucleo the nucleo to set
	 */
	public void setNucleo(Nucleo nucleo) {
		this.nucleo = nucleo;
	}

	/**
	 * @return the funcao
	 */
	public Funcao getFuncao() {
		return funcao;
	}

	/**
	 * @param funcao the funcao to set
	 */
	public void setFuncao(Funcao funcao) {
		this.funcao = funcao;
	}

	/**
	 * @return the matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return the dataAdmissao
	 */
	public DateTimeDB getDataAdmissao() {
		return dataAdmissao;
	}

	/**
	 * @param dataAdmissao the dataAdmissao to set
	 */
	public void setDataAdmissao(DateTimeDB dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	/**
	 * @return the dataDesligamento
	 */
	public DateTimeDB getDataDesligamento() {
		return dataDesligamento;
	}

	/**
	 * @param dataDesligamento the dataDesligamento to set
	 */
	public void setDataDesligamento(DateTimeDB dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
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

}