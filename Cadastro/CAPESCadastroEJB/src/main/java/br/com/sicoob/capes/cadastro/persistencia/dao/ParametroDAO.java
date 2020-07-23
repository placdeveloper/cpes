/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;

/**
 * 
 * @author Paulo.Stoppa
 *
 */
public interface ParametroDAO {

	/**
	 * Obtem o valor do par�metro baseado na institui��o
	 * 
	 * @param codigo
	 * @param idInstituicao
	 * @return
	 */
	ParametroVO obterParametro(Integer codigo, Integer idInstituicao);

}