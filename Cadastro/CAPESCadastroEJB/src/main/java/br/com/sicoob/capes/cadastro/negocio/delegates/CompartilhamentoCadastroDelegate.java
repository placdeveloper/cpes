/* 
 * Sicoob
 * TipoPessoaDelegate.java 
 * Criado em: 07/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.CompartilhamentoCadastroServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;

/**
 * 07/07/2011
 * 
 * @author Rodrigo.Chaves
 */
public class CompartilhamentoCadastroDelegate extends
		CAPESCadastroCrudDominioDelegate<CompartilhamentoCadastro, CompartilhamentoCadastroServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CompartilhamentoCadastroServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarCompartilhamentoCadastroServico();
	}
	
	/**
	 * O método Habilitar integracao srf.
	 *
	 * @param codCompartilhamentoCadastro o valor de cod compartilhamento cadastro
	 * @param ligarIntegracao o valor de ligar integracao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void habilitarIntegracaoSRF(Integer codCompartilhamentoCadastro, boolean ligarIntegracao) throws BancoobException {
		getServico().habilitarIntegracaoSRF(codCompartilhamentoCadastro, ligarIntegracao);
	}
}
