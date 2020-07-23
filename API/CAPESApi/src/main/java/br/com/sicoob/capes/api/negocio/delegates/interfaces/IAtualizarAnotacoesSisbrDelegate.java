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
	 * Baixa uma anotação de origem SISBR
	 * 
	 * @param dto
	 *            O dto com as informações da anotação.
	 * @return As anotações baixadas a partir das informação do dto.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	void baixarAnotacao(AnotacaoSisbrDTO dto) throws BancoobException;

	/**
	 * Inclui uma anotação de origem SISBR
	 * 
	 * @param dto
	 *            O dto com as informações da anotação.
	 * @return o ID da anotação incluída.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	Long incluirAnotacao(AnotacaoSisbrDTO dto) throws BancoobException;

}
