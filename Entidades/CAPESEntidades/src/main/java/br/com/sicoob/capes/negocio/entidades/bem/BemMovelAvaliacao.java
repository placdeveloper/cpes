package br.com.sicoob.capes.negocio.entidades.bem;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;

/**
 * A classe que representa a entidade bem móvel avaliação
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "BEMMOVELAVALIACAO")
@PrimaryKeyJoinColumn(referencedColumnName = "IDBEM")
@CamposAutorizacao(id = "idBem", camposExibicao = { 
	@CampoAutorizacao(propriedade = "valorCompraVenda", label = "VALOR DE COMPRA E VENDA", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorMonetario", ordenacao = 18),
	@CampoAutorizacao(propriedade = "dataCompraVenda", label = "DATA DE COMPRA E VENDA", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy", ordenacao = 19),
	@CampoAutorizacao(propriedade = "valorAvaliacao", label = "VALOR DA AVALIAÇÃO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorMonetario", ordenacao = 20),
	@CampoAutorizacao(propriedade = "dataAvaliacao", label = "DATA DA AVALIAÇÃO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy", ordenacao = 21),
//	@CampoAutorizacao(propriedade = "pessoaCompartilhamentoAvaliador.nomePessoa", label = "NOME AVALIADOR", ordenacao = 22),
	@CampoAutorizacao(propriedade = "processoAquisicao", label = "EM PROCESSO DE AQUISIÇÃO", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano", ordenacao = 23),
	@CampoAutorizacao(propriedade = "tipoEstadoConservacao.descricao", label = "ESTADO DE CONSERVAÇÃO", ordenacao = 24)
})
public class BemMovelAvaliacao extends BemMovel {
	private static final long serialVersionUID = -6358350528141126395L;

	@Column(name = "VALORCOMPRAVENDA", precision = 19, scale = 2)
	private BigDecimal valorCompraVenda;

	@Column(name = "DATACOMPRAVENDA")
	private Date dataCompraVenda;

	@Column(name = "VALORAVALIACAO", precision = 19, scale = 2)
	private BigDecimal valorAvaliacao;

	@Column(name = "DATAAVALIACAO")
	private Date dataAvaliacao;

	@Column(name = "BOLEMPROCESSOAQUISICAO")
	private Boolean processoAquisicao = Boolean.FALSE;
	
	@Column(name = "IDPESSOACOMPARTILHAMENTOAVALIADOR")
	private Long idPessoaCompartilhamentoAvaliador;

	@ManyToOne
	@JoinColumn(name = "CODTIPOESTADOCONSERVACAOBEM", referencedColumnName = "CODTIPOESTADOCONSERVACAOBEM")
	private TipoEstadoConservacaoBem tipoEstadoConservacao;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(schema = "CLI", name = "TIPOONUSBEMMOVELAVALIACAO", 
			joinColumns = { @JoinColumn(name = "IDBEM") }, 
			inverseJoinColumns = { @JoinColumn(name = "CODTIPOONUSBEM") })
	private Set<TipoOnusBem> tiposOnus;

	public BigDecimal getValorCompraVenda() {
		return valorCompraVenda;
	}

	public void setValorCompraVenda(BigDecimal valorCompraVenda) {
		this.valorCompraVenda = valorCompraVenda;
	}

	public Date getDataCompraVenda() {
		return dataCompraVenda;
	}

	public void setDataCompraVenda(Date dataCompraVenda) {
		this.dataCompraVenda = dataCompraVenda;
	}

	public BigDecimal getValorAvaliacao() {
		return valorAvaliacao;
	}

	public void setValorAvaliacao(BigDecimal valorAvaliacao) {
		this.valorAvaliacao = valorAvaliacao;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public Boolean getProcessoAquisicao() {
		return processoAquisicao;
	}

	public void setProcessoAquisicao(Boolean processoAquisicao) {
		this.processoAquisicao = processoAquisicao;
	}
	
	public Long getIdPessoaCompartilhamentoAvaliador() {
		return idPessoaCompartilhamentoAvaliador;
	}

	public void setIdPessoaCompartilhamentoAvaliador(Long idPessoaCompartilhamentoAvaliador) {
		this.idPessoaCompartilhamentoAvaliador = idPessoaCompartilhamentoAvaliador;
	}

	public TipoEstadoConservacaoBem getTipoEstadoConservacao() {
		return tipoEstadoConservacao;
	}

	public void setTipoEstadoConservacao(TipoEstadoConservacaoBem tipoEstadoConservacao) {
		this.tipoEstadoConservacao = tipoEstadoConservacao;
	}

	public Set<TipoOnusBem> getTiposOnus() {
		return tiposOnus;
	}

	public void setTiposOnus(Set<TipoOnusBem> tiposOnus) {
		this.tiposOnus = tiposOnus;
	}

}