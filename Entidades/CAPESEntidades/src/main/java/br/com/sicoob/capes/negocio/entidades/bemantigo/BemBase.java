/*
 * SICOOB
 * 
 * BemBase.java(br.com.sicoob.capes.negocio.entidades.BemBase)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.EntidadeCadastroBase;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Entidade que representa os dados comuns para Bem e HistoricoBem.
 * @author Erico.Junior
 */
@MappedSuperclass
public abstract class BemBase extends EntidadeCadastroBase {

	/** Serial UID. */
	private static final long serialVersionUID = 4457253217895092216L;

	/** O atributo pessoa compartilhamento. */
	@JoinColumn(name = "idPessoaCompartilhamento")
	@ManyToOne
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	@Column(name = "IDPESSOACOMPARTILHAMENTO", insertable = false, updatable = false)
	private Long idPessoaCompartilhamento;

	/** O atributo descricao. */
	@Column(name = "DESCBEMPESSOA", length = 2000)
	private String descricao;
	
	/** O atributo nome seguradora. */
	@Column(length = 50)
	private String nomeSeguradora;
	
	/** O atributo valor atual mercado. */
	@Column(precision = 19, scale = 4)
	private BigDecimal valorAtualMercado;
	
	/** O atributo valor seguro. */
	@Column(precision = 19, scale = 4)
	private BigDecimal valorSeguro;
	
	/** O atributo data vencimento seguro. */
	private DateTimeDB dataVencimentoSeguro;
	
	/** O atributo percentual. */
	@Column(name="PERCPROPRIEDADE", precision = 5, scale = 2)
	private BigDecimal percentual;
	
	/** O atributo sub tipo. */
	@JoinColumn( name = "CODSUBTIPOBEM", referencedColumnName = "CODSUBTIPOBEM" )
	@ManyToOne
	private SubTipoBem subTipo;
	
	/** O atributo situacao. */
	@JoinColumn( name = "CODSITUACAOBEMPESSOA", referencedColumnName = "CODSITUACAOBEMPESSOA" )
	@ManyToOne
	private SituacaoBem situacao;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;
	
	@Column(name = "IDBEMNOVO")
	private Long idBemNovo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return this.pessoaCompartilhamento;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoa) {
		this.pessoaCompartilhamento = pessoa;
	}
	
	public Long getIdPessoaCompartilhamento() {
		return idPessoaCompartilhamento;
	}

	public void setIdPessoaCompartilhamento(Long idPessoaCompartilhamento) {
		this.idPessoaCompartilhamento = idPessoaCompartilhamento;
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
	 * @return the nomeSeguradora
	 */
	public String getNomeSeguradora() {
		return nomeSeguradora;
	}

	/**
	 * @param nomeSeguradora the nomeSeguradora to set
	 */
	public void setNomeSeguradora(String nomeSeguradora) {
		this.nomeSeguradora = nomeSeguradora;
	}

	/**
	 * @return the valorAtualMercado
	 */
	public BigDecimal getValorAtualMercado() {
		return valorAtualMercado;
	}

	/**
	 * @param valorAtualMercado the valorAtualMercado to set
	 */
	public void setValorAtualMercado(BigDecimal valorAtualMercado) {
		this.valorAtualMercado = valorAtualMercado;
	}

	/**
	 * @return the valorSeguro
	 */
	public BigDecimal getValorSeguro() {
		return valorSeguro;
	}

	/**
	 * @param valorSeguro the valorSeguro to set
	 */
	public void setValorSeguro(BigDecimal valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	/**
	 * @return the dataVencimentoSeguro
	 */
	public DateTimeDB getDataVencimentoSeguro() {
		return dataVencimentoSeguro;
	}

	/**
	 * @param dataVencimentoSeguro the dataVencimentoSeguro to set
	 */
	public void setDataVencimentoSeguro(DateTimeDB dataVencimentoSeguro) {
		this.dataVencimentoSeguro = dataVencimentoSeguro;
	}

	/**
	 * @return the subTipo
	 */
	public SubTipoBem getSubTipo() {
		return subTipo;
	}

	/**
	 * @param subTipo the subTipo to set
	 */
	public void setSubTipo(SubTipoBem subTipo) {
		this.subTipo = subTipo;
	}

	/**
	 * @return the situacao
	 */
	public SituacaoBem getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao the situacao to set
	 */
	public void setSituacao(SituacaoBem situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return the percentual
	 */
	public BigDecimal getPercentual() {
		return percentual;
	}

	/**
	 * @param percentual the percentual to set
	 */
	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
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
	
	public Long getIdBemNovo() {
		return idBemNovo;
	}

	public void setIdBemNovo(Long idBemNovo) {
		this.idBemNovo = idBemNovo;
	}

}