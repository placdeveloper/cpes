/* 
 * Sicoob
 * TipoPessoaDAO.java 
 * Criado em: 30/06/2011
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.CompartilhamentoCadastro;

/**
 * 30/06/2011
 * @author Rodrigo.Rodrigues
 */
public interface CompartilhamentoCadastroDAO extends CAPESCadastroCrudDominioDaoIF<CompartilhamentoCadastro> {
	
	/**
	 * O método Habilitar integracao srf.
	 *
	 * @param codCompartilhamentoCadastro o valor de cod compartilhamento cadastro
	 * @param habilitarIntegracaoSRF o valor de habilitar integracao srf
	 * @throws BancoobException lança a exceção BancoobException
	 */
	void habilitarIntegracaoSRF(Integer codCompartilhamentoCadastro, boolean habilitarIntegracaoSRF) throws BancoobException;

}
