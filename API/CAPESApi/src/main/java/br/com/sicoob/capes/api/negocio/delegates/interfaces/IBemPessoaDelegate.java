package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.BemPessoaVO;

/**
 * @author paulo.stoppa
 * 
 * @deprecated Utilizar o novo delegate de bens {@link br.com.sicoob.capes.api.negocio.delegates.BemDelegate}
 * 
 * @see br.com.sicoob.capes.api.negocio.delegates.BemDelegate
 */
@Deprecated
public interface IBemPessoaDelegate extends IAbstractCAPESApiPessoaDelegate<BemPessoaVO> {

	/**
	 * Bloqueia o bem para altera��o.
	 * 
	 * @param codigoUtilizaInformacao
	 *            c�digo do sistema que utiliza a informa��o
	 * @param idBemPessoa
	 *            identificador do bem.
	 * @throws BancoobException
	 */
	void bloquearBem(Short codigoUtilizaInformacao, Long idBemPessoa) throws BancoobException;

}