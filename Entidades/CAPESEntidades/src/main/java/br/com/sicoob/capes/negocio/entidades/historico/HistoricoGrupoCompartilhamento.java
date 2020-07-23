/*
 * SICOOB
 * 
 * HistoricoGrupoCompartilhamento.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoCompartilhamento)
 */
package br.com.sicoob.capes.negocio.entidades.historico;

import static javax.persistence.InheritanceType.JOINED;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.GrupoCompartilhamentoBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.vigente.GrupoCompartilhamento;

/**
 * The Class HistoricoGrupoCompartilhamento.
 */
@Entity
@Table(schema = "CLI", name = "HISTGRUPOCOMPARTILHAMENTO")
@Inheritance(strategy = JOINED)
public class HistoricoGrupoCompartilhamento extends GrupoCompartilhamentoBase implements Historico {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -1785998945099883591L;

	/** O atributo id hist grupo compartilhamento. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHISTGRUPOCOMPARTILHAMENTO")
	private Integer idHistGrupoCompartilhamento;

	/** O atributo id grupo compartilhamento. */
	@Column(name = "IDGRUPOCOMPARTILHAMENTO", nullable = false)
	private Integer idGrupoCompartilhamento;

	/** O atributo data hora final. */
	@Column(name = "DATAHORAFIM", nullable = false)
	private DateTimeDB dataHoraFinal;

	/** O atributo grupo compartilhamento. */
	@ManyToOne(optional = true)
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "IDGRUPOCOMPARTILHAMENTO", referencedColumnName = "IDGRUPOCOMPARTILHAMENTO",
			insertable = false, updatable = false)
	private GrupoCompartilhamento grupoCompartilhamento;
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;

	/**
	 * Recupera id hist grupo compartilhamento.
	 * 
	 * @return id hist grupo compartilhamento
	 */
	public Integer getIdHistGrupoCompartilhamento() {
		return idHistGrupoCompartilhamento;
	}

	/**
	 * Preenche id hist grupo compartilhamento.
	 * 
	 * @param idHistGrupoCompartilhamento
	 *            o novo id hist grupo compartilhamento
	 */
	public void setIdHistGrupoCompartilhamento(Integer idHistGrupoCompartilhamento) {
		this.idHistGrupoCompartilhamento = idHistGrupoCompartilhamento;
	}

	/**
	 * Recupera id grupo compartilhamento.
	 * 
	 * @return id grupo compartilhamento
	 */
	public Integer getIdGrupoCompartilhamento() {
		return idGrupoCompartilhamento;
	}

	/**
	 * Preenche id grupo compartilhamento.
	 * 
	 * @param idGrupoCompartilhamento
	 *            o novo id grupo compartilhamento
	 */
	public void setIdGrupoCompartilhamento(Integer idGrupoCompartilhamento) {
		this.idGrupoCompartilhamento = idGrupoCompartilhamento;
	}

	/**
	 * Recupera data hora final.
	 * 
	 * @return data hora final
	 */
	public DateTimeDB getDataHoraFinal() {
		return dataHoraFinal;
	}

	/**
	 * Preenche data hora final.
	 * 
	 * @param dataHoraFinal
	 *            o novo data hora final
	 */
	public void setDataHoraFinal(DateTimeDB dataHoraFinal) {
		this.dataHoraFinal = dataHoraFinal;
	}

	/**
	 * Recupera grupo compartilhamento.
	 * 
	 * @return grupo compartilhamento
	 */
	public GrupoCompartilhamento getGrupoCompartilhamento() {
		return grupoCompartilhamento;
	}

	/**
	 * Preenche grupo compartilhamento.
	 * 
	 * @param grupoCompartilhamento
	 *            o novo grupo compartilhamento
	 */
	public void setGrupoCompartilhamento(GrupoCompartilhamento grupoCompartilhamento) {
		this.grupoCompartilhamento = grupoCompartilhamento;
	}

	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return getIdGrupoCompartilhamento();
	}

	/** 
	 * {@inheritDoc}
	 */
	public Date getDataHoraFim() {
		return getDataHoraFinal();
	}

	/** 
	 * {@inheritDoc}
	 */
	public void setDataHoraFim(Date dataHoraFim) {
		setDataHoraFinal(new DateTimeDB(dataHoraFim.getTime()));
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdHistGrupoCompartilhamento();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdHistGrupoCompartilhamento(id);
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
	public void setIdUsuarioExclusao(String idUsuarioExclusao) {
		this.idUsuarioExclusao = idUsuarioExclusao;
	}

}
