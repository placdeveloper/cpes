package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.BemImovelParticipanteVO;
import br.com.sicoob.capes.api.negocio.vo.BemImovelVO;

/**
 * Classe contendo os serviços de bens imóveis.
 * 
 * @author Bruno.Carneiro
 */
public interface BemImovelServico extends CAPESApiServico {

	/**
	 * Obtém os bens imóveis por idPessoa e idInstituicao.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém os bens imóveis por idPessoa, idInstituicao e código do tipo do
	 * bem.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterPorPessoaInstituicaoTipoBem(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException;

	/**
	 * Obtém os bens imóveis avançados por idPessoa, idInstituicao
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém os bens imóveis avançados por idPessoa, idInstituicao e código do
	 * tipo do bem.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException;
	
	/**
	 * Obtém os bens avaliados.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém os bens avaliados.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException;
	
	/**
	 * Obtém os bens básicos que possuem avaliação.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obtém os bens básicos que possuem avaliação.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException;

	/**
	 * Obtém os bens imóveis por idBem.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	BemImovelVO obterPorIdBem(Long idBem) throws BancoobException;
	
	/**
	 * Obtém os participantes do bem imóvel.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelParticipanteVO> obterParticipantes(Long idBem) throws BancoobException;

	/**
	 * Obtém todos os bens associados à uma determinada pessoa.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterTodosBensAssociados(Integer idPessoa, Integer idInstituicao);

}