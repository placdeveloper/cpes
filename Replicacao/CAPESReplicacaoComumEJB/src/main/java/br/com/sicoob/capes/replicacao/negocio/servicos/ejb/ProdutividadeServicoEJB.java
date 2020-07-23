/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.util.Date;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Produtividade;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ProdutividadeServicoLocal;
import br.com.sicoob.capes.replicacao.negocio.servicos.interfaces.ProdutividadeServicoRemote;
import br.com.sicoob.capes.replicacao.persistencia.dao.ProdutividadeDAO;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local( { ProdutividadeServicoLocal.class })
@Remote( { ProdutividadeServicoRemote.class })
public class ProdutividadeServicoEJB extends
		EntidadeReplicavelServicoEJB<Produtividade> implements ProdutividadeServicoRemote, ProdutividadeServicoLocal {

	@Inject
	@Default
	private transient ProdutividadeDAO produtorDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProdutividadeDAO getDAO() {
		return produtorDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Produtividade produtividade, Integer idInstituicao)
			throws BancoobException {
		produtividade.setDataInativacao(new Date());
		alterar(produtividade, idInstituicao);
	}
	
}
