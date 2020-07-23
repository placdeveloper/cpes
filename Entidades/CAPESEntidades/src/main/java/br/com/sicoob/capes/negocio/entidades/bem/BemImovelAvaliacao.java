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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;

/**
 * A classe que representa a entidade Bem imóvel avaliação
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "BEMIMOVELAVALIACAO")
@PrimaryKeyJoinColumn(referencedColumnName = "IDBEM")
@CamposAutorizacao(id = "idBem", camposExibicao = {
		@CampoAutorizacao(propriedade = "valorCompraVenda", label = "VALOR DE COMPRA E VENDA", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorMonetario", ordenacao = 27),
		@CampoAutorizacao(propriedade = "dataCompraVenda", label = "DATA DE COMPRA E VENDA", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy", ordenacao = 28),
		@CampoAutorizacao(propriedade = "valorAvaliacao", label = "VALOR DA AVALIAÇÃO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorMonetario", ordenacao = 29),
		@CampoAutorizacao(propriedade = "dataAvaliacao", label = "DATA DA AVALIAÇÃO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorData", mascara = "dd/MM/yyyy", ordenacao = 30),
//		@CampoAutorizacao(propriedade = "pessoaCompartilhamentoAvaliador.nomePessoa", label = "NOME AVALIADOR", ordenacao = 25),
		@CampoAutorizacao(propriedade = "processoAquisicao", label = "EM PROCESSO DE AQUISIÇÃO", formatador="br.com.sicoob.capes.comum.util.formatacao.FormatadorBooleano", ordenacao = 31),
})
public class BemImovelAvaliacao extends BemImovel {
	private static final long serialVersionUID = 7679954682229103062L;

	@Column(name = "VALORCOMPRAVENDA", precision = 19, scale = 2)
	private BigDecimal valorCompraVenda;

	@Column(name = "VALORAVALIACAO", precision = 19, scale = 2)
	private BigDecimal valorAvaliacao;

	@Column(name = "DATACOMPRAVENDA")
	private Date dataCompraVenda;

	@Column(name = "DATAAVALIACAO")
	private Date dataAvaliacao;

	@Column(name = "BOLEMPROCESSOAQUISICAO")
	private Boolean processoAquisicao = Boolean.FALSE;

	@Column(name = "IDPESSOACOMPARTILHAMENTOAVALIADOR")
	private Long idPessoaCompartilhamentoAvaliador;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(schema = "CLI", name = "TIPOONUSBEMIMOVELAVALIACAO", 
			joinColumns = { @JoinColumn(name = "IDBEM") }, 
			inverseJoinColumns = { @JoinColumn(name = "CODTIPOONUSBEM") })
	private Set<TipoOnusBem> tiposOnus;

	public BigDecimal getValorCompraVenda() {
		return valorCompraVenda;
	}

	public void setValorCompraVenda(BigDecimal valorCompraVenda) {
		this.valorCompraVenda = valorCompraVenda;
	}

	public BigDecimal getValorAvaliacao() {
		return valorAvaliacao;
	}

	public void setValorAvaliacao(BigDecimal valorAvaliacao) {
		this.valorAvaliacao = valorAvaliacao;
	}

	public Date getDataCompraVenda() {
		return dataCompraVenda;
	}

	public void setDataCompraVenda(Date dataCompraVenda) {
		this.dataCompraVenda = dataCompraVenda;
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

	public Set<TipoOnusBem> getTiposOnus() {
		return tiposOnus;
	}

	public void setTiposOnus(Set<TipoOnusBem> tiposOnus) {
		this.tiposOnus = tiposOnus;
	}

}