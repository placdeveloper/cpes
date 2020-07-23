package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.FuncionarioVO;

/**
 * A Interface FuncionarioDAO.
 */
public interface FuncionarioDAO extends CAPESApiDaoIF<FuncionarioVO> {

	/**
	 * Obter funcionario por cpf pessoa instituicao.
	 *
	 * @param cpf o valor de cpf
	 * @param idInstituicao o valor de id instituicao
	 * @return FuncionarioVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	FuncionarioVO obterFuncionarioPorCpfPessoaInstituicao(String cpf, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter funcionarios por instituicao funcao.
	 *
	 * @param idInstituicao o valor de id instituicao
	 * @param idFuncao o valor de id funcao
	 * @param idUnidadeInst o valor de id unidade inst
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<FuncionarioVO> obterFuncionariosPorInstituicaoFuncao(Integer idInstituicao, Short idFuncao, Integer idUnidadeInst) throws BancoobException;

	/**
	 * Obter gerente.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return FuncionarioVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	FuncionarioVO obterGerente(Integer idPessoa, Integer idInstituicao) throws BancoobException;

}