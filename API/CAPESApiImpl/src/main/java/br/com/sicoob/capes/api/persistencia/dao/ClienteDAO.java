package br.com.sicoob.capes.api.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.api.negocio.vo.ClienteVO;

/**
 * A Interface ClienteDAO.
 */
public interface ClienteDAO extends CAPESApiDaoIF<ClienteVO> {

	/**
	 * Obter cliente.
	 *
	 * @param criterios o valor de criterios
	 * @return ClienteVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	ClienteVO obterCliente(ConsultaDto<ClienteVO> criterios) throws BancoobException;

}