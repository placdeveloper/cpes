package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoPessoaContatoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoPessoaContatoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoPessoaContatoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoPessoaContato;

/**
 * @author diego.rezende
 * 
 */
@Stateless
@Local(TipoPessoaContatoServicoLocal.class)
@Remote(TipoPessoaContatoServicoRemote.class)
public class TipoPessoaContatoServicoEJB extends CAPESCadastroCrudServicoEJB<TipoPessoaContato> implements TipoPessoaContatoServicoRemote,
		TipoPessoaContatoServicoLocal {

	@Inject
	@Default
	private TipoPessoaContatoDAO tipoPessoaContatoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoPessoaContatoDAO getDAO() {
		return this.tipoPessoaContatoDAO;
	}


}
