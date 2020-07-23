/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDominioDaoIF;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem;

/**
 * Dao para subtipos de bem
 * 
 * @author juan.damasceno
 */
public interface SubTipoBemAntigoDAO extends CAPESCadastroCrudDominioDaoIF<SubTipoBem> {

	/**
	 * Pesquisar por tipo.
	 *
	 * @param criterios o valor de criterios
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<SubTipoBem> pesquisarPorTipo(ConsultaDto<SubTipoBem> criterios)
			throws BancoobException;

}
