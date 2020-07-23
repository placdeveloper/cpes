/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;

/**
 * Define as opera��es do servi�o de manipula��o de origem da informa��o (Fonte).
 * 
 * @author Erico.Junior
 */
public interface OrigemInformacaoServico extends
		CAPESCadastroCrudServico<OrigemInformacao> {
	
	/**
	 * Obter origem por nome.
	 *
	 * @param origem o valor de origem
	 * @return OrigemInformacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	OrigemInformacao obterOrigemPorNome(OrigemInformacao origem) throws BancoobException;

}
