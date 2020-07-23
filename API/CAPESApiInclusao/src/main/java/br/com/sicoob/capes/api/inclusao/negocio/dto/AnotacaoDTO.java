package br.com.sicoob.capes.api.inclusao.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * A Classe AnotacaoDTO.
 * 
 * @author bruno.carneiro
 */
public class AnotacaoDTO extends RegistroInclusaoDTO<Long> {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 2855403012705406869L;

	/** O atributo idAnotacao. */
	private Long idAnotacao;

	/** O atributo codigoTipoAnotacao. */
	private Integer codigoTipoAnotacao;

	/** O atributo codigoTipoBaixa. */
	private Short codigoTipoBaixa;

	/** O atributo codigoOrigemInformacao. */
	private Short codigoOrigemInformacao;

	/** O atributo codigoTipoConsultaOrigem. */
	private Integer codigoTipoConsultaOrigem;

	/** O atributo codigoObservacaoAnotacao. */
	private Short codigoObservacaoAnotacao;

	/** O atributo usuarioInclusao. */
	private String usuarioInclusao;

	/** O atributo usuarioAlteracao. */
	private String usuarioAlteracao;

	/** O atributo dataHoraOcorrencia. */
	private Date dataHoraOcorrencia;

	/** O atributo dataHoraAlteracao. */
	private Date dataHoraAlteracao;

	/** O atributo dataHoraBaixa. */
	private Date dataHoraBaixa;

	/** O atributo descObservacao. */
	private String descObservacao;

	/** O atributo valor. */
	private BigDecimal valor = BigDecimal.ZERO;

	/** O atributo flexibilidade. */
	private Boolean flexibilidade = Boolean.FALSE;

	/** O atributo idConsultaExterna. */
	private Integer idConsultaExterna;

	/** O atributo quantidade. */
	private Short quantidade = 0;

	/**
	 * Recupera o valor de idAnotacao.
	 * 
	 * @return o valor de idAnotacao
	 */
	public Long getIdAnotacao() {
		return idAnotacao;
	}

	/**
	 * Define o valor de idAnotacao.
	 * 
	 * @param idAnotacao
	 *            o novo valor de idAnotacao
	 */
	public void setIdAnotacao(Long idAnotacao) {
		this.idAnotacao = idAnotacao;
	}

	/**
	 * Recupera o valor de codigoTipoAnotacao.
	 * 
	 * @return o valor de codigoTipoAnotacao
	 */
	public Integer getCodigoTipoAnotacao() {
		return codigoTipoAnotacao;
	}

	/**
	 * Define o valor de codigoTipoAnotacao.
	 * 
	 * @param codigoTipoAnotacao
	 *            o novo valor de codigoTipoAnotacao
	 */
	public void setCodigoTipoAnotacao(Integer codigoTipoAnotacao) {
		this.codigoTipoAnotacao = codigoTipoAnotacao;
	}

	/**
	 * Recupera o valor de codigoTipoBaixa.
	 * 
	 * @return o valor de codigoTipoBaixa
	 */
	public Short getCodigoTipoBaixa() {
		return codigoTipoBaixa;
	}

	/**
	 * Define o valor de codigoTipoBaixa.
	 * 
	 * @param codigoTipoBaixa
	 *            o novo valor de codigoTipoBaixa
	 */
	public void setCodigoTipoBaixa(Short codigoTipoBaixa) {
		this.codigoTipoBaixa = codigoTipoBaixa;
	}

	/**
	 * Recupera o valor de codigoOrigemInformacao.
	 * 
	 * @return o valor de codigoOrigemInformacao
	 */
	public Short getCodigoOrigemInformacao() {
		return codigoOrigemInformacao;
	}

	/**
	 * Define o valor de codigoOrigemInformacao.
	 * 
	 * @param codigoOrigemInformacao
	 *            o novo valor de codigoOrigemInformacao
	 */
	public void setCodigoOrigemInformacao(Short codigoOrigemInformacao) {
		this.codigoOrigemInformacao = codigoOrigemInformacao;
	}

	/**
	 * Recupera o valor de codigoTipoConsultaOrigem.
	 * 
	 * @return o valor de codigoTipoConsultaOrigem
	 */
	public Integer getCodigoTipoConsultaOrigem() {
		return codigoTipoConsultaOrigem;
	}

	/**
	 * Define o valor de codigoTipoConsultaOrigem.
	 * 
	 * @param codigoTipoConsultaOrigem
	 *            o novo valor de codigoTipoConsultaOrigem
	 */
	public void setCodigoTipoConsultaOrigem(Integer codigoTipoConsultaOrigem) {
		this.codigoTipoConsultaOrigem = codigoTipoConsultaOrigem;
	}

	/**
	 * Recupera o valor de codigoObservacaoAnotacao.
	 * 
	 * @return o valor de codigoObservacaoAnotacao
	 */
	public Short getCodigoObservacaoAnotacao() {
		return codigoObservacaoAnotacao;
	}

