package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.AbstractPessoaVO;

/**
 * Business delegate a ser usado pelo Sistema CapesIntegracao.
 */
public interface IAbstractCAPESApiPessoaDelegate<K extends AbstractPessoaVO> extends ICAPESApiDelegate {

	/**
	 * Recupera entidade por ID
	 * 
	 * @param id
	 *            Serializable do identificador da entidade
	 * @return Retorna a entidade do determinado ID
	 * @throws BancoobException
	 */
	K obter(Serializable id) throws BancoobException;

	/**
	 * Recupera a lista da entidade pelo idPessoa e idInstituição
	 * 
	 * @param idPessoa
	 *            ID da pessoa
	 * @param idInstituicao
	 *            ID da instituição
	 * @return Retorna a lista de entidades encontradas pelo determinado ID pessoa e ID instituição
	 * @throws BancoobException
	 */
	List<K> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

}