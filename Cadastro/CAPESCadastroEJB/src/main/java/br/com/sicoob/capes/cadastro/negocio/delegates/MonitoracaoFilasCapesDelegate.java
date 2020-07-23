/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dto.MonitoracaoDLQsCapesDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.MonitoracaoFilasCapesDTO;
import br.com.sicoob.capes.cadastro.negocio.servicos.MonitoracaoFilasCapesServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;

/**
 * @author erico.junior
 * 
 */
public class MonitoracaoFilasCapesDelegate extends
		CAPESCadastroDelegate<MonitoracaoFilasCapesServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MonitoracaoFilasCapesServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarMonitoracaoFilasCapesServico();
	}

	/**
	 * @throws BancoobException
	 */
	public MonitoracaoFilasCapesDTO monitorarFilas() throws BancoobException {
		return getServico().monitorarFilas();
	}

	/**
	 * Monitorar dl qs.
	 *
	 * @return MonitoracaoDLQsCapesDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public MonitoracaoDLQsCapesDTO monitorarDLQs() throws BancoobException {
		return getServico().monitorarDLQs();
	}
	
	/**
	 * O método Reprocessar dlq cadastro.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void reprocessarDLQCadastro() throws BancoobException {
		getServico().reprocessarDLQCadastro();
	}

	/**
	 * O método Limpar fila dlq consultas externas.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void limparFilaDLQConsultasExternas() throws BancoobException {
		getServico().limparFilaDLQConsultasExternas();
	}
}