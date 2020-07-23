package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.PessoaJuridicaVO;

/**
 * A Interface PessoaJuridicaDAO.
 */
public interface PessoaJuridicaDAO extends CAPESApiDaoIF<PessoaJuridicaVO> {

	/**
	 * Obt�m a matriz e as filiais de uma determinada raiz de um CNPJ
	 * 
	 * @param raizCNPJ
	 *            A raiz do CNPJ
	 * @param idInstituicao
	 *            A institui��o para a pesquisa
	 * @return {@code List} lista de pessoas jur�dicas.
	 * @throws BancoobException
	 */
	List<PessoaJuridicaVO> obterMatrizFiliais(String raizCNPJ, Integer idInstituicao) throws BancoobException;

}