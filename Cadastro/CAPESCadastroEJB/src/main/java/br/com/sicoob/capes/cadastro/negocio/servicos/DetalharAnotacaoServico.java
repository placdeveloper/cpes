/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;

/**
 * Serviço utilizado para detalhar uma anotação.
 * 
 * @author Erico.Junior
 */
public interface DetalharAnotacaoServico extends CAPESCadastroServico {

	/**
	 * Detalha a anotação informada.
	 * 
	 * @param anotacao
	 *            A anotação a ser detalhada.
	 * @return A lista com os detalhes da anotação.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	List<DetalheAnotacaoDTO> detalharAnotacao(Anotacao anotacao) throws BancoobException;
}
