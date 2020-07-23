package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoClassificacaoBemServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoClassificacaoBemServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoClassificacaoBemDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoClassificacaoBem;

/**
 * Classe com os serviços de Tipo de classificação do bem.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Local(TipoClassificacaoBemServicoLocal.class)
@Remote(TipoClassificacaoBemServicoRemote.class)
public class TipoClassificacaoBemServicoEJB extends CAPESCadastroCrudServicoEJB<TipoClassificacaoBem> implements TipoClassificacaoBemServicoLocal, TipoClassificacaoBemServicoRemote {

	@Inject
	@Default
	private TipoClassificacaoBemDAO dao;

	@Override
	protected TipoClassificacaoBemDAO getDAO() {
		return dao;
	}

}