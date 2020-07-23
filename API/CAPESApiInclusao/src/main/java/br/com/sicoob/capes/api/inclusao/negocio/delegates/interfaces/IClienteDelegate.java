package br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ClienteDTO;

/**
 * Delegate com os servi�os de cliente.
 * 
 * @author Bruno.Carneiro
 */
public interface IClienteDelegate extends ICAPESApiInclusaoDelegate<ClienteDTO> {

	/**
	 * Realiza a altera��o do gerente do cliente.
	 * 
	 * @param dto
	 * @throws BancoobException
	 */
	void alterarGerente(ClienteDTO dto) throws BancoobException;

	/**
	 * Altera o gerente e notifica ou n�o o CTZ.
	 * 
	 * @param dto
	 * @param notificarCTZ
	 * 
	 * @deprecated a integra��o com o CTZ est� desativada.
	 * 
	 * @throws BancoobException
	 */
	@Deprecated
	void alterarGerente(ClienteDTO dto, boolean notificarCTZ) throws BancoobException;

}