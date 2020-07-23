/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;

/**
 * Opera��es de par�metro
 * 
 * @author Paulo.Stoppa
 *
 */
public interface ParametroServico extends CAPESCadastroServico {

	/**
	 * Servi�o para buscar o parametro
	 * 
	 * @param codigo
	 * @param idInstituicao
	 * @return
	 */
	ParametroVO obterParametro(Integer codigo, Integer idInstituicao);
	
	
	boolean obterParametroValorBoolean(Integer codigo, Integer idInstituicao) ;

}
