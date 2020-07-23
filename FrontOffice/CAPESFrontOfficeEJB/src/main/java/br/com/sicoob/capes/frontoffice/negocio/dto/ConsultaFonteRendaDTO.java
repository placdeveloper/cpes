package br.com.sicoob.capes.frontoffice.negocio.dto;

import java.math.BigDecimal;
import java.util.Date;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;

/**
 * A Classe FonteRendaDTO.
 * 
 * @author bruno.carneiro
 */
public class ConsultaFonteRendaDTO extends BancoobDto {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 3380843466535681944L;

	/** O atributo idFonteRenda. */
	@AtributoRetorno(posicao = 1)
	private Long idFonteRenda;

	/** O atributo codigoTipoFonteRenda. */
	@AtributoRetorno(posicao = 2)
	private Short codigoTipoFonteRenda;
	
	@AtributoRetorno(posicao = 3)
	private String descricaoTipoRenda;

	/** O atributo bolRendaFixa. */
	@AtributoRetorno(posicao = 4)
	private Boolean bolRendaFixa = Boolean.FALSE;
	
	/** O atributo descFonteRenda. */
	@AtributoRetorno(posicao = 5)
	private String descFonteRenda;

	/** O atributo dataValidadeRenda. */
	@AtributoRetorno(posicao = 6)
	private Date dataValidadeRenda;

	/** O atributo valorReceitaBrutaMensal. */
	@AtributoRetorno(posicao = 7)
	private BigDecimal valorReceitaBrutaMensal;

	/** O atributo idPessoaEmpregador. */
	@AtributoRetorno(posicao = 8)
	private Integer idPessoaEmpregador;

	/** O atributo bolSimplesNacional. */
	@AtributoRetorno(posicao = 9)
	private Boolean bolSimplesNacional;

	/** O atributo bolPossuiAtivo. */
	@AtributoRetorno(posicao = 10)
	private Boolean bolPossuiAtivo;

	/**
	 * Recupera o valor de idFonteRenda.
	 * 
	 * @return o valor de idFonteRenda
	 */
	public Long getIdFonteRenda() {
		return idFonteRenda;
	}

	/**
	 * Define o valor de idFonteRenda.
	 * 
	 * @param idFonteRenda
	 *            o novo valor de idFonteRenda
	 */
	public void setIdFonteRenda(Long idFonteRenda) {
		this.idFonteRenda = idFonteRenda;
	}

	/**
	 * Recupera o valor de codigoTipoFonteRenda.
	 * 
	 * @return o valor de codigoTipoFonteRenda
	 */
	public Short getCodigoTipoFonteRenda() {
		return codigoTipoFonteRenda;
	}

	/**
	 * Define o valor de codigoTipoFonteRenda.
	 * 
	 * @param codigoTipoFonteRenda
	 *            o novo valor de codigoTipoFonteRenda
	 */
	public void setCodigoTipoFonteRenda(Short codigoTipoFonteRenda) {
		this.codigoTipoFonteRenda = codigoTipoFonteRenda;
	}

	/**
	 * Recupera o valor de bolRendaFixa.
	 * 
	 * @return o valor de bolRendaFixa
	 */
	public Boolean getBolRendaFixa() {
		return bolRendaFixa;
	}

	/**
	 * Define o valor de bolRendaFixa.
	 * 
	 * @param bolRendaFixa
	 *            o novo valor de bolRendaFixa
	 */
	public void setBolRendaFixa(Boolean bolRendaFixa) {
		this.bolRendaFixa = bolRendaFixa;
	}

	/**
	 * Recupera o valor de dataValidadeRenda.
	 * 
	 * @return o valor de dataValidadeRenda
	 */
	public Date getDataValidadeRenda() {
		return dataValidadeRenda;
	}

	/**
	 * Define o valor de dataValidadeRenda.
	 * 
	 * @param dataValidadeRenda
	 *            o novo valor de dataValidadeRenda
	 */
	public void setDataValidadeRenda(Date dataValidadeRenda) {
		this.dataValidadeRenda = dataValidadeRenda;
	}

	/**
	 * Recupera o valor de valorReceitaBrutaMensal.
	 * 
	 * @return o valor de valorReceitaBrutaMensal
	 */
	public BigDecimal getValorReceitaBrutaMensal() {
		return valorReceitaBrutaMensal;
	}

	/**
	 * Define o valor de valorReceitaBrutaMensal.
	 * 
	 * @param valorReceitaBrutaMensal
	 *            o novo valor de valorReceitaBrutaMensal
	 */
	public void setValorReceitaBrutaMensal(BigDecimal valorReceitaBrutaMensal) {
		this.valorReceitaBrutaMensal = valorReceitaBrutaMensal;
	}

	/**
	 * Recupera o valor de descFonteRenda.
	 * 
	 * @return o valor de descFonteRenda
	 */
	public String getDescFonteRenda() {
		return descFonteRenda;
	}

	/**
	 * Define o valor de descFonteRenda.
	 * 
	 * @param descFonteRenda
	 *            o novo valor de descFonteRenda
	 */
	public void setDescFonteRenda(String descFonteRenda) {
		this.descFonteRenda = descFonteRenda;
	}

	/**
	 * Recupera o valor de idPessoaEmpregador.
	 * 
	 * @return o valor de idPessoaEmpregador
	 */
	public Integer getIdPessoaEmpregador() {
		return idPessoaEmpregador;
	}

	/**
	 * Define o valor de idPessoaEmpregador.
	 * 
	 * @param idPessoaEmpregador
	 *            o novo valor de idPessoaEmpregador
	 */
	public void setIdPessoaEmpregador(Integer idPessoaEmpregador) {
		this.idPessoaEmpregador = idPessoaEmpregador;
	}

	/**
	 * Recupera o valor de bolSimplesNacional.
	 * 
	 * @return o valor de bolSimplesNacional
	 */
	public Boolean getBolSimplesNacional() {
		return bolSimplesNacional;
	}

	/**
	 * Define o valor de bolSimplesNacional.
	 * 
	 * @param bolSimplesNacional
	 *            o novo valor de bolSimplesNacional
	 */
	public void setBolSimplesNacional(Boolean bolSimplesNacional) {
		this.bolSimplesNacional = bolSimplesNacional;
	}

	/**
	 * Recupera o valor de bolPossuiAtivo.
	 * 
	 * @return o valor de bolPossuiAtivo
	 */
	public Boolean getBolPossuiAtivo() {
		return bolPossuiAtivo;
	}

	/**
	 * Define o valor de bolPossuiAtivo.
	 * 
	 * @param bolPossuiAtivo
	 *            o novo valor de bolPossuiAtivo
	 */
	public void setBolPossuiAtivo(Boolean bolPossuiAtivo) {
		this.bolPossuiAtivo = bolPossuiAtivo;
	}

	public String getDescricaoTipoRenda() {
		return descricaoTipoRenda;
	}

	public void setDescricaoTipoRenda(String descricaoTipoRenda) {
		this.descricaoTipoRenda = descricaoTipoRenda;
	}

}