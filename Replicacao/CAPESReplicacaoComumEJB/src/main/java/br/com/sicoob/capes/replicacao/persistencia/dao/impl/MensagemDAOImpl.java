/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.Mensagem;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.MensagemDAO;

/**
 * Dao utilizada para as mensagens.
 * 
 * @author juan.damasceno
 */
public class MensagemDAOImpl extends EntidadeReplicavelDao<Mensagem> implements
		MensagemDAO {

	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTAR_MENSAGEM_IDDB2";

	/**
	 * Instancia um novo MensagemDAOImpl.
	 */
	public MensagemDAOImpl() {
		super(Mensagem.class, null, NOME_COMANDO_CONSULTAR_IDDB2);
	}
}