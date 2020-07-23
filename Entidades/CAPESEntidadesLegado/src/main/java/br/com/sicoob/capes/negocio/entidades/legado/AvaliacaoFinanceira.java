/*
 * SICOOB
 * 
 * AvaliacaoFinanceira.java(br.com.sicoob.capes.negocio.entidades.legado.AvaliacaoFinanceira)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Erico.Junior
 *
 */
@Entity
@Table(name = "AvalFinanceira")
public class AvaliacaoFinanceira extends EntidadeReplicavel<Integer> {

	/** Serial UID.*/
	private static final long serialVersionUID = 3483649784813530583L;

	/** O atributo num pessoa. */
	@Id
	private Integer numPessoa;
	
	/** O atributo pessoa. */
	@OneToOne
	@PrimaryKeyJoinColumn(name="NumPessoa")
	private Pessoa pessoa;
	
	/** O atributo valor limite credito curto prazo. */
	private BigDecimal valorLimiteCreditoCurtoPrazo;
	
	/** O atributo valor limite credito longo prazo. */
	private BigDecimal valorLimiteCreditoLongoPrazo;
	
	/** O atributo valor limite credito rotativo. */
	private BigDecimal valorLimiteCreditoRotativo;
	
	/** O atributo valor total gasto familiar. */
	private BigDecimal valorTotalGastoFamiliar;
	
	/** O atributo data ultima atualizacao. */
	private Date dataUltimaAtualizacao;
	
	/** O atributo data ultima renovacao. */
	private Date dataUltimaRenovacao;
	
	/** O atributo id d b2. */
	@Transient
	private Integer idDB2;
	/**
	 * @return the numPessoa
	 */
	public Integer getNumPessoa() {
		return numPessoa;
	}
	/**
	 * @param numPessoa the numPessoa to set
	 */
	public void setNumPessoa(Integer numPessoa) {
		this.numPessoa = numPessoa;
	}
	/**
	 * @return the pessoa
	 */
	public Pessoa getPessoa() {
		return pessoa;
	}
	/**
	 * @param pessoa the pessoa to set
	 */
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	/**
	 * @return the valorLimiteCreditoCurtoPrazo
	 */
	public BigDecimal getValorLimiteCreditoCurtoPrazo() {
		return valorLimiteCreditoCurtoPrazo;
	}
	/**
	 * @param valorLimiteCreditoCurtoPrazo the valorLimiteCreditoCurtoPrazo to set
	 */
	public void setValorLimiteCreditoCurtoPrazo(
			BigDecimal valorLimiteCreditoCurtoPrazo) {
		this.valorLimiteCreditoCurtoPrazo = valorLimiteCreditoCurtoPrazo;
	}
	/**
	 * @return the valorLimiteCreditoLongoPrazo
	 */
	public BigDecimal getValorLimiteCreditoLongoPrazo() {
		return valorLimiteCreditoLongoPrazo;
	}
	/**
	 * @param valorLimiteCreditoLongoPrazo the valorLimiteCreditoLongoPrazo to set
	 */
	public void setValorLimiteCreditoLongoPrazo(
			BigDecimal valorLimiteCreditoLongoPrazo) {
		this.valorLimiteCreditoLongoPrazo = valorLimiteCreditoLongoPrazo;
	}
	/**
	 * @return the valorLimiteCreditoRotativo
	 */
	public BigDecimal getValorLimiteCreditoRotativo() {
		return valorLimiteCreditoRotativo;
	}
	/**
	 * @param valorLimiteCreditoRotativo the valorLimiteCreditoRotativo to set
	 */
	public void setValorLimiteCreditoRotativo(BigDecimal valorLimiteCreditoRotativo) {
		this.valorLimiteCreditoRotativo = valorLimiteCreditoRotativo;
	}
	/**
	 * @return the valorTotalGastoFamiliar
	 */
	public BigDecimal getValorTotalGastoFamiliar() {
		return valorTotalGastoFamiliar;
	}
	/**
	 * @param valorTotalGastoFamiliar the valorTotalGastoFamiliar to set
	 */
	public void setValorTotalGastoFamiliar(BigDecimal valorTotalGastoFamiliar) {
		this.valorTotalGastoFamiliar = valorTotalGastoFamiliar;
	}
	/**
	 * @return the dataUltimaAtualizacao
	 */
	public Date getDataUltimaAtualizacao() {
		return dataUltimaAtualizacao;
	}
	/**
	 * @param dataUltimaAtualizacao the dataUltimaAtualizacao to set
	 */
	public void setDataUltimaAtualizacao(Date dataUltimaAtualizacao) {
		this.dataUltimaAtualizacao = dataUltimaAtualizacao;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getIdSQL() {
		return getNumPessoa();
	}
	/**
	 * @return the dataUltimaRenovacao
	 */
	public Date getDataUltimaRenovacao() {
		return dataUltimaRenovacao;
	}
	/**
	 * @param dataUltimaRenovacao the dataUltimaRenovacao to set
	 */
	public void setDataUltimaRenovacao(Date dataUltimaRenovacao) {
		this.dataUltimaRenovacao = dataUltimaRenovacao;
	}
	
	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return this.idDB2;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof Integer){
			setNumPessoa((Integer) idSQL);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Integer){
			setIdDB2((Integer)idDB2);
		}
	}
	
	/**
	 * Preenche id d b2.
	 * 
	 * @param idDB2
	 *            o novo id d b2
	 */
	public void setIdDB2(Integer idDB2) {
		this.idDB2 = idDB2;
	}
	
}
