/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.EmpreendimentoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.Empreendimento;

/**
 * @author erico.junior
 */
public class EmpreendimentoDelegate
		extends	CAPESCadastroCrudDelegate<Empreendimento, EmpreendimentoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EmpreendimentoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarEmpreendimentoServico();
	}

	/**
	 * Pesquisar proximo codigo.
	 *
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer pesquisarProximoCodigo() throws BancoobException {
		return getServico().pesquisarProximoCodigo();
	}

}