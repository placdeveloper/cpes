/*
 * SICOOB
 * 
 * FonteRenda.java(br.com.sicoob.capes.negocio.entidades.legado.FonteRenda)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * Entidade que representa a fonte de renda de uma pessoa.
 * 
 * @author Erico.Junior
 */
@Entity
@Table(name = "FonteRenda")
public class FonteRenda extends EntidadeReplicavel<String> {

	/** Número de série da classe. */
	private static final long serialVersionUID = 1L;

	/** Identificador da fonte de renda. */
	@Id
	@Column(name = "UIDFonteRenda")
	@GeneratedValue(generator="geradorGUID")
	@GenericGenerator(name="geradorGUID", 
			strategy = "br.com.sicoob.capes.negocio.entidades.legado.id.GUIDGenerator")    	
	private String id;

	/** Código da fonte da renda. */
	@Column(name = "CodFonteRenda")
	private Short codigo;

	/** Data de cadastro. */
	@Column(name = "DataCadastro")
	private Date dataCadastro;

	/** Indicativo de renda fixa. */
	@Column(name = "BolRendaFixa")
	private Boolean rendaFixa;

	/** Indicativo de renda do cônjuge. */
	@Column(name = "BolConjuge")
	private Boolean rendaConjuge = Boolean.FALSE;

	/** Receita bruta mensal. */
	@Column(name = "ReceitaBrutaMensal")
	private BigDecimal receitaBrutaMensal;

	/** Descrição da renda. */
	@Column(name = "DescRenda")
	private String descricao;

	/** O atributo pessoa. */
	@ManyToOne
	@JoinColumn (name = "NumPessoa", referencedColumnName = "NumPessoa")	
	private Pessoa pessoa;

	/** O atributo pessoa juridica. */
	@ManyToOne
	@JoinColumn (name = "NumPessoaJuridica", referencedColumnName = "NumPessoa")	
	private PessoaJuridica pessoaJuridica;

	/** O atributo id fonte renda d b2. */
	private Long idFonteRendaDB2;
	
	/** O atributo ativo. */
	@Column(name="BOLSIMPLESNACIONAL")
	private Boolean bolSimplesNacional;

	/** O atributo ativo. */
	@Column(name="BOLPOSSUIATIVO")
	private Boolean bolPossuiAtivo;
	
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
	 * @return the codigo
	 */
	public Short getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Short codigo) {
		this.codigo = codigo;
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
	 * @return the rendaFixa
	 */
	public Boolean getRendaFixa() {
		return rendaFixa;
	}

	/**
	 * @param rendaFixa the rendaFixa to set
	 */
	public void setRendaFixa(Boolean rendaFixa) {
		this.rendaFixa = rendaFixa;
	}

	/**
	 * @return the rendaConjuge
	 */
	public Boolean getRendaConjuge() {
		return rendaConjuge;
	}

	/**
	 * @param rendaConjuge the rendaConjuge to set
	 */
	public void setRendaConjuge(Boolean rendaConjuge) {
		this.rendaConjuge = rendaConjuge;
	}

	/**
	 * @return the receitaBrutaMensal
	 */
	public BigDecimal getReceitaBrutaMensal() {
		return receitaBrutaMensal;
	}

	/**
	 * @param receitaBrutaMensal the receitaBrutaMensal to set
	 */
	public void setReceitaBrutaMensal(BigDecimal receitaBrutaMensal) {
		this.receitaBrutaMensal = receitaBrutaMensal;
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
	 * @return the pessoaJuridica
	 */
	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	/**
	 * @param pessoaJuridica the pessoaJuridica to set
	 */
	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	/**
	 * @return the idFonteRendaDB2
	 */
	public Long getIdFonteRendaDB2() {
		return idFonteRendaDB2;
	}

	/**
	 * @param idFonteRendaDB2 the idFonteRendaDB2 to set
	 */
	public void setIdFonteRendaDB2(Long idFonteRendaDB2) {
		this.idFonteRendaDB2 = idFonteRendaDB2;
	}
	
	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return getIdFonteRendaDB2();
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
			setIdFonteRendaDB2((Long) idDB2);
		}
	}

	public Boolean getBolSimplesNacional() {
		return bolSimplesNacional;
	}

	public void setBolSimplesNacional(Boolean bolSimplesNacional) {
		this.bolSimplesNacional = bolSimplesNacional;
	}

	public Boolean getBolPossuiAtivo() {
		return bolPossuiAtivo;
	}
	
	public void setBolPossuiAtivo(Boolean bolPossuiAtivo) {
		this.bolPossuiAtivo = bolPossuiAtivo;
	}	

}
