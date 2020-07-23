package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.DadosConfiguracaoFluxoServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.DadosConfiguracaoFluxoServicoRemote;
import br.com.sicoob.capes.cadastro.persistencia.dao.DadosConfiguracaoFluxoDAO;
import br.com.sicoob.capes.negocio.entidades.DadosConfiguracaoFluxo;

/**
 * EJB contendo servicos relacionados a DadosConfiguracaoFluxo.
 */
@Stateless
@Local(DadosConfiguracaoFluxoServicoLocal.class)
@Remote(DadosConfiguracaoFluxoServicoRemote.class)
public class DadosConfiguracaoFluxoServicoEJB extends
		CAPESCadastroCrudServicoEJB<DadosConfiguracaoFluxo> implements
		DadosConfiguracaoFluxoServicoRemote, DadosConfiguracaoFluxoServicoLocal {
	
	@Inject
	@Default
	protected DadosConfiguracaoFluxoDAO dadosConfiguracaoFluxoDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DadosConfiguracaoFluxoDAO getDAO() {
		return this.dadosConfiguracaoFluxoDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public DadosConfiguracaoFluxo obter(Boolean isResponsavel, Boolean isGestor,
			Boolean possuiDocumento) throws BancoobException {
		return getDAO().obter(isResponsavel, isGestor, possuiDocumento);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<String> obterSiglasProcesso() throws BancoobException {
		return getDAO().obterSiglasProcesso();
	}

}
