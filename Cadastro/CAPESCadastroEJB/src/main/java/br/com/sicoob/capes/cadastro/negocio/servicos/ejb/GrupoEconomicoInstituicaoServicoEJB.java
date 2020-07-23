package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoInstituicaoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.GrupoEconomicoInstituicaoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.GrupoEconomicoInstituicaoDAO;
import br.com.sicoob.capes.negocio.entidades.GrupoEconomicoInstituicao;

/**
 * EJB contendo servicos relacionados a GrupoEconomicoInstituicao.
 */
@Stateless
@Remote(GrupoEconomicoInstituicaoServicoRemote.class)
@Local(GrupoEconomicoInstituicaoServicoLocal.class)
public class GrupoEconomicoInstituicaoServicoEJB extends CAPESCadastroCrudServicoEJB<GrupoEconomicoInstituicao>
		implements GrupoEconomicoInstituicaoServicoRemote, GrupoEconomicoInstituicaoServicoLocal {

	@Inject
	@Default
	private GrupoEconomicoInstituicaoDAO grupoEconomicoInstituicaoDAO;

	/**
	 * {@inheritDoc}
	 */
	protected GrupoEconomicoInstituicaoDAO getDAO() {
		return grupoEconomicoInstituicaoDAO;
	}

}
