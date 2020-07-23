/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * @author Erico.Junior
 *
 */
@ApplicationException (rollback = true)
public abstract class BeneficiarioINSSException extends CAPESProcessamentoException {

	/** Serial UID.*/
	private static final long serialVersionUID = -4569807951714140L;
	
	/** O atributo codErro. */
	private final transient Integer codErro;
	
	/**
	 * Instancia um novo BeneficiarioINSSException.
	 *
	 * @param excecao o valor de excecao
	 * @param codErro o valor de cod erro
	 */
	public BeneficiarioINSSException(Throwable excecao, Integer codErro){
		super(excecao);
		this.codErro = codErro;
	}
	
	/**
	 * Recupera o valor de codErro.
	 *
	 * @return o valor de codErro
	 */
	public Integer getCodErro(){
		return this.codErro;
	}
}
