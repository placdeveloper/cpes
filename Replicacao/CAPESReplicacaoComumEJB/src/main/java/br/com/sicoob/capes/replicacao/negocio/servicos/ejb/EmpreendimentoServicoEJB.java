/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Empreendimento;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.EmpreendimentoServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.EmpreendimentoServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.EmpreendimentoDAO;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local({ EmpreendimentoServicoLocal.class })
@Remote({ EmpreendimentoServicoRemote.class })
public class EmpreendimentoServicoEJB extends
		CAPESReplicacaoComumCrudServicoEJB<Empreendimento> implements
		EmpreendimentoServicoRemote, EmpreendimentoServicoLocal {

	@Inject
	@Default
	private transient EmpreendimentoDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EmpreendimentoDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	public Empreendimento obterProdLab(Integer codigo, Integer idCooperativaProdlab) throws BancoobException {
		return getDAO().obter(codigo, idCooperativaProdlab);
	}

	/**
	 * {@inheritDoc}
	 */
	public void incluirProdLab(Empreendimento empreendimentoReplicacao, Integer idCooperativaProdlab) throws BancoobException {
		getDAO().incluir(empreendimentoReplicacao, idCooperativaProdlab);
	}

	/**
	 * {@inheritDoc}
	 */
	public void alterarProdLab(Empreendimento empreendimentoReplicacao, Integer idCooperativaProdlab) throws BancoobException {
		getDAO().alterar(empreendimentoReplicacao, idCooperativaProdlab);
	}
}
