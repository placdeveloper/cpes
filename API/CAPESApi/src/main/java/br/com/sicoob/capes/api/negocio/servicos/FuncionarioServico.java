/*
 * SICOOB
 * 
 * FuncionarioServico.java(br.com.sicoob.capes.api.negocio.servicos.FuncionarioServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.FuncionarioVO;

/**
 * @author Lucas.Borges
 */
public interface FuncionarioServico extends CAPESApiServico {

	/**
	 * Obter por id.
	 * 
	 * @param idFuncionario
	 *            the id funcionario
	 * @return funcionario vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	FuncionarioVO obterByID(Integer idFuncionario) throws BancoobException;
	
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
	FuncionarioVO obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

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
	FuncionarioVO obterByCpfPessoaInstituicao(String cpf, Integer idInstituicao) throws BancoobException;

	/**
	 * Obter por instituicao funcao.
	 * 
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idFuncao
	 *            the id funcao
	 * @param idUnidadeInst
	 *            the id unidade inst
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<FuncionarioVO> obterByInstituicaoFuncao(Integer idInstituicao, Short idFuncao,
			Integer idUnidadeInst) throws BancoobException;
	
	
	/**
	 * Obtém o gerente da pessoa
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	FuncionarioVO obterGerente(Integer idPessoa, Integer idInstituicao) throws BancoobException;
}
