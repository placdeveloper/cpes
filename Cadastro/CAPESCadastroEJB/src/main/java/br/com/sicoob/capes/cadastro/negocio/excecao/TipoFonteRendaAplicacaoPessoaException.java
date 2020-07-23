/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * Exce��o utilizada para o tipo de fonte de renda aplicada ao tipo de pessoa inv�lido.
 * @author Erico.Junior
 */
@ApplicationException (rollback = true)
public class TipoFonteRendaAplicacaoPessoaException extends CAPESCadastroNegocioException {

	/** Serial UID.*/
	private static final long serialVersionUID = -1207478626868595441L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "MN019";
	
	/**
	 * Construtor da exce��o.
	 */
	public TipoFonteRendaAplicacaoPessoaException() {
		super(CHAVE_MSG);
	}

}
