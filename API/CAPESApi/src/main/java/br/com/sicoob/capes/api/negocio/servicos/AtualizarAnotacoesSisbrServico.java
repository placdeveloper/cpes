/*
 * SICOOB
 * 
 * AtualizarAnotacoesSisbrServico.java(br.com.sicoob.capes.api.negocio.servicos.AtualizarAnotacoesSisbrServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoSisbrDTO;

/**
 * Define as interfaces para a atualiza��o de anota��es do sisbr.
 * 
 * @author Erico.Junior
 */
public interface AtualizarAnotacoesSisbrServico extends CAPESApiServico {

	/**
	 * Inclui uma anota��o de origem SISBR
	 * @param dto O dto com as informa��es da anota��o.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	Long incluirAnotacao(AnotacaoSisbrDTO dto) throws BancoobException;
	
	/**
	 * Baixa uma anota��o de origem SISBR
	 * @param dto O dto com as informa��es da anota��o.
	 * @return Os id's das anota��es baixadas a partir das informa��o do dto.
	 * @throws BancoobException Caso ocorra alguma exce��o.
	 */
	List<Long> baixarAnotacao(AnotacaoSisbrDTO dto) throws BancoobException;
	
}