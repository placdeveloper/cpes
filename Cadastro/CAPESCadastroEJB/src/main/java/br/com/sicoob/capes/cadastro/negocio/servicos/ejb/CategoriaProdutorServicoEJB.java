/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CategoriaProdutorServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CategoriaProdutorServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CategoriaProdutorDAO;
import br.com.sicoob.capes.negocio.entidades.CategoriaProdutor;

/**
 * Implementa as operações do serviço de categoria do produtor.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { CategoriaProdutorServicoLocal.class })
@Remote( { CategoriaProdutorServicoRemote.class })
public class CategoriaProdutorServicoEJB extends
		CAPESCadastroCrudServicoEJB<CategoriaProdutor> implements CategoriaProdutorServicoRemote, CategoriaProdutorServicoLocal {

	@Inject
	@Default
	private transient CategoriaProdutorDAO dao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CategoriaProdutorDAO getDAO() {
		return dao;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<CategoriaProdutor> obterCategoriasPorStatus(CategoriaProdutor categoria) throws BancoobException {
		return getDAO().obterCategoriasPorStatus(categoria);
	}
	
	protected void setCategoriaProdutorDAO(CategoriaProdutorDAO categoriaDao) {
		this.dao = categoriaDao;
	}
}
