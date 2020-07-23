/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.legado.historico.HistoricoAlteracaoCnpjCpf;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;
import br.com.sicoob.capes.replicacao.persistencia.dao.HistoricoAlteracaoCnpjCpfDAO;

/**
 * Dao utilizado para o histórico de alteração de cpf/cnpj
 * 
 * @author Erico.Junior
 */
public class HistoricoAlteracaoCnpjCpfDAOImpl extends
		EntidadeReplicavelDao<HistoricoAlteracaoCnpjCpf> implements
		HistoricoAlteracaoCnpjCpfDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISAR_HISTORICO_ALTERCAO_CPF_CNPJ";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "";

	/**
	 * Instancia um novo HistoricoAlteracaoCnpjCpfDAOImpl.
	 */
	public HistoricoAlteracaoCnpjCpfDAOImpl() {
		super(HistoricoAlteracaoCnpjCpf.class, NOME_COMANDO_PESQUISAR,NOME_COMANDO_CONSULTAR_IDDB2);
	}

}
