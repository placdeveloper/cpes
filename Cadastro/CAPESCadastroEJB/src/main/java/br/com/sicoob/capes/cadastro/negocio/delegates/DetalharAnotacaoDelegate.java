/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.DetalharAnotacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;

/**
 * Delegate utilizada para o serviço de detalhar anotações.
 * 
 * @author Erico.Junior
 */
public class DetalharAnotacaoDelegate extends
		CAPESCadastroDelegate<DetalharAnotacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DetalharAnotacaoServico localizarServico() {

		return CAPESCadastroServiceLocator.getInstance()
				.localizarDetalharAnotacaoServico();
	}

	/**
	 * Detalha a anotação informada.
	 * 
	 * @param anotacao
	 *            A anotação a ser detalhada.
	 * @return A lista com os detalhes da anotação.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public List<DetalheAnotacaoDTO> detalharAnotacao(Anotacao anotacao) throws BancoobException {
		return getServico().detalharAnotacao(anotacao);
	}
}
