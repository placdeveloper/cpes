package br.com.sicoob.capes.api.negocio.delegates.interfaces;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.BemMovelVO;

/**
 * 
 * @author paulo.stoppa
 *
 */
public interface IBemMovelDelegate extends ICAPESApiDelegate {

	/**
	 * Obtém as informações de bens móveis por idPessoa e idInstituicao
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemMovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém os bens móveis por idPessoa, idInstituicao e codigo do tipo do bem.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemMovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException;

	/**
	 * Obtém as informações de bens móveis avançados por idPessoa e idInstituicao
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemMovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém os bens móveis por idPessoa, idInstituicao e codigo do tipo do bem.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemMovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException;

	/**
	 * Obtém as informações de bens móveis avaliados
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemMovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém as informações de bens móveis avaliados
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemMovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException;

	/**
	 * Obtém as informações de bens móveis básicos que possuem avaliação
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemMovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém as informações de bens móveis básicos que possuem avaliação
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemMovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException;

	/**
	 * Obtém as informações de bens móveis por id do bem.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	BemMovelVO obterPorIdBem(Long idBem) throws BancoobException;

}