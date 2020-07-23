package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.TributacaoPessoaVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface ITributacaoPessoaDelegate extends ICAPESApiDelegate {

	/**
	 * Obter por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return tributacao pessoa vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	TributacaoPessoaVO obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

}
