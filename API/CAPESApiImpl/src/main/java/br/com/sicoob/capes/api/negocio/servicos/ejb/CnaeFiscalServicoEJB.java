package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.excecao.ParametroInvalidoException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.CnaeFiscalServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.CnaeFiscalServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.CnaeFiscalVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.CnaeFiscalDAO;


@Stateless
@Local(CnaeFiscalServicoLocal.class)
@Remote(CnaeFiscalServicoRemote.class)
public class CnaeFiscalServicoEJB extends CAPESApiServicoEJB implements CnaeFiscalServicoLocal, CnaeFiscalServicoRemote {
	
	@Inject
	@Default
	private CnaeFiscalDAO cnaeFiscalDAO;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CnaeFiscalVO> listar() throws BancoobException {
		return getDAO().listar();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CnaeFiscalVO obterCnaeFiscalPorCodigo(String codigoCnae) throws BancoobException {
		return getDAO().obterCnaeFiscalPorCodigo(codigoCnae);
	}
	
	private CnaeFiscalDAO getDAO(){
		return cnaeFiscalDAO;
	}

	public List<CnaeFiscalVO> obterPorDescricao(String descricao) throws BancoobException {
		if (!StringUtils.isEmpty(descricao)) {
			if (descricao.indexOf('%') >= 0) {
				throw new ParametroInvalidoException();
			}
		}
		return getDAO().obterPorDescricao(descricao);
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return cnaeFiscalDAO;
	}

}