/*
 * SICOOB
 * 
 * EnderecoPessoaServico.java(br.com.sicoob.capes.api.negocio.servicos.EnderecoPessoaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.dto.EnderecoPessoaDTO;
import br.com.sicoob.capes.api.negocio.vo.EnderecoPessoaVO;

/**
 * @author Lucas.Borges
 */
public interface EnderecoPessoaServico extends CAPESApiServicoPessoa<EnderecoPessoaVO>{

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
	EnderecoPessoaVO obterEnderecoCorrespondenciaByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) 
			throws BancoobException;

	/**
	 * Incluir endereco.
	 * 
	 * @param endereco
	 *            the endereco
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	void incluirEndereco(EnderecoPessoaDTO endereco)throws BancoobException;
}
