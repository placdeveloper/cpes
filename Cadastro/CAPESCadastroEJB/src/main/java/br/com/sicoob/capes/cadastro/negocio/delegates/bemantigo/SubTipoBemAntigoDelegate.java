/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDominioDelegate;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.SubTipoBemAntigoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bemantigo.SubTipoBem;

/**
 * Delegate para os tipos de bens.
 * @author juan.damascemo
 */
public class SubTipoBemAntigoDelegate extends
	CAPESCadastroCrudDominioDelegate<SubTipoBem, SubTipoBemAntigoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected SubTipoBemAntigoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarSubTipoBemAntigoServico();
	}
	
	/**
	 * Pesquisar por tipo.
	 *
	 * @param criterios o valor de criterios
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<SubTipoBem> pesquisarPorTipo(ConsultaDto<SubTipoBem> criterios)
			throws BancoobException {
		return localizarServico().pesquisarPorTipo(criterios);
	}
}
