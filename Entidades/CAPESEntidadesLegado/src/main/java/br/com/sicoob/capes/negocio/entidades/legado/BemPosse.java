/*
 * SICOOB
 * 
 * BemPosse.java(br.com.sicoob.capes.negocio.entidades.legado.BemPosse)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.legado.pk.BemPossePK;

/**
 * Classe que representa o bem onus
 * @author Juan.Damasceno
 *
 */
@Entity
@Table(name="BEMPESSOAPOSSE")
public class BemPosse extends EntidadeReplicavel<BemPossePK>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo bem posse pk. */
	@EmbeddedId
	private BemPossePK bemPossePK;
	
	/** O atributo id posse db2. */
	@Column(name="IDBEMPESSOAPOSSEDB2")
	private Integer idPosseDb2;
	
	/** O atributo cod tipo posse terra. */
	@Column(name="CODPOSSETERRA")
	private Short codTipoPosseTerra;
	
	/** O atributo quantiadade area. */
	@Column(name="QTDAREAPOSSE")
	private BigDecimal quantiadadeArea;

	/** O atributo seq bem posse. */
	@Column(name="CODSEQPOSSE", updatable=false, insertable=false)
	private Short seqBemPosse; 	
	
	@Column(name = "NumPessoaParceira")
	private Integer numPessoaParceira;
	
	/**
	 * @return the seqBemPosse
	 */
	public Short getSeqBemPosse() {
		return seqBemPosse;
	}

	/**
	 * @param seqBemPosse the seqBemPosse to set
	 */
	public void setSeqBemPosse(Short seqBemPosse) {
		this.seqBemPosse = seqBemPosse;
	}

	/**
	 * @param bemPossePK the bemPossePK to set
	 */
	public void setBemPossePK(BemPossePK bemPossePK) {
		this.bemPossePK = bemPossePK;
	}

	/**
	 * @return the idPosseDb2
	 */
	public Integer getIdPosseDb2() {
		return idPosseDb2;
	}

	/**
	 * @param idPosseDb2 the idPosseDb2 to set
	 */
	public void setIdPosseDb2(Integer idPosseDb2) {
		this.idPosseDb2 = idPosseDb2;
	}

	/**
	 * @return the codTipoPosseTerra
	 */
	public Short getCodTipoPosseTerra() {
		return codTipoPosseTerra;
	}

	/**
	 * @param codTipoPosseTerra the codTipoPosseTerra to set
	 */
	public void setCodTipoPosseTerra(Short codTipoPosseTerra) {
		this.codTipoPosseTerra = codTipoPosseTerra;
	}

	/**
	 * @return the quantiadadeArea
	 */
	public BigDecimal getQuantiadadeArea() {
		return quantiadadeArea;
	}

	/**
	 * @param quantiadadeArea the quantiadadeArea to set
	 */
	public void setQuantiadadeArea(BigDecimal quantiadadeArea) {
		this.quantiadadeArea = quantiadadeArea;
	}

	/**
	 * @return the bemPossePK
	 */
	public BemPossePK getBemPossePK() {
		return bemPossePK;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public BemPossePK getIdSQL() {
		return getBemPossePK();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return getIdPosseDb2();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof BemPossePK){
			setBemPossePK((BemPossePK) idSQL);
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
			setIdPosseDb2((Integer) idDB2);
		}
	}

	public Integer getNumPessoaParceira() {
		return numPessoaParceira;
	}

	public void setNumPessoaParceira(Integer numPessoaParceira) {
		this.numPessoaParceira = numPessoaParceira;
	}
}