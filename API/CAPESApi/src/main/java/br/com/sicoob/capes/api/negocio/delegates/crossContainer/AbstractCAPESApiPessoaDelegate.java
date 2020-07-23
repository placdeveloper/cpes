/*
 * SICOOB
 * 
 * AbstractCAPESApiPessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.AbstractCAPESApiPessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IAbstractCAPESApiPessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.CAPESApiServicoPessoa;
import br.com.sicoob.capes.api.negocio.vo.AbstractPessoaVO;

/**
 * Business delegate a ser usado pelo Sistema CapesIntegracao.
 */
public abstract class AbstractCAPESApiPessoaDelegate<K extends AbstractPessoaVO,T extends CAPESApiServicoPessoa<K>>
		extends CAPESApiDelegate<T> implements IAbstractCAPESApiPessoaDelegate<K> {

	/**
	 * Recupera entidade por ID 
	 * 
	 * @param id
	 * 		Serializable do identificador da entidade
	 * @return
	 * 		Retorna a entidade do determinado ID
	 * @throws BancoobException
	 */
	public K obter(Serializable id)
			throws BancoobException {
		return getServico().obterByID(id);
	}
	
	/**
	 * Recupera a lista da entidade pelo idPessoa e idInstituição
	 *  
	 * @param idPessoa 
	 * 		ID da pessoa	
	 * @param idInstituicao
	 * 		ID da instituição
	 * @return
	 * 		Retorna a lista de entidades encontradas pelo determinado ID pessoa e ID instituição
	 * @throws BancoobException
	 */
	public List<K> obterPorPessoaInstituicao(Integer idPessoa,
			Integer idInstituicao) throws BancoobException {
		return getServico().obterByPessoaInstituicao(idPessoa, idInstituicao);
	}
	
}