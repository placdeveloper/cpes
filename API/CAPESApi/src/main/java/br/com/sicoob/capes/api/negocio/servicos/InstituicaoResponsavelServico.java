/*
 * SICOOB
 * 
 * InstituicaoResponsavelServico.java(br.com.sicoob.capes.api.negocio.servicos.InstituicaoResponsavelServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.InstituicaoResponsavelVO;

/**
 * Interface de Servi�o para a {@link InstituicaoResponsavelServico}
 *  
 * @author Marcelo.Onofre
 *
 */
public interface InstituicaoResponsavelServico extends CAPESApiServico {
	
	/**
	 * M�todo que consulta um Respons�vel pelo CPF/CNPJ juntamente com o c�digo
	 * de compartilhamento do cadastro
	 * 
	 * @param cpfCnpj
	 * 		CPF/CNPJ da pessoa
	 * @param codCompartilhamentoCadastro
	 * 		C�digo de compartilhamento de cadastro
	 * @return
	 * 		Retorna um {@link InstituicaoResponsavelVO}
	 * @throws BancoobException 
	 */
	InstituicaoResponsavelVO obterByCpfCnpj(
			String cpfCnpj, Short codCompartilhamentoCadastro) throws BancoobException;

	/**
	 * Obter por id pessoa compartilhamento.
	 * 
	 * @param idPessoaCompartilhamento
	 *            the id pessoa compartilhamento
	 * @param codTipoPessoa
	 *            the cod tipo pessoa
	 * @return instituicao responsavel vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	InstituicaoResponsavelVO obterPorIDPessoaCompartilhamento(Long idPessoaCompartilhamento, Short codTipoPessoa)
			throws BancoobException;
	
	/**
	 * Obter por cpf cnpj.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @return instituicao responsavel vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	InstituicaoResponsavelVO obterByCpfCnpj(String cpfCnpj) throws BancoobException;
}
