package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.PessoaFisicaVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface IPessoaFisicaDelegate extends ICAPESApiDelegate {

	/**
	 * Obter por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return pessoa fisica vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	PessoaFisicaVO obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém a pessoa fisica por CPF e instituição
	 * 
	 * @param cpf
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	PessoaFisicaVO obterPorCPFInstituicao(String cpf, Integer idInstituicao) throws BancoobException;

}
