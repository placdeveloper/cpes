/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;

/**
 * Serviço utilizado para o histórico do cedente
 * 
 * @author erico.junior
 */
public interface HistoricoCedenteServico extends CAPESReplicacaoComumServico {

	/**
	 * O método Incluir historico cendente.
	 *
	 * @param pessoa o valor de pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void incluirHistoricoCendente(Pessoa pessoa, Integer idInstituicao)
			throws BancoobException;
}
