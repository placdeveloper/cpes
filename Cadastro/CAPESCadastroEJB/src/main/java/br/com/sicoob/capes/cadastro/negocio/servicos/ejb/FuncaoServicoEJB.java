/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.FuncaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.FuncaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.FuncaoDAO;
import br.com.sicoob.capes.negocio.entidades.Funcao;

/**
 * Implementa as operações do serviço de {@link Funcao}.
 * 
 * @author lucas.borges
 */
@Stateless
@Local( { FuncaoServicoLocal.class })
@Remote( { FuncaoServicoRemote.class })
public class FuncaoServicoEJB extends CAPESCadastroCrudServicoEJB<Funcao>
		implements FuncaoServicoRemote, FuncaoServicoLocal {

	@Inject
	@Default
	private FuncaoDAO funcaoDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FuncaoDAO getDAO() {
		return funcaoDAO;
	}
	
}
