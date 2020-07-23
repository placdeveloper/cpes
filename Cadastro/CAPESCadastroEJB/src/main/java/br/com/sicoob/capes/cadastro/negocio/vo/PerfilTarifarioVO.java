/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.vo;

import br.com.bancoob.negocio.vo.BancoobVo;


/**
 * @author Erico.Junior
 *
 */
public class PerfilTarifarioVO extends BancoobVo {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -1557986278255905042L;
	
	/** O atributo codPerfilTarifario. */
	private Integer codPerfilTarifario;
	
	/** O atributo idInstituicao. */
	private Integer idInstituicao;
	
	/** O atributo sigla. */
	private String sigla;
	
	/** O atributo descricao. */
	private String descricao;
	/**
	 * @return the codPerfilTarifario
	 */
	public Integer getCodPerfilTarifario() {
		return codPerfilTarifario;
	}
	/**
	 * @param codPerfilTarifario the codPerfilTarifario to set
	 */
	public void setCodPerfilTarifario(Integer codPerfilTarifario) {
		this.codPerfilTarifario = codPerfilTarifario;
	}
	/**
	 * @return the idInstituicao
	 */
	public Integer getIdInstituicao() {
		return idInstituicao;
	}
	/**
	 * @param idInstituicao the idInstituicao to set
	 */
	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}
	/**
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}
	/**
	 * @param sigla the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
