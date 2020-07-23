/*
 * SICOOB
 * 
 * AtualizarAnotacoesSisbrDelegate.java(br.com.sicoob.capes.api.negocio.delegates.AtualizarAnotacoesSisbrDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IAtualizarAnotacoesSisbrDelegate;
import br.com.sicoob.capes.api.negocio.servicos.AtualizarAnotacoesSisbrServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;

/**
 * Delegate utilizada para as anotações de origem SISBR.
 * 
 * @author erico.junior
 */
public class AtualizarAnotacoesSisbrDelegate extends CAPESApiDelegate<AtualizarAnotacoesSisbrServico> implements IAtualizarAnotacoesSisbrDelegate {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected AtualizarAnotacoesSisbrServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarAtualizarAnotacoesSisbrServico();
	}

	/**
	 * Baixa uma anotação de origem SISBR
	 * 
	 * @param dto
	 *            O dto com as informações da anotação.
	 * @return As anotações baixadas a partir das informação do dto.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public void baixarAnotacao(AnotacaoSisbrDTO dto) throws BancoobException {
		getServico().baixarAnotacao(dto);
	}

	/**
	 * Inclui uma anotação de origem SISBR
	 * 
	 * @param dto
	 *            O dto com as informações da anotação.
	 * @return o ID da anotação incluída.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	public Long incluirAnotacao(AnotacaoSisbrDTO dto) throws BancoobException {
		return getServico().incluirAnotacao(dto);
	}

}
