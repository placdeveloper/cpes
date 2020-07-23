/* 
 * Sicoob
 * TipoPessoaServicoEJB.java 
 * Criado em: 01/07/2011
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
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoPessoaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoPessoaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoPessoaDAO;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;

/**
 * 01/07/2011
 * 
 * @author Rodrigo.Chaves
 */
@Stateless
@Local(TipoPessoaServicoLocal.class)
@Remote(TipoPessoaServicoRemote.class)
public class TipoPessoaServicoEJB extends CAPESCadastroCrudServicoEJB<TipoPessoa>
		implements TipoPessoaServicoRemote, TipoPessoaServicoLocal {

	@Inject
	@Default
	private TipoPessoaDAO tipoPessoaDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoPessoaDAO getDAO() {
		return this.tipoPessoaDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TipoPessoa> listar() throws BancoobException {
		return super.listar();
	}

}
