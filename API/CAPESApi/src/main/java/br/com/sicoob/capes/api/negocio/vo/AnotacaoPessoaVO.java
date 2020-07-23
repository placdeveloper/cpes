/*
 * SICOOB
 * 
 * AnotacaoPessoaVO.java(br.com.sicoob.capes.api.negocio.vo.AnotacaoPessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.vo.BancoobVo;

/**
 * @author Lucas.Borges
 */
public class AnotacaoPessoaVO extends BancoobVo{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 7627387628184100034L;

	// ANOTACAO
	/** O atributo id anotacao. */
	private Long idAnotacao;
	
	/** O atributo quantidade. */
	private Short quantidade;
	
	/** O atributo valor. */
	private BigDecimal valor;
	
	/** O atributo observacao. */
	private String observacao;
	
	/** O atributo flexibilidade. */
	private Boolean flexibilidade;
	
	// TIPO ANOTACAO
	/** O atributo codigo tipo anotacao. */
	private Integer codigoTipoAnotacao;
	
	/** O atributo descricao tipo anotacao. */
	private String descricaoTipoAnotacao;
	
	// CATEGORIA TIPO ANOTACAO
	/** O atributo codigo categoria anotacao. */
	private Short codigoCategoriaAnotacao;
	
	/** O atributo descricao categoria anotacao. */
	private String descricaoCategoriaAnotacao;
	
	// ORIGEM INFORMACAO TIPO ANOTACAO
	/** O atributo codigo origem info. */
	private Short codigoOrigemInfo;
	
	/** O atributo descricao origem info. */
	private String descricaoOrigemInfo;

	/** O atributo data hora anotacao. */
	private Date dataHoraAnotacao;
	
	/** O atributo data hora ocorrencia. */
	private Date dataHoraOcorrencia;
	
	/** O atributo data hora baixa. */
	private Date dataHoraBaixa;
	
	/** O atributo id tipo baixa. */
	private Short idTipoBaixa;
	
	/** O atributo desc tipo baixa. */
	private String descricaoTipoBaixa;
	
	/** O Atributo codigo do tipo de consulta **/
	private Integer idTipoConsultaOrigem;
	
	/** O atributo descricao do tipo de consulta **/
	private String descricaoTipoConsultaOrigem;
	
	/**
	 * Cria uma nova instância de anotacao pessoa vo.
	 */
	public AnotacaoPessoaVO() {

	}
	
	/**
	 * Cria uma nova instância de anotacao pessoa vo.
	 * 
	 * @param idAnotacao
	 *            the id anotacao
	 * @param quantidade
	 *            the quantidade
	 * @param valor
	 *            the valor
	 * @param observacao
	 *            the observacao
	 * @param flexibilidade
	 *            the flexibilidade
	 * @param codigoTipoAnotacao
	 *            the codigo tipo anotacao
	 * @param descricaoTipoAnotacao
	 *            the descricao tipo anotacao
	 * @param codigoCategoriaAnotacao
	 *            the codigo categoria anotacao
	 * @param descricaoCategoriaAnotacao
	 *            the descricao categoria anotacao
	 * @param codigoOrigemInfo
	 *            the codigo origem info
	 * @param descricaoOrigemInfo
	 *            the descricao origem info
	 */
	public AnotacaoPessoaVO(Long idAnotacao, Short quantidade, BigDecimal valor,
			String observacao, Boolean flexibilidade,
			Integer codigoTipoAnotacao, String descricaoTipoAnotacao,
			Short codigoCategoriaAnotacao, String descricaoCategoriaAnotacao,
			Short codigoOrigemInfo, String descricaoOrigemInfo,
			Date dataHoraAnotacao, Date dataHoraOcorrencia,
			Date dataHoraBaixa, Short idTipoBaixa, String descricaoTipoBaixa) {
		this.idAnotacao = idAnotacao;
		this.quantidade = quantidade;
		this.valor = valor;
		this.observacao = observacao;
		this.flexibilidade = flexibilidade;
		this.codigoTipoAnotacao = codigoTipoAnotacao;
		this.descricaoTipoAnotacao = descricaoTipoAnotacao;
		this.codigoCategoriaAnotacao = codigoCategoriaAnotacao;
		this.descricaoCategoriaAnotacao = descricaoCategoriaAnotacao;
		this.codigoOrigemInfo = codigoOrigemInfo;
		this.descricaoOrigemInfo = descricaoOrigemInfo;
		this.dataHoraAnotacao = dataHoraAnotacao;
		this.dataHoraOcorrencia = dataHoraOcorrencia;
		this.dataHoraBaixa = dataHoraBaixa;
		this.idTipoBaixa = idTipoBaixa;
		this.descricaoTipoBaixa = descricaoTipoBaixa;
		
	}
	
