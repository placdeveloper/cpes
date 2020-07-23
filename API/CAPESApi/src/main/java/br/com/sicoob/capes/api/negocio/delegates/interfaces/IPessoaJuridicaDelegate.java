package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.PessoaJuridicaVO;

/**
 * 1
 * 
 * @author paulo.stoppa
 *
 */
public interface IPessoaJuridicaDelegate extends ICAPESApiDelegate {

	/**
	 * Obter por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return pessoa juridica vo
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	PessoaJuridicaVO obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém a matriz e as filiais de uma determinada raiz de um CNPJ
	 * 
	 * @param raizCNPJ
	 *            A raiz do CNPJ
	 * @param idInstituicao
	 *            A instituição para a pesquisa
	 * @return {@code List} lista de pessoas jurídicas.
	 * @throws BancoobException
	 */
	List<PessoaJuridicaVO> obterMatrizFiliais(String raizCNPJ, Integer idInstituicao) throws BancoobException;

}