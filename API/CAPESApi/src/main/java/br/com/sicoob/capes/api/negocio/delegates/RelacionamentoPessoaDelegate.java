/*
 * SICOOB
 * 
 * RelacionamentoPessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.RelacionamentoPessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IRelacionamentoPessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.RelacionamentoPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaVO;

/**
 * @author Lucas.Borges
 */
public class RelacionamentoPessoaDelegate extends
		AbstractCAPESApiPessoaDelegate<RelacionamentoPessoaVO,RelacionamentoPessoaServico> implements IRelacionamentoPessoaDelegate {
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected RelacionamentoPessoaServico localizarServico() {
		return CAPESApiServiceLocator.getInstance()
				.localizarRelacionamentoPessoaServico();
	}

	/**
	 * Obter conjuges por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<RelacionamentoPessoaVO> obterConjugesPorPessoaInstituicao(
			Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterConjugesByPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * Obter quadro societario por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<RelacionamentoPessoaVO> obterQuadroSocietarioPorPessoaInstituicao(
			Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterQuadroSocietarioByPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * Obter por pessoa instituicao tipo.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @param codigoTipoRelacionamento
	 *            the codigo tipo relacionamento
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<RelacionamentoPessoaVO> obterPorPessoaInstituicaoTipo(
			Integer idPessoa, Integer idInstituicao,
			Short codigoTipoRelacionamento) throws BancoobException {
		return getServico().obterByPessoaInstituicaoTipo(idPessoa, idInstituicao, codigoTipoRelacionamento);
	}
}
