// 15/08/2013
package br.com.sicoob.capes.cadastro.negocio.servicos.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.AutorizarServico;

/**
 * A Interface AutorizarServicoLocal.
 */
public interface AutorizarServicoLocal extends AutorizarServico {

	/**
	 * 
	 * @param siglaProcesso
	 * @param idRegistroControlado
	 * @return
	 * @throws BancoobException
	 */
	 boolean isFluxoAtivo(String siglaProcesso, String idRegistroControlado) throws BancoobException;
}