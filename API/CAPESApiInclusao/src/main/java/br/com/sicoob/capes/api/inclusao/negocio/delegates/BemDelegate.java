package br.com.sicoob.capes.api.inclusao.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IBemDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemProprietarioDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.BemServico;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.locator.CAPESApiInclusaoServiceLocator;

/**
 * A Classe BemDelegate.
 * 
 * @author bruno.carneiro
 */
public class BemDelegate extends CAPESApiInclusaoDelegate<BemDTO, BemServico> implements IBemDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemServico localizarServico() {
		return CAPESApiInclusaoServiceLocator.getInstance().localizarBemServico();
	}

	/**
	 * Inclui um bem do tipo "sem patrimônio" para a pessoa informada.
	 * 
	 * @param dto
	 * @throws BancoobException
	 */
	public void incluirSemPatrimonio(BemDTO dto) throws BancoobException {
		getServico().incluirSemPatrimonio(dto);
	}

	/**
	 * Inclui um bem do tipo "recusou-se a informar" para a pessoa informada.
	 * 
	 * @param dto
	 * @throws BancoobException
	 */
	public void incluirRecusouInformar(BemDTO dto) throws BancoobException {
		getServico().incluirRecusouInformar(dto);
	}

	/**
	 * Faz a alteração dos proprietários do bem.
	 * 
	 * @param dto
	 * @param proprietarios
	 * @throws BancoobException
	 */
	public void alterarProprietarios(BemDTO dto, List<BemProprietarioDTO> proprietarios) throws BancoobException {
		getServico().alterarProprietarios(dto, proprietarios);
	}

}