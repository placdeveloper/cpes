package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.DestinoExportacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.DestinoExportacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.DestinoExportacaoDAO;
import br.com.sicoob.capes.negocio.entidades.DestinoExportacao;

/**
 * EJB contendo servicos relacionados a DestinoExportacao.
 */
@Stateless
@Local({ DestinoExportacaoServicoLocal.class })
@Remote({ DestinoExportacaoServicoRemote.class })
public class DestinoExportacaoServicoEJB extends CAPESCadastroCrudServicoEJB<DestinoExportacao> implements DestinoExportacaoServicoRemote, DestinoExportacaoServicoLocal {

	@Inject
	@Default
	protected DestinoExportacaoDAO destinoExportacaoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DestinoExportacaoDAO getDAO() {
		return destinoExportacaoDAO;
	}

}