	/**
	 * Instancia um novo AnotacaoPessoaVO.
	 *
	 * @param idAnotacao o valor de id anotacao
	 * @param quantidade o valor de quantidade
	 * @param valor o valor de valor
	 * @param observacao o valor de observacao
	 * @param flexibilidade o valor de flexibilidade
	 * @param codigoTipoAnotacao o valor de codigo tipo anotacao
	 * @param descricaoTipoAnotacao o valor de descricao tipo anotacao
	 * @param codigoCategoriaAnotacao o valor de codigo categoria anotacao
	 * @param descricaoCategoriaAnotacao o valor de descricao categoria anotacao
	 * @param codigoOrigemInfo o valor de codigo origem info
	 * @param descricaoOrigemInfo o valor de descricao origem info
	 * @param dataHoraAnotacao o valor de data hora anotacao
	 * @param dataHoraOcorrencia o valor de data hora ocorrencia
	 * @param dataHoraBaixa o valor de data hora baixa
	 * @param idTipoBaixa o valor de id tipo baixa
	 * @param descricaoTipoBaixa o valor de descricao tipo baixa
	 * @param idTipoConsultaOrigem o valor de id tipo consulta origem
	 * @param descricaoTipoConsultaOrigem o valor de descricao tipo consulta origem
	 */
	public AnotacaoPessoaVO(Long idAnotacao, Short quantidade, BigDecimal valor,
			String observacao, Boolean flexibilidade,
			Integer codigoTipoAnotacao, String descricaoTipoAnotacao,
			Short codigoCategoriaAnotacao, String descricaoCategoriaAnotacao,
			Short codigoOrigemInfo, String descricaoOrigemInfo,
			Date dataHoraAnotacao, Date dataHoraOcorrencia,
			Date dataHoraBaixa, Short idTipoBaixa, String descricaoTipoBaixa,
			Integer idTipoConsultaOrigem, String descricaoTipoConsultaOrigem) {
		this(idAnotacao, quantidade, valor,
				observacao, flexibilidade,
				codigoTipoAnotacao, descricaoTipoAnotacao,
				codigoCategoriaAnotacao, descricaoCategoriaAnotacao,
				codigoOrigemInfo, descricaoOrigemInfo,
				dataHoraAnotacao, dataHoraOcorrencia,
				dataHoraBaixa, idTipoBaixa, descricaoTipoBaixa);
		this.idTipoConsultaOrigem = idTipoConsultaOrigem;
		this.descricaoTipoConsultaOrigem = descricaoTipoConsultaOrigem;
	}

	/**
	 * Cria uma nova instância de anotacao pessoa vo.
	 * 
	 * @param idAnotacao
	 *            the id anotacao
	 * @param quantidade
	 *            the quantidade
	 * @param valor
	 *            the valor
	 * @param observacao
	 *            the observacao
	 * @param flexibilidade
	 *            the flexibilidade
	 * @param codigoTipoAnotacao
	 *            the codigo tipo anotacao
	 * @param descricaoTipoAnotacao
	 *            the descricao tipo anotacao
	 * @param codigoCategoriaAnotacao
	 *            the codigo categoria anotacao
	 * @param descricaoCategoriaAnotacao
	 *            the descricao categoria anotacao
	 * @param codigoOrigemInfo
	 *            the codigo origem info
	 * @param descricaoOrigemInfo
	 *            the descricao origem info
	 * @param dataHoraAnotacao
	 *            the data hora anotacao
	 * @param dataHoraOcorrencia
	 *            the data hora ocorrencia
	 */
	public AnotacaoPessoaVO(Long idAnotacao, Integer codigoTipoAnotacao, Date dataHoraOcorrencia, Date dataHoraBaixa, Short quantidade, BigDecimal valor) {
		this.idAnotacao = idAnotacao;
		this.codigoTipoAnotacao = codigoTipoAnotacao;
		this.dataHoraOcorrencia = dataHoraOcorrencia;
		this.dataHoraBaixa = dataHoraBaixa;
		this.quantidade = quantidade;
		this.valor = valor;
	}
	
