package br.com.sicoob.capes.api.persistencia.dao;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.BemPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.BemPosseVO;

/**
 * A Interface BemPessoaDAO.
 */
public interface BemPessoaDAO extends CAPESApiDaoIF<BemPessoaVO> {
	
	/**
	 * Obter posses.
	 *
	 * @param idBem o valor de id bem
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<BemPosseVO> obterPosses(Long idBem) throws BancoobException;
	
	/**
	 * Obtém os bens antigos da pessoa como proprietário ou participante.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemPessoaVO> obterPorPessoaInstituicaoParceiro(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obtém o id do bem novo à partir do bem antigo.
	 * 
	 * @param idBemPessoa
	 * @return
	 * @throws BancoobException
	 */
	Long obterIdBemNovo(Long idBemPessoa) throws BancoobException;

}