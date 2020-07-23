package br.com.sicoob.capes.api.inclusao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ClienteDTO;

/**
 * Interface para os servi�os de cliente.
 * 
 * @author Bruno.Carneiro
 */
public interface ClienteServico extends CAPESApiInclusaoServico<ClienteDTO> {
	
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
	 * @throws BancoobException
	 */
	void alterarGerente(ClienteDTO dto, boolean notificarCTZ) throws BancoobException;

}