package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.BemImovelParticipanteVO;
import br.com.sicoob.capes.api.negocio.vo.BemImovelVO;

/**
 * Classe contendo os servi�os de bens im�veis.
 * 
 * @author Bruno.Carneiro
 */
public interface BemImovelServico extends CAPESApiServico {

	/**
	 * Obt�m os bens im�veis por idPessoa e idInstituicao.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obt�m os bens im�veis por idPessoa, idInstituicao e c�digo do tipo do
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
	 * Obt�m os bens im�veis avan�ados por idPessoa, idInstituicao
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obt�m os bens im�veis avan�ados por idPessoa, idInstituicao e c�digo do
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
	 * Obt�m os bens avaliados.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obt�m os bens avaliados.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException;
	
	/**
	 * Obt�m os bens b�sicos que possuem avalia��o.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;

	/**
	 * Obt�m os bens b�sicos que possuem avalia��o.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException;

	/**
	 * Obt�m os bens im�veis por idBem.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	BemImovelVO obterPorIdBem(Long idBem) throws BancoobException;
	
	/**
	 * Obt�m os participantes do bem im�vel.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelParticipanteVO> obterParticipantes(Long idBem) throws BancoobException;

	/**
	 * Obt�m todos os bens associados � uma determinada pessoa.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	List<BemImovelVO> obterTodosBensAssociados(Integer idPessoa, Integer idInstituicao);

}