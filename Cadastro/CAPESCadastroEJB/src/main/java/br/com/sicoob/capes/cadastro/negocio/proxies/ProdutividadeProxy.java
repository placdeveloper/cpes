/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.proxies;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * @author erico.junior
 *
 */
public class ProdutividadeProxy implements Serializable {

	/** Serial UID.*/
	private static final long serialVersionUID = 1756040826165029040L;
	
	/** O atributo idProdutividade. */
	private Long idProdutividade;
	
	/** O atributo anoInicioSafra. */
	private Short anoInicioSafra;
	
	/** O atributo anoFimSafra. */
	private Short anoFimSafra;
	
	/** O atributo denominacaoImovel. */
	private String denominacaoImovel;
	
	/** O atributo descEmpreendimento. */
	private String descEmpreendimento;
	
	/** O atributo quantidadeOuArea. */
	private BigDecimal quantidadeOuArea;
	
	/** O atributo producao. */
	private BigDecimal producao;
	
	/** O atributo valorPrecoMedio. */
	private BigDecimal valorPrecoMedio;
	
	/** O atributo valorRendaBruta. */
	private BigDecimal valorRendaBruta;
	
	/** O atributo codSituacao. */
	private Short codSituacao;
	
	/** O atributo descSituacao. */
	private String descSituacao;
	
	/** O atributo idInstituicaoAtualizacao. */
	private Integer idInstituicaoAtualizacao;
	
	/** O atributo dataHoraInicio. */
	private DateTimeDB dataHoraInicio;
	
	/** O atributo idRegistroControlado. */
	private String idRegistroControlado;
	
	/** O atributo codigoSituacaoAprovacao. */
	private Short codigoSituacaoAprovacao;
	
	/** O atributo situacaoAprovacao. */
	private SituacaoRegistroEnum situacaoAprovacao;

	/**
	 * Recupera o valor de anoInicioSafra.
	 *
	 * @return o valor de anoInicioSafra
	 */
	public Short getAnoInicioSafra() {
		return anoInicioSafra;
	}
	
	/**
	 * Define o valor de anoInicioSafra.
	 *
	 * @param anoInicioSafra o novo valor de anoInicioSafra
	 */
	public void setAnoInicioSafra(Short anoInicioSafra) {
		this.anoInicioSafra = anoInicioSafra;
	}
	
	/**
	 * Recupera o valor de anoFimSafra.
	 *
	 * @return o valor de anoFimSafra
	 */
	public Short getAnoFimSafra() {
		return anoFimSafra;
	}
	
	/**
	 * Define o valor de anoFimSafra.
	 *
	 * @param anoFimSafra o novo valor de anoFimSafra
	 */
	public void setAnoFimSafra(Short anoFimSafra) {
		this.anoFimSafra = anoFimSafra;
	}
	
	/**
	 * Recupera o valor de denominacaoImovel.
	 *
	 * @return o valor de denominacaoImovel
	 */
	public String getDenominacaoImovel() {
		return denominacaoImovel;
	}
	
	/**
	 * Define o valor de denominacaoImovel.
	 *
	 * @param denominacaoImovel o novo valor de denominacaoImovel
	 */
	public void setDenominacaoImovel(String denominacaoImovel) {
		this.denominacaoImovel = denominacaoImovel;
	}
	
	/**
	 * Recupera o valor de descEmpreendimento.
	 *
	 * @return o valor de descEmpreendimento
	 */
	public String getDescEmpreendimento() {
		return descEmpreendimento;
	}
	
	/**
	 * Define o valor de descEmpreendimento.
	 *
	 * @param descEmpreendimento o novo valor de descEmpreendimento
	 */
	public void setDescEmpreendimento(String descEmpreendimento) {
		this.descEmpreendimento = descEmpreendimento;
	}
	
	/**
	 * Recupera o valor de quantidadeOuArea.
	 *
	 * @return o valor de quantidadeOuArea
	 */
	public BigDecimal getQuantidadeOuArea() {
		return quantidadeOuArea;
	}
	
	/**
	 * Define o valor de quantidadeOuArea.
	 *
	 * @param quantidadeOuArea o novo valor de quantidadeOuArea
	 */
	public void setQuantidadeOuArea(BigDecimal quantidadeOuArea) {
		this.quantidadeOuArea = quantidadeOuArea;
	}
	
	/**
	 * Recupera o valor de producao.
	 *
	 * @return o valor de producao
	 */
	public BigDecimal getProducao() {
		return producao;
	}
	
	/**
	 * Define o valor de producao.
	 *
	 * @param producao o novo valor de producao
	 */
	public void setProducao(BigDecimal producao) {
		this.producao = producao;
	}
	
	/**
	 * Recupera o valor de valorPrecoMedio.
	 *
	 * @return o valor de valorPrecoMedio
	 */
	public BigDecimal getValorPrecoMedio() {
		return valorPrecoMedio;
	}
	
	/**
	 * Define o valor de valorPrecoMedio.
	 *
	 * @param valorPrecoMedio o novo valor de valorPrecoMedio
	 */
	public void setValorPrecoMedio(BigDecimal valorPrecoMedio) {
		this.valorPrecoMedio = valorPrecoMedio;
	}
	
