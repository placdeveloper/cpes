/*
 * SICOOB
 * 
 * TipoFormaConstituicaoEsferaAdministrativaPK.java(br.com.sicoob.capes.negocio.entidades.pk.TipoFormaConstituicaoEsferaAdministrativaPK)
 */
package br.com.sicoob.capes.negocio.entidades.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * The Class TipoFormaConstituicaoEsferaAdministrativaPK.
 */
@Embeddable
public class TipoFormaConstituicaoEsferaAdministrativaPK extends BancoobChavePrimaria {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 3294567306234269154L;

	/** O atributo codigo esfera administrativa. */
	@Column(name = "CODESFERAADMINISTRATIVA")
	private Short codigoEsferaAdministrativa;

	/** O atributo codigo tipo forma constituicao. */
	@Column(name = "CODTIPOFORMACONSTITUICAO")
	private Short codigoTipoFormaConstituicao;

	/**
	 * Recupera codigo esfera administrativa.
	 * 
	 * @return codigo esfera administrativa
	 */
	public Short getCodigoEsferaAdministrativa() {
		return codigoEsferaAdministrativa;
	}

	/**
	 * Preenche codigo esfera administrativa.
	 * 
	 * @param codigoEsferaAdministrativa
	 *            o novo codigo esfera administrativa
	 */
	public void setCodigoEsferaAdministrativa(Short codigoEsferaAdministrativa) {
		this.codigoEsferaAdministrativa = codigoEsferaAdministrativa;
	}

	/**
	 * Recupera codigo tipo forma constituicao.
	 * 
	 * @return codigo tipo forma constituicao
	 */
	public Short getCodigoTipoFormaConstituicao() {
		return codigoTipoFormaConstituicao;
	}

	/**
	 * Preenche codigo tipo forma constituicao.
	 * 
	 * @param codigoTipoFormaConstituicao
	 *            o novo codigo tipo forma constituicao
	 */
	public void setCodigoTipoFormaConstituicao(Short codigoTipoFormaConstituicao) {
		this.codigoTipoFormaConstituicao = codigoTipoFormaConstituicao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codigoEsferaAdministrativa", "codigoTipoFormaConstituicao");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "codigoEsferaAdministrativa", "codigoTipoFormaConstituicao");
	}

}