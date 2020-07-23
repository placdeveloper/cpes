package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IClienteDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.ClienteDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.ClienteServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * Delegate com os servi�os de cliente.
 * 
 * @author Bruno.Carneiro
 */
public class ClienteDelegate extends CAPESApiInclusaoDelegate<ClienteDTO, ClienteServico> implements IClienteDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ClienteServico localizarServico() {
		return CAPESApiInclusaoServiceLocator.getInstance().localizarClienteServico();
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