/*
 * SICOOB
 * 
 * DadosClienteServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.DadosClienteServicoEJB)
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
import br.com.sicoob.capes.api.negocio.servicos.interfaces.DadosClienteServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.DadosClienteServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.DadosClienteVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.DadosClienteDAO;

/**
 * The Class DadosClienteServicoEJB.
 */
@Stateless
@Local(DadosClienteServicoLocal.class)
@Remote(DadosClienteServicoRemote.class)
public class DadosClienteServicoEJB extends CAPESApiServicoEJB implements DadosClienteServicoRemote, DadosClienteServicoLocal{
	
	@Inject
	@Default
	private DadosClienteDAO clienteDAO;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public DadosClienteVO obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return clienteDAO.obterPorPessoaInstituicaoUnico(idPessoa, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<DadosClienteVO> obterByInstituicaoFuncionario(Integer idInstituicao, Integer idFuncionario) throws BancoobException {
		validarObrigatoriedadeInstituicao(idInstituicao);
		if (idFuncionario == null) {
			throw new BancoobException("idFuncionario não informado.");
		}
		return clienteDAO.obterDadosClientePorInstituicaoFuncionario(idInstituicao, idFuncionario);
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return clienteDAO;
	}

}