/* 
 * Sicoob
 * TipoPessoaServicoEJB.java 
 * Criado em: 01/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.io.Serializable;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.delegates.CAPESCadastroFabricaDelegates;
import br.com.sicoob.capes.cadastro.negocio.delegates.GrupoCompartilhamentoDelegate;
import br.com.sicoob.capes.cadastro.negocio.excecao.CompartilhamentoNaoPodeExcluirException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CompartilhamentoCadastroServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CompartilhamentoCadastroServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CompartilhamentoCadastroDAO;
import br.com.sicoob.capes.cadastro.persistencia.dao.impl.CompartilhamentoCadastroDAOImpl;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;

/**
 * 01/07/2011
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(CompartilhamentoCadastroServicoLocal.class)
@Remote(CompartilhamentoCadastroServicoRemote.class)
public class CompartilhamentoCadastroServicoEJB extends CAPESCadastroCrudDominioServicoEJB<CompartilhamentoCadastro>
		implements CompartilhamentoCadastroServicoRemote, CompartilhamentoCadastroServicoLocal {

	@Inject
	@Default
	private CompartilhamentoCadastroDAO compartilhamentoCadastroDAO;
	
	@PersistenceContext(unitName = "emCapes")
	private EntityManager emCapes;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CompartilhamentoCadastroDAO getDAO() {
		((CompartilhamentoCadastroDAOImpl) compartilhamentoCadastroDAO).setEntityManager(emCapes);
		return this.compartilhamentoCadastroDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {
		GrupoCompartilhamentoDelegate grupoCompartilhamentoDelegate = CAPESCadastroFabricaDelegates.getInstance().criarGrupoCompartilhamentoDelegate();
		CompartilhamentoCadastro compartilhamentoCadastro = obter(chave);
		
		if(grupoCompartilhamentoDelegate.verificarCompartilhamentoCadastro(compartilhamentoCadastro.getCodigo())){
			throw new CompartilhamentoNaoPodeExcluirException();
		}else{
			getDAO().excluir(chave);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void habilitarIntegracaoSRF(Integer idGrupoCompartilhamento, boolean ligar) throws BancoobException {
		getDAO().habilitarIntegracaoSRF(idGrupoCompartilhamento, ligar);
	}

}
