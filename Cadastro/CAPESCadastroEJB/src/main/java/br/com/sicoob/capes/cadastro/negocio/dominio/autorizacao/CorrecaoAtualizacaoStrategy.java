// 12/03/2013 - 08:12:47
package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * @author Rodrigo.Chaves
 */
public interface CorrecaoAtualizacaoStrategy {

	/**
	 * O método Execute.
	 *
	 * @param <A> o tipo generico
	 * @param autorizacao o valor de autorizacao
	 * @param aprovavel o valor de aprovavel
	 * @param tipoOperacao o valor de tipo operacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	<A extends CAPESEntidade<Long> & Aprovavel> void execute(
			Autorizacao autorizacao, A aprovavel, TipoOperacaoEnum tipoOperacao)
			throws BancoobException;
}
