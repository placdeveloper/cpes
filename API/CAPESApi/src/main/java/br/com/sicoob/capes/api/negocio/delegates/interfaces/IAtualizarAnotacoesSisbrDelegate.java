package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface IAtualizarAnotacoesSisbrDelegate extends ICAPESApiDelegate {

	/**
	 * Baixa uma anota��o de origem SISBR
	 * 
	 * @param dto
	 *            O dto com as informa��es da anota��o.
	 * @return As anota��es baixadas a partir das informa��o do dto.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	void baixarAnotacao(AnotacaoSisbrDTO dto) throws BancoobException;

	/**
	 * Inclui uma anota��o de origem SISBR
	 * 
	 * @param dto
	 *            O dto com as informa��es da anota��o.
	 * @return o ID da anota��o inclu�da.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	Long incluirAnotacao(AnotacaoSisbrDTO dto) throws BancoobException;

}
