/*
 * SICOOB
 * 
 * HistoricoGrupoEconomicoPessoa.java(br.com.sicoob.capes.negocio.entidades.historico.HistoricoGrupoEconomicoPessoa)
 */
package br.com.sicoob.capes.negocio.entidades.historico;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoPessoaBase;
import br.com.sicoob.capes.negocio.entidades.interfaces.Historico;
import br.com.sicoob.capes.negocio.entidades.interfaces.Replicavel;
import br.com.sicoob.capes.negocio.entidades.listener.ReplicacaoListener;

/**
 * The Class HistoricoGrupoEconomicoPessoa.
 */
@Entity
@Table(schema = "CLI", name = "HISTGRUPOECONOMICOPESSOA")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(ReplicacaoListener.class)
public class HistoricoGrupoEconomicoPessoa extends GrupoEconomicoPessoaBase implements
		Historico, Replicavel {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1843756014286754984L;

	/** O atributo id hist grupo economico pessoa. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDHISTGRUPOECONOMICOPESSOA")
	private Integer idHistGrupoEconomicoPessoa;

	/** O atributo data hora fim. */
	@Column(name = "DATAHORAFIM", nullable = false)
	private DateTimeDB dataHoraFim = new DateTimeDB();
	
	/** O atributo id usuario exclusao. */
	@Column(name = "IDUSUARIOEXCL", length = 40)
	private String idUsuarioExclusao;

	/**
	 * Recupera id hist grupo economico pessoa.
	 * 
	 * @return id hist grupo economico pessoa
	 */
	public Integer getIdHistGrupoEconomicoPessoa() {
		return idHistGrupoEconomicoPessoa;
	}

	/**
	 * Preenche id hist grupo economico pessoa.
	 * 
	 * @param idGrupoEconomicoPessoa
	 *            o novo id hist grupo economico pessoa
	 */
	public void setIdHistGrupoEconomicoPessoa(Integer idGrupoEconomicoPessoa) {
		this.idHistGrupoEconomicoPessoa = idGrupoEconomicoPessoa;
	}

	/**
	 * @return the dataHoraFim
	 */
	public Date getDataHoraFim() {
		return new Date(dataHoraFim.getTime());
	}

	/**
	 * @param dataHoraFim
	 *            the dataHoraFim to set
	 */
	public void setDataHoraFim(Date dataHoraFim) {
		if (dataHoraFim != null) {
			this.dataHoraFim = new DateTimeDB(dataHoraFim.getTime());
		}
	}

	/** 
	 * {@inheritDoc}
	 */
	public Serializable getIdEntidadeVigente() {
		return getIdHistGrupoEconomicoPessoa();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Integer getId() {
		return getIdHistGrupoEconomicoPessoa();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(Integer id) {
		setIdHistGrupoEconomicoPessoa(id);
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