	/**
	 * Recupera id anotacao.
	 * 
	 * @return id anotacao
	 */
	public Long getIdAnotacao() {
		return idAnotacao;
	}

	/**
	 * Preenche id anotacao.
	 * 
	 * @param idAnotacao
	 *            o novo id anotacao
	 */
	public void setIdAnotacao(Long idAnotacao) {
		this.idAnotacao = idAnotacao;
	}

	/**
	 * Recupera quantidade.
	 * 
	 * @return quantidade
	 */
	public Short getQuantidade() {
		return quantidade;
	}

	/**
	 * Preenche quantidade.
	 * 
	 * @param quantidade
	 *            o novo quantidade
	 */
	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * Recupera valor.
	 * 
	 * @return valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * Preenche valor.
	 * 
	 * @param valor
	 *            o novo valor
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * Recupera observacao.
	 * 
	 * @return observacao
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * Preenche observacao.
	 * 
	 * @param observacao
	 *            o novo observacao
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * Recupera flexibilidade.
	 * 
	 * @return flexibilidade
	 */
	public Boolean getFlexibilidade() {
		return flexibilidade;
	}

	/**
	 * Preenche flexibilidade.
	 * 
	 * @param flexibilidade
	 *            o novo flexibilidade
	 */
	public void setFlexibilidade(Boolean flexibilidade) {
		this.flexibilidade = flexibilidade;
	}

	/**
	 * Recupera codigo tipo anotacao.
	 * 
	 * @return codigo tipo anotacao
	 */
	public Integer getCodigoTipoAnotacao() {
		return codigoTipoAnotacao;
	}

	/**
	 * Preenche codigo tipo anotacao.
	 * 
	 * @param codigoTipoAnotacao
	 *            o novo codigo tipo anotacao
	 */
	public void setCodigoTipoAnotacao(Integer codigoTipoAnotacao) {
		this.codigoTipoAnotacao = codigoTipoAnotacao;
	}

	/**
	 * Recupera descricao tipo anotacao.
	 * 
	 * @return descricao tipo anotacao
	 */
	public String getDescricaoTipoAnotacao() {
		return descricaoTipoAnotacao;
	}

	/**
	 * Preenche descricao tipo anotacao.
	 * 
	 * @param descricaoTipoAnotacao
	 *            o novo descricao tipo anotacao
	 */
	public void setDescricaoTipoAnotacao(String descricaoTipoAnotacao) {
		this.descricaoTipoAnotacao = descricaoTipoAnotacao;
	}

	/**
	 * Recupera codigo categoria anotacao.
	 * 
	 * @return codigo categoria anotacao
	 */
	public Short getCodigoCategoriaAnotacao() {
		return codigoCategoriaAnotacao;
	}

	/**
	 * Preenche codigo categoria anotacao.
	 * 
	 * @param codigoCategoriaAnotacao
	 *            o novo codigo categoria anotacao
	 */
	public void setCodigoCategoriaAnotacao(Short codigoCategoriaAnotacao) {
		this.codigoCategoriaAnotacao = codigoCategoriaAnotacao;
	}

	/**
	 * Recupera descricao categoria anotacao.
	 * 
	 * @return descricao categoria anotacao
	 */
	public String getDescricaoCategoriaAnotacao() {
		return descricaoCategoriaAnotacao;
	}

	/**
	 * Preenche descricao categoria anotacao.
	 * 
	 * @param descricaoCategoriaAnotacao
	 *            o novo descricao categoria anotacao
	 */
	public void setDescricaoCategoriaAnotacao(String descricaoCategoriaAnotacao) {
		this.descricaoCategoriaAnotacao = descricaoCategoriaAnotacao;
	}

	/**
	 * Recupera codigo origem info.
	 * 
	 * @return codigo origem info
	 */
	public Short getCodigoOrigemInfo() {
		return codigoOrigemInfo;
	}

	/**
	 * Preenche codigo origem info.
	 * 
	 * @param codigoOrigemInfo
	 *            o novo codigo origem info
	 */
	public void setCodigoOrigemInfo(Short codigoOrigemInfo) {
		this.codigoOrigemInfo = codigoOrigemInfo;
	}

