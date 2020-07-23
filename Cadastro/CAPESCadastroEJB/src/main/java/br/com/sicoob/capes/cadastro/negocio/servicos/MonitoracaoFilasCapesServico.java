/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dto.MonitoracaoDLQsCapesDTO;
import br.com.sicoob.capes.cadastro.negocio.dto.MonitoracaoFilasCapesDTO;

/**
 * Servi�o utilizado para monitorar as filas utilizadas no CAPES.
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
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	MonitoracaoDLQsCapesDTO monitorarDLQs() throws BancoobException;
	
	/**
	 * O m�todo Reprocessar dlq cadastro.
	 *
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void reprocessarDLQCadastro() throws BancoobException;

	/**
	 * O m�todo Limpar fila dlq consultas externas.
	 *
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void limparFilaDLQConsultasExternas() throws BancoobException;

}
