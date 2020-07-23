/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.HistoricoAlteracaoCpfCnpj;
import br.com.sicoob.capes.cadastro.persistencia.dao.HistoricoAlteracaoCpfCnpjDAO;

/**
 * DAO utilizado para o histórico de alteração de cpf/cnpj
 * 
 * @author Erico.Junior
 */
public class HistoricoAlteracaoCpfCnpjDAOImpl extends
		CAPESCadastroCrudDao<HistoricoAlteracaoCpfCnpj> implements
		HistoricoAlteracaoCpfCnpjDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_HISTORICO_ALTERACAO_CPF_CNPJ";

	/**
	 * Instancia um novo HistoricoAlteracaoCpfCnpjDAOImpl.
	 */
	public HistoricoAlteracaoCpfCnpjDAOImpl() {
		super(HistoricoAlteracaoCpfCnpj.class, NOME_COMANDO_PESQUISAR);
	}

}