	/**
	 * Recupera o valor de valorRendaBruta.
	 *
	 * @return o valor de valorRendaBruta
	 */
	public BigDecimal getValorRendaBruta() {
		return valorRendaBruta;
	}
	
	/**
	 * Define o valor de valorRendaBruta.
	 *
	 * @param valorRendaBruta o novo valor de valorRendaBruta
	 */
	public void setValorRendaBruta(BigDecimal valorRendaBruta) {
		this.valorRendaBruta = valorRendaBruta;
	}
	
	/**
	 * Recupera o valor de descSituacao.
	 *
	 * @return o valor de descSituacao
	 */
	public String getDescSituacao() {
		return descSituacao;
	}
	
	/**
	 * Define o valor de descSituacao.
	 *
	 * @param descSituacao o novo valor de descSituacao
	 */
	public void setDescSituacao(String descSituacao) {
		this.descSituacao = descSituacao;
	}
	
	/**
	 * Recupera o valor de idProdutividade.
	 *
	 * @return o valor de idProdutividade
	 */
	public Long getIdProdutividade() {
		return idProdutividade;
	}
	
	/**
	 * Define o valor de idProdutividade.
	 *
	 * @param idProdutividade o novo valor de idProdutividade
	 */
	public void setIdProdutividade(Long idProdutividade) {
		this.idProdutividade = idProdutividade;
	}
	
	/**
	 * Recupera o valor de codSituacao.
	 *
	 * @return o valor de codSituacao
	 */
	public Short getCodSituacao() {
		return codSituacao;
	}
	
	/**
	 * Define o valor de codSituacao.
	 *
	 * @param codSituacao o novo valor de codSituacao
	 */
	public void setCodSituacao(Short codSituacao) {
		this.codSituacao = codSituacao;
	}
	
	/**
	 * Recupera o valor de idInstituicaoAtualizacao.
	 *
	 * @return o valor de idInstituicaoAtualizacao
	 */
	public Integer getIdInstituicaoAtualizacao() {
		return idInstituicaoAtualizacao;
	}
	
	/**
	 * Define o valor de idInstituicaoAtualizacao.
	 *
	 * @param idInstituicaoAtualizacao o novo valor de idInstituicaoAtualizacao
	 */
	public void setIdInstituicaoAtualizacao(Integer idInstituicaoAtualizacao) {
		this.idInstituicaoAtualizacao = idInstituicaoAtualizacao;
	}
	
	/**
	 * Recupera o valor de dataHoraInicio.
	 *
	 * @return o valor de dataHoraInicio
	 */
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}
	
	/**
	 * Define o valor de dataHoraInicio.
	 *
	 * @param dataHoraInicio o novo valor de dataHoraInicio
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}
	/**
	 * Retornar o IdRegistroControlado como uma entidade, para uso do
	 * componente de visualização GED/GFT
	 * @return idRegistroControlado
	 */
	public String getIdRegistroControlado() {
		if(idRegistroControlado != null && !"".equals(idRegistroControlado)){
			return idRegistroControlado;
		}
		return TipoAutorizacaoEntidadeEnum.PRODUTIVIDADE.name() + Aprovavel.SEPARADOR_ID_REGISTRO_CONTROLADO + getIdProdutividade();
	}
	
	/**
	 * Define o valor de idRegistroControlado.
	 *
	 * @param idRegistroControlado o novo valor de idRegistroControlado
	 */
	public void setIdRegistroControlado(String idRegistroControlado) {
		this.idRegistroControlado = idRegistroControlado;
	}
	
	/**
	 * Recupera o valor de codigoSituacaoAprovacao.
	 *
	 * @return o valor de codigoSituacaoAprovacao
	 */
	public Short getCodigoSituacaoAprovacao() {
		return codigoSituacaoAprovacao;
	}
	
	/**
	 * Define o valor de codigoSituacaoAprovacao.
	 *
	 * @param codigoSituacaoAprovacao o novo valor de codigoSituacaoAprovacao
	 */
	public void setCodigoSituacaoAprovacao(Short codigoSituacaoAprovacao) {
		this.codigoSituacaoAprovacao = codigoSituacaoAprovacao;
	}
	
	/**
	 * Recupera o valor de situacaoAprovacao.
	 *
	 * @return o valor de situacaoAprovacao
	 */
	public SituacaoRegistroEnum getSituacaoAprovacao() {
		if ((situacaoAprovacao == null) && (codigoSituacaoAprovacao != null)) {
			situacaoAprovacao = SituacaoRegistroEnum.valueOf(codigoSituacaoAprovacao);
		}
		return situacaoAprovacao;
	}
	
	/**
	 * Define o valor de situacaoAprovacao.
	 *
	 * @param situacaoAprovacao o novo valor de situacaoAprovacao
	 */
	public void setSituacaoAprovacao(SituacaoRegistroEnum situacaoAprovacao) {
		this.situacaoAprovacao = situacaoAprovacao;
	}
}