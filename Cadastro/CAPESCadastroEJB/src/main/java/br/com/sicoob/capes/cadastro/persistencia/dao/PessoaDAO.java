/**
 * 
 */
package br.com.sicoob.capes.cadastro.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.PessoaBasicaVO;
import br.com.sicoob.capes.negocio.entidades.Pessoa;

/**
 * Interface para o DAO de pessoas.
 * 
 * @author Erico.Junior
 */
public interface PessoaDAO extends CAPESCadastroCrudDaoIF<Pessoa> {

	/**
	 * Consulta a pessoa a partir do cnpj/cpf informado.
	 * 
	 * @param documento
	 *            O CNPJ ou CPF da pessoa.
	 * @return A pessoa encontada.
	 * @throws br.com.sicoob.capes.cadastro.negocio.excecao.ClienteNaoEncontradoException
	 *             Caso o cliente não exista na base.
	 */
	Pessoa consultarPessoaPorDocumento(String documento);

	/**
	 * Obter por lista pessoa instituicao.
	 *
	 * @param idPessoas o valor de id pessoas
	 * @param idInstituicao o valor de id instituicao
	 * @return List
	 */
	List<PessoaBasicaVO> obterPorListaPessoaInstituicao(List<Integer> idPessoas, Integer idInstituicao);
	
	/**
	 * Consultar pessoa por cpf cnpj ur.
	 *
	 * @param cpfCnpj o valor de cpf cnpj
	 * @return Long
	 * @throws BancoobException lança a exceção BancoobException
	 */
	Long consultarPessoaPorCpfCnpjUR(String cpfCnpj) throws BancoobException;
	
}