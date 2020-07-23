package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.DominioVO;
import br.com.sicoob.capes.cadastro.negocio.enums.TipoDominioEntidadeEnum;

/**
 * A Interface DominioDAO.
 */
public interface DominioDAO extends CAPESApiDaoIF<DominioVO> {

	/**
	 * Obter dominio por tipo dominio.
	 *
	 * @param tipoDominio o valor de tipo dominio
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<DominioVO> obterDominioPorTipoDominio(TipoDominioEntidadeEnum tipoDominio) throws BancoobException;
}