/*
 * SICOOB
 * 
 * BemPessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.BemPessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IBemPessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.BemPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.BemPessoaVO;

/**
 * @author Lucas.Borges
 * 
 * @deprecated Utilizar o novo delegate de bens
 *             {@link br.com.sicoob.capes.api.negocio.delegates.BemDelegate}
 * 
 * @see br.com.sicoob.capes.api.negocio.delegates.BemDelegate
 */
@Deprecated
public class BemPessoaDelegate extends AbstractCAPESApiPessoaDelegate<BemPessoaVO, BemPessoaServico> implements IBemPessoaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemPessoaServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarBemPessoaServico();
	}

	/**
	 * Bloqueia o bem para alteração.
	 * 
	 * @param codigoUtilizaInformacao
	 *            código do sistema que utiliza a informação
	 * @param idBemPessoa
	 *            identificador do bem.
	 * @throws BancoobException
	 */
	public void bloquearBem(Short codigoUtilizaInformacao, Long idBemPessoa) throws BancoobException {
		getServico().bloquearBem(codigoUtilizaInformacao, idBemPessoa);
	}
}