/*
 * SICOOB
 * 
 * ProdutividadePessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.ProdutividadePessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IProdutividadePessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.ProdutividadePessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.ProdutividadePessoaVO;

/**
 * @author Lucas.Borges
 */
public class ProdutividadePessoaDelegate extends AbstractCAPESApiPessoaDelegate<ProdutividadePessoaVO, ProdutividadePessoaServico> implements IProdutividadePessoaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProdutividadePessoaServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarProdutividadeServico();
	}
	
	/**
	 * Obter todas por pessoa instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<ProdutividadePessoaVO> obterTodasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterTodasPorPessoaInstituicao(idPessoa, idInstituicao);
	}
}