/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;

/**
 * Servi�o utilizado para detalhar uma anota��o.
 * 
 * @author Erico.Junior
 */
public interface DetalharAnotacaoServico extends CAPESCadastroServico {

	/**
	 * Detalha a anota��o informada.
	 * 
	 * @param anotacao
	 *            A anota��o a ser detalhada.
	 * @return A lista com os detalhes da anota��o.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	List<DetalheAnotacaoDTO> detalharAnotacao(Anotacao anotacao) throws BancoobException;
}
