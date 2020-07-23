package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.EnderecoPessoaVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface IEnderecoPessoaDelegate extends IAbstractCAPESApiPessoaDelegate<EnderecoPessoaVO> {

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
	EnderecoPessoaVO obterEnderecoCorrespondenciaPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

}