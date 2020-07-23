/*
 * SICOOB
 * 
 * MensagemPessoaDelegate.java(br.com.sicoob.capes.api.negocio.delegates.MensagemPessoaDelegate)
 */
package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IMensagemPessoaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.MensagemPessoaServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.MensagemPessoaVO;

/**
 * @author Lucas.Borges
 */
public class MensagemPessoaDelegate extends CAPESApiDelegate<MensagemPessoaServico> implements IMensagemPessoaDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected MensagemPessoaServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarMensagemPessoaServico();
	}

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
	public List<MensagemPessoaVO> obterAtivasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterAtivasByPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * @param mensagemPessoaVO
	 * @return
	 * @throws BancoobException
	 */
	public MensagemPessoaVO incluirMensagem(MensagemPessoaVO mensagemPessoaVO) throws BancoobException {
		return getServico().incluirMensagem(mensagemPessoaVO);
	}

	/**
	 * @param mensagemPessoaVO
	 * @return
	 * @throws BancoobException
	 */

	/**
	 * Informar o idMensagem e loginUsuarioOperacao
	 * 
	 * @param mensagem
	 * @throws BancoobException
	 */
	public void excluirMensagem(MensagemPessoaVO mensagem) throws BancoobException {
		getServico().excluirMensagem(mensagem);
	}

	/**
	 * @param mensagens
	 * @return
	 * @throws BancoobException
	 */
	public List<MensagemPessoaVO> incluirMensagens(List<MensagemPessoaVO> mensagens) throws BancoobException {
		return getServico().incluirMensagens(mensagens);
	}

	/**
	 * Informar o idMensagem e loginUsuarioOperacao
	 * 
	 * @param idMensagens
	 * @throws BancoobException
	 */
	public void excluirMensagens(List<MensagemPessoaVO> mensagemPessoaVO) throws BancoobException {
		getServico().excluirMensagens(mensagemPessoaVO);
	}

	/**
	 * Informar o cpf, idInstituicao, loginUsuarioOperacao e dados da mensagem.
	 * 
	 * @param mensagemPessoaVO
	 * @throws BancoobException
	 */
	public List<MensagemPessoaVO> incluirMensagensPorCpf(List<MensagemPessoaVO> mensagemPessoaVO) throws BancoobException {
		return getServico().incluirMensagensPorCpf(mensagemPessoaVO);
	}

	/**
	 * Obter mensagens ativas por pessoa instituicao.
	 * 
	 * @param idPessoa
	 *            o valor de id pessoa
	 * @param idInstituicao
	 *            o valor de id instituicao
	 * @param tipoMensagem
	 *            o valor de tipo mensagem
	 * @return List
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	public List<MensagemPessoaVO> obterMensagensAtivasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short tipoMensagem) throws BancoobException {
		return getServico().obterMensagensAtivasPorPessoaInstituicao(idPessoa, idInstituicao, tipoMensagem);
	}
}