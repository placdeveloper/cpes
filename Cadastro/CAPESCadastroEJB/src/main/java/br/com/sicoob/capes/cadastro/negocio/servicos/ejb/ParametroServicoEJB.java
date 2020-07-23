package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ParametroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.ParametroServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.ParametroDAO;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
@Stateless
@Local(ParametroServicoLocal.class)
@Remote(ParametroServicoRemote.class)
public class ParametroServicoEJB extends CAPESCadastroServicoEJB implements ParametroServicoLocal, ParametroServicoRemote {

	@Inject
	@Default
	private ParametroDAO dao;

	/**
	 * @see br.com.sicoob.capes.cadastro.negocio.servicos.ParametroServico#obterParametro(Integer, Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ParametroVO obterParametro(Integer codigo, Integer idInstituicao) {
		return dao.obterParametro(codigo, idInstituicao);
	}

	/**
	 * @see br.com.sicoob.capes.cadastro.negocio.servicos.ParametroServico#obterParametroValorBoolean(Integer, Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public boolean obterParametroValorBoolean(Integer codigo, Integer idInstituicao) {
		ParametroVO parametro = obterParametro(codigo, idInstituicao);
		if (parametro != null){
			return Boolean.parseBoolean(parametro.getValor());
		}
		return false;	
	}
}
