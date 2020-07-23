/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.OrigemInformacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.OrigemInformacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.OrigemInformacaoDAO;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;

/**
 * Implementa as operações do serviço de origem da informação.
 * 
 * @author Erico.Junior
 */
@Stateless
@Local( { OrigemInformacaoServicoLocal.class })
@Remote( { OrigemInformacaoServicoRemote.class })
public class OrigemInformacaoServicoEJB extends
		CAPESCadastroCrudServicoEJB<OrigemInformacao> implements
		OrigemInformacaoServicoRemote, OrigemInformacaoServicoLocal {

	@Inject
	@Default
	protected OrigemInformacaoDAO origemInformacaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected OrigemInformacaoDAO getDAO() {
		return origemInformacaoDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public OrigemInformacao obterOrigemPorNome(OrigemInformacao origem) throws BancoobException {
		return getDAO().obterOrigemPorNome(origem);
	}

}
