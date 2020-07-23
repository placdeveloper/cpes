/**
 * 
 */
package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.legado.Pessoa;

/**
 * Servi�o utilizado para o hist�rico do cedente
 * 
 * @author erico.junior
 */
public interface HistoricoCedenteServico extends CAPESReplicacaoComumServico {

	/**
	 * O m�todo Incluir historico cendente.
	 *
	 * @param pessoa o valor de pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void incluirHistoricoCendente(Pessoa pessoa, Integer idInstituicao)
			throws BancoobException;
}
