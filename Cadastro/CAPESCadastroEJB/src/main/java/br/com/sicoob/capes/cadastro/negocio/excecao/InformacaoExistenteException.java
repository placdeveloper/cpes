package br.com.sicoob.capes.cadastro.negocio.excecao;

/**
 * A Classe InformacaoExistenteException.
 */
public class InformacaoExistenteException extends CAPESCadastroNegocioException {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -709751838490028934L;

	/** A constante CHAVE_MENSAGEM. */
	private static final String CHAVE_MENSAGEM = "MN186";

	/**
	 * Instancia um novo InformacaoExistenteException.
	 */
	public InformacaoExistenteException() {
		super(CHAVE_MENSAGEM);
	}
}