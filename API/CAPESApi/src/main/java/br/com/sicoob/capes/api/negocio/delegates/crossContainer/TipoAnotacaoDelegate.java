package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ITipoAnotacaoDelegate;
import br.com.sicoob.capes.api.negocio.servicos.TipoAnotacaoServico;
import br.com.sicoob.capes.api.negocio.vo.TipoAnotacaoVO;

/**
 * A Classe TipoAnotacaoDelegate.
 */
public class TipoAnotacaoDelegate extends CAPESApiDelegate<TipoAnotacaoServico> implements ITipoAnotacaoDelegate {
	
	/**
	 * 
	 */
	protected TipoAnotacaoDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static TipoAnotacaoDelegate getInstance() {
		return valueOf(TipoAnotacaoDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoAnotacaoServico localizarServico() {
		return getLocator().localizarTipoAnotacaoServico();
	}
	
	/**
	 * Obter tipos anotacao ativos.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<TipoAnotacaoVO> obterTiposAnotacaoAtivos() throws BancoobException {
		return getServico().obterTiposAnotacaoAtivos();
	}
	
	/**
	 * Obter tipo anotacao por id.
	 *
	 * @param idTipoAnotacao o valor de id tipo anotacao
	 * @return TipoAnotacaoVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public TipoAnotacaoVO obterTipoAnotacaoPorId(Integer idTipoAnotacao) throws BancoobException {
		return getServico().obterTipoAnotacaoPorId(idTipoAnotacao);
	}
}