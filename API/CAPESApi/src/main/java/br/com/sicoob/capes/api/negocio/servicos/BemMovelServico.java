package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.BemMovelVO;

/**
 * Classe contendo os serviços de bens móveis.
 * 
 * @author Bruno.Carneiro
 */
public interface BemMovelServico extends CAPESApiServico {

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
	 * Obtém as informações de bens móveis por idPessoa e idInstituicao e código
	 * do tipo do bem
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemMovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException;

	/**
	 * Obtém as informações de bens móveis avançados por idPessoa e
	 * idInstituicao
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemMovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém as informações de bens móveis avançados por idPessoa e
	 * idInstituicao e código do tipo do bem
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
	 * Obtém os bens móveis por idBem
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	BemMovelVO obterPorIdBem(Long idBem) throws BancoobException;

}