/*
 * SICOOB
 * 
 * BemOnus.java(br.com.sicoob.capes.negocio.entidades.legado.BemOnus)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.legado.pk.BemOnusPK;

/**
 * Classe que representa o bem onus
 * @author Juan.Damasceno
 *
 */
@Entity
@Table(name="BEMPESSOAONUS")
public class BemOnus extends EntidadeReplicavel<BemOnusPK>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** O atributo bem onus pk. */
	@EmbeddedId
	private BemOnusPK bemOnusPK;
	
	/** O atributo id onus db2. */
	@Column(name="IDBEMPESSOAONUSDB2")
	private Integer idOnusDb2;
	
	/** O atributo numero grau. */
	@Column(name="NUMGRAU")
	private Integer numeroGrau;
	
	/** O atributo descricao. */
	@Column(name="DESCONUS")
	private String descricao;
	
	/** O atributo nome instituicao credora. */
	@Column(name="DescNomeInstituicaoCredOnus")
	private String nomeInstituicaoCredora;
	
	/** O atributo valor. */
	@Column(name="VALORONUSBEM")
	private BigDecimal valor;
	
	/** O atributo data prevista liberacao. */
	@Column(name="DataPrevLiberacaoOnus") 
	private Date dataPrevistaLiberacao;
	
	/** O atributo seq bem onus. */
	@Column(name="CODSEQonus", updatable=false, insertable=false)
	private Integer seqBemOnus; 
	
	/**
	 * @return the numeroGrau
	 */
	public Integer getNumeroGrau() {
		return numeroGrau;
	}

	/**
	 * @param numeroGrau the numeroGrau to set
	 */
	public void setNumeroGrau(Integer numeroGrau) {
		this.numeroGrau = numeroGrau;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the nomeInstituicaoCredora
	 */
	public String getNomeInstituicaoCredora() {
		return nomeInstituicaoCredora;
	}

	/**
	 * @param nomeInstituicaoCredora the nomeInstituicaoCredora to set
	 */
	public void setNomeInstituicaoCredora(String nomeInstituicaoCredora) {
		this.nomeInstituicaoCredora = nomeInstituicaoCredora;
	}

	/**
	 * @return the valor
	 */
	public BigDecimal getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/**
	 * @return the dataPrevistaLiberacao
	 */
	public Date getDataPrevistaLiberacao() {
		return dataPrevistaLiberacao;
	}

	/**
	 * @param dataPrevistaLiberacao the dataPrevistaLiberacao to set
	 */
	public void setDataPrevistaLiberacao(Date dataPrevistaLiberacao) {
		this.dataPrevistaLiberacao = dataPrevistaLiberacao;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public BemOnusPK getIdSQL() {
		return getBemOnusPK();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return getIdOnusDb2();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof BemOnusPK){
			setBemOnusPK((BemOnusPK) idSQL);
		}
	}

	/**
	 * @return the bemOnusPK
	 */
	public BemOnusPK getBemOnusPK() {
		return bemOnusPK;
	}

	/**
	 * @param bemOnusPK the bemOnusPK to set
	 */
	public void setBemOnusPK(BemOnusPK bemOnusPK) {
		this.bemOnusPK = bemOnusPK;
	}

	/**
	 * @return the idOnusDb2
	 */
	public Integer getIdOnusDb2() {
		return idOnusDb2;
	}

	/**
	 * @param idOnusDb2 the idOnusDb2 to set
	 */
	public void setIdOnusDb2(Integer idOnusDb2) {
		this.idOnusDb2 = idOnusDb2;
	}

	/**
	 * @return the seqBemOnus
	 */
	public Integer getSeqBemOnus() {
		return seqBemOnus;
	}

	/**
	 * @param seqBemOnus the seqBemOnus to set
	 */
	public void setSeqBemOnus(Integer seqBemOnus) {
		this.seqBemOnus = seqBemOnus;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Integer){
			setIdOnusDb2((Integer) idDB2);
		}
	}
}