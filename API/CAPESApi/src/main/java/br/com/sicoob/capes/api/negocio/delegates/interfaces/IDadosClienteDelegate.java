package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.DadosClienteVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface IDadosClienteDelegate extends ICAPESApiDelegate {

	/**
	 * Obter por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return dados cliente vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	DadosClienteVO obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter os dados do cliente por instituicao, funcionario
	 * 
	 * @param idInstituicao
	 *            ID da instituicao
	 * @param idFuncionario
	 *            ID do funcionario
	 * @return
	 * @throws BancoobException
	 */
	List<DadosClienteVO> obterPorInstituicaoFuncionario(Integer idInstituicao, Integer idFuncionario) throws BancoobException;

}
