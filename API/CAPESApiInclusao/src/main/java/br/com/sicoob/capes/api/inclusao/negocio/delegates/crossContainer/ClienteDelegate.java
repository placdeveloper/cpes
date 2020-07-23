package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IClienteDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ClienteDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.ClienteServico;

/**
 * Delegate com os servi�os de cliente.
 * 
 * @author Bruno.Carneiro
 */
public class ClienteDelegate extends CAPESApiInclusaoDelegate<ClienteDTO, ClienteServico> implements IClienteDelegate {
	
	/**
	 * 
	 */
	protected ClienteDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static ClienteDelegate getInstance() {
		return valueOf(ClienteDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ClienteServico localizarServico() {
		return getLocator().localizarClienteServico();
	}
	
	/**
	 * Realiza a altera��o do gerente do cliente.
	 * 
	 * @param dto
	 * @throws BancoobException
	 */
	public void alterarGerente(ClienteDTO dto) throws BancoobException {
		getServico().alterarGerente(dto);
	}

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
	public void alterarGerente(ClienteDTO dto, boolean notificarCTZ) throws BancoobException {
		getServico().alterarGerente(dto, notificarCTZ);
	}
}