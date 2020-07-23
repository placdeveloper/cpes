package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoMensagemServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoMensagemServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoMensagemDAO;
import br.com.sicoob.capes.negocio.entidades.TipoMensagem;

/**
 * EJB contendo servicos relacionados a TipoMensagem.
 */
@Stateless
@Local({ TipoMensagemServicoLocal.class })
@Remote({ TipoMensagemServicoRemote.class })
public class TipoMensagemServicoEJB extends CAPESCadastroCrudServicoEJB<TipoMensagem> implements TipoMensagemServicoLocal, TipoMensagemServicoRemote {

	@Inject
	@Default
	private TipoMensagemDAO tipoMensagemDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoMensagemDAO getDAO() {
		return tipoMensagemDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<TipoMensagem> listaDeTipoMensagensDoTipoDestinoExibicao(Short codTipoDestinoExibicao) {
		return tipoMensagemDAO.listaDeTipoMensagensDoTipoDestinoExibicao(codTipoDestinoExibicao);
	}

}