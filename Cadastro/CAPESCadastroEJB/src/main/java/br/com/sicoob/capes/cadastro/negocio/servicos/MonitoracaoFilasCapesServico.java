/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dto.MonitoracaoDLQsCapesDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.MonitoracaoFilasCapesDTO;

/**
 * Serviço utilizado para monitorar as filas utilizadas no CAPES.
 * @author erico.junior
 */
public interface MonitoracaoFilasCapesServico extends CAPESCadastroServico {

	/**
	 * @throws BancoobException
	 */
	MonitoracaoFilasCapesDTO monitorarFilas() throws BancoobException;
	
	/**
	 * Monitorar dl qs.
	 *
	 * @return MonitoracaoDLQsCapesDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	MonitoracaoDLQsCapesDTO monitorarDLQs() throws BancoobException;
	
	/**
	 * O método Reprocessar dlq cadastro.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void reprocessarDLQCadastro() throws BancoobException;

	/**
	 * O método Limpar fila dlq consultas externas.
	 *
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void limparFilaDLQConsultasExternas() throws BancoobException;

}
