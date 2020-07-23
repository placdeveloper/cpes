/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.FinalidadeEmpreendimentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.FinalidadeEmpreendimentoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.FinalidadeEmpreendimentoDAO;
import br.com.sicoob.capes.negocio.entidades.FinalidadeEmpreendimento;

/**
 * 
 * @author diego.rezende
 */
@Stateless
@Local( { FinalidadeEmpreendimentoServicoLocal.class })
@Remote( { FinalidadeEmpreendimentoServicoRemote.class })
public class FinalidadeEmpreendimentoServicoEJB extends
		CAPESCadastroCrudServicoEJB<FinalidadeEmpreendimento> implements FinalidadeEmpreendimentoServicoRemote, FinalidadeEmpreendimentoServicoLocal {

	@Inject
	@Default
	private FinalidadeEmpreendimentoDAO dao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected FinalidadeEmpreendimentoDAO getDAO() {
		return dao;
	}

}
