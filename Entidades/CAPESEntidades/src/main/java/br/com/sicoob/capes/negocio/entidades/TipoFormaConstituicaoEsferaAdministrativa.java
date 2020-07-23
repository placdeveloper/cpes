/*
 * SICOOB
 * 
 * TipoFormaConstituicaoEsferaAdministrativa.java(br.com.sicoob.capes.negocio.entidades.TipoFormaConstituicaoEsferaAdministrativa)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.pk.TipoFormaConstituicaoEsferaAdministrativaPK;

/**
 * The Class TipoFormaConstituicaoEsferaAdministrativa.
 */
@Entity
@Table(name = "TIPOFORMACONSTITUICAOESFERAADM", schema = "CLI")
public class TipoFormaConstituicaoEsferaAdministrativa extends CAPESEntidade<TipoFormaConstituicaoEsferaAdministrativaPK> {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = -4506096274029010741L;

	/** O atributo id. */
	@EmbeddedId
	private TipoFormaConstituicaoEsferaAdministrativaPK id;

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public TipoFormaConstituicaoEsferaAdministrativaPK getId() {
		return id;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setId(TipoFormaConstituicaoEsferaAdministrativaPK id) {
		this.id = id;
	}

	/** O atributo tipo forma constituicao. */
	@JoinColumn(name = "CODTIPOFORMACONSTITUICAO", referencedColumnName = "CODTIPOFORMACONSTITUICAO", insertable = false, updatable = false)
	@ManyToOne
	private TipoFormaConstituicao tipoFormaConstituicao;

	/**
	 * Recupera tipo forma constituicao.
	 * 
	 * @return tipo forma constituicao
	 */
	public TipoFormaConstituicao getTipoFormaConstituicao() {
		return tipoFormaConstituicao;
	}

	/**
	 * Preenche tipo forma constituicao.
	 * 
	 * @param tipoFormaConstituicao
	 *            o novo tipo forma constituicao
	 */
	public void setTipoFormaConstituicao(TipoFormaConstituicao tipoFormaConstituicao) {
		this.tipoFormaConstituicao = tipoFormaConstituicao;
	}

}