/**
 * 
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ITipoFonteRendaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.TipoFonteRendaServico;
import br.com.sicoob.capes.api.negocio.vo.TipoFonteRendaVO;

/**
 * @author Erico.Junior
 * 
 */
public class TipoFonteRendaDelegate extends CAPESApiDelegate<TipoFonteRendaServico> implements ITipoFonteRendaDelegate {
	
	/**
	 * 
	 */
	protected TipoFonteRendaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static TipoFonteRendaDelegate getInstance() {
		return valueOf(TipoFonteRendaDelegate.class);
	}

	@Override
	protected TipoFonteRendaServico localizarServico() {
		return getLocator().localizarTipoFonteRendaServico();
	}
	
	public TipoFonteRendaVO obterTipoFonteRenda(Short codigo) throws BancoobException {
		return getServico().obterTipoFonteRenda(codigo);
	}
	
	/**
	 * Obter tipos de fonte de renda.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<TipoFonteRendaVO> listar() throws BancoobException {
		return getServico().listar();
	}
	
	/**
	 * Lista os tipos de fonte de renda por codigo do tipo da pessoa
	 * @param codigoTipoPessoa
	 * @return
	 * @throws BancoobException
	 */
	public List<TipoFonteRendaVO> listarPorTipoPessoa(Short codigoTipoPessoa) throws BancoobException {
		return getServico().listarPorTipoPessoa(codigoTipoPessoa);
	}
}
