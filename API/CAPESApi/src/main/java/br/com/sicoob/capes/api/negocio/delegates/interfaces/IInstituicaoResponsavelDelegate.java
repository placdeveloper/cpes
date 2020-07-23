/*
 * SICOOB
 * 
 * InstituicaoResponsavelDelegate.java(br.com.sicoob.capes.api.negocio.delegates.InstituicaoResponsavelDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.InstituicaoResponsavelVO;

/**
 * Delegate responsavel por retornar o Responsavel
 * 
 * @author Marcelo.Onofre
 *
 */
public interface IInstituicaoResponsavelDelegate extends ICAPESApiDelegate {

	/**
	 * Metodo que consulta um Responsavel pelo CPF/CNPJ juntamente com o codigo de compartilhamento do cadastro
	 * 
	 * @param cpfCnpj
	 *            CPF/CNPJ da pessoa
	 * @param codCompartilhamentoCadastro
	 *            Codigo de compartilhamento de cadastro
	 * @return Retorna um {@link InstituicaoResponsavelVO}
	 * @throws BancoobException
	 */
	InstituicaoResponsavelVO obterPorCpfCnpj(String cpfCnpj, Short codCompartilhamentoCadastro) throws BancoobException;

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
	InstituicaoResponsavelVO obterPorIDPessoaCompartilhamento(Long idPessoaCompartilhamento, Short codTipoPessoa) throws BancoobException;

	/**
	 * Obter por cpf cnpj.
	 * 
	 * @param cpfCnpj
	 *            the cpf cnpj
	 * @return instituicao responsavel vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	InstituicaoResponsavelVO obterPorCpfCnpj(String cpfCnpj) throws BancoobException;

}
