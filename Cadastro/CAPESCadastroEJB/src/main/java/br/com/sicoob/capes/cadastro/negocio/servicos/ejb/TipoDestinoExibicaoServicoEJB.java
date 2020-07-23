package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoDestinoExibicaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoDestinoExibicaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.CAPESCadastroCrudDaoIF;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoDestinoExibicaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoDestinoExibicao;

/**
 * EJB contendo servicos relacionados a TipoDestinoExibicao.
 */
@Stateless
@Local( { TipoDestinoExibicaoServicoLocal.class })
@Remote( { TipoDestinoExibicaoServicoRemote.class })
public class TipoDestinoExibicaoServicoEJB extends CAPESCadastroCrudServicoEJB<TipoDestinoExibicao>{
	
	@Inject
	@Default
	private TipoDestinoExibicaoDAO tipoDestinoExibicaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESCadastroCrudDaoIF<TipoDestinoExibicao> getDAO() {
		return tipoDestinoExibicaoDAO;
	}
	
}