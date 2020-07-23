/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.OrigemInformacao;

/**
 * Interface para o DAO de origem da informa��o (Fonte). 
 * @author Erico.Junior
 */
public interface OrigemInformacaoDAO extends CAPESCadastroCrudDaoIF<OrigemInformacao> {
	
	/**
	 * Obter origem por nome.
	 *
	 * @param origem o valor de origem
	 * @return OrigemInformacao
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	OrigemInformacao obterOrigemPorNome(OrigemInformacao origem) throws BancoobException;

}
