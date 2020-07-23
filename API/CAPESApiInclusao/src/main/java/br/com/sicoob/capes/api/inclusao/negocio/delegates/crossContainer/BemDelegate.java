package br.com.sicoob.capes.api.inclusao.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.inclusao.negocio.delegates.interfaces.IBemDelegate;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemDTO;
import br.com.sicoob.capes.api.inclusao.negocio.dto.BemProprietarioDTO;
import br.com.sicoob.capes.api.inclusao.negocio.servicos.BemServico;

/**
 * A Classe BemDelegate.
 * 
 * @author bruno.carneiro
 */
public class BemDelegate extends CAPESApiInclusaoDelegate<BemDTO, BemServico> implements IBemDelegate {
	
	/**
	 * 
	 */
	protected BemDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static BemDelegate getInstance() {
		return valueOf(BemDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemServico localizarServico() {
		return getLocator().localizarBemServico();
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