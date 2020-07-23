/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb.bemantigo;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.TipoBemAntigoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo.TipoBemAntigoServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.servicos.ejb.CAPESCadastroCrudDominioServicoEJB;
import br.com.sicoob.capes.cadastro.persistencia.dao.bemantigo.TipoBemAntigoDAO;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;

/**
 * Servico para os tipos de bens
 * @author erico.junior
 */
@Stateless
@Local( { TipoBemAntigoServicoLocal.class })
@Remote( { TipoBemAntigoServicoRemote.class })
public class TipoBemAntigoServicoEJB extends CAPESCadastroCrudDominioServicoEJB<TipoBem> implements
		TipoBemAntigoServicoRemote, TipoBemAntigoServicoLocal {

	@Inject
	@Default
	protected TipoBemAntigoDAO tipoBemDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoBemAntigoDAO getDAO() {
		return tipoBemDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoBem> listarTiposComSubtipo() throws BancoobException {
		return getDAO().listarTiposComSubtipo();
	}

}
