package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ICnaeFiscalDelegate;
import br.com.sicoob.capes.api.negocio.servicos.CnaeFiscalServico;
import br.com.sicoob.capes.api.negocio.vo.CnaeFiscalVO;

public class CnaeFiscalDelegate extends CAPESApiDelegate<CnaeFiscalServico> implements ICnaeFiscalDelegate {
	
	/**
	 * 
	 */
	protected CnaeFiscalDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static CnaeFiscalDelegate getInstance() {
		return valueOf(CnaeFiscalDelegate.class);
	}

	@Override
	protected CnaeFiscalServico localizarServico() {
		return getLocator().localizarCnaeFiscalServico();
	}

	public List<CnaeFiscalVO> listar() throws BancoobException {
		return getServico().listar();
	}

	public CnaeFiscalVO obterCnaeFiscalPorCodigo(String codigoCnae) throws BancoobException {
		return getServico().obterCnaeFiscalPorCodigo(codigoCnae);
	}
	
	public List<CnaeFiscalVO> obterPorDescricao(String descricao) throws BancoobException {
		return getServico().obterPorDescricao(descricao);
	}
}
