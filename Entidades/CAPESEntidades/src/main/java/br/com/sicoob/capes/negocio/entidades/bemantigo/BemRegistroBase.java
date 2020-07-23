/*
 * SICOOB
 * 
 * BemRegistroBase.java(br.com.sicoob.capes.negocio.entidades.BemRegistroBase)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * Classe que representa o bem registro.
 * 
 * @author Juan.Damasceno
 *
 */
@MappedSuperclass
public abstract class BemRegistroBase extends CAPESEntidade<Integer> implements Ibem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** O atributo bem. */
	@ManyToOne(optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="IDBEMPESSOA")
	private Bem bem;
	
	/** O atributo data hora inicio. */
	private DateTimeDB dataHoraInicio;
	
	/** O atributo matricula registro. */
	@Column(name="DESCMATRICULAREGISTRO", length = 80)
	private String matriculaRegistro;
	
	/** O atributo livro. */
	@Column(name="DESCLIVRO", length = 40)
	private String livro;
	
	/** O atributo folha. */
	@Column(name="DESCFOLHA", length = 40)
	private String folha;
	
	/** O atributo ipr. */
	@Column(name="DESCIPR", length = 18)
	private String ipr;
	
	/** O atributo incra. */
	@Column(name="DESCINCRA", length = 20)
	private String incra; 
	
	/** O atributo area matricula. */
	@Column(name="QTDAREAMATRICULA", precision = 13, scale = 6)
	private BigDecimal areaMatricula;
	
	/** O atributo observacao matricula. */
	@Column(name="DESCOBSMATRICULA", length = 1000)
	private String observacaoMatricula;
	
	/** O atributo data ultima matricula. */
	@Column(name="DATAULTATUMATRICULA")
	private DateTimeDB dataUltimaMatricula;

	/**
	 * @return the bem
	 */
	public Bem getBem() {
		return bem;
	}

	/**
	 * @param bem the bem to set
	 */
	public void setBem(Bem bem) {
		this.bem = bem;
	}

	/**
	 * @return the dataHoraInicio
	 */
	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	/**
	 * @param dataHoraInicio the dataHoraInicio to set
	 */
	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
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
	 * @return the dataUltimaMatricula
	 */
	public DateTimeDB getDataUltimaMatricula() {
		return dataUltimaMatricula;
	}

	/**
	 * @param dataUltimaMatricula the dataUltimaMatricula to set
	 */
	public void setDataUltimaMatricula(DateTimeDB ultimaMatricula) {
		this.dataUltimaMatricula = ultimaMatricula;
	}
}