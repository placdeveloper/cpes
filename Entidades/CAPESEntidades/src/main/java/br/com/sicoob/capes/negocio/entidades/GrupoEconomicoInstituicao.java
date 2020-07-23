package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import br.com.bancoob.persistencia.types.DateTimeDB;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.capes.negocio.entidades.pk.GrupoEconomicoInstituicaoPK;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
@Entity
@Table(name = "GRUPOECONOMICOINSTITUICAO", schema = "CLI")
@IdClass(GrupoEconomicoInstituicaoPK.class)
public class GrupoEconomicoInstituicao extends CAPESEntidade<GrupoEconomicoInstituicaoPK> {

	private static final long serialVersionUID = 6501165214827874535L;

	@Id
	@Column(name = "IDINSTITUICAO", updatable = false, insertable = true)
	private Integer idInstituicao;

	@Id
	@Column(name = "IDGRUPOECONOMICOCENTRALIZADO", updatable = false, insertable = true)
	private Integer idGrupo;

	@Column(name = "DATAHORAINICIO", nullable = false)
	private DateTimeDB dataHoraInicio;

	@Override
	public GrupoEconomicoInstituicaoPK getId() {
		return new GrupoEconomicoInstituicaoPK(getIdInstituicao(), getIdGrupo());
	}

	@Override
	public void setId(GrupoEconomicoInstituicaoPK id) {
		setIdGrupo(id.getIdGrupo());
		setIdInstituicao(id.getIdInstituicao());
	}

	/**
	 * 
	 */
	public GrupoEconomicoInstituicao() {
	}

	/**
	 * 
	 * @param idInstituicao
	 */
	public GrupoEconomicoInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public Integer getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public DateTimeDB getDataHoraInicio() {
		return dataHoraInicio;
	}

	public void setDataHoraInicio(DateTimeDB dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "id", "idGrupo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "id", "idGrupo");
	}

}
