/*
 * SICOOB
 * 
 * ProdutividadePessoaVO.java(br.com.sicoob.capes.api.negocio.vo.ProdutividadePessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Lucas.Borges
 */
public class ProdutividadePessoaVO extends AbstractPessoaVO{
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 3260242155127041743L;

	// PRODUTIVIDADE
	/** O atributo id produtividade. */
	private Long idProdutividade;
	
	/** O atributo descricao. */
	private String descricao;	
	
	/** O atributo producao. */
	private BigDecimal producao;
	
	/** O atributo quantidade ou area. */
	private BigDecimal quantidadeOuArea;

	/** O atributo percentual rebate. */
	private BigDecimal percentualRebate;
	
	/** O atributo valor renda bruta. */
	private BigDecimal valorRendaBruta;

	/** O atributo ano inicio safra. */
	private Short anoInicioSafra;

	/** O atributo ano fim safra. */
	private Short anoFimSafra;
	
	/** O atributo valor preco medio. */
	private BigDecimal valorPrecoMedio;
	
	/** O atributo id bem imovel. */
	private Long idBemImovel;	
	
	/** O atributo id bem imovel. */
	private Long idBemAntigo;
	
	/** O atributo situacao. */
	private Short situacao;
	
	/** O atributo data hora inicio. */
	private Date dataHoraInicio;

	// EMPREENDIMENTO
	/** O atributo id empreendimento. */
	private Integer idEmpreendimento;
	
	/** O atributo descricao empreendimento. */
	private String descricaoEmpreendimento;
	
	/** O atributo cod finalidade empreendimento. */
	private Short codFinalidadeEmpreendimento;

	/** O atributo cod unidade medida area empreendimento. */
	private Short codUnidadeMedidaAreaEmpreendimento;
	
	/** O atributo descricao unidade medida area empreendimento. */
	private String descricaoUnidadeMedidaAreaEmpreendimento;
	
	/** O atributo sigla unidade medida area empreendimento. */
	private String siglaUnidadeMedidaAreaEmpreendimento;

	/** O atributo cod unidade medida previsao empreendimento. */
	private Short codUnidadeMedidaPrevisaoEmpreendimento;
	
	/** O atributo descricao unidade medida previsao empreendimento. */
	private String descricaoUnidadeMedidaPrevisaoEmpreendimento;
	
	/** O atributo sigla unidade medida previsao empreendimento. */
	private String siglaUnidadeMedidaPrevisaoEmpreendimento;
	
	// CATEGORIA PRODUTOR
	/** O atributo cod categoria produtor. */
	private Short codCategoriaProdutor;
	
	/** O atributo desc categoria produtor. */
	private String descCategoriaProdutor;
	
	// PRODUTOR
	/** O atributo cod inscricao produtor rural. */
	private String codInscricaoProdutorRural;
	
	/** O atributo cod empreendimento bacen */
	private String codigoBacen;
	
	/**
	 * Cria uma nova instância de produtividade pessoa vo.
	 */
	public ProdutividadePessoaVO() {

	}
	 
