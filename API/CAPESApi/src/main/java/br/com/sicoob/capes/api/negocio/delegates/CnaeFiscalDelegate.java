package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.ICnaeFiscalDelegate;
import br.com.sicoob.capes.api.negocio.servicos.CnaeFiscalServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.CnaeFiscalVO;

public class CnaeFiscalDelegate extends CAPESApiDelegate<CnaeFiscalServico> implements ICnaeFiscalDelegate {

	@Override
	protected CnaeFiscalServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarCnaeFiscalServico();
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
