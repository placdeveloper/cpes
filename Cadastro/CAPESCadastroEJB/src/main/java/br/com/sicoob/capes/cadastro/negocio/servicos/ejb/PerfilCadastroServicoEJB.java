/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PerfilCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.PerfilCadastroServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.PerfilCadastroDAO;
import br.com.sicoob.capes.negocio.entidades.PerfilCadastro;

/**
 * Implementa as operacoes do servico de perfil do cadastro.
 * 
 * @author Isaac.Pessoa
 */
@Stateless
@Local( { PerfilCadastroServicoLocal.class })
@Remote( { PerfilCadastroServicoRemote.class })
public class PerfilCadastroServicoEJB extends
		CAPESCadastroCrudServicoEJB<PerfilCadastro> implements PerfilCadastroServicoRemote, PerfilCadastroServicoLocal {

	@Inject
	@Default
	protected PerfilCadastroDAO dao;

	@Override
	protected PerfilCadastroDAO getDAO() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB#listar()
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PerfilCadastro> listar() throws BancoobException {
		return super.listar();
	}

	
}
