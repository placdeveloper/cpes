/* 
 * Sicoob
 * RegistroRelacionamentoDAOImpl.java 
 * Criado em: 05/10/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.impl;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.persistencia.dao.RegistroRelacionamentoDAO;
import br.com.sicoob.capes.negocio.entidades.vigente.RegistroRelacionamento;

/**
 * Implementação da DAO de {@link RegistroRelacionamento}
 * 
 * 05/10/2011
 * 
 * @author Rodrigo.Chaves
 */
public class RegistroRelacionamentoDAOImpl extends EntidadeCadastroDao<RegistroRelacionamento> implements
		RegistroRelacionamentoDAO {

	/** A constante NOME_COMANDO_PESQUISAR. */
	private static final String NOME_COMANDO_PESQUISAR = "PESQUISA_REGISTRO_RELACIONAMENTO";

	/**
	 * Instancia um novo RegistroRelacionamentoDAOImpl.
	 */
	public RegistroRelacionamentoDAOImpl() {		
		super(RegistroRelacionamento.class, NOME_COMANDO_PESQUISAR);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void marcarEmAlteracao(String tableName, String nomeColunaId,
			Long id, Integer idInstituicaoAtualizacao) throws BancoobException {
		
		super.marcarEmAlteracao("CLI.RELACIONAMENTOPESSOA", nomeColunaId, id,
				idInstituicaoAtualizacao);
	}
	
}