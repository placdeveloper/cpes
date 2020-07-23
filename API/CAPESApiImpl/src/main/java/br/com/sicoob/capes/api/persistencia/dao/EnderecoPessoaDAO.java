package br.com.sicoob.capes.api.persistencia.dao;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.EnderecoPessoaVO;

/**
 * A Interface EnderecoPessoaDAO.
 */
public interface EnderecoPessoaDAO extends CAPESApiDaoIF<EnderecoPessoaVO> {
	
	/**
	 * Obter endereco correspondencia por pessoa instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @return EnderecoPessoaVO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	EnderecoPessoaVO obterEnderecoCorrespondenciaPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

}