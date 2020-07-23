package br.com.sicoob.capes.cadastro.negocio.dto;

import br.com.bancoob.negocio.dto.BancoobDto;
import br.com.sicoob.capes.negocio.entidades.vigente.ResponsavelCadastro;

/**
 * DTO utilizado para a consulta de autorizacao do cadastro. 
 * @author Juan.Damasceno
 *
 */
public class VerificaNecessidadeAutorizacaoDTO extends BancoobDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** O atributo necessarioAutorizacao. */
	private Boolean necessarioAutorizacao;
	
	/** O atributo responsavelCadastro. */
	private ResponsavelCadastro responsavelCadastro;
	
	/**
	 * @return the isNecessarioAutorizacao
	 */
	public Boolean isNecessarioAutorizacao() {
		return necessarioAutorizacao;
	}
	/**
	 * @param isNecessarioAutorizacao the isNecessarioAutorizacao to set
	 */
	public void setNecessarioAutorizacao(Boolean isNecessarioAutorizacao) {
		this.necessarioAutorizacao = isNecessarioAutorizacao;
	}
	/**
	 * @return the responsavelCadastro
	 */
	public ResponsavelCadastro getResponsavelCadastro() {
		return responsavelCadastro;
	}
	/**
	 * @param responsavelCadastro the responsavelCadastro to set
	 */
	public void setResponsavelCadastro(ResponsavelCadastro responsavelCadastro) {
		this.responsavelCadastro = responsavelCadastro;
	}
}
