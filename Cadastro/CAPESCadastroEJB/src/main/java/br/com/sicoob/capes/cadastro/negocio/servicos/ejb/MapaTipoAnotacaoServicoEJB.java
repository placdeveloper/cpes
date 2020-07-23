/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.MapaTipoAnotacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.MapaTipoAnotacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.MapaTipoAnotacaoDAO;
import br.com.sicoob.capes.negocio.entidades.MapaTipoAnotacao;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;

/**
 * Implementa as operações do serviço de mapa de tipo de anotações.
 * 
 */
@Stateless
@Local({ MapaTipoAnotacaoServicoLocal.class })
@Remote({ MapaTipoAnotacaoServicoRemote.class })
public class MapaTipoAnotacaoServicoEJB extends CAPESCadastroCrudServicoEJB<MapaTipoAnotacao> 
		implements MapaTipoAnotacaoServicoRemote, MapaTipoAnotacaoServicoLocal {

	@Inject
	@Default
	private MapaTipoAnotacaoDAO mapaTipoAnotacaoDao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MapaTipoAnotacaoDAO getDAO() {
		return mapaTipoAnotacaoDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public MapaTipoAnotacao obterTipoAnotacaoAnotacaoExterna(MapaTipoAnotacao mapaTipoAnotacao) throws BancoobException {
		return getDAO().obterTipoAnotacaoAnotacaoExterna(mapaTipoAnotacao);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<MapaTipoAnotacao> obterMapasTipoAnotacaoPorTipoAnotacao(TipoAnotacao tipoAnotacao) throws BancoobException {
		return getDAO().obterMapasTipoAnotacaoPorTipoAnotacao(tipoAnotacao);
	}

}