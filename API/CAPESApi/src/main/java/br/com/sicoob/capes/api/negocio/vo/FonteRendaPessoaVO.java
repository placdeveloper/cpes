/*
 * SICOOB
 * 
 * FonteRendaPessoaVO.java(br.com.sicoob.capes.api.negocio.vo.FonteRendaPessoaVO)
 */
package br.com.sicoob.capes.api.negocio.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Lucas.Borges
 */
public class FonteRendaPessoaVO extends AbstractPessoaVO{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -7467588018691386299L;

	// FONTE RENDA PESSOA
	/** O atributo id fonte renda. */
	private Long idFonteRenda;

	/** O atributo data hora inicio. */
	private Date dataHoraInicio;
	
	/** O atributo renda fixa. */
	private boolean rendaFixa;
	
	/** O atributo data validade renda. */
	private Date dataValidadeRenda;
	
	/** O atributo valor receita bruta mensal. */
	private BigDecimal valorReceitaBrutaMensal;
	
	/** O atributo descricao. */
	private String descricao;
	
	// TIPO FONTE RENDA
	/** O atributo codigo tipo fonte renda. */
	private Short codigoTipoFonteRenda;
	
	/** O atributo descricao tipo fonte renda. */
	private String descricaoTipoFonteRenda;
	
	// PESSOA EMPREGADOR
	/** O atributo id pessoa empregador. */
	private Integer idPessoaEmpregador;

	/** O atributo bolSimplesNacional. */
	private Boolean bolSimplesNacional;

	/** O atributo bolPossuiAtivo. */
	private Boolean bolPossuiAtivo;

	/**
	 * Cria uma nova instância de fonte renda pessoa vo.
	 */
	public FonteRendaPessoaVO() {

	}

	/**
	 * Cria uma nova instância de fonte renda pessoa vo.
	 * 
	 * @param idFonteRenda
	 *            the id fonte renda
	 * @param dataHoraInicio
	 *            the data hora inicio
	 * @param rendaFixa
	 *            the renda fixa
	 * @param dataValidadeRenda
	 *            the data validade renda
	 * @param valorReceitaBrutaMensal
	 *            the valor receita bruta mensal
	 * @param descricao
	 *            the descricao
	 * @param codigoTipoFonteRenda
	 *            the codigo tipo fonte renda
	 * @param descricaoTipoFonteRenda
	 *            the descricao tipo fonte renda
	 * @param idPessoaEmpregador
	 *            the id pessoa empregador
	 */
	public FonteRendaPessoaVO(Long idFonteRenda, Date dataHoraInicio,
			boolean rendaFixa, Date dataValidadeRenda,
			BigDecimal valorReceitaBrutaMensal, String descricao,
			Short codigoTipoFonteRenda, String descricaoTipoFonteRenda,
			Integer idPessoaEmpregador) {
		this.idFonteRenda = idFonteRenda;
		this.dataHoraInicio = dataHoraInicio;
		this.rendaFixa = rendaFixa;
		this.dataValidadeRenda = dataValidadeRenda;
		this.valorReceitaBrutaMensal = valorReceitaBrutaMensal;
		this.descricao = descricao;
		this.codigoTipoFonteRenda = codigoTipoFonteRenda;
		this.descricaoTipoFonteRenda = descricaoTipoFonteRenda;
		this.idPessoaEmpregador = idPessoaEmpregador;
	}

	/**
	 * Recupera id fonte renda.
	 * 
	 * @return id fonte renda
	 */
	public Long getIdFonteRenda() {
		return idFonteRenda;
	}

	/**
	 * Preenche id fonte renda.
	 * 
	 * @param idFonteRenda
	 *            o novo id fonte renda
	 */
	public void setIdFonteRenda(Long idFonteRenda) {
		this.idFonteRenda = idFonteRenda;
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
	 * Verifica se é renda fixa.
	 * 
	 * @return true, se for renda fixa
	 */
	public boolean isRendaFixa() {
		return rendaFixa;
	}

	/**
	 * Preenche renda fixa.
	 * 
	 * @param rendaFixa
	 *            o novo renda fixa
	 */
	public void setRendaFixa(boolean rendaFixa) {
		this.rendaFixa = rendaFixa;
	}

	/**
	 * Recupera data validade renda.
	 * 
	 * @return data validade renda
	 */
	public Date getDataValidadeRenda() {
		return dataValidadeRenda;
	}

	/**
	 * Preenche data validade renda.
	 * 
	 * @param dataValidadeRenda
	 *            o novo data validade renda
	 */
	public void setDataValidadeRenda(Date dataValidadeRenda) {
		this.dataValidadeRenda = dataValidadeRenda;
	}

	/**
	 * Recupera valor receita bruta mensal.
	 * 
	 * @return valor receita bruta mensal
	 */
	public BigDecimal getValorReceitaBrutaMensal() {
		return valorReceitaBrutaMensal;
	}

	/**
	 * Preenche valor receita bruta mensal.
	 * 
	 * @param valorReceitaBrutaMensal
	 *            o novo valor receita bruta mensal
	 */
	public void setValorReceitaBrutaMensal(BigDecimal valorReceitaBrutaMensal) {
		this.valorReceitaBrutaMensal = valorReceitaBrutaMensal;
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
	 * Recupera codigo tipo fonte renda.
	 * 
	 * @return codigo tipo fonte renda
	 */
	public Short getCodigoTipoFonteRenda() {
		return codigoTipoFonteRenda;
	}

	/**
	 * Preenche codigo tipo fonte renda.
	 * 
	 * @param codigoTipoFonteRenda
	 *            o novo codigo tipo fonte renda
	 */
	public void setCodigoTipoFonteRenda(Short codigoTipoFonteRenda) {
		this.codigoTipoFonteRenda = codigoTipoFonteRenda;
	}

	/**
	 * Recupera descricao tipo fonte renda.
	 * 
	 * @return descricao tipo fonte renda
	 */
	public String getDescricaoTipoFonteRenda() {
		return descricaoTipoFonteRenda;
	}

	/**
	 * Preenche descricao tipo fonte renda.
	 * 
	 * @param descricaoTipoFonteRenda
	 *            o novo descricao tipo fonte renda
	 */
	public void setDescricaoTipoFonteRenda(String descricaoTipoFonteRenda) {
		this.descricaoTipoFonteRenda = descricaoTipoFonteRenda;
	}

	/**
	 * Recupera id pessoa empregador.
	 * 
	 * @return id pessoa empregador
	 */
	public Integer getIdPessoaEmpregador() {
		return idPessoaEmpregador;
	}

	/**
	 * Preenche id pessoa empregador.
	 * 
	 * @param idPessoaEmpregador
	 *            o novo id pessoa empregador
	 */
	public void setIdPessoaEmpregador(Integer idPessoaEmpregador) {
		this.idPessoaEmpregador = idPessoaEmpregador;
	}

	public Boolean getBolSimplesNacional() {
		return bolSimplesNacional;
	}

	public void setBolSimplesNacional(Boolean bolSimplesNacional) {
		this.bolSimplesNacional = bolSimplesNacional;
	}

	public Boolean getBolPossuiAtivo() {
		return bolPossuiAtivo;
	}

	public void setBolPossuiAtivo(Boolean bolPossuiAtivo) {
		this.bolPossuiAtivo = bolPossuiAtivo;
	}

}
