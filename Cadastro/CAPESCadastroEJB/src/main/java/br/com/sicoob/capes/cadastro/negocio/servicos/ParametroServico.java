/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.sicoob.capes.cadastro.negocio.vo.ParametroVO;

/**
 * Operações de parâmetro
 * 
 * @author Paulo.Stoppa
 *
 */
public interface ParametroServico extends CAPESCadastroServico {

	/**
	 * Serviço para buscar o parametro
	 * 
	 * @param codigo
	 * @param idInstituicao
	 * @return
	 */
	ParametroVO obterParametro(Integer codigo, Integer idInstituicao);
	
	
	boolean obterParametroValorBoolean(Integer codigo, Integer idInstituicao) ;

}
