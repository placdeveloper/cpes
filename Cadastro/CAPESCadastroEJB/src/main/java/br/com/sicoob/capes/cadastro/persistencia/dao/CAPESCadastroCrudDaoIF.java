package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.dao.BancoobCrudDaoIF;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;

/**
 * @author Stefanini IT Solutions
 */
public interface CAPESCadastroCrudDaoIF<T extends CAPESEntidade<?>> extends BancoobCrudDaoIF<T> {

	/**
	 * O método Substituir colecoes persistentes.
	 *
	 * @param destino o valor de destino
	 * @param origem o valor de origem
	 * @param colecoes o valor de colecoes
	 */
	void substituirColecoesPersistentes(T destino, T origem, String... colecoes);

	/**
	 * O método Excluir entidade.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void excluirEntidade(T objeto) throws BancoobException;
	
	/**
	 * O método Remover objeto sessao.
	 *
	 * @param objeto o valor de objeto
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void removerObjetoSessao(T objeto) throws BancoobException; 
}
