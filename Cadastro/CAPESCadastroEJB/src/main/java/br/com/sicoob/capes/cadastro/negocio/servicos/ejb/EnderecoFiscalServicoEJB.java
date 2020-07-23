package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EnderecoFiscalServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.EnderecoFiscalServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.EnderecoFiscalDAO;
import br.com.sicoob.capes.negocio.entidades.EnderecoFiscal;

/**
 * EJB contendo servicos relacionados a EnderecoFiscal.
 */
@Stateless
@Local( { EnderecoFiscalServicoLocal.class })
@Remote( { EnderecoFiscalServicoRemote.class })
public class EnderecoFiscalServicoEJB extends
CAPESCadastroCrudServicoEJB<EnderecoFiscal> implements EnderecoFiscalServicoRemote, EnderecoFiscalServicoLocal {

	@Inject
	@Default
	private EnderecoFiscalDAO enderecoFiscalDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoFiscalDAO getDAO() {
		return enderecoFiscalDAO;
	}
	
}