	/**
	 * Define o valor de codigoObservacaoAnotacao.
	 * 
	 * @param codigoObservacaoAnotacao
	 *            o novo valor de codigoObservacaoAnotacao
	 */
	public void setCodigoObservacaoAnotacao(Short codigoObservacaoAnotacao) {
		this.codigoObservacaoAnotacao = codigoObservacaoAnotacao;
	}

	/**
	 * Recupera o valor de usuarioInclusao.
	 * 
	 * @return o valor de usuarioInclusao
	 */
	public String getUsuarioInclusao() {
		return usuarioInclusao;
	}

	/**
	 * Define o valor de usuarioInclusao.
	 * 
	 * @param usuarioInclusao
	 *            o novo valor de usuarioInclusao
	 */
	public void setUsuarioInclusao(String usuarioInclusao) {
		this.usuarioInclusao = usuarioInclusao;
	}

	/**
	 * Recupera o valor de usuarioAlteracao.
	 * 
	 * @return o valor de usuarioAlteracao
	 */
	public String getUsuarioAlteracao() {
		return usuarioAlteracao;
	}

	/**
	 * Define o valor de usuarioAlteracao.
	 * 
	 * @param usuarioAlteracao
	 *            o novo valor de usuarioAlteracao
	 */
	public void setUsuarioAlteracao(String usuarioAlteracao) {
		this.usuarioAlteracao = usuarioAlteracao;
	}

	/**
	 * Recupera o valor de dataHoraOcorrencia.
	 * 
	 * @return o valor de dataHoraOcorrencia
	 */
	public Date getDataHoraOcorrencia() {
		return dataHoraOcorrencia;
	}

	/**
	 * Define o valor de dataHoraOcorrencia.
	 * 
	 * @param dataHoraOcorrencia
	 *            o novo valor de dataHoraOcorrencia
	 */
	public void setDataHoraOcorrencia(Date dataHoraOcorrencia) {
		this.dataHoraOcorrencia = dataHoraOcorrencia;
	}

	/**
	 * Recupera o valor de dataHoraAlteracao.
	 * 
	 * @return o valor de dataHoraAlteracao
	 */
	public Date getDataHoraAlteracao() {
		return dataHoraAlteracao;
	}

	/**
	 * Define o valor de dataHoraAlteracao.
	 * 
	 * @param dataHoraAlteracao
	 *            o novo valor de dataHoraAlteracao
	 */
	public void setDataHoraAlteracao(Date dataHoraAlteracao) {
		this.dataHoraAlteracao = dataHoraAlteracao;
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
	 * @param dataHoraBaixa
	 *            o novo valor de dataHoraBaixa
	 */
	public void setDataHoraBaixa(Date dataHoraBaixa) {
		this.dataHoraBaixa = dataHoraBaixa;
	}

	/**
	 * Recupera o valor de descObservacao.
	 * 
	 * @return o valor de descObservacao
	 */
	public String getDescObservacao() {
		return descObservacao;
	}

	/**
	 * Define o valor de descObservacao.
	 * 
	 * @param descObservacao
	 *            o novo valor de descObservacao
	 */
	public void setDescObservacao(String descObservacao) {
		this.descObservacao = descObservacao;
	}

	/**
	 * Recupera o valor de valor.
	 * 
	 * @return o valor de valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * Define o valor de valor.
	 * 
	 * @param valor
	 *            o novo valor de valor
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * Recupera o valor de flexibilidade.
	 * 
	 * @return o valor de flexibilidade
	 */
	public Boolean getFlexibilidade() {
		return flexibilidade;
	}

	/**
	 * Define o valor de flexibilidade.
	 * 
	 * @param flexibilidade
	 *            o novo valor de flexibilidade
	 */
	public void setFlexibilidade(Boolean flexibilidade) {
		this.flexibilidade = flexibilidade;
	}

	/**
	 * Recupera o valor de idConsultaExterna.
	 * 
	 * @return o valor de idConsultaExterna
	 */
	public Integer getIdConsultaExterna() {
		return idConsultaExterna;
	}

	/**
	 * Define o valor de idConsultaExterna.
	 * 
	 * @param idConsultaExterna
	 *            o novo valor de idConsultaExterna
	 */
	public void setIdConsultaExterna(Integer idConsultaExterna) {
		this.idConsultaExterna = idConsultaExterna;
	}

	/**
	 * Recupera o valor de quantidade.
	 * 
	 * @return o valor de quantidade
	 */
	public Short getQuantidade() {
		return quantidade;
	}

	/**
	 * Define o valor de quantidade.
	 * 
	 * @param quantidade
	 *            o novo valor de quantidade
	 */
	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public Long getId() {
		return getIdAnotacao();
	}

	@Override
	public void setId(Long id) {
		setIdAnotacao(id);
	}

}