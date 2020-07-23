/*
 * SICOOB
 * 
 * EnderecoPessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.EnderecoPessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IEnderecoPessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.EnderecoPessoaServico;
import br.com.sicoob.capes.api.negocio.vo.EnderecoPessoaVO;

/**
 * @author Lucas.Borges
 */
public class EnderecoPessoaDelegate extends AbstractCAPESApiPessoaDelegate<EnderecoPessoaVO,EnderecoPessoaServico> implements IEnderecoPessoaDelegate {
	
	/**
	 * 
	 */
	protected EnderecoPessoaDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static EnderecoPessoaDelegate getInstance() {
		return valueOf(EnderecoPessoaDelegate.class);
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected EnderecoPessoaServico localizarServico() {
		return getLocator().localizarEnderecoPessoaServico();
	}
	
	/**
	 * Obter endereco correspondencia por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return endereco pessoa vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public EnderecoPessoaVO obterEnderecoCorrespondenciaPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException{
		return getServico().obterEnderecoCorrespondenciaByPessoaInstituicao(idPessoa, idInstituicao);
	}
	
}