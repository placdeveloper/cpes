/**
 * 
 */
package br.com.sicoob.capes.replicacao.persistencia.dao.impl;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Bem;
import br.com.sicoob.capes.replicacao.persistencia.dao.BemDAO;
import br.com.sicoob.capes.replicacao.persistencia.dao.EntidadeReplicavelDao;

/**
 * DAO para os Bens.
 * 
 * @author Erico.Junior
 */
public class BemDAOImpl extends EntidadeReplicavelDao<Bem> implements BemDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_BEM_PESSOA";
	
	/** A constante NOME_COMANDO_CONSULTAR_IDDB2. */
	private static final String NOME_COMANDO_CONSULTAR_IDDB2 = "CONSULTA_BEM_POR_ID_DB2";

	/**
	 * Instancia um novo BemDAOImpl.
	 */
	public BemDAOImpl() {
		super(Bem.class, NOME_COMANDO_PESQUISAR, NOME_COMANDO_CONSULTAR_IDDB2);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Bem> obterPorIdsDB2(Serializable chave, Integer numeroCooperativa) throws BancoobException {
		List<Bem> listBem = super.obterPorIdsDB2(chave, numeroCooperativa);
		
		if (listBem != null && !listBem.isEmpty()) {
			if (listBem.get(0).getBensOnus() != null)  {
				listBem.get(0).getBensOnus().size();
			}
			if (listBem.get(0).getBensPosse() != null) {
				listBem.get(0).getBensPosse().size();
			}
			if (listBem.get(0).getBensRegistro() != null) {
				listBem.get(0).getBensRegistro().size();
			}
		}
		
		return listBem;
	}

}
