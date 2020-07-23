package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ITipoTelefoneDelegate;
import br.com.sicoob.capes.api.negocio.servicos.TipoTelefoneServico;
import br.com.sicoob.capes.api.negocio.vo.TipoTelefoneVO;

/**
 * A Classe TipoTelefoneDelegate.
 */
public class TipoTelefoneDelegate extends CAPESApiDelegate<TipoTelefoneServico> implements ITipoTelefoneDelegate {
	
	/**
	 * 
	 */
	protected TipoTelefoneDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static TipoTelefoneDelegate getInstance() {
		return valueOf(TipoTelefoneDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoTelefoneServico localizarServico() {
		return getLocator().localizarTipoTelefoneServico();
	}
	
	/**
	 * Obter tipos de telefone.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<TipoTelefoneVO> listar() throws BancoobException {
		return getServico().listar();
	}
	
}