/*
 * SICOOB
 * 
 * RelacionamentoPessoaBase.java(br.com.sicoob.capes.negocio.entidades.RelacionamentoPessoaBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Classe base para as entidades de "RelacionamentoPessoa"
 *
 * 24/08/2011
 * @author Rodrigo.Chaves
 */
@MappedSuperclass
public abstract class RelacionamentoPessoaBase extends EntidadeCadastroBase {

	/** Serial UID */
	private static final long serialVersionUID = -2563602629866493622L;
	
	/** O atributo percentual capital empresa. */
	@Column(name = "PERCCAPITALEMPRESA", precision = 5, scale = 2, nullable = false)
	private BigDecimal percentualCapitalEmpresa = BigDecimal.ZERO;
	
	/** O atributo data inicio relacionamento. */
	@Column(name = "DATAINICIORELACIONAMENTO")
	private DateTimeDB dataInicioRelacionamento;
	
	/** O atributo data fim relacionamento. */
	@Column(name = "DATAFIMMANDATO")
	private DateTimeDB dataFimMandato;
	
	/** O atributo data fim relacionamento. */
	@Column(name = "DATAINICIOMANDATO")
	private DateTimeDB dataInicioMandato;
	
	/** O atributo pessoa. */
	@ManyToOne
	@JoinColumn(name = "IDPESSOA")
	private Pessoa pessoa;
	
	/** O atributo pessoa compartilhamento. */
	@ManyToOne
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	/** O atributo pessoa relacionada. */
	@ManyToOne
	@JoinColumn(name = "IDPESSOARELACIONADA", nullable = false)
	private Pessoa pessoaRelacionada;

	/** O atributo tipo relacionamento. */
	@ManyToOne
	@JoinColumn(name = "CODTIPORELACIONAMENTOPESSOA", nullable = false)
	private TipoRelacionamentoPessoa tipoRelacionamento;

	/** O atributo id instituicao. */
	@Column(name = "IDINSTITUICAO", precision = 10, scale = 0)
	private Integer idInstituicao;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;
	
	/**
	 * @return o valor de percentualCapitalEmpresa
	 */
	public BigDecimal getPercentualCapitalEmpresa() {
		return percentualCapitalEmpresa;
	}

	/**
	 * @param percentualCapitalEmpresa o valor de percentualCapitalEmpresa
	 */
	public void setPercentualCapitalEmpresa(BigDecimal percentualCapitalEmpresa) {
		this.percentualCapitalEmpresa = percentualCapitalEmpresa;
	}

	/**
	 * @return o valor de dataInicioRelacionamento
	 */
	public DateTimeDB getDataInicioRelacionamento() {
		return dataInicioRelacionamento;
	}

	/**
	 * @param dataFimMandato o valor de dataFimMandato
	 */
	public void setDataInicioRelacionamento(DateTimeDB dataInicioRelacionamento) {
		this.dataInicioRelacionamento = dataInicioRelacionamento;
	}

	/**
	 * @return o valor de dataFimMandato
	 */
	public DateTimeDB getDataFimMandato() {
		return dataFimMandato;
	}

	/**
	 * @param dataFimMandato o valor de dataFimMandato
	 */
	public void setDataFimMandato(DateTimeDB dataFimMandato) {
		this.dataFimMandato = dataFimMandato;
	}
	
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento == null ? getPessoa().getPessoaCompartilhamento() : pessoaCompartilhamento;
	}

	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoaCompartilhamento) {
		this.pessoaCompartilhamento = pessoaCompartilhamento;
	}

	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	/**
	 * @return o valor de pessoaRelacionada
	 */
	public Pessoa getPessoaRelacionada() {
		return pessoaRelacionada;
	}

	/**
	 * @param pessoaRelacionada o valor de pessoaRelacionada
	 */
	public void setPessoaRelacionada(Pessoa pessoaRelacionada) {
		this.pessoaRelacionada = pessoaRelacionada;
	}

	/**
	 * @return o valor de tipoRelacionamento
	 */
	public TipoRelacionamentoPessoa getTipoRelacionamento() {
		return tipoRelacionamento;
	}

	/**
	 * @param tipoRelacionamento o valor de tipoRelacionamento
	 */
	public void setTipoRelacionamento(TipoRelacionamentoPessoa tipoRelacionamento) {
		this.tipoRelacionamento = tipoRelacionamento;
	}

	/**
	 * @return o valor de idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * @param idInstituicao o valor de idInstituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
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
	 * @return
	 */
	public DateTimeDB getDataInicioMandato() {
		return dataInicioMandato;
	}

	/**
	 * @param dataInicioMandato
	 */
	public void setDataInicioMandato(DateTimeDB dataInicioMandato) {
		this.dataInicioMandato = dataInicioMandato;
	}

}
