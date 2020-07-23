/*
 * SICOOB
 * 
 * BemRegistro.java(br.com.sicoob.capes.negocio.entidades.legado.BemRegistro)
 */
package br.com.sicoob.capes.negocio.entidades.legado;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.legado.pk.BemRegistroPK;

/**
 * Classe que representa o bem registro
 * @author Juan.Damasceno
 *
 */
@Entity
@Table(name="BEMPESSOAREGISTRO")
public class BemRegistro extends EntidadeReplicavel<BemRegistroPK>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo bem registro pk. */
	@EmbeddedId
	private BemRegistroPK bemRegistroPK;
	
	/** O atributo id registro db2. */
	@Column(name="IDBEMPESSOAREGISTRODB2")
	private Integer idRegistroDb2;
	
	/** O atributo matricula registro. */
	@Column(name="DESCMATRICULAREGISTRO")
	private String matriculaRegistro;
	
	/** O atributo livro. */
	@Column(name="DESCLIVRO")
	private String livro;
	
	/** O atributo folha. */
	@Column(name="DESCFOLHA")
	private String folha;
	
	/** O atributo ipr. */
	@Column(name="DESCIPR")
	private String ipr;
	
	/** O atributo incra. */
	@Column(name="DESCINCRA")
	private String incra;
	
	/** O atributo area matricula. */
	@Column(name="QTDAREAMATRICULA")
	private BigDecimal areaMatricula;
	
	/** O atributo data ultima matricula. */
	@Column(name="DATAULTATUMATRICULA")
	private Date dataUltimaMatricula;
	
	/** O atributo observacao matricula. */
	@Column(name="DESCOBSMATRICULA")
	private String observacaoMatricula;

	/** O atributo seq bem registro. */
	@Column(name="CODSEQREGISTRO", updatable=false, insertable=false)
	private Short seqBemRegistro; 
	
	/**
	 * @return the seqBemRegistro
	 */
	public Short getSeqBemRegistro() {
		return seqBemRegistro;
	}

	/**
	 * @param seqBemRegistro the seqBemRegistro to set
	 */
	public void setSeqBemRegistro(Short seqBemRegistro) {
		this.seqBemRegistro = seqBemRegistro;
	}

	/**
	 * @return the idRegistroDb2
	 */
	public Integer getIdRegistroDb2() {
		return idRegistroDb2;
	}

	/**
	 * @param idRegistroDb2 the idRegistroDb2 to set
	 */
	public void setIdRegistroDb2(Integer idRegistroDb2) {
		this.idRegistroDb2 = idRegistroDb2;
	}

	/**
	 * @return the matriculaRegistro
	 */
	public String getMatriculaRegistro() {
		return matriculaRegistro;
	}

	/**
	 * @param matriculaRegistro the matriculaRegistro to set
	 */
	public void setMatriculaRegistro(String matriculaRegistro) {
		this.matriculaRegistro = matriculaRegistro;
	}

	/**
	 * @return the livro
	 */
	public String getLivro() {
		return livro;
	}

	/**
	 * @param livro the livro to set
	 */
	public void setLivro(String livro) {
		this.livro = livro;
	}

	/**
	 * @return the folha
	 */
	public String getFolha() {
		return folha;
	}

	/**
	 * @param folha the folha to set
	 */
	public void setFolha(String folha) {
		this.folha = folha;
	}

	/**
	 * @return the ipr
	 */
	public String getIpr() {
		return ipr;
	}

	/**
	 * @param ipr the ipr to set
	 */
	public void setIpr(String ipr) {
		this.ipr = ipr;
	}

	/**
	 * @return the incra
	 */
	public String getIncra() {
		return incra;
	}

	/**
	 * @param incra the incra to set
	 */
	public void setIncra(String incra) {
		this.incra = incra;
	}

	/**
	 * @return the areaMatricula
	 */
	public BigDecimal getAreaMatricula() {
		return areaMatricula;
	}

	/**
	 * @param areaMatricula the areaMatricula to set
	 */
	public void setAreaMatricula(BigDecimal areaMatricula) {
		this.areaMatricula = areaMatricula;
	}


	/**
	 * @return the dataUltimaMatricula
	 */
	public Date getDataUltimaMatricula() {
		return dataUltimaMatricula;
	}

	/**
	 * @param dataUltimaMatricula the dataUltimaMatricula to set
	 */
	public void setDataUltimaMatricula(Date dataUltimaMatricula) {
		this.dataUltimaMatricula = dataUltimaMatricula;
	}

	/**
	 * @return the observacaoMatricula
	 */
	public String getObservacaoMatricula() {
		return observacaoMatricula;
	}

	/**
	 * @param observacaoMatricula the observacaoMatricula to set
	 */
	public void setObservacaoMatricula(String observacaoMatricula) {
		this.observacaoMatricula = observacaoMatricula;
	}

	/**
	 * @return the bemRegistroPK
	 */
	public BemRegistroPK getBemRegistroPK() {
		return bemRegistroPK;
	}

	/**
	 * @param bemRegistroPK the bemRegistroPK to set
	 */
	public void setBemRegistroPK(BemRegistroPK bemRegistroPK) {
		this.bemRegistroPK = bemRegistroPK;
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdDB2() {
		return getIdRegistroDb2();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdSQL(Serializable idSQL) {
		if(idSQL instanceof BemRegistroPK){
			bemRegistroPK = (BemRegistroPK) idSQL;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public BemRegistroPK getIdSQL() {
		return getBemRegistroPK();
	}

	/**
	 * {@inheritDoc}
	 */
	/** 
	 * {@inheritDoc}
	 */
	public void setIdDB2(Serializable idDB2) {
		if(idDB2 instanceof Integer){
			setIdRegistroDb2((Integer) idDB2);
		}
	}	
}