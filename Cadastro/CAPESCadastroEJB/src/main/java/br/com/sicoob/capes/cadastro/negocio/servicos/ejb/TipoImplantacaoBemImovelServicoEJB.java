package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoImplantacaoBemImovelServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.TipoImplantacaoBemImovelServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.TipoImplantacaoBemImovelDAO;
import br.com.sicoob.capes.negocio.entidades.bem.TipoImplantacaoBemImovel;

/**
 * Classe contendo os servi�os de tipo de implanta��o do bem im�vel.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Local(TipoImplantacaoBemImovelServicoLocal.class)
@Remote(TipoImplantacaoBemImovelServicoRemote.class)
public class TipoImplantacaoBemImovelServicoEJB extends CAPESCadastroCrudServicoEJB<TipoImplantacaoBemImovel> implements TipoImplantacaoBemImovelServicoLocal, TipoImplantacaoBemImovelServicoRemote {

	@Inject
	@Default
	private TipoImplantacaoBemImovelDAO dao;

	@Override
	protected TipoImplantacaoBemImovelDAO getDAO() {
		return dao;
	}

}