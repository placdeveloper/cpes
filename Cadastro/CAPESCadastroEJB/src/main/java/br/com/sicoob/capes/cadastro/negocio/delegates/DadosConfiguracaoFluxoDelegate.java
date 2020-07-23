package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.DadosConfiguracaoFluxoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.DadosConfiguracaoFluxo;

/**
 * A Classe DadosConfiguracaoFluxoDelegate.
 */
public class DadosConfiguracaoFluxoDelegate extends CAPESCadastroCrudDelegate<DadosConfiguracaoFluxo, DadosConfiguracaoFluxoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DadosConfiguracaoFluxoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarDadosConfiguracaoFluxoServico();
	}

	/**
	 * Obter.
	 *
	 * @param isResponsavel o valor de is responsavel
	 * @param isGestor o valor de is gestor
	 * @param possuiDocumento o valor de possui documento
	 * @return DadosConfiguracaoFluxo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public DadosConfiguracaoFluxo obter(Boolean isResponsavel, Boolean isGestor, Boolean possuiDocumento) throws BancoobException {
		return getServico().obter(isResponsavel, isGestor, possuiDocumento);
	}

	/**
	 * Obter siglas processo.
	 *
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<String> obterSiglasProcesso() throws BancoobException {
		return getServico().obterSiglasProcesso();
	}
}