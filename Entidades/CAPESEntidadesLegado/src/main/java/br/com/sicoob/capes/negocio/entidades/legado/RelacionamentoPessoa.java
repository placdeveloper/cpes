/*
 * SICOOB
 * 
 * RelacionamentoPessoa.java(br.com.sicoob.capes.negocio.entidades.legado.RelacionamentoPessoa)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * Entidade que armazena as informações de relacionamento entre pessoas
 *
 * 28/10/2011
 * @author Rodrigo.Chaves
 */
@Entity
@Table(name = "DB2RelacionamentoPessoa")
public class RelacionamentoPessoa extends EntidadeReplicavel<Integer> {

	/** Serial UID */
	private static final long serialVersionUID = 7918807636600364258L;
	
	/** O atributo id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdRelacionamentoPessoa")
	private Integer id;
	
	/** O atributo pessoa. */
	@ManyToOne(optional = false)
	@JoinColumn (name = "NumPessoa", referencedColumnName = "NumPessoa")	
	private Pessoa pessoa;	
	
	/** O atributo pessoa relacionada. */
	@ManyToOne(optional = false)
	@JoinColumn (name = "NumPessoaRelacionada", referencedColumnName = "NumPessoa")	
	private Pessoa pessoaRelacionada;	
	
	/** O atributo codigo tipo relacionamento. */
	@Column(name = "CodRelacionamento", nullable = false, precision = 5)
	private Short codigoTipoRelacionamento;
	
	/** O atributo data inicio relacionamento. */
	@Column(name = "DataInicioRelacionamento", nullable = false)
	private DateTimeDB dataInicioRelacionamento;
	
	/** O atributo data fim relacionamento. */
	@Column(name = "DataFimRelacionamento")
	private DateTimeDB dataFimRelacionamento;
	
	/** O atributo percentual capital empresa. */
	@Column(name = "PercCapitalEmpresa", precision = 7, scale = 4)
	private BigDecimal percentualCapitalEmpresa = BigDecimal.ZERO;
	
	/** O atributo indeterminado. */
	@Column(name = "BolTempoIndeterminado")
	private Boolean indeterminado;
	
	/** O atributo id relacionamento d b2. */
	@Column(name = "IdRelacionamentoDB2", precision = 10)
	private Long idRelacionamentoDB2;

	/** O atributo id instituicao. */
	@Transient
	private Integer idInstituicao;
	
	/** O atributo tipo conjuge. */
	@Transient
	private boolean tipoConjuge;
	
	/**
	 * Recupera id.
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Preenche id.
	 * 
	 * @param id
	 *            o novo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Recupera pessoa.
	 * 
	 * @return pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * Preenche pessoa.
	 * 
	 * @param pessoa
	 *            o novo pessoa
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * Recupera pessoa relacionada.
	 * 
	 * @return pessoa relacionada
	 */
	public Pessoa getPessoaRelacionada() {
		return pessoaRelacionada;
	}

	/**
	 * Preenche pessoa relacionada.
	 * 
	 * @param pessoaRelacionada
	 *            o novo pessoa relacionada
	 */
	public void setPessoaRelacionada(Pessoa pessoaRelacionada) {
		this.pessoaRelacionada = pessoaRelacionada;
	}

	/**
	 * Recupera codigo tipo relacionamento.
	 * 
	 * @return codigo tipo relacionamento
	 */
	public Short getCodigoTipoRelacionamento() {
		return codigoTipoRelacionamento;
	}

	/**
	 * Preenche codigo tipo relacionamento.
	 * 
	 * @param codigoTipoRelacionamento
	 *            o novo codigo tipo relacionamento
	 */
	public void setCodigoTipoRelacionamento(Short codigoTipoRelacionamento) {
		this.codigoTipoRelacionamento = codigoTipoRelacionamento;
	}

	/**
	 * Recupera data inicio relacionamento.
	 * 
	 * @return data inicio relacionamento
	 */
	public DateTimeDB getDataInicioRelacionamento() {
		return dataInicioRelacionamento;
	}

	/**
	 * Preenche data inicio relacionamento.
	 * 
	 * @param dataInicio
	 *            o novo data inicio relacionamento
	 */
	public void setDataInicioRelacionamento(DateTimeDB dataInicio) {
		this.dataInicioRelacionamento = dataInicio;
	}

	/**
	 * Recupera data fim relacionamento.
	 * 
	 * @return data fim relacionamento
	 */
	public DateTimeDB getDataFimRelacionamento() {
		return dataFimRelacionamento;
	}

	/**
	 * Preenche data fim relacionamento.
	 * 
	 * @param dataFim
	 *            o novo data fim relacionamento
	 */
	public void setDataFimRelacionamento(DateTimeDB dataFim) {
		this.dataFimRelacionamento = dataFim;
	}

	/**
	 * Recupera percentual capital empresa.
	 * 
	 * @return percentual capital empresa
	 */
	public BigDecimal getPercentualCapitalEmpresa() {
		return percentualCapitalEmpresa;
	}

	/**
	 * Preenche percentual capital empresa.
	 * 
	 * @param percentualCapitalEmpresa
	 *            o novo percentual capital empresa
	 */
	public void setPercentualCapitalEmpresa(BigDecimal percentualCapitalEmpresa) {
		this.percentualCapitalEmpresa = percentualCapitalEmpresa;
	}

	/**
	 * Recupera indeterminado.
	 * 
	 * @return indeterminado
	 */
	public Boolean getIndeterminado() {
		return indeterminado;
	}

	/**
	 * Preenche indeterminado.
	 * 
	 * @param indeterminado
	 *            o novo indeterminado
	 */
	public void setIndeterminado(Boolean indeterminado) {
		this.indeterminado = indeterminado;
	}

	/**
	 * Recupera id relacionamento d b2.
	 * 
	 * @return id relacionamento d b2
	 */
	public Long getIdRelacionamentoDB2() {
		return idRelacionamentoDB2;
	}

	/**
	 * Preenche id relacionamento d b2.
	 * 
	 * @param idRelacionamentoDB2
	 *            o novo id relacionamento d b2
	 */
	public void setIdRelacionamentoDB2(Long idRelacionamentoDB2) {
		this.idRelacionamentoDB2 = idRelacionamentoDB2;
	}

	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Preenche id instituicao.
	 * 
	 * @param idInstituicao
	 *            o novo id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/**
	 * Verifica se é tipo conjuge.
	 * 
	 * @return true, se for tipo conjuge
	 */
	public boolean isTipoConjuge() {
		return tipoConjuge;
	}

	/**
	 * Preenche tipo conjuge.
	 * 
	 * @param tipoConjuge
	 *            o novo tipo conjuge
	 */
	public void setTipoConjuge(boolean tipoConjuge) {
		this.tipoConjuge = tipoConjuge;
	}

	/**
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return getIdRelacionamentoDB2();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer getIdSQL() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof Integer){
			setId((Integer) idSQL);
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
			setIdRelacionamentoDB2((Long) idDB2);
		}
	}
}
