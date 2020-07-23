package br.com.sicoob.capes.cadastro.negocio.dominio.autorizacao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.negocio.entidades.Autorizacao;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;

/**
 * A Interface SalvamentoAtualizacaoStrategy.
 */
public interface SalvamentoAtualizacaoStrategy {

	/**
	 * Execute.
	 *
	 * @param aprovavel o valor de aprovavel
	 * @param tipoOperacao o valor de tipo operacao
	 * @return Autorizacao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Autorizacao execute(Aprovavel aprovavel, TipoOperacaoEnum tipoOperacao) throws BancoobException;

}
