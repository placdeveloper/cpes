package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.CentraisSingularesServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.cadastro.negocio.vo.CentralSingularVO;
import br.com.sicoob.capes.cadastro.negocio.vo.UnidadeVO;

/**
 * A Classe CentraisSingularesDelegate.
 */
public class CentraisSingularesDelegate extends CAPESCadastroDelegate<CentraisSingularesServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CentraisSingularesServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarCentraisSingularesServico();
	}

	/**
	 * Obter lista centrais.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<CentralSingularVO> obterListaCentrais() throws BancoobException {
		return getServico().obterListaCentrais();
	}

	/**
	 * Obter lista singulares.
	 *
	 * @param numeroCooperativa o valor de numero cooperativa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<CentralSingularVO> obterListaSingulares(Integer numeroCooperativa) throws BancoobException {
		return getServico().obterListaSingulares(numeroCooperativa);
	}
	
	/**
	 * Obter lista pacs.
	 *
	 * @param numeroCooperativa o valor de numero cooperativa
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<UnidadeVO> obterListaPacs(Integer numeroCooperativa) throws BancoobException {
		return getServico().obterListaPacs(numeroCooperativa);
	}

}