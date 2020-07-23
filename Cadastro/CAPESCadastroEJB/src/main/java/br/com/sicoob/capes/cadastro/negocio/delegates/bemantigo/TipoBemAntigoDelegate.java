/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates.bemantigo;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroCrudDominioDelegate;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.TipoBemAntigoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;

/**
 * Delegate para os tipos de bens.
 * 
 * @author erico.junior
 */
public class TipoBemAntigoDelegate extends
	CAPESCadastroCrudDominioDelegate<TipoBem, TipoBemAntigoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoBemAntigoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoBemAntigoServico();
	}
	
	/**
	 * Listar tipos com subtipo.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<TipoBem> listarTiposComSubtipo() throws BancoobException {
		return localizarServico().listarTiposComSubtipo();
	}
}