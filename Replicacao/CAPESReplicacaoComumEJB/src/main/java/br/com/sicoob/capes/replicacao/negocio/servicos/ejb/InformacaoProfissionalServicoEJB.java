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
import br.com.bancoob.persistencia.excecao.ViolacaoIntegridadeException;
import br.com.sicoob.capes.negocio.entidades.legado.InformacaoProfissional;
import br.com.sicoob.capes.replicacao.negocio.excecao.FuncionarioLegadoExclusaoException;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.InformacaoProfissionalServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.InformacaoProfissionalServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.InformacaoProfissionalDAO;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local({ InformacaoProfissionalServicoLocal.class })
@Remote({ InformacaoProfissionalServicoRemote.class })
public class InformacaoProfissionalServicoEJB extends
		EntidadeReplicavelServicoEJB<InformacaoProfissional> implements
		InformacaoProfissionalServicoRemote, InformacaoProfissionalServicoLocal {

	@Inject
	@Default
	private InformacaoProfissionalDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InformacaoProfissionalDAO getDAO() {
		return dao;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(InformacaoProfissional objeto, Integer idInstituicao) throws BancoobException {
		try {
			super.excluir(objeto, idInstituicao);
		} catch (ViolacaoIntegridadeException e) {
			throw new FuncionarioLegadoExclusaoException(e);
		}
	}
}

