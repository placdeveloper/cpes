/*
 * SICOOB
 * 
 * Funcionario.java(br.com.sicoob.capes.negocio.entidades.legado.Funcionario)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class Funcionario.
 */
@Entity
@Table(name="FUNCIONARIO")
public class Funcionario extends EntidadeReplicavel<Integer>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo id funcionario. */
	@Id
	@Column(name="NUMPESSOAFUNC")
	private Integer idFuncionario;
	
	/** O atributo matricula. */
	@Column(name="MATRICFUNC")
	private String matricula;
	
	/** O atributo senha. */
	@Column(name="SENHAFUNC")
	private String senha;
	
	/** O atributo bol gerente. */
	private Boolean bolGerente;
	
	/** O atributo num cooperativa. */
	private Integer numCooperativa;
	
	/** O atributo num pac. */
	private Integer numPac;
	
	/** O atributo id funcao. */
	private Short idFuncao;
	
	/** O atributo num nucleo. */
	private Integer numNucleo;
	
	/** O atributo data admissao. */
	private Date dataAdmissao;
	
	/** O atributo data desligamento. */
	private Date dataDesligamento;

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
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the bolGerente
	 */
	public Boolean getBolGerente() {
		return bolGerente;
	}

	/**
	 * @param bolGerente the bolGerente to set
	 */
	public void setBolGerente(Boolean bolGerente) {
		this.bolGerente = bolGerente;
	}

	/**
	 * @return the numCooperativa
	 */
	public Integer getNumCooperativa() {
		return numCooperativa;
	}

	/**
	 * @param numCooperativa the numCooperativa to set
	 */
	public void setNumCooperativa(Integer numCooperativa) {
		this.numCooperativa = numCooperativa;
	}

	/**
	 * @return the numPac
	 */
	public Integer getNumPac() {
		return numPac;
	}

	/**
	 * @param numPac the numPac to set
	 */
	public void setNumPac(Integer numPac) {
		this.numPac = numPac;
	}

	/**
	 * @return the idFuncao
	 */
	public Short getIdFuncao() {
		return idFuncao;
	}

	/**
	 * @param idFuncao the idFuncao to set
	 */
	public void setIdFuncao(Short idFuncao) {
		this.idFuncao = idFuncao;
	}

	/**
	 * @return the numNucleo
	 */
	public Integer getNumNucleo() {
		return numNucleo;
	}

	/**
	 * @param numNucleo the numNucleo to set
	 */
	public void setNumNucleo(Integer numNucleo) {
		this.numNucleo = numNucleo;
	}

	/**
	 * @return the dataAdmissao
	 */
	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	/**
	 * @param dataAdmissao the dataAdmissao to set
	 */
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	/**
	 * @return the dataDesligamento
	 */
	public Date getDataDesligamento() {
		return dataDesligamento;
	}

	/**
	 * @param dataDesligamento the dataDesligamento to set
	 */
	public void setDataDesligamento(Date dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getIdSQL() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof Integer){
			this.idFuncionario = (Integer) idSQL;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {;
	}	
}
