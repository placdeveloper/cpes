/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.Empreendimento;

/**
 * @author erico.junior
 * 
 */
public interface EmpreendimentoServico extends
		CAPESCadastroCrudServico<Empreendimento> {

	/**
	 * Recupera o próximo código disponível
	 *
	 * @return o próximo código
	 */
	Integer pesquisarProximoCodigo() throws BancoobException;

}
