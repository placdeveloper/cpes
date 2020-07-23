package br.com.sicoob.capes.replicacao.negocio.excecao;

import br.com.bancoob.negocio.excecao.NegocioException;

/**
 * A Classe ExclusaoNucleoAssociadoException.
 */
public class ExclusaoNucleoAssociadoException extends NegocioException {
	private static final long serialVersionUID = -1796359037815806641L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN001";

	/**
	 * Instancia um novo ExclusaoNucleoAssociadoException.
	 */
	public ExclusaoNucleoAssociadoException() {
		super(CHAVE_MSG);
	}
	
	public ExclusaoNucleoAssociadoException(Throwable e) {
		super(CHAVE_MSG, e);
	}

}