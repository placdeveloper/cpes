/*
 * SICOOB
 * 
 * DadosClienteDelegate.java(br.com.sicoob.capes.api.negocio.delegates.DadosClienteDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IDadosClienteDelegate;
import br.com.sicoob.capes.api.negocio.servicos.DadosClienteServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.DadosClienteVO;

/**
 * @author Lucas.Borges
 */
public class DadosClienteDelegate extends
		CAPESApiDelegate<DadosClienteServico> implements IDadosClienteDelegate {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected DadosClienteServico localizarServico() {
		return CAPESApiServiceLocator.getInstance()
				.localizarDadosClienteServico();
	}

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
	public DadosClienteVO obterPorPessoaInstituicao(Integer idPessoa,
			Integer idInstituicao) throws BancoobException {
		return getServico().obterByPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obter os dados do cliente por instituicao, funcionario
	 * 
	 * @param idInstituicao ID da instituicao
	 * @param idFuncionario ID do funcionario
	 * @return
	 * @throws BancoobException
	 */
	public List<DadosClienteVO> obterPorInstituicaoFuncionario(Integer idInstituicao,
			Integer idFuncionario) throws BancoobException{
		return getServico().obterByInstituicaoFuncionario(idInstituicao, idFuncionario);
	}

}
