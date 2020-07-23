package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizacaoDocumentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.AutorizacaoDocumentoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.AutorizacaoDocumentoDAO;
import br.com.sicoob.capes.negocio.entidades.AutorizacaoDocumento;

/**
 * EJB contendo servicos relacionados a AutorizacaoDocumento.
 */
@Stateless
@Remote(AutorizacaoDocumentoServicoRemote.class)
@Local(AutorizacaoDocumentoServicoLocal.class)
public class AutorizacaoDocumentoServicoEJB extends
		CAPESCadastroCrudServicoEJB<AutorizacaoDocumento> implements AutorizacaoDocumentoServicoRemote, AutorizacaoDocumentoServicoLocal {

	@Inject
	@Default
	private AutorizacaoDocumentoDAO dao;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AutorizacaoDocumentoDAO getDAO() {
		return dao;
	}

}
