/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.excecao;

import javax.ejb.ApplicationException;

import br.com.bancoob.persistencia.excecao.PersistenciaException;

/**
 * Exce��o utilizada para problemas na persist�ncia da replica��o do cadastro.
 * 
 * @author Erico.Junior
 */
@ApplicationException(rollback = true)
public class CAPESReplicacaoComumPersistenciaException extends PersistenciaException {

	/** Serial UID. */
	private static final long serialVersionUID = 7648891969414098122L;

	/**
	 * Instancia um novo CAPESReplicacaoComumPersistenciaException.
	 *
	 * @param excecao o valor de excecao
	 */
	public CAPESReplicacaoComumPersistenciaException(Exception excecao) {
		super(excecao);
	}

}
