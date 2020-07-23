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
 * Define as interfaces para a atualização de anotações do sisbr.
 * 
 * @author Erico.Junior
 */
public interface AtualizarAnotacoesSisbrServico extends CAPESApiServico {

	/**
	 * Inclui uma anotação de origem SISBR
	 * @param dto O dto com as informações da anotação.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	Long incluirAnotacao(AnotacaoSisbrDTO dto) throws BancoobException;
	
	/**
	 * Baixa uma anotação de origem SISBR
	 * @param dto O dto com as informações da anotação.
	 * @return Os id's das anotações baixadas a partir das informação do dto.
	 * @throws BancoobException Caso ocorra alguma exceção.
	 */
	List<Long> baixarAnotacao(AnotacaoSisbrDTO dto) throws BancoobException;
	
}