	/**
	 * Cria uma nova instância de produtividade pessoa vo.
	 * 
	 * @param idProdutividade
	 *            the id produtividade
	 * @param descricao
	 *            the descricao
	 * @param producao
	 *            the producao
	 * @param quantidadeOuArea
	 *            the quantidade ou area
	 * @param percentualRebate
	 *            the percentual rebate
	 * @param valorRendaBruta
	 *            the valor renda bruta
	 * @param anoInicioSafra
	 *            the ano inicio safra
	 * @param anoFimSafra
	 *            the ano fim safra
	 * @param valorPrecoMedio
	 *            the valor preco medio
	 * @param idBemImovel
	 *            the id bem imovel
	 * @param situacao
	 *            the situacao
	 * @param dataHoraInicio
	 *            the data hora inicio
	 * @param idEmpreendimento
	 *            the id empreendimento
	 * @param descricaoEmpreendimento
	 *            the descricao empreendimento
	 * @param codFinalidadeEmpreendimento
	 *            the cod finalidade empreendimento
	 * @param codUnidadeMedidaAreaEmpreendimento
	 *            the cod unidade medida area empreendimento
	 * @param descricaoUnidadeMedidaAreaEmpreendimento
	 *            the descricao unidade medida area empreendimento
	 * @param siglaUnidadeMedidaAreaEmpreendimento
	 *            the sigla unidade medida area empreendimento
	 * @param codUnidadeMedidaPrevisaoEmpreendimento
	 *            the cod unidade medida previsao empreendimento
	 * @param descricaoUnidadeMedidaPrevisaoEmpreendimento
	 *            the descricao unidade medida previsao empreendimento
	 * @param siglaUnidadeMedidaPrevisaoEmpreendimento
	 *            the sigla unidade medida previsao empreendimento
	 * @param codCategoriaProdutor
	 *            the cod categoria produtor
	 * @param descCategoriaProdutor
	 *            the desc categoria produtor
	 * @param codInscricaoProdutorRural
	 *            the cod inscricao produtor rural
	 */
	public ProdutividadePessoaVO(Long idProdutividade, String descricao,
			BigDecimal producao, BigDecimal quantidadeOuArea,
			BigDecimal percentualRebate, BigDecimal valorRendaBruta,
			Short anoInicioSafra, Short anoFimSafra,
			BigDecimal valorPrecoMedio, Long idBemImovel, Long idBemAntigo, 
			Short situacao, Date dataHoraInicio, Integer idEmpreendimento,
			String descricaoEmpreendimento, Short codFinalidadeEmpreendimento,
			Short codUnidadeMedidaAreaEmpreendimento,
			String descricaoUnidadeMedidaAreaEmpreendimento,
			String siglaUnidadeMedidaAreaEmpreendimento,
			Short codUnidadeMedidaPrevisaoEmpreendimento,
			String descricaoUnidadeMedidaPrevisaoEmpreendimento,
			String siglaUnidadeMedidaPrevisaoEmpreendimento,
			Short codCategoriaProdutor, String descCategoriaProdutor,
			String codInscricaoProdutorRural,
			String codigoBacen) {
		this.idProdutividade = idProdutividade;
		this.descricao = descricao;
		this.producao = producao;
		this.quantidadeOuArea = quantidadeOuArea;
		this.percentualRebate = percentualRebate;
		this.valorRendaBruta = valorRendaBruta;
		this.anoInicioSafra = anoInicioSafra;
		this.anoFimSafra = anoFimSafra;
		this.valorPrecoMedio = valorPrecoMedio;
		this.idBemImovel = idBemImovel;
		this.idBemAntigo = idBemAntigo;
		this.situacao = situacao;
		this.dataHoraInicio = dataHoraInicio;
		this.idEmpreendimento = idEmpreendimento;
		this.descricaoEmpreendimento = descricaoEmpreendimento;
		this.codFinalidadeEmpreendimento = codFinalidadeEmpreendimento;
		this.codUnidadeMedidaAreaEmpreendimento = codUnidadeMedidaAreaEmpreendimento;
		this.descricaoUnidadeMedidaAreaEmpreendimento = descricaoUnidadeMedidaAreaEmpreendimento;
		this.siglaUnidadeMedidaAreaEmpreendimento = siglaUnidadeMedidaAreaEmpreendimento;
		this.codUnidadeMedidaPrevisaoEmpreendimento = codUnidadeMedidaPrevisaoEmpreendimento;
		this.descricaoUnidadeMedidaPrevisaoEmpreendimento = descricaoUnidadeMedidaPrevisaoEmpreendimento;
		this.siglaUnidadeMedidaPrevisaoEmpreendimento = siglaUnidadeMedidaPrevisaoEmpreendimento;
		this.codCategoriaProdutor = codCategoriaProdutor;
		this.descCategoriaProdutor = descCategoriaProdutor;
		this.codInscricaoProdutorRural = codInscricaoProdutorRural;
		this.codigoBacen = codigoBacen;
	}
	
