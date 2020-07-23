/*
 * SICOOB
 * 
 * FuncionarioServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.FuncionarioServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.FuncionarioServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.FuncionarioServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.FuncionarioVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.FuncionarioDAO;

/**
 * The Class FuncionarioServicoEJB.
 */
@Stateless
@Local(FuncionarioServicoLocal.class)
@Remote(FuncionarioServicoRemote.class)
public class FuncionarioServicoEJB extends CAPESApiServicoEJB implements FuncionarioServicoRemote, FuncionarioServicoLocal {

	@Inject
	@Default
	private FuncionarioDAO funcionarioDAO;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public FuncionarioVO obterByID(Integer idFuncionario) throws BancoobException {
		if (idFuncionario == null) {
			throw new BancoobException("ID funcionário não informado.");
		}
		return funcionarioDAO.obterPorId(idFuncionario);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public FuncionarioVO obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return funcionarioDAO.obterPorPessoaInstituicaoUnico(idPessoa, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public FuncionarioVO obterByCpfPessoaInstituicao(String cpf, Integer idInstituicao) throws BancoobException {
		if (cpf == null) {
			throw new BancoobException("CPF não informado.");
		}
		if (idInstituicao == null) {
			throw new BancoobException("ID instituição não informado.");
		}
		return funcionarioDAO.obterFuncionarioPorCpfPessoaInstituicao(cpf, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<FuncionarioVO> obterByInstituicaoFuncao(Integer idInstituicao, Short idFuncao, Integer idUnidadeInst) throws BancoobException {
		if (idInstituicao == null) {
			throw new BancoobException("ID instituição não informado.");
		}
		return funcionarioDAO.obterFuncionariosPorInstituicaoFuncao(idInstituicao, idFuncao, idUnidadeInst);
	}

	/**
	 * {@inheritDoc}
	 */
	public FuncionarioVO obterGerente(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return funcionarioDAO.obterGerente(idPessoa, idInstituicao);
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return funcionarioDAO;
	}

}