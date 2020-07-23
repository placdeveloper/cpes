package br.com.sicoob.capes.negocio.entidades.pk;

import java.io.Serializable;

import br.com.sicoob.capes.comum.util.ReflexaoUtils;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
public class GrupoEconomicoInstituicaoPK implements Serializable {

	private static final long serialVersionUID = -5264923653752209221L;

	private Integer idInstituicao;

	private Integer idGrupo;

	/**
	 * 
	 */
	public GrupoEconomicoInstituicaoPK() {
	}

	/**
	 * 
	 * @param idInstituicao
	 */
	public GrupoEconomicoInstituicaoPK(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	
	/**
	 * 
	 * @param idInstituicao
	 * @param idGrupo
	 */
	public GrupoEconomicoInstituicaoPK(Integer idInstituicao, Integer idGrupo) {
		this.idInstituicao = idInstituicao;
		this.idGrupo = idGrupo;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return ReflexaoUtils.hashCode(this, "idInstituicao", "idGrupo");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		return ReflexaoUtils.equals(this, obj, "idInstituicao", "idGrupo");
	}

}