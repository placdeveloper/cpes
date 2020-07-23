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
import br.com.sicoob.capes.negocio.entidades.legado.AvaliacaoFinanceira;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.AvaliacaoFinanceiraServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.AvaliacaoFinanceiraServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.AvaliacaoFinanceiraDAO;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local({ AvaliacaoFinanceiraServicoLocal.class })
@Remote({ AvaliacaoFinanceiraServicoRemote.class })
public class AvaliacaoFinanceiraServicoEJB extends
		EntidadeReplicavelServicoEJB<AvaliacaoFinanceira> implements
		AvaliacaoFinanceiraServicoRemote, AvaliacaoFinanceiraServicoLocal {

	@Inject
	@Default
	private transient AvaliacaoFinanceiraDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AvaliacaoFinanceiraDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AvaliacaoFinanceira obterPorIdDB2(AvaliacaoFinanceira entidade,
			Integer idInstituicao) throws BancoobException {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(AvaliacaoFinanceira avaliacaoFinanceira, Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		getDAO().atualizarHistorico(avaliacaoFinanceira.getNumPessoa(), numeroCooperativa);
		super.alterar(avaliacaoFinanceira, idInstituicao);
	}
	
}
