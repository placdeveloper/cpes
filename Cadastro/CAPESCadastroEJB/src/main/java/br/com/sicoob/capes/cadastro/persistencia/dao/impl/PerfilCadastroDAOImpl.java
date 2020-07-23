/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.sicoob.capes.negocio.entidades.PerfilCadastro;
import br.com.sicoob.capes.cadastro.persistencia.dao.PerfilCadastroDAO;

/**
 * @author Isaac.Pessoa
 *
 */
public class PerfilCadastroDAOImpl extends CAPESCadastroCrudDao<PerfilCadastro>
		implements PerfilCadastroDAO {

	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_CONSULTA";

	/**
	 * Construtor do DAO.
	 */
	public PerfilCadastroDAOImpl() {
		super(PerfilCadastro.class, NOME_COMANDO_PESQUISAR);
	}

}
