/*
 * SICOOB
 * 
 * AnotacaoPessoaDelegate.java(br.com.sicoob.capes.comum.negocio.delegates.AnotacaoPessoaDelegate)
 */
package br.com.sicoob.capes.comum.negocio.delegates;

import java.util.List;
import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.servicos.AnotacaoPessoaServico;
import br.com.sicoob.capes.comum.negocio.servicos.locator.CAPESComumServiceLocator;

/**
 * The Class AnotacaoPessoaDelegate.
 */
public class AnotacaoPessoaDelegate extends CAPESComumDelegate<AnotacaoPessoaServico> {

	/** 
	 * {@inheritDoc}
	 */
	@Override
	protected AnotacaoPessoaServico localizarServico() {

		return CAPESComumServiceLocator.getInstance().localizarAnotacaoPessoaServico();
	}

	/**
	 * Obter vigentes por pessoa instituicao tipo.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idTipoAnotacao
	 *            the id tipo anotacao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<Map<String, Object>> obterVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao,
			Integer idTipoAnotacao) throws BancoobException {

		return getServico().obterVigentesPorPessoaInstituicaoTipo(idPessoa, idInstituicao, idTipoAnotacao);
	}
	
	/**
	 * Obter vigentes por pessoa instituicao tipo.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @param idInstituicao
	 *            the id instituicao
	 * @param idTipoAnotacao
	 *            the id tipo anotacao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	public List<Map<String, Object>> obterVigentesPorPessoaInstituicaoTipo(String cpfCnpj, Integer idInstituicao,
			Integer idTipoAnotacao) throws BancoobException {
		
		return getServico().obterVigentesPorPessoaInstituicaoTipo(cpfCnpj, idInstituicao, idTipoAnotacao);
	}

}
