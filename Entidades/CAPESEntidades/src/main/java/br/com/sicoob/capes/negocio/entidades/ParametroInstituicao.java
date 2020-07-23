/*
 * SICOOB
 * 
 * TipoEmail.java(br.com.sicoob.capes.negocio.entidades.TipoEmail)
 */
package br.com.sicoob.capes.negocio.entidades;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.sicoob.capes.negocio.entidades.pk.ParametroInstituicaoPK;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
@Entity
@Table(name = "PARAMETROINSTITUICAO", schema = "CLI")
public class ParametroInstituicao extends CAPESEntidade<ParametroInstituicaoPK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2638063056102908721L;

	/** O atributo pk. */
	@EmbeddedId
	private ParametroInstituicaoPK pk;

	/** O atributo parametro. */
	@ManyToOne
	@JoinColumn(name = "CODPARAMETRO", insertable = false, updatable = false)
	private Parametro parametro;

	/**
	 * Recupera o valor de pk.
	 *
	 * @return o valor de pk
	 */
	public ParametroInstituicaoPK getPk() {
		return pk;
	}

	/**
	 * Define o valor de pk.
	 *
	 * @param pk
	 *            o novo valor de pk
	 */
	public void setPk(ParametroInstituicaoPK pk) {
		this.pk = pk;
	}

	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ParametroInstituicaoPK getId() {
		return pk;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(ParametroInstituicaoPK id) {
		this.pk = id;
	}

}
