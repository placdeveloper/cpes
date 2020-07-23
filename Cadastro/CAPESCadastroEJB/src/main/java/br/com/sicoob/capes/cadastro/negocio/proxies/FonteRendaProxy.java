/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.proxies;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.sicoob.capes.cadastro.negocio.enums.TipoAutorizacaoEntidadeEnum;
import br.com.sicoob.capes.comum.negocio.enums.SituacaoRegistroEnum;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import br.com.sicoob.tipos.DateTime;

/**
 * @author Erico.Junior
 *
 */
public class FonteRendaProxy implements Serializable {

	/** Serial UID.*/
	private static final long serialVersionUID = -7955100458516983653L;
	
	/** O atributo idFonteRenda. */
	private Long idFonteRenda;
	
	/** O atributo idPessoa. */
	private Long idPessoa;
	
	/** O atributo codigoTipoFonteRenda. */
	private Short codigoTipoFonteRenda;

	/** O atributo descricaoTipoFonteRenda. */
	private String descricaoTipoFonteRenda;

	/** O atributo bolRendaFixa. */
	private Boolean bolRendaFixa = Boolean.FALSE;
	
	/** O atributo bolPossuiAtivo. */
	private Boolean bolPossuiAtivo;
	
	/** O atributo bolSimplesNacional. */
	private Boolean bolSimplesNacional;
	
	/** O atributo dataHoraInicio. */
	private DateTime dataHoraInicio;
	
	/** O atributo dataValidadeRenda. */
	private DateTime dataValidadeRenda;
	
	/** O atributo valorReceitaBrutaMensal. */
	private BigDecimal valorReceitaBrutaMensal;
	
	/** O atributo descFonteRenda. */
	private String descFonteRenda;

	/** O atributo idPessoaEmpregador. */
	private Long idPessoaEmpregador;
	
	/** O atributo idRegistroControlado. */
	private String idRegistroControlado;
	
	/** O atributo idUsuarioAprovacao. */
	private String idUsuarioAprovacao;
	
	/** O atributo idInstituicaoAtualizacao. */
	private Integer idInstituicaoAtualizacao;
	
	/** O atributo codigoSituacaoAprovacao. */
	private Short codigoSituacaoAprovacao;
	
	/** O atributo situacaoAprovacao. */
	private SituacaoRegistroEnum situacaoAprovacao;

	/**
	 * @return the idFonteRenda
	 */
	public Long getIdFonteRenda() {
		return idFonteRenda;
	}

	/**
	 * @param idFonteRenda the idFonteRenda to set
	 */
	public void setIdFonteRenda(Long idFonteRenda) {
		this.idFonteRenda = idFonteRenda;
	}

	/**
	 * @return the idPessoa
	 */
	public Long getIdPessoa() {
		return idPessoa;
	}

	/**
	 * @param idPessoa the idPessoa to set
	 */
	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	/**
	 * @return the codigoTipoFonteRenda
	 */
	public Short getCodigoTipoFonteRenda() {
		return codigoTipoFonteRenda;
	}

	/**
	 * @param codigoTipoFonteRenda the codigoTipoFonteRenda to set
	 */
	public void setCodigoTipoFonteRenda(Short codigoTipoFonteRenda) {
		this.codigoTipoFonteRenda = codigoTipoFonteRenda;
	}

	/**
	 * @return the descricaoTipoFonteRenda
	 */
	public String getDescricaoTipoFonteRenda() {
		return descricaoTipoFonteRenda;
	}

	/**
	 * @param descricaoTipoFonteRenda the descricaoTipoFonteRenda to set
	 */
	public void setDescricaoTipoFonteRenda(String descricaoTipoFonteRenda) {
		this.descricaoTipoFonteRenda = descricaoTipoFonteRenda;
	}

	/**
	 * @return the bolPossuiAtivo
	 */
	public Boolean getBolPossuiAtivo() {
		return bolPossuiAtivo;
	}

	/**
	 * @param bolPossuiAtivo the bolPossuiAtivo to set
	 */
	public void setBolPossuiAtivo(Boolean bolPossuiAtivo) {
		this.bolPossuiAtivo = bolPossuiAtivo;
	}

	/**
	 * @return the bolSimplesNacional
	 */
	public Boolean getBolSimplesNacional() {
		return bolSimplesNacional;
	}

	/**
	 * @param bolSimplesNacional the bolSimplesNacional to set
	 */
	public void setBolSimplesNacional(Boolean bolSimplesNacional) {
		this.bolSimplesNacional = bolSimplesNacional;
	}
	
	/**
	 * @return the bolRendaFixa
	 */
	public Boolean getBolRendaFixa() {
		return bolRendaFixa;
	}

	/**
	 * @param bolRendaFixa the bolRendaFixa to set
	 */
	public void setBolRendaFixa(Boolean bolRendaFixa) {
		this.bolRendaFixa = bolRendaFixa;
	}

	/**
	 * @return the dataValidadeRenda
	 */
	public DateTime getDataValidadeRenda() {
		return dataValidadeRenda;
	}

	/**
	 * @param dataValidadeRenda the dataValidadeRenda to set
	 */
	public void setDataValidadeRenda(DateTime dataValidadeRenda) {
		this.dataValidadeRenda = dataValidadeRenda;
	}

	/**
	 * @return the valorReceitaBrutaMensal
	 */
	public BigDecimal getValorReceitaBrutaMensal() {
		return valorReceitaBrutaMensal;
	}

	/**
	 * @param valorReceitaBrutaMensal the valorReceitaBrutaMensal to set
	 */
	public void setValorReceitaBrutaMensal(BigDecimal valorReceitaBrutaMensal) {
		this.valorReceitaBrutaMensal = valorReceitaBrutaMensal;
	}

	/**
	 * @return the descFonteRenda
	 */
	public String getDescFonteRenda() {
		return descFonteRenda;
	}

	/**
	 * @param descFonteRenda the descFonteRenda to set
	 */
	public void setDescFonteRenda(String descFonteRenda) {
		this.descFonteRenda = descFonteRenda;
	}

	/**
	 * @return the idPessoaEmpregador
	 */
	public Long getIdPessoaEmpregador() {
		return idPessoaEmpregador;
	}

	/**
	 * @param idPessoaEmpregador the idPessoaEmpregador to set
	 */
	public void setIdPessoaEmpregador(Long idPessoaEmpregador) {
		this.idPessoaEmpregador = idPessoaEmpregador;
	}

	/**
	 * @return the dataHoraInicio
	 */
	public DateTime getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * @param dataHoraInicio the dataHoraInicio to set
	 */
	public void setDataHoraInicio(DateTime dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * Recupera o valor de idRegistroControlado.
	 *
	 * @return o valor de idRegistroControlado
	 */
	public String getIdRegistroControlado() {
		if (idRegistroControlado != null && !"".equals(idRegistroControlado)) {
			return idRegistroControlado;
		}
		return TipoAutorizacaoEntidadeEnum.FONTE_RENDA.name() + Aprovavel.SEPARADOR_ID_REGISTRO_CONTROLADO + getIdFonteRenda();
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

	/**
	 * Recupera o valor de idUsuarioAprovacao.
	 *
	 * @return o valor de idUsuarioAprovacao
	 */
	public String getIdUsuarioAprovacao() {
		return idUsuarioAprovacao;
	}

	/**
	 * Define o valor de idUsuarioAprovacao.
	 *
	 * @param idUsuarioAprovacao o novo valor de idUsuarioAprovacao
	 */
	public void setIdUsuarioAprovacao(String idUsuarioAprovacao) {
		this.idUsuarioAprovacao = idUsuarioAprovacao;
	}
}
