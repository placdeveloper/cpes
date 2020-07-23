/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.servicos.CAPESCadastroCrudDominioServico;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem;

/**
 * Servi�o para os subtipos de bens
 * 
 * @author juan.damasceno
 */
public interface SubTipoBemAntigoServico extends CAPESCadastroCrudDominioServico<SubTipoBem> {

	/**
	 * Pesquisar por tipo.
	 *
	 * @param criterios o valor de criterios
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<SubTipoBem> pesquisarPorTipo(ConsultaDto<SubTipoBem> criterios)
			throws BancoobException;

}