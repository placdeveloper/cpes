package br.com.sicoob.capes.api.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.excecao.ParametroInvalidoException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.OcupacaoProfissionalServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.OcupacaoProfissionalServicoRemote;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.OcupacaoProfissionalDAO;

/**
 * Classe com os serviços de ocupação profissional.
 * 
 * @author Bruno.Carneiro
 * 
 */
@Stateless
@Local(OcupacaoProfissionalServicoLocal.class)
@Remote(OcupacaoProfissionalServicoRemote.class)
public class OcupacaoProfissionalServicoEJB extends CAPESApiServicoEJB implements OcupacaoProfissionalServicoLocal, OcupacaoProfissionalServicoRemote {

	@Inject
	@Default
	private OcupacaoProfissionalDAO ocupacaoProfissionalDAO;

	@Override
	protected CAPESApiDao obterDAO() {
		return ocupacaoProfissionalDAO;
	}
	
	@Override
	protected <V extends FiltroConsultaAPIAbstrato> void validarFiltroPesquisar(FiltroConsultaAPIAbstrato filtro) throws BancoobException {
		if (filtro == null) {
			throw new ParametroInvalidoException();
		}
	}

}