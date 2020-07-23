package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.FuncionarioVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface IFuncionarioDelegate extends ICAPESApiDelegate {

	/**
	 * Obter.
	 * 
	 * @param idFuncionario
	 *            the id funcionario
	 * @return funcionario vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	FuncionarioVO obter(Integer idFuncionario) throws BancoobException;

	/**
	 * Obter por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return funcionario vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	FuncionarioVO obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por cpf pessoa instituicao.
	 * 
	 * @param cpf
	 *            the cpf
	 * @param idInstituicao
	 *            the id instituicao
	 * @return funcionario vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	FuncionarioVO obterPorCpfPessoaInstituicao(String cpf, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter funcionario por ID instituicao, Id funcao e ID unidade instituicao
	 * 
	 * @param idInstituicao
	 * @param idFuncao
	 * @param idUnidadeInst
	 * @return Lista de {@link FuncionarioVO}
	 * @throws BancoobException
	 */
	List<FuncionarioVO> obterPorInstituicaoFuncaoUnidadeInst(Integer idInstituicao, Short idFuncao, Integer idUnidadeInst) throws BancoobException;

	/**
	 * Obter gerente.
	 *
	 * @param idPessoa
	 *            o valor de id pessoa
	 * @param idInstituicao
	 *            o valor de id instituicao
	 * @return FuncionarioVO
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	FuncionarioVO obterGerente(Integer idPessoa, Integer idInstituicao) throws BancoobException;

}
