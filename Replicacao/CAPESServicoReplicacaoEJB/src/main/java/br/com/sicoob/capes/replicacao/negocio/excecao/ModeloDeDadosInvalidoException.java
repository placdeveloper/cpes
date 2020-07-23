package br.com.sicoob.capes.replicacao.negocio.excecao;

import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * Exceção que será lançada quando houver algum comando/consulta SQL que possua algum objeto do 
 * banco de dados inválido.
 * @author Juan.Damasceno
 *
 */
public class ModeloDeDadosInvalidoException extends PersistenciaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Instancia um novo ModeloDeDadosInvalidoException.
	 *
	 * @param excecao o valor de excecao
	 */
	public ModeloDeDadosInvalidoException(Exception excecao) {
		super(excecao);
	}
}
