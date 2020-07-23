/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos.bemantigo;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.CAPESCadastroCrudDominioServico;
import br.com.sicoob.capes.negocio.entidades.bemantigo.TipoBem;

/**
 * Servi�o para os tipos de bens
 * 
 * @author erico.junior
 */
public interface TipoBemAntigoServico extends CAPESCadastroCrudDominioServico<TipoBem>{

	/**
	 * Listar tipos com subtipo.
	 *
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<TipoBem> listarTiposComSubtipo() throws BancoobException;

}