	/**
	 * Cria uma nova instância de produtividade pessoa vo.
	 * 
	 * @param idProdutividade
	 *            the id produtividade
	 * @param descricao
	 *            the descricao
	 * @param producao
	 *            the producao
	 * @param quantidadeOuArea
	 *            the quantidade ou area
	 * @param percentualRebate
	 *            the percentual rebate
	 * @param valorRendaBruta
	 *            the valor renda bruta
	 * @param anoInicioSafra
	 *            the ano inicio safra
	 * @param anoFimSafra
	 *            the ano fim safra
	 * @param valorPrecoMedio
	 *            the valor preco medio
	 * @param idBemImovel
	 *            the id bem imovel
	 * @param situacao
	 *            the situacao
	 * @param dataHoraInicio
	 *            the data hora inicio
	 * @param idEmpreendimento
	 *            the id empreendimento
	 * @param descricaoEmpreendimento
	 *            the descricao empreendimento
	 * @param codFinalidadeEmpreendimento
	 *            the cod finalidade empreendimento
	 * @param codUnidadeMedidaAreaEmpreendimento
	 *            the cod unidade medida area empreendimento
	 * @param descricaoUnidadeMedidaAreaEmpreendimento
	 *            the descricao unidade medida area empreendimento
	 * @param siglaUnidadeMedidaAreaEmpreendimento
	 *            the sigla unidade medida area empreendimento
	 * @param codUnidadeMedidaPrevisaoEmpreendimento
	 *            the cod unidade medida previsao empreendimento
	 * @param descricaoUnidadeMedidaPrevisaoEmpreendimento
	 *            the descricao unidade medida previsao empreendimento
	 * @param siglaUnidadeMedidaPrevisaoEmpreendimento
	 *            the sigla unidade medida previsao empreendimento
	 * @param codCategoriaProdutor
	 *            the cod categoria produtor
	 * @param descCategoriaProdutor
	 *            the desc categoria produtor
	 * @param codInscricaoProdutorRural
	 *            the cod inscricao produtor rural
	 */
	public ProdutividadePessoaVO(Long idProdutividade, String descricao,
			BigDecimal producao, BigDecimal quantidadeOuArea,
			BigDecimal percentualRebate, BigDecimal valorRendaBruta,
			Short anoInicioSafra, Short anoFimSafra,
			BigDecimal valorPrecoMedio, Long idBemImovel, Short situacao, 
			Date dataHoraInicio, Integer idEmpreendimento,
			String descricaoEmpreendimento, Short codFinalidadeEmpreendimento,
			Short codUnidadeMedidaAreaEmpreendimento,
			String descricaoUnidadeMedidaAreaEmpreendimento,
			String siglaUnidadeMedidaAreaEmpreendimento,
			Short codUnidadeMedidaPrevisaoEmpreendimento,
			String descricaoUnidadeMedidaPrevisaoEmpreendimento,
			String siglaUnidadeMedidaPrevisaoEmpreendimento,
			Short codCategoriaProdutor, String descCategoriaProdutor,
			String codInscricaoProdutorRural, String codigoBacen, Long idBemAntigo) {
		this(idProdutividade, descricao,
				producao, quantidadeOuArea,
				percentualRebate, valorRendaBruta,
				anoInicioSafra, anoFimSafra,
				valorPrecoMedio, idBemImovel, idBemAntigo, 
				situacao, dataHoraInicio, idEmpreendimento,
				descricaoEmpreendimento, codFinalidadeEmpreendimento,
				codUnidadeMedidaAreaEmpreendimento,
				descricaoUnidadeMedidaAreaEmpreendimento,
				siglaUnidadeMedidaAreaEmpreendimento,
				codUnidadeMedidaPrevisaoEmpreendimento,
				descricaoUnidadeMedidaPrevisaoEmpreendimento,
				siglaUnidadeMedidaPrevisaoEmpreendimento,
				codCategoriaProdutor, descCategoriaProdutor,
				codInscricaoProdutorRural, codigoBacen);
		this.idBemAntigo = idBemAntigo;
	}

	/**
	 * Recupera id produtividade.
	 * 
	 * @return id produtividade
	 */
	public Long getIdProdutividade() {
		return idProdutividade;
	}

