// 30/04/2013 - 16:00:32
package br.com.sicoob.capes.replicacao.negocio.excecao;


/**
 * @author Rodrigo.Chaves
 */
public class ParametroObrigatorioException extends CAPESReplicacaoComumRuntimeException {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 7823028886626806082L;
	
	/** A constante CHAVE_MSG. */
	private static final String CHAVE_MSG = "msg.validacao.parametro.obrigatorio";

	/**
	 * Construtor
	 * 
	 * @param excecao
	 */
	public ParametroObrigatorioException(Exception excecao) {
		super(CHAVE_MSG, excecao);
	}

	/**
	 * Construtor
	 * 
	 * @param parametro
	 * @param excecao
	 */
	public ParametroObrigatorioException(String parametro, Exception excecao) {
		super(CHAVE_MSG, new Object[] { parametro }, excecao);
	}

	/**
	 * Construtor
	 * 
	 * @param parametro
	 */
	public ParametroObrigatorioException(String parametro) {
		super(CHAVE_MSG, new Object[] { parametro });
	}

}
