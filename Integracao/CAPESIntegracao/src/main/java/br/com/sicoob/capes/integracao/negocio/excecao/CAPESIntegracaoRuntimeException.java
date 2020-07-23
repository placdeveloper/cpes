package br.com.sicoob.capes.integracao.negocio.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.excecao.BancoobRuntimeException;

/**
 * @author Rodrigo.Chaves
 */
@ApplicationException(rollback = true)
public class CAPESIntegracaoRuntimeException extends BancoobRuntimeException {

	/** A Constante serialVersionUID. */
	private static final long serialVersionUID = 6310350594344867283L;

	/**
	 * Construtor
	 * 
	 * @param excecao
	 */
	public CAPESIntegracaoRuntimeException(Exception excecao, String sistema) {
		super("msg.erro.integracao", new String[] { sistema, excecao.getMessage() }, excecao);
	}

	/**
	 * Construtor
	 * 
	 * @param excecao
	 * @param mensagem
	 */
	public CAPESIntegracaoRuntimeException(Exception excecao, String sistema, String mensagem) {
		super("msg.erro.integracao", new String[] { sistema, mensagem + " (" + excecao.getMessage() + ")" }, excecao);
	}

	/**
	 * Construtor
	 * 
	 * @param chave
	 */
	public CAPESIntegracaoRuntimeException(String sistema, String mensagem) {
		super(recuperarMensagem("msg.erro.integracao", new Object[] { sistema, mensagem }));
	}

}