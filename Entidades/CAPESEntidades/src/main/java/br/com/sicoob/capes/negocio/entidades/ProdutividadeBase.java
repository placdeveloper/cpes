/*
 * SICOOB
 * 
 * ProdutividadeBase.java(br.com.sicoob.capes.negocio.entidades.ProdutividadeBase)
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
 * 
 * @author Erico.Junior
 */
@MappedSuperclass
public abstract class ProdutividadeBase extends EntidadeCadastroBase {
	
	/** Serial UID.*/
	private static final long serialVersionUID = 1754450042806306759L;

	/** O atributo pessoa compartilhamento. */
	@ManyToOne
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO", insertable = false, updatable = false)
	private PessoaCompartilhamento pessoaCompartilhamento;
	
	@Column(name = "IDBEM")
	private Long idBemImovel;
	
	@Column(name = "IDBEMPESSOA")
	private Long idBemAntigo;
	
	/** O atributo descricao. */
	@Column(name = "DESCPRODUTIVIDADE")
	private String descricao;	
	
	/** O atributo producao. */
	@Column(name = "QTDPRODUCAO")
	private BigDecimal producao;
	
	/** O atributo quantidade ou area. */
	@Column(name = "QTDOUAREA")
	private BigDecimal quantidadeOuArea;

	/** O atributo percentual rebate. */
	@Column(name = "VALORPERCENTUALREBATE")
	private BigDecimal percentualRebate;
	
	/** O atributo valor renda bruta. */
	@Column(name = "VALORRENDABRUTADOZEMESES")
	private BigDecimal valorRendaBruta;

	/** O atributo ano inicio safra. */
	@Column(name = "NUMANOINICIOSAFRA")
	private Short anoInicioSafra;

	/** O atributo ano fim safra. */
	@Column(name = "NUMANOFIMSAFRA")
	private Short anoFimSafra;
	
	/** O atributo valor preco medio. */
	@Column(name = "VALORPRECOMEDIO")
	private BigDecimal valorPrecoMedio;
	
	/** O atributo empreendimento. */
	@ManyToOne
	@JoinColumn(name = "CODEMPREENDIMENTO")
	private Empreendimento empreendimento;
	
	/** O atributo situacao. */
	@Column(name = "CODSITUACAO")
	private Short situacao;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return pessoaCompartilhamento;
	}
	
	/**
	 * Preenche pessoa compartilhamento.
	 * 
	 * @param pessoaCompartilhamento
	 *            o novo pessoa compartilhamento
	 */
	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoaCompartilhamento){
		this.pessoaCompartilhamento = pessoaCompartilhamento;
	}
	
	/**
	 * Recupera descricao.
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Preenche descricao.
	 * 
	 * @param descricao
	 *            o novo descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Recupera producao.
	 * 
	 * @return producao
	 */
	public BigDecimal getProducao() {
		return producao;
	}

	/**
	 * Preenche producao.
	 * 
	 * @param producao
	 *            o novo producao
	 */
	public void setProducao(BigDecimal producao) {
		this.producao = producao;
	}

	/**
	 * Recupera quantidade ou area.
	 * 
	 * @return quantidade ou area
	 */
	public BigDecimal getQuantidadeOuArea() {
		return quantidadeOuArea;
	}

	/**
	 * Preenche quantidade ou area.
	 * 
	 * @param quantidadeOuArea
	 *            o novo quantidade ou area
	 */
	public void setQuantidadeOuArea(BigDecimal quantidadeOuArea) {
		this.quantidadeOuArea = quantidadeOuArea;
	}

	/**
	 * Recupera percentual rebate.
	 * 
	 * @return percentual rebate
	 */
	public BigDecimal getPercentualRebate() {
		return percentualRebate;
	}

	/**
	 * Preenche percentual rebate.
	 * 
	 * @param percentualRebate
	 *            o novo percentual rebate
	 */
	public void setPercentualRebate(BigDecimal percentualRebate) {
		this.percentualRebate = percentualRebate;
	}
	
	/**
	 * Recupera valor renda bruta.
	 * 
	 * @return valor renda bruta
	 */
	public BigDecimal getValorRendaBruta() {
		return valorRendaBruta;
	}

	/**
	 * Preenche valor renda bruta.
	 * 
	 * @param valorRendaBruta
	 *            o novo valor renda bruta
	 */
	public void setValorRendaBruta(BigDecimal valorRendaBruta) {
		this.valorRendaBruta = valorRendaBruta;
	}

	/**
	 * Recupera ano inicio safra.
	 * 
	 * @return ano inicio safra
	 */
	public Short getAnoInicioSafra() {
		return anoInicioSafra;
	}

	/**
	 * Preenche ano inicio safra.
	 * 
	 * @param anoInicioSafra
	 *            o novo ano inicio safra
	 */
	public void setAnoInicioSafra(Short anoInicioSafra) {
		this.anoInicioSafra = anoInicioSafra;
	}

	/**
	 * Recupera ano fim safra.
	 * 
	 * @return ano fim safra
	 */
	public Short getAnoFimSafra() {
		return anoFimSafra;
	}

	/**
	 * Preenche ano fim safra.
	 * 
	 * @param anoFimSafra
	 *            o novo ano fim safra
	 */
	public void setAnoFimSafra(Short anoFimSafra) {
		this.anoFimSafra = anoFimSafra;
	}

	/**
	 * Recupera valor preco medio.
	 * 
	 * @return valor preco medio
	 */
	public BigDecimal getValorPrecoMedio() {
		return valorPrecoMedio;
	}

	/**
	 * Preenche valor preco medio.
	 * 
	 * @param valorPrecoMedio
	 *            o novo valor preco medio
	 */
	public void setValorPrecoMedio(BigDecimal valorPrecoMedio) {
		this.valorPrecoMedio = valorPrecoMedio;
	}

	/**
	 * Recupera empreendimento.
	 * 
	 * @return empreendimento
	 */
	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	/**
	 * Preenche empreendimento.
	 * 
	 * @param empreendimento
	 *            o novo empreendimento
	 */
	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
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
	
	public Long getIdBemAntigo() {
		return idBemAntigo;
	}
	
	public void setIdBemAntigo(Long idBemAntigo) {
		this.idBemAntigo = idBemAntigo;
	}

	public Long getIdBemImovel() {
		return idBemImovel;
	}

	public void setIdBemImovel(Long idBemImovel) {
		this.idBemImovel = idBemImovel;
	}
	
}