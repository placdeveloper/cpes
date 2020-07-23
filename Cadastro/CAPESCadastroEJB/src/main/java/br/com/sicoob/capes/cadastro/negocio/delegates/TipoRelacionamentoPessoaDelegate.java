/* 
 * Sicoob
 * TipoRelacionamentoPessoaDelegate.java 
 * Criado em: 08/08/2011
 */
package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.TipoRelacionamentoPessoaServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.TipoPessoa;
import br.com.sicoob.capes.negocio.entidades.TipoRelacionamentoPessoa;

/**
 * 08/08/2011
 * 
 * @author Rodrigo.Chaves
 */
public class TipoRelacionamentoPessoaDelegate extends
		CAPESCadastroCrudDominioDelegate<TipoRelacionamentoPessoa, TipoRelacionamentoPessoaServico> {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected TipoRelacionamentoPessoaServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance()
				.localizarTipoRelacionamentoPessoaServico();
	}

	/**
	 * @see TipoRelacionamentoPessoaServico#alterar(TipoRelacionamentoPessoa,
	 *      TipoRelacionamentoPessoa)
	 */
	public void alterar(TipoRelacionamentoPessoa objeto, TipoRelacionamentoPessoa reverso)
			throws BancoobException {
		
		localizarServico().alterar(objeto, reverso);
	}

	/**
	 * Pesquisar por tipos pessoa.
	 *
	 * @param tipoPessoaRelacionamento o valor de tipo pessoa relacionamento
	 * @param tipoPessoaRelacionada o valor de tipo pessoa relacionada
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<TipoRelacionamentoPessoa> pesquisarPorTiposPessoa(
			TipoPessoa tipoPessoaRelacionamento, TipoPessoa tipoPessoaRelacionada)
			throws BancoobException {
		
		return localizarServico().pesquisarPorTiposPessoa(tipoPessoaRelacionamento,
				tipoPessoaRelacionada);
	}
	
	/**
	 * 
	 * @return
	 * @throws BancoobException
	 */
	public List<TipoRelacionamentoPessoa> pesquisarTiposRelacionamentosProdutosBancoob() throws BancoobException{
		return getServico().pesquisarTiposRelacionamentosProdutosBancoob();
	}
	
}
