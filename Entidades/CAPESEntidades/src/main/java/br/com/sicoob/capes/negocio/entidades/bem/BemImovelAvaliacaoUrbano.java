package br.com.sicoob.capes.negocio.entidades.bem;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao;
import br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao;

/**
 * A classe que representa a entidade Bem imóvel avaliação urbano
 * 
 * @author Bruno.Carneiro
 */
@Entity
@Table(schema = "CLI", name = "BEMIMOVELAVALIACAOURBANO")
@PrimaryKeyJoinColumn(referencedColumnName = "IDBEM")
@CamposAutorizacao(id = "idBem", camposExibicao = {
		@CampoAutorizacao(propriedade = "tipoImplantacaoBemImovel.descricao", label = "TIPO IMPLANTAÇÃO", ordenacao = 32),
		@CampoAutorizacao(propriedade = "tipoEstadoConservacao.descricao", label = "ESTADO CONSERVAÇÃO", ordenacao = 33),
		@CampoAutorizacao(propriedade = "tipoPadraoAcabamentoBemImovel.descricao", label = "PADRÃO ACABAMENTO", ordenacao = 34),
		@CampoAutorizacao(propriedade = "tipoServicoCondominialBemImovel.descricao", label = "SERVIÇO CONDOMINIAL", ordenacao = 35),
		@CampoAutorizacao(propriedade = "areaPrivativa", label = "ÁREA PRIVATIVA", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorDecimal", ordenacao = 36),
		@CampoAutorizacao(propriedade = "areaTerreno", label = "ÁREA TERRENO", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorDecimal", ordenacao = 37),
		@CampoAutorizacao(propriedade = "areaTestada", label = "ÁREA TESTADA", formatador = "br.com.sicoob.capes.comum.util.formatacao.FormatadorDecimal", ordenacao = 38),
		@CampoAutorizacao(propriedade = "quantidadeDormitorios", label = "QUANTIDADE DE DORMITÓRIOS", ordenacao = 39),
		@CampoAutorizacao(propriedade = "quantidadeVagasGaragem", label = "QUANTIDADE DE VAGAS GARAGEM", ordenacao = 40)
})
public class BemImovelAvaliacaoUrbano extends BemImovelAvaliacao {
	private static final long serialVersionUID = -5958032834958736189L;

	@JoinColumn(name = "CODTIPOIMPLANTACAOBEMIMOVEL", referencedColumnName = "CODTIPOIMPLANTACAOBEMIMOVEL")
	@ManyToOne
	private TipoImplantacaoBemImovel tipoImplantacaoBemImovel;

	@JoinColumn(name = "CODTIPOESTADOCONSERVACAOBEM", referencedColumnName = "CODTIPOESTADOCONSERVACAOBEM")
	@ManyToOne
	private TipoEstadoConservacaoBem tipoEstadoConservacao;

	@JoinColumn(name = "CODTIPOPADRAOACABAMENTOBEMIMOVEL", referencedColumnName = "CODTIPOPADRAOACABAMENTOBEMIMOVEL")
	@ManyToOne
	private TipoPadraoAcabamentoBemImovel tipoPadraoAcabamentoBemImovel;

	@JoinColumn(name = "CODTIPOSERVICOCONDOMINIALBEMIMOVEL", referencedColumnName = "CODTIPOSERVICOCONDOMINIALBEMIMOVEL")
	@ManyToOne
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