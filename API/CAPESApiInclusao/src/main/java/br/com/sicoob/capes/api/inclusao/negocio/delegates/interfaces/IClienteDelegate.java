package br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ClienteDTO;

/**
 * Delegate com os serviços de cliente.
 * 
 * @author Bruno.Carneiro
 */
public interface IClienteDelegate extends ICAPESApiInclusaoDelegate<ClienteDTO> {

	/**
	 * Realiza a alteração do gerente do cliente.
	 * 
	 * @param dto
	 * @throws BancoobException
	 */
	void alterarGerente(ClienteDTO dto) throws BancoobException;

	/**
	 * Altera o gerente e notifica ou não o CTZ.
	 * 
	 * @param dto
	 * @param notificarCTZ
	 * 
	 * @deprecated a integração com o CTZ está desativada.
	 * 
	 * @throws BancoobException
	 */
	@Deprecated
	void alterarGerente(ClienteDTO dto, boolean notificarCTZ) throws BancoobException;

}