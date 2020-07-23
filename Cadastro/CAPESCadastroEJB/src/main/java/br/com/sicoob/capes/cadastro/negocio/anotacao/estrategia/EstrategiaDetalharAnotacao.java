/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.anotacao.estrategia;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;

/**
 * Interface que define as operações para detalhamento de uma anotação.
 * @author Erico.Junior
 */
public interface EstrategiaDetalharAnotacao {

	/**
	 * Recupera os detalhes da anotação informada.
	 * @param anotacao A anotação da qual se deseja os detalhes.
	 * @return Os detalhes da anotação informada.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	List<DetalheAnotacaoDTO> obterDetalhesAnotacao(Anotacao anotacao) throws BancoobException;
}
