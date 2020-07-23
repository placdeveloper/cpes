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
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<BemPosseVO> obterPosses(Long idBem) throws BancoobException;
	
	/**
	 * Obt�m os bens antigos da pessoa como propriet�rio ou participante.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemPessoaVO> obterPorPessoaInstituicaoParceiro(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * Obt�m o id do bem novo � partir do bem antigo.
	 * 
	 * @param idBemPessoa
	 * @return
	 * @throws BancoobException
	 */
	Long obterIdBemNovo(Long idBemPessoa) throws BancoobException;

}