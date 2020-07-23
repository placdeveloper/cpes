/*
 * SICOOB
 * 
 * PerfilTarifarioPK.java(br.com.sicoob.capes.negocio.entidades.pk.PerfilTarifarioPK)
 */
package br.com.sicoob.capes.negocio.entidades.pk;

import javax.persistence.Embeddable;

import br.com.bancoob.negocio.entidades.BancoobChavePrimaria;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * The Class PerfilTarifarioPK.
 */
@Embeddable
public class PerfilTarifarioPK extends BancoobChavePrimaria {
	
	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** O atributo cod perfil tarifario. */
	private Integer codPerfilTarifario;
	
	/** O atributo id instituicao. */
	private Integer idInstituicao;

	/**
	 * Recupera cod perfil tarifario.
	 * 
	 * @return cod perfil tarifario
	 */
	public Integer getCodPerfilTarifario() {
		return codPerfilTarifario;
	}

	/**
	 * Preenche cod perfil tarifario.
	 * 
	 * @param codPerfilTarifario
	 *            o novo cod perfil tarifario
	 */
	public void setCodPerfilTarifario(Integer codPerfilTarifario) {
		this.codPerfilTarifario = codPerfilTarifario;
	}

	/**
	 * Recupera id instituicao.
	 * 
	 * @return id instituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	/**
	 * Preenche id instituicao.
	 * 
	 * @param idInstituicao
	 *            o novo id instituicao
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "codPerfilTarifario", "idInstituicao");
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "codPerfilTarifario", "idInstituicao");
	}
	
	
}
