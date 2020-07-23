package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.InformacaoUtilizadaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.InformacaoUtilizada;

/**
 * A Classe InformacaoUtilizadaDelegate.
 */
public class InformacaoUtilizadaDelegate extends CAPESCadastroCrudDelegate<InformacaoUtilizada, InformacaoUtilizadaServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected InformacaoUtilizadaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarInformacaoUtilizadaServico();
	}

	/**
	 * Listar sistemas usando informacao.
	 *
	 * @param codigoTipoInformacao o valor de codigo tipo informacao
	 * @param codigoInformacao o valor de codigo informacao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<String> listarSistemasUsandoInformacao(Short codigoTipoInformacao, Long codigoInformacao) throws BancoobException {
		return getServico().listarSistemasUsandoInformacao(codigoTipoInformacao, codigoInformacao);
	}
}