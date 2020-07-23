/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CategoriaAnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CategoriaAnotacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.CategoriaAnotacaoDAO;
import br.com.sicoob.capes.negocio.entidades.CategoriaAnotacao;

/**
 * Implementa as operações do serviço de anotações.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { CategoriaAnotacaoServicoLocal.class })
@Remote( { CategoriaAnotacaoServicoRemote.class })
public class CategoriaAnotacaoServicoEJB extends
		CAPESCadastroCrudServicoEJB<CategoriaAnotacao> implements CategoriaAnotacaoServicoRemote, CategoriaAnotacaoServicoLocal {

	@Inject
	@Default
	private CategoriaAnotacaoDAO categoriaAnotacaoDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<CategoriaAnotacao> getDAO() {
		return categoriaAnotacaoDAO;
	}

	protected void setCategoriaAnotacaoDAO(CategoriaAnotacaoDAO dao) {
		this.categoriaAnotacaoDAO = dao;
	}
}
