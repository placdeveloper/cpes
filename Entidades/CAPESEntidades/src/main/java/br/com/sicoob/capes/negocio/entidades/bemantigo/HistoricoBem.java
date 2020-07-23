/*
 * SICOOB
 * 
 * HistoricoBem.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoBem)
 */
package br.com.sicoob.capes.negocio.entidades.bemantigo;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;

import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;

/**
 * The Class HistoricoBem.
 */
@Entity(name = "HISTBEMPESSOAANTIGO")
@Table(name="HISTBEMPESSOA", schema="CLI")
@Inheritance(strategy=InheritanceType.JOINED)
@Filters({ @Filter(name = "periodoHistorico"), @Filter(name = "periodoHistoricoAntiga"),@Filter(name = "periodoHistoricoDatasIguais") })
public class HistoricoBem extends BemBase implements Historico{

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -4142584736415120100L;

	/** O atributo id historico. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IDHISTBEMPESSOA")
	private Long idHistorico;
	
	/** O atributo id bem pessoa. */
	private Long idBemPessoa;
	
	/** O atributo data hora fim. */
	@Column(name="DATAHORAFIM")
	private Date dataHoraFim;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {
		return getIdHistorico();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Long id) {
	}

	/**
	 * Recupera id historico.
	 * 
	 * @return id historico
	 */
	public Long getIdHistorico() {
		return idHistorico;
	}

	/**
	 * Preenche id historico.
	 * 
	 * @param idHistorico
	 *            o novo id historico
	 */
	public void setIdHistorico(Long idHistorico) {
		this.idHistorico = idHistorico;
	}

	/**
	 * @return the idBemPessoa
	 */
	public Long getIdBemPessoa() {
		return idBemPessoa;
	}

	/**
	 * @param idBemPessoa the idBemPessoa to set
	 */
	public void setIdBemPessoa(Long idBemPessoa) {
		this.idBemPessoa = idBemPessoa;
	}

	/**
	 * @return the dataHoraFim
	 */
	public Date getDataHoraFim() {
		return dataHoraFim;
	}

	/**
	 * @param dataHoraFim the dataHoraFim to set
	 */
	public void setDataHoraFim(Date dataHoraFim) {
		this.dataHoraFim = dataHoraFim;
	}
	

	/** 
	 * {@inheritDoc}
	 */
	public Number getIdEntidadeVigente() {
		return getIdBemPessoa();
	}
	
	/**
	 * Recupera bens onus.
	 * 
	 * @return bens onus
	 */
	public Set<HistoricoBemOnus> getBensOnus() {
		return Collections.emptySet();
	}

	/**
	 * Recupera bens posse.
	 * 
	 * @return bens posse
	 */
	public Set<HistoricoBemPosse> getBensPosse() {
		return Collections.emptySet();
	}

	/**
	 * Recupera bens registro.
	 * 
	 * @return bens registro
	 */
	public Set<HistoricoBemRegistro> getBensRegistro() {
		return Collections.emptySet();
	}
	
	/**
	 * Recupera id usuario exclusao.
	 * 
	 * @return id usuario exclusao
	 */
	public String getIdUsuarioExclusao() {
		return idUsuarioExclusao;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public void setIdUsuarioExclusao(String idUsuario) {
		this.idUsuarioExclusao = idUsuario;
	}
}
