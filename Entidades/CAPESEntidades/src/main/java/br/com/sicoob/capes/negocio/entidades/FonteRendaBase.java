/*
 * SICOOB
 * 
 * FonteRendaBase.java(br.com.sicoob.capes.negocio.entidades.FonteRendaBase)
 */
package br.com.sicoob.capes.negocio.entidades;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Entidade que representa os dados comuns para FonteRenda e HistoricoFonteRenda.
 * 
 * @author Erico.Junior
 */
@MappedSuperclass
public abstract class FonteRendaBase extends EntidadeCadastroBase {

	/** Serial UID. */
	private static final long serialVersionUID = 4457253217895092216L;
	
	/** O atributo pessoa compartilhamento. */
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTO")
	@ManyToOne
	private PessoaCompartilhamento pessoaCompartilhamento;

	/** O atributo tipo fonte renda. */
	@JoinColumn( name = "CODTIPOFONTERENDA", referencedColumnName = "CODTIPOFONTERENDA")
	@ManyToOne	
	private TipoFonteRenda tipoFonteRenda;
	
	/** O atributo bol renda fixa. */
	private Boolean bolRendaFixa = Boolean.FALSE;
	
	/** O atributo data validade renda. */
	private DateTimeDB dataValidadeRenda;
	
	/** O atributo valor receita bruta mensal. */
	private BigDecimal valorReceitaBrutaMensal;
	
	/** O atributo desc fonte renda. */
	private String descFonteRenda;
	
	/** O atributo pessoa empregador. */
	@JoinColumn( name = "IDPESSOACOMPARTILHAMENTOEMPREGADOR", referencedColumnName = "IDPESSOACOMPARTILHAMENTO" )
	@ManyToOne
	private PessoaCompartilhamento pessoaEmpregador;

	/** O atributo data hora inicio. */
	@Column(name = "DATAHORAINICIO")
	private DateTimeDB dataHoraInicio;
	
	/** O atributo ativo. */
	@Column(name="BOLSIMPLESNACIONAL")
	private Boolean bolSimplesNacional;

	/** O atributo ativo. */
	@Column(name="BOLPOSSUIATIVO")
	private Boolean bolPossuiAtivo;
	
	/**
	 * @return the tipoFonteRenda
	 */
	public TipoFonteRenda getTipoFonteRenda() {
		return tipoFonteRenda;
	}

	/**
	 * @param tipoFonteRenda the tipoFonteRenda to set
	 */
	public void setTipoFonteRenda(TipoFonteRenda tipoFonteRenda) {
		this.tipoFonteRenda = tipoFonteRenda;
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
	public DateTimeDB getDataValidadeRenda() {
		return dataValidadeRenda;
	}

	/**
	 * @param dataValidadeRenda the dataValidadeRenda to set
	 */
	public void setDataValidadeRenda(DateTimeDB dataValidadeRenda) {
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
	 * @return the pessoaEmpregador
	 */
	public PessoaCompartilhamento getPessoaEmpregador() {
		return pessoaEmpregador;
	}

	/**
	 * @param pessoaEmpregador the pessoaEmpregador to set
	 */
	public void setPessoaEmpregador(PessoaCompartilhamento pessoaEmpregador) {
		this.pessoaEmpregador = pessoaEmpregador;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PessoaCompartilhamento getPessoaCompartilhamento() {
		return this.pessoaCompartilhamento;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPessoaCompartilhamento(PessoaCompartilhamento pessoaCompartilhamento) {
		this.pessoaCompartilhamento = pessoaCompartilhamento;
	}

	/**
	 * Recupera data hora inicio.
	 * 
	 * @return data hora inicio
	 */
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * Preenche data hora inicio.
	 * 
	 * @param dataHoraInicio
	 *            o novo data hora inicio
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * Recupera bolPossuiAtivo.
	 * @return bolPossuiAtivo
	 */
	public Boolean getBolPossuiAtivo() {
		return bolPossuiAtivo;
	}

	/**
	 * Preenche bolPossuiAtivo.
	 * @param bolPossuiAtivo
	 */
	public void setBolPossuiAtivo(Boolean bolPossuiAtivo) {
		this.bolPossuiAtivo = bolPossuiAtivo;
	}
}
