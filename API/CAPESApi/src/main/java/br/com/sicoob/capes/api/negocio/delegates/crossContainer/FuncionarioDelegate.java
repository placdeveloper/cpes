/*
 * SICOOB
 * 
 * FuncionarioDelegate.java(br.com.sicoob.capes.api.negocio.delegates.FuncionarioDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IFuncionarioDelegate;
import br.com.sicoob.capes.api.negocio.servicos.FuncionarioServico;
import br.com.sicoob.capes.api.negocio.vo.FuncionarioVO;

/**
 * @author Lucas.Borges
 */
public class FuncionarioDelegate extends
		CAPESApiDelegate<FuncionarioServico> implements IFuncionarioDelegate {
	
	/**
	 * 
	 */
	protected FuncionarioDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static FuncionarioDelegate getInstance() {
		return valueOf(FuncionarioDelegate.class);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected FuncionarioServico localizarServico() {
		return getLocator()
				.localizarFuncionarioServico();
	}

	/**
	 * Obter.
	 * 
	 * @param idFuncionario
	 *            the id funcionario
	 * @return funcionario vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public FuncionarioVO obter(Integer idFuncionario)
			throws BancoobException {
		return getServico().obterByID(idFuncionario);
	}

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
	public FuncionarioVO obterPorPessoaInstituicao(Integer idPessoa,
			Integer idInstituicao) throws BancoobException {
		return getServico().obterByPessoaInstituicao(idPessoa, idInstituicao);
	}

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
	public FuncionarioVO obterPorCpfPessoaInstituicao(String cpf,
			Integer idInstituicao) throws BancoobException {
		return getServico().obterByCpfPessoaInstituicao(cpf, idInstituicao);
	}

	/**
	 * Obter funcionario por ID instituicao, Id funcao e ID unidade instituicao
	 * @param idInstituicao 
	 * @param idFuncao
	 * @param idUnidadeInst
	 * @return Lista de {@link FuncionarioVO}
	 * @throws BancoobException
	 */
	public List<FuncionarioVO> obterPorInstituicaoFuncaoUnidadeInst(Integer idInstituicao,
			Short idFuncao, Integer idUnidadeInst) throws BancoobException {
		return getServico().obterByInstituicaoFuncao(idInstituicao, idFuncao, idUnidadeInst);
	}
	
	/**
	 * Obter gerente.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return FuncionarioVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public FuncionarioVO obterGerente(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterGerente(idPessoa, idInstituicao);
	}

}
