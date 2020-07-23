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
import br.com.sicoob.capes.cadastro.negocio.dominio.replicacao.facade.NacionalidadeFachada;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.NacionalidadeServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.NacionalidadeServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.NacionalidadeDAO;
import br.com.sicoob.capes.negocio.entidades.Nacionalidade;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local({ NacionalidadeServicoLocal.class })
@Remote({ NacionalidadeServicoRemote.class })
public class NacionalidadeServicoEJB extends CAPESCadastroCrudDominioServicoEJB<Nacionalidade> implements NacionalidadeServicoRemote, NacionalidadeServicoLocal {

	@Inject
	@Default
	private NacionalidadeDAO dao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected NacionalidadeDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(Nacionalidade objeto) throws BancoobException {
		super.alterar(objeto);
		replicarNacionalidade(objeto);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Nacionalidade incluir(Nacionalidade objeto) throws BancoobException {
		Nacionalidade nacionalidadeIncluida = super.incluir(objeto);
		replicarNacionalidade(nacionalidadeIncluida);
		return nacionalidadeIncluida;
	}

	private void replicarNacionalidade(Nacionalidade nacionalidade) throws BancoobException {
		NacionalidadeFachada nacionalidadeFachada = new NacionalidadeFachada();
		nacionalidadeFachada.replicarNacionalidade(nacionalidade);
	}
	
}
