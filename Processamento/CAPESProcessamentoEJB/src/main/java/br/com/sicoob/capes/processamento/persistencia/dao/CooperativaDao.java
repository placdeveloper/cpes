package br.com.sicoob.capes.processamento.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.processamento.negocio.vo.CooperativaVO;

/**
 * A Interface CooperativaDao.
 */
public interface CooperativaDao extends CAPESProcessamentoDaoIF {

	/**
	 * Obter pacs bancoob.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<CooperativaVO> obterPacsBancoob() throws BancoobException;
}