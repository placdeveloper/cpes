/**
 * 
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
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoDocumentoIdentificacaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoDocumentoIdentificacaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoDocumentoIdentificacaoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoDocumentoIdentificacao;

/**
 * @author Erico.Junior
 * 
 */
@Stateless
@Local( { TipoDocumentoIdentificacaoServicoLocal.class })
@Remote( { TipoDocumentoIdentificacaoServicoRemote.class })
public class TipoDocumentoIdentificacaoServicoEJB extends
		CAPESCadastroCrudServicoEJB<TipoDocumentoIdentificacao> implements
		TipoDocumentoIdentificacaoServicoRemote, TipoDocumentoIdentificacaoServicoLocal {

	@Inject
	@Default
	protected TipoDocumentoIdentificacaoDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoDocumentoIdentificacaoDAO getDAO() {
		return dao;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TipoDocumentoIdentificacao> listar() throws BancoobException {
		return super.listar();
	}

}
