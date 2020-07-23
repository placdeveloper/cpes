/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.dto;

import java.io.Serializable;

import br.com.sicoob.capes.negocio.entidades.Instituicao;

/**
 * VO utilizado no caso de uso manter relacionamento instituição.
 * 
 * @author Erico.Junior
 */
public class RelacionamentoInstituicaoDTO implements Serializable {

	/** Serial UID. */
	private static final long serialVersionUID = -4987306828897304571L;

	/** O atributo responsavelCadastro. */
	private Boolean responsavelCadastro;

	/** O atributo instituicao. */
	private Instituicao instituicao;
	
	/**
	 * @return the responsavelCadastro
	 */
	public Boolean getResponsavelCadastro() {
		return responsavelCadastro;
	}

	/**
	 * @param responsavelCadastro
	 *            the responsavelCadastro to set
	 */
	public void setResponsavelCadastro(Boolean responsavelCadastro) {
		this.responsavelCadastro = responsavelCadastro;
	}

	/**
	 * @return the instituicao
	 */
	public Instituicao getInstituicao() {
		return instituicao;
	}

	/**
	 * @param instituicao
	 *            the instituicao to set
	 */
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
}
