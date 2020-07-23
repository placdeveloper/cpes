/* 
 * Sicoob
 * TipoPessoaServico.java 
 * Criado em: 01/07/2011
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;		

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;

/**
 * Define as operações para o serviço de tipos de pessoa
 *
 * 01/07/2011
 * @author Rodrigo.Chaves
 */
public interface CompartilhamentoCadastroServico extends CAPESCadastroCrudDominioServico<CompartilhamentoCadastro> {
	
	/**
	 * O método Habilitar integracao srf.
	 *
	 * @param codCompartilhamentoCadastro o valor de cod compartilhamento cadastro
	 * @param ligarIntegracao o valor de ligar integracao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void habilitarIntegracaoSRF(Integer codCompartilhamentoCadastro, boolean ligarIntegracao) throws BancoobException;

}
