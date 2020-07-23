/*
 * SICOOB
 * 
 * InformacaoProfissional.java(br.com.sicoob.capes.negocio.entidades.legado.InformacaoProfissional)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * @author Erico.Junior
 *
 */
@Entity
@Table(name="Trabalha")
public class InformacaoProfissional extends EntidadeReplicavel<String>{
	
	/** Serial UID.*/
	private static final long serialVersionUID = -6831356385634734354L;

	/** O atributo id. */
	@Id
	@Column(name = "UIDTrabalha")
	@GeneratedValue(generator="geradorGUID")
	@GenericGenerator(name="geradorGUID", 
			strategy = "br.com.sicoob.capes.negocio.entidades.legado.id.GUIDGenerator")    	
	private String id;
	
	/** O atributo pessoa. */
	@ManyToOne
	@JoinColumn (name = "NumPessoaFisica", referencedColumnName = "NumPessoa")	
	private PessoaFisica pessoa;
	
	/** O atributo empregador. */
	@ManyToOne
	@JoinColumn (name = "NumPessoaJuridica", referencedColumnName = "NumPessoa")	
	private PessoaJuridica empregador;

	/** O atributo codigo conselho. */
	@Column(name="CodSiglaConsReg")
	private Short codigoConselho;
	
	/** O atributo situacao. */
	@Column(name="CodSitFuncionario")
	private Short situacao;	
	
	/** O atributo tipo funcionario. */
	@Column(name="CodTipoFuncionario")
	private Short tipoFuncionario;

	/** O atributo tipo afastamento. */
	@Column(name="CodTpAfastFuncionario")
	private Short tipoAfastamento;

	/** O atributo data admissao. */
	@Column(name="DataAdmissao")
	private DateTimeDB dataAdmissao;

	/** O atributo data desligamento. */
	@Column(name="DataDesligamento")
	private DateTimeDB dataDesligamento;

	/** O atributo matricula. */
	@Column(name="DescMatriculaFunc")
	private String matricula;	
	
	/** O atributo cargo. */
	@Column(name="DescOcupacaoProfissional")
	private String cargo;
	
	/** O atributo mes ano ferias. */
	@Column(name="DescPerAquisitivo")
	private String mesAnoFerias;			
	
	/** O atributo id informacao profissional d b2. */
	private Integer idInformacaoProfissionalDB2;
	
	/** O atributo numero inscricao conselho. */
	@Column(name = "NumInscConsReg")
	private String numeroInscricaoConselho;

	/** O atributo uf conselho. */
	@Column(name = "SiglaUFConsReg")
	private String ufConselho;
	
	/** O atributo descricaoEmpresa. */
	@Column(name = "DescEmpresaTrabalha")
	private String descricaoEmpresa;

	/**
	 * Recupera id.
	 * 
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Preenche id.
	 * 
	 * @param id
	 *            o novo id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Recupera pessoa.
	 * 
	 * @return pessoa
	 */
	public PessoaFisica getPessoa() {
		return pessoa;
	}

	/**
	 * Preenche pessoa.
	 * 
	 * @param pessoa
	 *            o novo pessoa
	 */
	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * Recupera empregador.
	 * 
	 * @return empregador
	 */
	public PessoaJuridica getEmpregador() {
		return empregador;
	}

	/**
	 * Preenche empregador.
	 * 
	 * @param empregador
	 *            o novo empregador
	 */
	public void setEmpregador(PessoaJuridica empregador) {
		this.empregador = empregador;
	}

	/**
	 * Recupera codigo conselho.
	 * 
	 * @return codigo conselho
	 */
	public Short getCodigoConselho() {
		return codigoConselho;
	}

	/**
	 * Preenche codigo conselho.
	 * 
	 * @param codigoConselho
	 *            o novo codigo conselho
	 */
	public void setCodigoConselho(Short codigoConselho) {
		this.codigoConselho = codigoConselho;
	}

	/**
	 * Recupera situacao.
	 * 
	 * @return situacao
	 */
	public Short getSituacao() {
		return situacao;
	}

	/**
	 * Preenche situacao.
	 * 
	 * @param situacao
	 *            o novo situacao
	 */
	public void setSituacao(Short situacao) {
		this.situacao = situacao;
	}

	/**
	 * Recupera tipo funcionario.
	 * 
	 * @return tipo funcionario
	 */
	public Short getTipoFuncionario() {
		return tipoFuncionario;
	}

