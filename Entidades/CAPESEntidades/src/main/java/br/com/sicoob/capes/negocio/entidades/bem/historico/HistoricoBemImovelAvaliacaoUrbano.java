package br.com.sicoob.capes.negocio.entidades.bem.historico;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.bem.TipoEstadoConservacaoBem;
import br.com.sicoob.capes.negocio.entidades.bem.TipoImplantacaoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoPadraoAcabamentoBemImovel;
import br.com.sicoob.capes.negocio.entidades.bem.TipoServicoCondominialBemImovel;

/**
 * A classe que representa a entidade Histórico bem imóvel avaliação urbano.
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "HISTBEMIMOVELAVALIACAOURBANO")
public class HistoricoBemImovelAvaliacaoUrbano extends HistoricoBemImovelAvaliacao {
	private static final long serialVersionUID = -5503610364714595277L;

	@ManyToOne
	@JoinColumn(name = "CODTIPOIMPLANTACAOBEMIMOVEL", referencedColumnName = "CODTIPOIMPLANTACAOBEMIMOVEL")
	private TipoImplantacaoBemImovel tipoImplantacaoBemImovel;

	@ManyToOne
	@JoinColumn(name = "CODTIPOESTADOCONSERVACAOBEM", referencedColumnName = "CODTIPOESTADOCONSERVACAOBEM")
	private TipoEstadoConservacaoBem tipoEstadoConservacao;

	@ManyToOne
	@JoinColumn(name = "CODTIPOPADRAOACABAMENTOBEMIMOVEL", referencedColumnName = "CODTIPOPADRAOACABAMENTOBEMIMOVEL")
	private TipoPadraoAcabamentoBemImovel tipoPadraoAcabamentoBemImovel;

	@ManyToOne
	@JoinColumn(name = "CODTIPOSERVICOCONDOMINIALBEMIMOVEL", referencedColumnName = "CODTIPOSERVICOCONDOMINIALBEMIMOVEL")
	private TipoServicoCondominialBemImovel tipoServicoCondominialBemImovel;

	@Column(name = "AREAPRIVATIVA", precision = 13, scale = 4)
	private BigDecimal areaPrivativa;

	@Column(name = "AREATERRENO", precision = 13, scale = 4)
	private BigDecimal areaTerreno;

	@Column(name = "AREATESTADA", precision = 13, scale = 4)
	private BigDecimal areaTestada;

	@Column(name = "QTDDORMITORIO")
	private Short quantidadeDormitorios;

	@Column(name = "QTDVAGAGARAGEM")
	private Short quantidadeVagasGaragem;

	public TipoImplantacaoBemImovel getTipoImplantacaoBemImovel() {
		return tipoImplantacaoBemImovel;
	}

	public void setTipoImplantacaoBemImovel(TipoImplantacaoBemImovel tipoImplantacaoBemImovel) {
		this.tipoImplantacaoBemImovel = tipoImplantacaoBemImovel;
	}

	public TipoEstadoConservacaoBem getTipoEstadoConservacao() {
		return tipoEstadoConservacao;
	}

	public void setTipoEstadoConservacao(TipoEstadoConservacaoBem tipoEstadoConservacao) {
		this.tipoEstadoConservacao = tipoEstadoConservacao;
	}

	public TipoPadraoAcabamentoBemImovel getTipoPadraoAcabamentoBemImovel() {
		return tipoPadraoAcabamentoBemImovel;
	}

	public void setTipoPadraoAcabamentoBemImovel(TipoPadraoAcabamentoBemImovel tipoPadraoAcabamentoBemImovel) {
		this.tipoPadraoAcabamentoBemImovel = tipoPadraoAcabamentoBemImovel;
	}

	public TipoServicoCondominialBemImovel getTipoServicoCondominialBemImovel() {
		return tipoServicoCondominialBemImovel;
	}

	public void setTipoServicoCondominialBemImovel(TipoServicoCondominialBemImovel tipoServicoCondominialBemImovel) {
		this.tipoServicoCondominialBemImovel = tipoServicoCondominialBemImovel;
	}

	public BigDecimal getAreaPrivativa() {
		return areaPrivativa;
	}

	public void setAreaPrivativa(BigDecimal areaPrivativa) {
		this.areaPrivativa = areaPrivativa;
	}

	public BigDecimal getAreaTerreno() {
		return areaTerreno;
	}

	public void setAreaTerreno(BigDecimal areaTerreno) {
		this.areaTerreno = areaTerreno;
	}

	public BigDecimal getAreaTestada() {
		return areaTestada;
	}

	public void setAreaTestada(BigDecimal areaTestada) {
		this.areaTestada = areaTestada;
	}

	public Short getQuantidadeDormitorios() {
		return quantidadeDormitorios;
	}

	public void setQuantidadeDormitorios(Short quantidadeDormitorios) {
		this.quantidadeDormitorios = quantidadeDormitorios;
	}

	public Short getQuantidadeVagasGaragem() {
		return quantidadeVagasGaragem;
	}

	public void setQuantidadeVagasGaragem(Short quantidadeVagasGaragem) {
		this.quantidadeVagasGaragem = quantidadeVagasGaragem;
	}

}