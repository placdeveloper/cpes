/**
 * 
 */
package br.com.sicoob.capes.processamento.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.dto.AnotacaoExternaDTO;

/**
 * Define as interfaces para a atualização de anotações das consultas externas.
 * 
 * @author Erico.Junior
 */
public interface AtualizarAnotacoesConsultasExternasServico extends
		CAPESProcessamentoServico {

	/**
	 * Atualiza as anotações a partir da consulta externa executada.
	 * 
	 * @param consultaExterna
	 *            Objeto com o resultado da consulta externa.
	 * @throws BancoobException
	 *             Caso ocorra alguma exceção.
	 */
	void atualizarAnotacoesConsultasExternas(AnotacaoExternaDTO consultaExterna)
			throws BancoobException;
}
