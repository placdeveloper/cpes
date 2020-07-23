package br.com.sicoob.capes.processamento.negocio.anotacao.estrategia;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;

/**
 * A Interface EstrategiaAtualizarAnotacoesExternas.
 */
public interface EstrategiaAtualizarAnotacoesExternas {
	
	/**
	 * O m�todo Executar atualizacao anotacao externa.
	 *
	 * @param origemInformacao o valor de origem informacao
	 * @param dto o valor de dto
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void executarAtualizacaoAnotacaoExterna(OrigemInformacao origemInformacao, AnotacaoExternaDTO dto) throws BancoobException;
}