	/**
	 * Recupera descricao origem info.
	 * 
	 * @return descricao origem info
	 */
	public String getDescricaoOrigemInfo() {
		return descricaoOrigemInfo;
	}

	/**
	 * Preenche descricao origem info.
	 * 
	 * @param descricaoOrigemInfo
	 *            o novo descricao origem info
	 */
	public void setDescricaoOrigemInfo(String descricaoOrigemInfo) {
		this.descricaoOrigemInfo = descricaoOrigemInfo;
	}

	/**
	 * @return the dataHoraAnotacao
	 */
	public Date getDataHoraAnotacao() {
		return dataHoraAnotacao;
	}

	/**
	 * @param dataHoraAnotacao the dataHoraAnotacao to set
	 */
	public void setDataHoraAnotacao(Date dataHoraAnotacao) {
		this.dataHoraAnotacao = dataHoraAnotacao;
	}

	/**
	 * @return the dataHoraOcorrencia
	 */
	public Date getDataHoraOcorrencia() {
		return dataHoraOcorrencia;
	}

	/**
	 * @param dataHoraOcorrencia the dataHoraOcorrencia to set
	 */
	public void setDataHoraOcorrencia(Date dataHoraOcorrencia) {
		this.dataHoraOcorrencia = dataHoraOcorrencia;
	}

	/**
	 * Recupera o valor de idTipoBaixa.
	 *
	 * @return o valor de idTipoBaixa
	 */
	public Short getIdTipoBaixa() {
		return idTipoBaixa;
	}

	/**
	 * Define o valor de idTipoBaixa.
	 *
	 * @param idTipoBaixa o novo valor de idTipoBaixa
	 */
	public void setIdTipoBaixa(Short idTipoBaixa) {
		this.idTipoBaixa = idTipoBaixa;
	}

	/**
	 * Recupera o valor de descricaoTipoBaixa.
	 *
	 * @return o valor de descricaoTipoBaixa
	 */
	public String getDescricaoTipoBaixa() {
		return descricaoTipoBaixa;
	}

	/**
	 * Define o valor de descricaoTipoBaixa.
	 *
	 * @param descricaoTipoBaixa o novo valor de descricaoTipoBaixa
	 */
	public void setDescricaoTipoBaixa(String descricaoTipoBaixa) {
		this.descricaoTipoBaixa = descricaoTipoBaixa;
	}

	/**
	 * Recupera o valor de dataHoraBaixa.
	 *
	 * @return o valor de dataHoraBaixa
	 */
	public Date getDataHoraBaixa() {
		return dataHoraBaixa;
	}

	/**
	 * Define o valor de dataHoraBaixa.
	 *
	 * @param dataHoraBaixa o novo valor de dataHoraBaixa
	 */
	public void setDataHoraBaixa(Date dataHoraBaixa) {
		this.dataHoraBaixa = dataHoraBaixa;
	}

	/**
	 * Recupera o valor de idTipoConsultaOrigem.
	 *
	 * @return o valor de idTipoConsultaOrigem
	 */
	public Integer getIdTipoConsultaOrigem() {
		return idTipoConsultaOrigem;
	}

	/**
	 * Define o valor de idTipoConsultaOrigem.
	 *
	 * @param idTipoConsultaOrigem o novo valor de idTipoConsultaOrigem
	 */
	public void setIdTipoConsultaOrigem(Integer idTipoConsultaOrigem) {
		this.idTipoConsultaOrigem = idTipoConsultaOrigem;
	}

	/**
	 * Recupera o valor de descricaoTipoConsultaOrigem.
	 *
	 * @return o valor de descricaoTipoConsultaOrigem
	 */
	public String getDescricaoTipoConsultaOrigem() {
		return descricaoTipoConsultaOrigem;
	}

	/**
	 * Define o valor de descricaoTipoConsultaOrigem.
	 *
	 * @param descricaoTipoConsultaOrigem o novo valor de descricaoTipoConsultaOrigem
	 */
	public void setDescricaoTipoConsultaOrigem(String descricaoTipoConsultaOrigem) {
		this.descricaoTipoConsultaOrigem = descricaoTipoConsultaOrigem;
	}
	
}