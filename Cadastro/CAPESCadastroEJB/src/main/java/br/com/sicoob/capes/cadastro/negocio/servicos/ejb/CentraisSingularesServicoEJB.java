package br.com.sicoob.capes.cadastro.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CentraisSingularesServicoLocal;
import br.com.sicoob.capes.cadastro.negocio.servicos.interfaces.CentraisSingularesServicoRemote;
import br.com.sicoob.capes.cadastro.negocio.vo.CentralSingularVO;
import br.com.sicoob.capes.cadastro.negocio.vo.UnidadeVO;
import br.com.sicoob.capes.cadastro.persistencia.dao.CentraisSingularesDAO;
import br.com.sicoob.capes.integracao.negocio.servicos.interfaces.SCIIntegracaoServicoLocal;

/**
 * EJB contendo servicos relacionados a CentraisSingulares.
 */
@Stateless
@Local({ CentraisSingularesServicoLocal.class })
@Remote({ CentraisSingularesServicoRemote.class })
public class CentraisSingularesServicoEJB extends CAPESCadastroServicoEJB implements CentraisSingularesServicoLocal, CentraisSingularesServicoRemote {
	
	@Inject
	@Default
	protected CentraisSingularesDAO centraisSingularesDAO;
	
	/** O atributo servicoIntegracao. */
	@EJB(mappedName = "capes/integracao/SCIIntegracaoServico")
	private SCIIntegracaoServicoLocal servicoIntegracao;

	/**
	 * {@inheritDoc}
	 */
	public List<CentralSingularVO> obterListaCentrais() throws BancoobException {
		return centraisSingularesDAO.obterListaCentrais(obterInstituicaoUsuarioLogado().getIdInstituicao());
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CentralSingularVO> obterListaSingulares(Integer numeroCooperativa) throws BancoobException {
		List<CentralSingularVO> listaSingulares = null;
		if(servicoIntegracao.isConfederacao(obterInstituicaoUsuarioLogado().getIdInstituicao())){
			//Parâmetro numcentral
			listaSingulares = centraisSingularesDAO.obterListaSingulares(numeroCooperativa);
		}else{
			//Parâmetro idInst usuário logado
			listaSingulares = centraisSingularesDAO.obterListaSingulares(Integer.valueOf(obterInstituicaoUsuarioLogado().getNumero()));
		}
		return listaSingulares;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<UnidadeVO> obterListaPacs(Integer numeroCooperativa) throws BancoobException {
		return centraisSingularesDAO.obterListaPacs(numeroCooperativa);
	}

}