	/**
	 * Preenche id produtividade.
	 * 
	 * @param idProdutividade
	 *            o novo id produtividade
	 */
	public void setIdProdutividade(Long idProdutividade) {
		this.idProdutividade = idProdutividade;
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
	 * Recupera id bem imovel.
	 * 
	 * @return id bem imovel
	 */
	public Long getIdBemImovel() {
		return idBemImovel;
	}

	/**
	 * Preenche id bem imovel.
	 * 
	 * @param idBemImovel
	 *            o novo id bem imovel
	 */
	public void setIdBemImovel(Long idBemImovel) {
		this.idBemImovel = idBemImovel;
	}
	
	public Long getIdBemAntigo() {
		return idBemAntigo;
	}

	public void setIdBemAntigo(Long idBemAntigo) {
		this.idBemAntigo = idBemAntigo;
	}

	/**
	 * Recupera id empreendimento.
	 * 
	 * @return id empreendimento
	 */
	public Integer getIdEmpreendimento() {
		return idEmpreendimento;
	}

	/**
	 * Preenche id empreendimento.
	 * 
	 * @param idEmpreendimento
	 *            o novo id empreendimento
	 */
	public void setIdEmpreendimento(Integer idEmpreendimento) {
		this.idEmpreendimento = idEmpreendimento;
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
	 * Recupera descricao empreendimento.
	 * 
	 * @return descricao empreendimento
	 */
	public String getDescricaoEmpreendimento() {
		return descricaoEmpreendimento;
	}

	/**
	 * Preenche descricao empreendimento.
	 * 
	 * @param descricaoEmpreendimento
	 *            o novo descricao empreendimento
	 */
	public void setDescricaoEmpreendimento(String descricaoEmpreendimento) {
		this.descricaoEmpreendimento = descricaoEmpreendimento;
	}

	/**
	 * Recupera cod finalidade empreendimento.
	 * 
	 * @return cod finalidade empreendimento
	 */
	public Short getCodFinalidadeEmpreendimento() {
		return codFinalidadeEmpreendimento;
	}

	/**
	 * Preenche cod finalidade empreendimento.
	 * 
	 * @param codFinalidadeEmpreendimento
	 *            o novo cod finalidade empreendimento
	 */
	public void setCodFinalidadeEmpreendimento(Short codFinalidadeEmpreendimento) {
		this.codFinalidadeEmpreendimento = codFinalidadeEmpreendimento;
	}

	/**
	 * Recupera cod unidade medida area empreendimento.
	 * 
	 * @return cod unidade medida area empreendimento
	 */
	public Short getCodUnidadeMedidaAreaEmpreendimento() {
		return codUnidadeMedidaAreaEmpreendimento;
	}

	/**
	 * Preenche cod unidade medida area empreendimento.
	 * 
	 * @param codUnidadeMedidaAreaEmpreendimento
	 *            o novo cod unidade medida area empreendimento
	 */
	public void setCodUnidadeMedidaAreaEmpreendimento(
			Short codUnidadeMedidaAreaEmpreendimento) {
		this.codUnidadeMedidaAreaEmpreendimento = codUnidadeMedidaAreaEmpreendimento;
	}

	/**
	 * Recupera descricao unidade medida area empreendimento.
	 * 
	 * @return descricao unidade medida area empreendimento
	 */
	public String getDescricaoUnidadeMedidaAreaEmpreendimento() {
		return descricaoUnidadeMedidaAreaEmpreendimento;
	}

	/**
	 * Preenche descricao unidade medida area empreendimento.
	 * 
	 * @param descricaoUnidadeMedidaAreaEmpreendimento
	 *            o novo descricao unidade medida area empreendimento
	 */
	public void setDescricaoUnidadeMedidaAreaEmpreendimento(
			String descricaoUnidadeMedidaAreaEmpreendimento) {
		this.descricaoUnidadeMedidaAreaEmpreendimento = descricaoUnidadeMedidaAreaEmpreendimento;
	}

	/**
	 * Recupera sigla unidade medida area empreendimento.
	 * 
	 * @return sigla unidade medida area empreendimento
	 */
	public String getSiglaUnidadeMedidaAreaEmpreendimento() {
		return siglaUnidadeMedidaAreaEmpreendimento;
	}

	/**
	 * Preenche sigla unidade medida area empreendimento.
	 * 
	 * @param siglaUnidadeMedidaAreaEmpreendimento
	 *            o novo sigla unidade medida area empreendimento
	 */
	public void setSiglaUnidadeMedidaAreaEmpreendimento(
			String siglaUnidadeMedidaAreaEmpreendimento) {
		this.siglaUnidadeMedidaAreaEmpreendimento = siglaUnidadeMedidaAreaEmpreendimento;
	}

	/**
	 * Recupera cod unidade medida previsao empreendimento.
	 * 
	 * @return cod unidade medida previsao empreendimento
	 */
	public Short getCodUnidadeMedidaPrevisaoEmpreendimento() {
		return codUnidadeMedidaPrevisaoEmpreendimento;
	}

	/**
	 * Preenche cod unidade medida previsao empreendimento.
	 * 
	 * @param codUnidadeMedidaPrevisaoEmpreendimento
	 *            o novo cod unidade medida previsao empreendimento
	 */
	public void setCodUnidadeMedidaPrevisaoEmpreendimento(
			Short codUnidadeMedidaPrevisaoEmpreendimento) {
		this.codUnidadeMedidaPrevisaoEmpreendimento = codUnidadeMedidaPrevisaoEmpreendimento;
	}

	/**
	 * Recupera descricao unidade medida previsao empreendimento.
	 * 
	 * @return descricao unidade medida previsao empreendimento
	 */
	public String getDescricaoUnidadeMedidaPrevisaoEmpreendimento() {
		return descricaoUnidadeMedidaPrevisaoEmpreendimento;
	}

	/**
	 * Preenche descricao unidade medida previsao empreendimento.
	 * 
	 * @param descricaoUnidadeMedidaPrevisaoEmpreendimento
	 *            o novo descricao unidade medida previsao empreendimento
	 */
	public void setDescricaoUnidadeMedidaPrevisaoEmpreendimento(
			String descricaoUnidadeMedidaPrevisaoEmpreendimento) {
		this.descricaoUnidadeMedidaPrevisaoEmpreendimento = descricaoUnidadeMedidaPrevisaoEmpreendimento;
	}

	/**
	 * Recupera sigla unidade medida previsao empreendimento.
	 * 
	 * @return sigla unidade medida previsao empreendimento
	 */
	public String getSiglaUnidadeMedidaPrevisaoEmpreendimento() {
		return siglaUnidadeMedidaPrevisaoEmpreendimento;
	}

	/**
	 * Preenche sigla unidade medida previsao empreendimento.
	 * 
	 * @param siglaUnidadeMedidaPrevisaoEmpreendimento
	 *            o novo sigla unidade medida previsao empreendimento
	 */
	public void setSiglaUnidadeMedidaPrevisaoEmpreendimento(
			String siglaUnidadeMedidaPrevisaoEmpreendimento) {
		this.siglaUnidadeMedidaPrevisaoEmpreendimento = siglaUnidadeMedidaPrevisaoEmpreendimento;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public Date getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(Date dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * Recupera cod categoria produtor.
	 * 
	 * @return cod categoria produtor
	 */
	public Short getCodCategoriaProdutor() {
		return codCategoriaProdutor;
	}

	/**
	 * Preenche cod categoria produtor.
	 * 
	 * @param codCategoriaProdutor
	 *            o novo cod categoria produtor
	 */
	public void setCodCategoriaProdutor(Short codCategoriaProdutor) {
		this.codCategoriaProdutor = codCategoriaProdutor;
	}

	/**
	 * Recupera desc categoria produtor.
	 * 
	 * @return desc categoria produtor
	 */
	public String getDescCategoriaProdutor() {
		return descCategoriaProdutor;
	}

	/**
	 * Preenche desc categoria produtor.
	 * 
	 * @param descCategoriaProdutor
	 *            o novo desc categoria produtor
	 */
	public void setDescCategoriaProdutor(String descCategoriaProdutor) {
		this.descCategoriaProdutor = descCategoriaProdutor;
	}

	/**
	 * Recupera cod inscricao produtor rural.
	 * 
	 * @return cod inscricao produtor rural
	 */
	public String getCodInscricaoProdutorRural() {
		return codInscricaoProdutorRural;
	}

	/**
	 * Preenche cod inscricao produtor rural.
	 * 
	 * @param codInscricaoProdutorRural
	 *            o novo cod inscricao produtor rural
	 */
	public void setCodInscricaoProdutorRural(String codInscricaoProdutorRural) {
		this.codInscricaoProdutorRural = codInscricaoProdutorRural;
	}

	/**
	 * Recupera cod bacen.
	 * 
	 * @return cod bacen
	 */
	public String getCodigoBacen() {
		return codigoBacen;
	}

	/**
	 * Preenche cod bacen.
	 * 
	 * @param codigoBacen
	 *            o novo cod bacen
	 */
	public void setCodigoBacen(String codigoBacen) {
		this.codigoBacen = codigoBacen;
	}

}
