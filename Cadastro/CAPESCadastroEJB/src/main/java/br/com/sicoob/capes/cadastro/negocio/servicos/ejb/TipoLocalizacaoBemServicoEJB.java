package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoLocalizacaoBemServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoLocalizacaoBemServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoLocalizacaoBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoLocalizacaoBem;

/**
 * Classe que contém os serviços de tipo localização do bem.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Local(TipoLocalizacaoBemServicoLocal.class)
@Remote(TipoLocalizacaoBemServicoRemote.class)
public class TipoLocalizacaoBemServicoEJB extends CAPESCadastroCrudServicoEJB<TipoLocalizacaoBem> implements TipoLocalizacaoBemServicoLocal, TipoLocalizacaoBemServicoRemote {

	@Inject
	@Default
	private TipoLocalizacaoBemDAO dao;

	@Override
	protected TipoLocalizacaoBemDAO getDAO() {
		return dao;
	}

}