	/**
	 * Preenche tipo funcionario.
	 * 
	 * @param tipoFuncionario
	 *            o novo tipo funcionario
	 */
	public void setTipoFuncionario(Short tipoFuncionario) {
		this.tipoFuncionario = tipoFuncionario;
	}

	/**
	 * Recupera tipo afastamento.
	 * 
	 * @return tipo afastamento
	 */
	public Short getTipoAfastamento() {
		return tipoAfastamento;
	}

	/**
	 * Preenche tipo afastamento.
	 * 
	 * @param tipoAfastamento
	 *            o novo tipo afastamento
	 */
	public void setTipoAfastamento(Short tipoAfastamento) {
		this.tipoAfastamento = tipoAfastamento;
	}

	/**
	 * Recupera data admissao.
	 * 
	 * @return data admissao
	 */
	public DateTimeDB getDataAdmissao() {
		return dataAdmissao;
	}

	/**
	 * Preenche data admissao.
	 * 
	 * @param dataAdmissao
	 *            o novo data admissao
	 */
	public void setDataAdmissao(DateTimeDB dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	/**
	 * Recupera data desligamento.
	 * 
	 * @return data desligamento
	 */
	public DateTimeDB getDataDesligamento() {
		return dataDesligamento;
	}

	/**
	 * Preenche data desligamento.
	 * 
	 * @param dataDesligamento
	 *            o novo data desligamento
	 */
	public void setDataDesligamento(DateTimeDB dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	/**
	 * Recupera matricula.
	 * 
	 * @return matricula
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 * Preenche matricula.
	 * 
	 * @param matricula
	 *            o novo matricula
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * Recupera cargo.
	 * 
	 * @return cargo
	 */
	public String getCargo() {
		return cargo;
	}

	/**
	 * Preenche cargo.
	 * 
	 * @param cargo
	 *            o novo cargo
	 */
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	/**
	 * Recupera mes ano ferias.
	 * 
	 * @return mes ano ferias
	 */
	public String getMesAnoFerias() {
		return mesAnoFerias;
	}

	/**
	 * Preenche mes ano ferias.
	 * 
	 * @param mesAnoFerias
	 *            o novo mes ano ferias
	 */
	public void setMesAnoFerias(String mesAnoFerias) {
		this.mesAnoFerias = mesAnoFerias;
	}

	/**
	 * Recupera id informacao profissional d b2.
	 * 
	 * @return id informacao profissional d b2
	 */
	public Integer getIdInformacaoProfissionalDB2() {
		return idInformacaoProfissionalDB2;
	}

	/**
	 * Preenche id informacao profissional d b2.
	 * 
	 * @param idInformacaoProfissionalDB2
	 *            o novo id informacao profissional d b2
	 */
	public void setIdInformacaoProfissionalDB2(Integer idInformacaoProfissionalDB2) {
		this.idInformacaoProfissionalDB2 = idInformacaoProfissionalDB2;
	}

	/**
	 * Recupera numero inscricao conselho.
	 * 
	 * @return numero inscricao conselho
	 */
	public String getNumeroInscricaoConselho() {
		return numeroInscricaoConselho;
	}

	/**
	 * Preenche numero inscricao conselho.
	 * 
	 * @param numeroInscricaoConselho
	 *            o novo numero inscricao conselho
	 */
	public void setNumeroInscricaoConselho(String numeroInscricaoConselho) {
		this.numeroInscricaoConselho = numeroInscricaoConselho;
	}

	/**
	 * Recupera uf conselho.
	 * 
	 * @return uf conselho
	 */
	public String getUfConselho() {
		return ufConselho;
	}

	/**
	 * Preenche uf conselho.
	 * 
	 * @param ufConselho
	 *            o novo uf conselho
	 */
	public void setUfConselho(String ufConselho) {
		this.ufConselho = ufConselho;
	}
	
	/**
	 * Recupera o valor de descricaoEmpresa.
	 *
	 * @return o valor de descricaoEmpresa
	 */
	public String getDescricaoEmpresa() {
		return descricaoEmpresa;
	}

	/**
	 * Define o valor de descricaoEmpresa.
	 *
	 * @param descricaoEmpresa o novo valor de descricaoEmpresa
	 */
	public void setDescricaoEmpresa(String descricaoEmpresa) {
		this.descricaoEmpresa = descricaoEmpresa;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return getIdInformacaoProfissionalDB2();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Integer){
			setIdInformacaoProfissionalDB2((Integer) idDB2);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof String){
			setId((String) idSQL);
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

}