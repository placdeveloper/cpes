package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TipoAnotacaoVO;

/**
 * A Interface TipoAnotacaoDAO.
 */
public interface TipoAnotacaoDAO extends CAPESApiDaoIF<TipoAnotacaoVO> {

	/**
	 * Obter tipos anotacao ativos.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<TipoAnotacaoVO> obterTiposAnotacaoAtivos() throws BancoobException;

	/**
	 * Obter tipo anotacao por id.
	 *
	 * @param idTipoAnotacao o valor de id tipo anotacao
	 * @return TipoAnotacaoVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	TipoAnotacaoVO obterTipoAnotacaoPorId(Integer idTipoAnotacao) throws BancoobException;

}