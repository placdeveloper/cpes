/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.negocio.entidades.legado.Produto;
import br.com.sicoob.capes.processamento.negocio.servicos.interfaces.ProdutoServicoLocal;
import br.com.sicoob.capes.processamento.negocio.servicos.interfaces.ProdutoServicoRemote;
import br.com.sicoob.capes.processamento.persistencia.dao.ProdutoDao;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local({ ProdutoServicoLocal.class })
@Remote({ ProdutoServicoRemote.class })
public class ProdutoServicoEJB extends CAPESProcessamentoCrudServicoEJB<Produto> implements ProdutoServicoRemote, ProdutoServicoLocal {

	@Inject
	@Default
	private ProdutoDao dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProdutoDao getDAO() {
		return dao;
	}

}