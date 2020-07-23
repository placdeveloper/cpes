/*
 * SICOOB
 * 
 * PessoaJuridicaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.PessoaJuridicaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IPessoaJuridicaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.PessoaJuridicaServico;
import br.com.sicoob.capes.api.negocio.vo.PessoaJuridicaVO;

/**
 * @author Lucas.Borges
 */
public class PessoaJuridicaDelegate extends
		CAPESApiDelegate<PessoaJuridicaServico> implements IPessoaJuridicaDelegate {
	
	/**
	 * 
	 */
	protected PessoaJuridicaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static PessoaJuridicaDelegate getInstance() {
		return valueOf(PessoaJuridicaDelegate.class);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaJuridicaServico localizarServico() {
		return getLocator()
				.localizarPessoaJuridicaServico();
	}

	/**
	 * Obter por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return pessoa juridica vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public PessoaJuridicaVO obterPorPessoaInstituicao(Integer idPessoa,
			Integer idInstituicao) throws BancoobException {
		return getServico().obterByPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obtém a matriz e as filiais de uma determinada raiz de um CNPJ
	 * 
	 * @param raizCNPJ
	 *            A raiz do CNPJ
	 * @param idInstituicao
	 *            A instituição para a pesquisa
	 * @return {@code List} lista de pessoas jurídicas.
	 * @throws BancoobException
	 */
	public List<PessoaJuridicaVO> obterMatrizFiliais(String raizCNPJ, Integer idInstituicao) throws BancoobException {
		return getServico().obterMatrizFiliais(raizCNPJ, idInstituicao);
	}

}