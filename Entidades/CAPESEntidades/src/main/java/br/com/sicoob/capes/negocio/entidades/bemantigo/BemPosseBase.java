/*
 * SICOOB
 * 
 * BemPosseBase.java(br.com.sicoob.capes.negocio.entidades.BemPosseBase)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;


/**
 * Classe que representa BemPosse
 * 
 * @author Juan.Damasceno
 *
 */
@MappedSuperclass
public abstract class BemPosseBase extends CAPESEntidade<Integer> implements Ibem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo bem. */
	@ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="IDBEMPESSOA")
	private Bem bem;
	
	/** O atributo data hora inicio. */
	private DateTimeDB dataHoraInicio;
	
	/** O atributo tipo posse bem. */
	@ManyToOne
	@JoinColumn(name="CODTIPOPOSSEBEM")
	private TipoPosseBem tipoPosseBem;
	
	/** O atributo area. */
	@Column(name="QTDAREAPOSSE", precision = 13, scale = 6)
	private BigDecimal area;
	
	/**
	 * Campo criado para compatibilização do bem novo.
	 */
	@Column(name = "IDPESSOACOMPARTILHAMENTOPARCEIRO")
	private Long idPessoaCompartilhamentoParceiro;

	/** O atributo pessoa compartilhamento. */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDPESSOACOMPARTILHAMENTOPARCEIRO", insertable = false, updatable = false)
	private PessoaCompartilhamento pessoaCompartilhamentoParceiro;

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
	public void setDataHoraInicio(DateTimeDB dateHoraInicio) {
		this.dataHoraInicio = dateHoraInicio;
	}

	/**
	 * @return the tipoPosseBem
	 */
	public TipoPosseBem getTipoPosseBem() {
		return tipoPosseBem;
	}

	/**
	 * @param tipoPosseBem the tipoPosseBem to set
	 */
	public void setTipoPosseBem(TipoPosseBem tipoPosseBem) {
		this.tipoPosseBem = tipoPosseBem;
	}

	/**
	 * @return the area
	 */
	public BigDecimal getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public Long getIdPessoaCompartilhamentoParceiro() {
		return idPessoaCompartilhamentoParceiro;
	}

	public void setIdPessoaCompartilhamentoParceiro(Long idPessoaCompartilhamentoParceiro) {
		this.idPessoaCompartilhamentoParceiro = idPessoaCompartilhamentoParceiro;
	}

	public PessoaCompartilhamento getPessoaCompartilhamentoParceiro() {
		return pessoaCompartilhamentoParceiro;
	}

	public void setPessoaCompartilhamentoParceiro(PessoaCompartilhamento pessoaCompartilhamentoParceiro) {
		this.pessoaCompartilhamentoParceiro = pessoaCompartilhamentoParceiro;
	}

}
