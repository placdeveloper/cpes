/**
 * 
 */
package br.com.sicoob.capes.processamento.persistencia.dao;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Produto;

/**
 * @author Erico.Junior
 * 
 */
public interface ProdutoDao extends
		CAPESProcessamentoCrudDaoIF<Produto> {

	/**
	 * Recupera o produo na base da cooperativa informada
	 * @param chave A chave do produto
	 * @param numCooperativa O número da cooperativa para busca.
	 * @return  o produo na base da cooperativa informada
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	Produto obter(Serializable chave, Integer numCooperativa) throws BancoobException;
}
