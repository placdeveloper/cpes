/**
 * 
 */
package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.PessoaBasicaVO;
import br.com.sicoob.capes.negocio.entidades.Pessoa;

/**
 * Define as opera��es do servi�o de manipula��o de pessoas.
 * 
 * @author Erico.Junior
 */
public interface PessoaServico extends CAPESCadastroCrudServico<Pessoa> {

	/**
	 * M�todo para alterar o cpf ou cnpj da pessoa informada.
	 * 
	 * @param pessoa
	 *            a Pessoa a ser alterada.
	 * @param cpfCnpjNovo
	 *            O novo cpf ou cnpj. 
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o na altera��o.
	 */
	void alterarCpfCnpj(Pessoa pessoa, String cpfCnpjNovo) throws BancoobException;

	/**
	 * Consulta a pessoa a partir do documento informado, podendo ser um CPF ou
	 * CNPJ.
	 * 
	 * @param cpfCnpj
	 *            O documento para a consulta.
	 * @return A pessoa consultada para o documento.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	Pessoa consultarPessoaPorDocumento(String cpfCnpj) throws BancoobException;
	
	/**
	 * Verifica se a pessoa informada � uma pessoa f�sica.
	 * 
	 * @param pessoa
	 *            A pessoa a ser verificada.
	 * @return se a pessoa informada � pessoa f�sica ou n�o.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	boolean isPessoaFisica(Pessoa pessoa) throws BancoobException;

	/**
	 * Verifica se a pessoa informada � uma pessoa jur�dica.
	 * 
	 * @param pessoa
	 *            A pessoa a ser verificada.
	 * @return se a pessoa informada � pessoa jur�dica ou n�o.
	 * @throws BancoobException
	 *             Caso ocorra alguma exce��o.
	 */
	boolean isPessoaJuridica(Pessoa pessoa) throws BancoobException;

	/**
	 * Obter por lista pessoa instituicao.
	 *
	 * @param idPessoas o valor de id pessoas
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 */
	List<PessoaBasicaVO> obterPorListaPessoaInstituicao(List<Integer> idPessoas, Integer idInstituicao);
	
}