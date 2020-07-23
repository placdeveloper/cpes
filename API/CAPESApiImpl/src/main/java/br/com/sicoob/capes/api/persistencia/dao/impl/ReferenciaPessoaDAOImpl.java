package br.com.sicoob.capes.api.persistencia.dao.impl;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.ReferenciaPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.ReferenciaPessoaDAO;

/**
 * A Classe ReferenciaPessoaDAOImpl.
 */
public class ReferenciaPessoaDAOImpl extends CAPESApiDAO<ReferenciaPessoaVO> implements ReferenciaPessoaDAO {
	
	/**
	 * Instancia um novo ReferenciaPessoaDAOImpl.
	 */
	public ReferenciaPessoaDAOImpl(){
		super("CONSULTAR_REFERENCIA_PESSOA");
	}
	
	@Override
	protected String obterNomeComandoPesquisar() throws BancoobException {
		return "PESQUISAR_REFERENCIA_PESSOA";
	}
	
	@Override
	protected String obterNomeComandoQuantidadePesquisar() throws BancoobException {
		return "PESQUISAR_QUANTIDADE_REFERENCIA_PESSOA";
	}

}