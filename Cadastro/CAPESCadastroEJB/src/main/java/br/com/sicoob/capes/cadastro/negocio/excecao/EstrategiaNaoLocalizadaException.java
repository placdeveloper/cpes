// 27/05/2013 - 10:39:09
package br.com.sicoob.capes.cadastro.negocio.excecao;

import javax.ejb.ApplicationException;

/**
 * @author Rodrigo.Chaves
 */
@ApplicationException(rollback = true)
public class EstrategiaNaoLocalizadaException extends CAPESCadastroNegocioException{

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -5190133075715655707L;

	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "msg.erro.criacao.estrategia";
	
	/**
	 * Construtor
	 * 
	 * @param tipoEstrategia
	 *            a classe (ou interface) mae da hierarquia de estrategias
	 * @param localizador
	 *            os dados utilizados para localizar a estrategia
	 */
	public EstrategiaNaoLocalizadaException(Class<?> tipoEstrategia, String localizador) {
		super(CHAVE_MSG, tipoEstrategia.getName(), localizador);
	}

}
