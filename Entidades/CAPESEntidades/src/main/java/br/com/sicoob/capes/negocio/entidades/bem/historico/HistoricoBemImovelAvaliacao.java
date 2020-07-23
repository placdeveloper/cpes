package br.com.sicoob.capes.negocio.entidades.bem.historico;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.bem.TipoOnusBem;

/**
 * A classe que representa a entidade Histórico bem imóvel avaliação.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "HISTBEMIMOVELAVALIACAO")
public class HistoricoBemImovelAvaliacao extends HistoricoBemImovel {
	private static final long serialVersionUID = 4312920096125942459L;

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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(schema = "CLI", name = "HISTTIPOONUSBEMIMOVELAVALIACAO", 
			joinColumns = { @JoinColumn(name = "IDHISTBEM") }, 
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