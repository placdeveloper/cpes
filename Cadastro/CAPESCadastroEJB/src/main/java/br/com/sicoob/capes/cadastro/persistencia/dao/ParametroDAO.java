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
	 * Obtem o valor do parâmetro baseado na instituição
	 * 
	 * @param codigo
	 * @param idInstituicao
	 * @return
	 */
	ParametroVO obterParametro(Integer codigo, Integer idInstituicao);

}