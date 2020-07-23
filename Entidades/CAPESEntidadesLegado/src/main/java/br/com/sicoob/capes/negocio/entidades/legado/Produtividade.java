/*
 * SICOOB
 * 
 * Produtividade.java(br.com.sicoob.capes.negocio.entidades.legado.Produtividade)
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
 * @author Erico.Junior
 *
 */
@Entity
@Table(name = "ProdutividadePessoa")
public class Produtividade extends EntidadeReplicavel<String> {

	/** Serial UID.*/
	private static final long serialVersionUID = -3733313223487850408L;

	/** O atributo id. */
	@Id
	@Column (name = "UIDProdPes")
	@GeneratedValue(generator="geradorGUID")
	@GenericGenerator(name="geradorGUID", 
			strategy = "br.com.sicoob.capes.negocio.entidades.legado.id.GUIDGenerator")     	
	private String id;
	
	/** O atributo pessoa. */
	@ManyToOne
	@JoinColumn (name = "NumPessoa", referencedColumnName = "NumPessoa")	
	private Pessoa pessoa;
	
	/** O atributo data cadastro. */
	@Column (name = "dataCadastro")
	private Date dataCadastro;
	
	/** O atributo descricao. */
	@Column (name = "DescProdutividadeCli")
	private String descricao;
	
	/** O atributo quantidade producao hectare. */
	@Column (name = "QuantProducaoHectare")
	private BigDecimal quantidadeProducaoHectare;

	/** O atributo area hectare. */
	@Column (name = "AreaHectare")
	private BigDecimal areaHectare;
	
	/** O atributo valor percentual rebate. */
	@Column (name = "ValorPercentualRebate")	
	private BigDecimal valorPercentualRebate;
	
	/** O atributo renda agropecuaria12 meses. */
	@Column (name = "RendaAgropecuaria12Meses")
	private BigDecimal rendaAgropecuaria12Meses;
	
	/** O atributo ano referencia. */
	@Column (name = "NumAnoReferencia")
	private Short anoReferencia;
	
	/** O atributo preco medio unidade. */
	@Column (name = "PrecoMedioUnidade")
	private BigDecimal precoMedioUnidade;
	
	/** O atributo bem. */
	@ManyToOne
	@JoinColumn (name = "UIDBemPessoa", referencedColumnName = "UIDBemPessoa")
	private Bem bem;
	
	/** O atributo empreendimento. */
	@ManyToOne
	@JoinColumn (name = "idempreendimento", referencedColumnName = "idempreendimento")	
	private Empreendimento empreendimento;

	/** O atributo data inativacao. */
	private Date dataInativacao;
	
	/** O atributo id produtividade pessoa d b2. */
	private Long idProdutividadePessoaDB2;
	
	/**
	 * @return o valor do atributo anoReferencia.
	 */
	public Short getAnoReferencia() {
		return anoReferencia;
	}

	/**
	 * @param anoReferencia o novo valor do atributo anoReferencia.
	 */
	public void setAnoReferencia(Short anoReferencia) {
		this.anoReferencia = anoReferencia;
	}

	/**
	 * @return o valor do atributo areaHectare.
	 */
	public BigDecimal getAreaHectare() {
		return areaHectare;
	}

	/**
	 * @param areaHectare o novo valor do atributo areaHectare.
	 */
	public void setAreaHectare(BigDecimal areaHectare) {
		this.areaHectare = areaHectare;
	}

	/**
	 * @return o valor do atributo bem.
	 */
	public Bem getBem() {
		return bem;
	}

	/**
	 * @param bem o novo valor do atributo bem.
	 */
	public void setBem(Bem bem) {
		this.bem = bem;
	}

	/**
	 * @return o valor do atributo dataCadastro.
	 */
	public Date getDataCadastro() {
		return dataCadastro;
	}

	/**
	 * @param dataCadastro o novo valor do atributo dataCadastro.
	 */
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @return o valor do atributo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao o novo valor do atributo descricao.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return o valor do atributo empreendimento.
	 */
	public Empreendimento getEmpreendimento() {
		return empreendimento;
	}

	/**
	 * @param empreendimento o novo valor do atributo empreendimento.
	 */
	public void setEmpreendimento(Empreendimento empreendimento) {
		this.empreendimento = empreendimento;
	}

	/**
	 * @return o valor do atributo id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id o novo valor do atributo id.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return o valor do atributo precoMedioUnidade.
	 */
	public BigDecimal getPrecoMedioUnidade() {
		return precoMedioUnidade;
	}

	/**
	 * @param precoMedioUnidade o novo valor do atributo precoMedioUnidade.
	 */
	public void setPrecoMedioUnidade(BigDecimal precoMedioUnidade) {
		this.precoMedioUnidade = precoMedioUnidade;
	}

	/**
	 * @return o valor do atributo quantidadeProducaoHectare.
	 */
	public BigDecimal getQuantidadeProducaoHectare() {
		return quantidadeProducaoHectare;
	}

	/**
	 * @param quantidadeProducaoHectare o novo valor do atributo quantidadeProducaoHectare.
	 */
	public void setQuantidadeProducaoHectare(BigDecimal quantidadeProducaoHectare) {
		this.quantidadeProducaoHectare = quantidadeProducaoHectare;
	}

	/**
	 * @return o valor do atributo rendaAgropecuaria12Meses.
	 */
	public BigDecimal getRendaAgropecuaria12Meses() {
		return rendaAgropecuaria12Meses;
	}

	/**
	 * @param rendaAgropecuaria12Meses o novo valor do atributo rendaAgropecuaria12Meses.
	 */
	public void setRendaAgropecuaria12Meses(BigDecimal rendaAgropecuaria12Meses) {
		this.rendaAgropecuaria12Meses = rendaAgropecuaria12Meses;
	}

	/**
	 * @return o valor do atributo valorPercentualRebate.
	 */
	public BigDecimal getValorPercentualRebate() {
		return valorPercentualRebate;
	}

	/**
	 * @param valorPercentualRebate o novo valor do atributo valorPercentualRebate.
	 */
	public void setValorPercentualRebate(BigDecimal valorPercentualRebate) {
		this.valorPercentualRebate = valorPercentualRebate;
	}
	
	/**
	 * Recupera id produtividade pessoa d b2.
	 * 
	 * @return id produtividade pessoa d b2
	 */
	public Long getIdProdutividadePessoaDB2() {
		return idProdutividadePessoaDB2;
	}

	/**
	 * Preenche id produtividade pessoa d b2.
	 * 
	 * @param idProdutividadePessoaDB2
	 *            o novo id produtividade pessoa d b2
	 */
	public void setIdProdutividadePessoaDB2(Long idProdutividadePessoaDB2) {
		this.idProdutividadePessoaDB2 = idProdutividadePessoaDB2;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return getIdProdutividadePessoaDB2();
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
	 * Recupera data inativacao.
	 * 
	 * @return data inativacao
	 */
	public Date getDataInativacao() {
		return dataInativacao;
	}

	/**
	 * Preenche data inativacao.
	 * 
	 * @param dataInativacao
	 *            o novo data inativacao
	 */
	public void setDataInativacao(Date dataInativacao) {
		this.dataInativacao = dataInativacao;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Long){
			setIdProdutividadePessoaDB2((Long)idDB2);
		}
	}
}
