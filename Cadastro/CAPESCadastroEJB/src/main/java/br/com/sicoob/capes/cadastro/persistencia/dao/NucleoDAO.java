/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Nucleo;

/**
 * Interface para o DAO de Nucleo.
 * 
 * @author juan.damasceno
 */
public interface NucleoDAO extends
		CAPESCadastroCrudDaoIF<Nucleo> {

	/**
	 * Pesquisar proximo codigo.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @return Integer
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Integer pesquisarProximoCodigo(Integer idInstituicao) throws BancoobException;
}