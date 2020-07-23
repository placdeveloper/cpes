/*
 * SICOOB
 * 
 * PessoaFisicaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.PessoaFisicaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IPessoaFisicaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.PessoaFisicaServico;
import br.com.sicoob.capes.api.negocio.vo.PessoaFisicaVO;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;

/**
 * @author Lucas.Borges
 */
public class PessoaFisicaDelegate extends
		CAPESApiDelegate<PessoaFisicaServico> implements IPessoaFisicaDelegate {
	
	/**
	 * 
	 */
	protected PessoaFisicaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static PessoaFisicaDelegate getInstance() {
		return valueOf(PessoaFisicaDelegate.class);
	}
	
	/** O atributo logger. */
	private ISicoobLogger logger = getLogger();

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected PessoaFisicaServico localizarServico() {
		return getLocator()
				.localizarPessoaFisicaServico();
	}

	/**
	 * Obter por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return pessoa fisica vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public PessoaFisicaVO obterPorPessoaInstituicao(Integer idPessoa,
			Integer idInstituicao) throws BancoobException {
		logger.debug("PessoaFisicaDelegate obterPorPessoaInstituicao - idPessoa:" + idPessoa + " idInstituicao:" + idInstituicao);
		PessoaFisicaVO obterByPessoaInstituicao = getServico().obterByPessoaInstituicao(idPessoa, idInstituicao);
		logger.debug("PessoaFisicaDelegate obterPorPessoaInstituicao - Retorno:" + obterByPessoaInstituicao);
		return obterByPessoaInstituicao;
	}
	
	/**
	 * Obtém a pessoa fisica por CPF e instituição
	 * 
	 * @param cpf
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public PessoaFisicaVO obterPorCPFInstituicao(String cpf, Integer idInstituicao) throws BancoobException {
		return getServico().obterPorCPFInstituicao(cpf, idInstituicao);
	}

}
