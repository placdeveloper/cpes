/* 
 * Sicoob
 * ViolacaoUKException.java 
 * Criado em: 10/10/2011
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;


/**
 * Exceção a ser lançada na validação negocial de regras de negócio relacionadas à uma UK (unique
 * key)
 * 
 * 10/10/2011
 * 
 * @author rodrigo.chaves
 */
public class ViolacaoUKException extends CAPESCadastroNegocioException {

	/** A constante CHAVE_MENSAGEM_PADRAO. */
	private static final String CHAVE_MENSAGEM_PADRAO = "MN125";
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 1451191018018493184L;

	/**
	 * Instancia um novo ViolacaoUKException.
	 *
	 * @param mensagem o valor de mensagem
	 * @param parametros o valor de parametros
	 */
	public ViolacaoUKException(String mensagem, Object... parametros) {
		super(mensagem, parametros);
	}

	/**
	 * Instancia um novo ViolacaoUKException.
	 */
	public ViolacaoUKException() {
		super(CHAVE_MENSAGEM_PADRAO);
	}
}
