package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * A alteração cadastral é permitido somente quando a instituição for a responsável pelo cadastro.
 * @author Juan.Damasceno
 *
 */
@ApplicationException (rollback = true)
public class InstituicaoNaoResponsavelPeloCadastroException extends CAPESCadastroNegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** A constante MN023. */
	private static final String MN023 = "MN023";

	/**
	 * Instancia um novo InstituicaoNaoResponsavelPeloCadastroException.
	 */
	public InstituicaoNaoResponsavelPeloCadastroException() {
		super(MN023);
	}
}