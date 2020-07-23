package br.com.sicoob.capes.integracao.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.RiscoVO;

/**
 * A Interface CRLIntegracaoServico.
 * 
 * @author Bruno.Carneiro
 */
public interface CRLIntegracaoServico extends CAPESIntegracaoServico {

	/**
	 * Obter risco.
	 * 
	 * @param idPessoa
	 *            o valor de id pessoa
	 * @param idInstituicao
	 *            o valor de id instituicao
	 * @return RiscoVO
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	RiscoVO obterRisco(Integer idPessoa, Integer idInstituicao) throws BancoobException;

}