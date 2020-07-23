/*
 * SICOOB
 * 
 * AtualizarAnotacoesSisbrDelegate.java(br.com.sicoob.capes.api.negocio.delegates.AtualizarAnotacoesSisbrDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IAtualizarAnotacoesSisbrDelegate;
import br.com.sicoob.capes.api.negocio.servicos.AtualizarAnotacoesSisbrServico;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;

/**
 * Delegate utilizada para as anota��es de origem SISBR.
 * 
 * @author erico.junior
 */
public class AtualizarAnotacoesSisbrDelegate extends CAPESApiDelegate<AtualizarAnotacoesSisbrServico> implements IAtualizarAnotacoesSisbrDelegate {
	
	/**
	 * 
	 */
	protected AtualizarAnotacoesSisbrDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static AtualizarAnotacoesSisbrDelegate getInstance() {
		return valueOf(AtualizarAnotacoesSisbrDelegate.class);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected AtualizarAnotacoesSisbrServico localizarServico() {
		return getLocator().localizarAtualizarAnotacoesSisbrServico();
	}

	/**
	 * Baixa uma anota��o de origem SISBR
	 * 
	 * @param dto
	 *            O dto com as informa��es da anota��o.
	 * @return As anota��es baixadas a partir das informa��o do dto.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public void baixarAnotacao(AnotacaoSisbrDTO dto) throws BancoobException {
		getServico().baixarAnotacao(dto);
	}

	/**
	 * Inclui uma anota��o de origem SISBR
	 * 
	 * @param dto
	 *            O dto com as informa��es da anota��o.
	 * @return o ID da anota��o inclu�da.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	public Long incluirAnotacao(AnotacaoSisbrDTO dto) throws BancoobException {
		return getServico().incluirAnotacao(dto);
	}

}
