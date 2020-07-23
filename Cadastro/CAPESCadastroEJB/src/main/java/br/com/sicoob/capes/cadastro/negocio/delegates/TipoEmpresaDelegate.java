/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoEmpresaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoEmpresa;
import br.com.sicoob.capes.negocio.entidades.vigente.FonteRenda;

/**
 * @author Erico.Junior
 * 
 */
public class TipoEmpresaDelegate extends
		CAPESCadastroCrudDominioDelegate<TipoEmpresa, TipoEmpresaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoEmpresaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarTipoEmpresaServico();
	}

	public TipoEmpresa obterTipoEmpresaPorFaixaRendaAnual(FonteRenda fonteRenda) throws BancoobException {
		return getServico().obterTipoEmpresaPorFaixaRendaAnual(fonteRenda);
	}
	
}
