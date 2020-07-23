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

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PerfilTarifarioServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PerfilTarifarioServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.PerfilTarifarioDAO;
import br.com.sicoob.capes.negocio.entidades.Funcionario;
import br.com.sicoob.capes.negocio.entidades.PerfilTarifario;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaCompartilhamento;

/**
 * Implementa as operações do serviço de PerfilTarifario.
 * 
 * @author juan.damasceno
 */
@Stateless
@Local( { PerfilTarifarioServicoLocal.class })
@Remote( { PerfilTarifarioServicoRemote.class })
public class PerfilTarifarioServicoEJB extends CAPESCadastroCrudServicoEJB<PerfilTarifario>
		implements PerfilTarifarioServicoRemote, PerfilTarifarioServicoLocal {

	@Inject
	@Default
	protected PerfilTarifarioDAO perfilTarifarioDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<PerfilTarifario> getDAO() {
		return perfilTarifarioDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Funcionario> listarPorPessoa(PessoaCompartilhamento pessoa) {
		return null;
	}
}
