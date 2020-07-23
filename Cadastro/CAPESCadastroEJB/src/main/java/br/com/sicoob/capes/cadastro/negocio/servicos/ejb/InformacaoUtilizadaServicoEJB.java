package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.InformacaoUtilizadaServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.InformacaoUtilizadaServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.InformacaoUtilizadaDAO;
import br.com.sicoob.capes.negocio.entidades.InformacaoUtilizada;

/**
 * EJB contendo servicos relacionados a InformacaoUtilizada.
 */
@Stateless
@Local({ InformacaoUtilizadaServicoLocal.class })
@Remote({ InformacaoUtilizadaServicoRemote.class })
public class InformacaoUtilizadaServicoEJB extends CAPESCadastroCrudServicoEJB<InformacaoUtilizada> implements InformacaoUtilizadaServicoRemote, InformacaoUtilizadaServicoLocal {

	@Inject
	@Default
	protected InformacaoUtilizadaDAO informacaoUtilizadaDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InformacaoUtilizadaDAO getDAO() {
		return informacaoUtilizadaDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> listarSistemasUsandoInformacao(Short codigoTipoInformacao, Long codigoInformacao) throws BancoobException {
		return getDAO().listarSistemasUsandoInformacao(codigoTipoInformacao, codigoInformacao);
	}

}