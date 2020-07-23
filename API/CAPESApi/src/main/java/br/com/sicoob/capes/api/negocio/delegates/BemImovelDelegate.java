package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IBemImovelDelegate;
import br.com.sicoob.capes.api.negocio.servicos.BemImovelServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.BemImovelParticipanteVO;
import br.com.sicoob.capes.api.negocio.vo.BemImovelVO;

/**
 * Classe com os servi�os de bens im�veis.
 * 
 * @author Bruno.Carneiro
 */
public class BemImovelDelegate extends CAPESApiDelegate<BemImovelServico> implements IBemImovelDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemImovelServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarBemImovelServico();
	}

	/**
	 * Obt�m os bens im�veis por idPessoa e idInstituicao
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterPorPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obt�m os bens im�veis por idPessoa, idInstituicao e codigo do tipo do bem
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovelVO> obterPorPessoaInstituicaoTipoBem(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		return getServico().obterPorPessoaInstituicaoTipoBem(idPessoa, idInstituicao, codigoTipoBem);
	}
	
	/**
	 * Obt�m os bens im�veis avan�ados por idPessoa e idInstituicao
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterAvancadosPorPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obt�m os bens im�veis avan�ados por idPessoa, idInstituicao e codigo do
	 * tipo do bem
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		return getServico().obterAvancadosPorPessoaInstituicao(idPessoa, idInstituicao, codigoTipoBem);
	}
	
	/**
	 * Obt�m os bens avaliados.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterAvaliadosPorPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * Obt�m os bens avaliados.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		return getServico().obterAvaliadosPorPessoaInstituicao(idPessoa, idInstituicao, codigoTipoBem);
	}
	
	/**
	 * Obt�m os bens b�sicos que possuem avalia��o.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterBasicosAvaliadosPorPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * Obt�m os bens b�sicos que possuem avalia��o.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		return getServico().obterBasicosAvaliadosPorPessoaInstituicao(idPessoa, idInstituicao, codigoTipoBem);
	}
	
	/**
	 * Obt�m todos os bens associados � uma determinada pessoa.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovelVO> obterTodosBensAssociados(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterTodosBensAssociados(idPessoa, idInstituicao);
	}

	/**
	 * Obt�m as informa��es do bem im�vel por id do bem.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	public BemImovelVO obterPorIdBem(Long idBem) throws BancoobException {
		return getServico().obterPorIdBem(idBem);
	}
	
	/**
	 * Obt�m os participantes do bem im�vel.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovelParticipanteVO> obterParticipantes(Long idBem) throws BancoobException {
		return getServico().obterParticipantes(idBem);
	}

}