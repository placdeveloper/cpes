/* 
 * Sicoob
 * TipoPoderRelacionamentoServicoEJB.java 
 * Criado em: 29/08/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoPoderRelacionamentoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoPoderRelacionamentoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoPoderRelacionamentoDAO;
import br.com.sicoob.capes.negocio.entidades.TipoPoderRelacionamento;

/**
 * Servico para {@link TipoPoderRelacionamento}
 *
 * 29/08/2011
 * @author rodrigo.chaves
 */
@Stateless
@Local(TipoPoderRelacionamentoServicoLocal.class)
@Remote(TipoPoderRelacionamentoServicoRemote.class)
public class TipoPoderRelacionamentoServicoEJB extends
		CAPESCadastroCrudServicoEJB<TipoPoderRelacionamento> implements
		TipoPoderRelacionamentoServicoRemote, TipoPoderRelacionamentoServicoLocal {

	@Inject
	@Default
	private TipoPoderRelacionamentoDAO tipoPoderRelacionamentoDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoPoderRelacionamentoDAO getDAO() {
		return this.tipoPoderRelacionamentoDAO;
	}


}
