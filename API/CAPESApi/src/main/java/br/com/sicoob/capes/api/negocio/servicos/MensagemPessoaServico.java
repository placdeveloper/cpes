/*
 * SICOOB
 * 
 * MensagemPessoaServico.java(br.com.sicoob.capes.api.negocio.servicos.MensagemPessoaServico)
 */
package br.com.sicoob.capes.api.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.vo.MensagemPessoaVO;

/**
 * @author Lucas.Borges
 */
public interface MensagemPessoaServico extends CAPESApiServico {

	/**
	 * Obter ativas por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            the id pessoa
	 * @param idInstituicao
	 *            the id instituicao
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	List<MensagemPessoaVO> obterAtivasByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException;
	
	/**
	 * @param mensagemPessoaVO
	 * @return
	 * @throws BancoobException
	 */
	MensagemPessoaVO incluirMensagem(MensagemPessoaVO mensagemPessoaVO) throws BancoobException;
	
	/**
	 * 
	 * @param mensagens
	 * @return
	 * @throws BancoobException
	 */
	List<MensagemPessoaVO> incluirMensagens(List<MensagemPessoaVO> mensagens) throws BancoobException;
	
	/**
	 * 
	 * @param mensagemPessoaVO
	 * @throws BancoobException
	 */
	void excluirMensagens(List<MensagemPessoaVO> mensagemPessoaVO) throws BancoobException;
	
	/**
	 * @param idMensagem
	 * @throws BancoobException
	 */
	void excluirMensagem(MensagemPessoaVO mensagem) throws BancoobException;

	/**
	 * @param mensagemPessoaVO
	 * @throws BancoobException
	 */
	 List<MensagemPessoaVO> incluirMensagensPorCpf(List<MensagemPessoaVO> mensagemPessoaVO) throws BancoobException;
	
	/**
	 * Obter mensagens ativas por pessoa instituicao.
	 *
	 * @param idPessoa o valor de id pessoa
	 * @param idInstituicao o valor de id instituicao
	 * @param tipoMensagem o valor de tipo mensagem
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	List<MensagemPessoaVO> obterMensagensAtivasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short tipoMensagem) throws BancoobException;
}
