/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.anotacao.estrategia;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.DetalheAnotacaoDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;

/**
 * Interface que define as opera��es para detalhamento de uma anota��o.
 * @author Erico.Junior
 */
public interface EstrategiaDetalharAnotacao {

	/**
	 * Recupera os detalhes da anota��o informada.
	 * @param anotacao A anota��o da qual se deseja os detalhes.
	 * @return Os detalhes da anota��o informada.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	List<DetalheAnotacaoDTO> obterDetalhesAnotacao(Anotacao anotacao) throws BancoobException;
}
