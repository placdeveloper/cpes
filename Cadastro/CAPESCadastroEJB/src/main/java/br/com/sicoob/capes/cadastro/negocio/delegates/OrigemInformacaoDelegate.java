/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.OrigemInformacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;

/**
 * Business delegate para as origens das informações.
 * 
 * @author Erico.Junior
 */
public class OrigemInformacaoDelegate extends
		CAPESCadastroCrudDelegate<OrigemInformacao, OrigemInformacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected OrigemInformacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarOrigemInformacaoServico();
	}
	
	/**
	 * Obter origem por nome.
	 *
	 * @param origem o valor de origem
	 * @return OrigemInformacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public OrigemInformacao obterOrigemPorNome(OrigemInformacao origem) throws BancoobException{
		return getServico().obterOrigemPorNome(origem);
	}

}
