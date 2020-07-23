package br.com.sicoob.capes.processamento.persistencia.dao;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.persistencia.dao.BancoobCrudDaoIF;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidadeBase;

/**
 * A Interface CAPESProcessamentoCrudDaoIF.
 *
 * @param <T> o tipo generico
 */
public interface CAPESProcessamentoCrudDaoIF<T extends CAPESEntidadeBase<?>>
		extends BancoobCrudDaoIF<T> {

	/**
	 * Método para obter uma entidade.
	 * 
	 * @param chave
	 *            A chave da entidade.
	 * @param numeroCooperativa
	 *            O número da cooperativa.
	 * @return A entidade procurada na cooperativa informada.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção na alteração.
	 */
	T obter(Serializable chave, Integer numeroCooperativa) throws BancoobException;
}
