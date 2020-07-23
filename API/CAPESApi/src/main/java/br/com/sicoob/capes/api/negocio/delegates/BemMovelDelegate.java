package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IBemMovelDelegate;
import br.com.sicoob.capes.api.negocio.servicos.BemMovelServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.BemMovelVO;

/**
 * Classe contendo os servi�os de bens m�veis.
 * 
 * @author Bruno.Carneiro
 */
public class BemMovelDelegate extends CAPESApiDelegate<BemMovelServico> implements IBemMovelDelegate {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemMovelServico localizarServico() {
		return CAPESApiServiceLocator.getInstance().localizarBemMovelServico();
	}

	/**
	 * Obt�m as informa��es de bens m�veis por idPessoa e idInstituicao
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<BemMovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterPorPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obt�m os bens m�veis por idPessoa, idInstituicao e codigo do tipo do bem.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	public List<BemMovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		return getServico().obterPorPessoaInstituicao(idPessoa, idInstituicao, codigoTipoBem);
	}

	/**
	 * Obt�m as informa��es de bens m�veis avan�ados por idPessoa e
	 * idInstituicao
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<BemMovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterAvancadosPorPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Obt�m os bens m�veis por idPessoa, idInstituicao e codigo do tipo do bem.
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	public List<BemMovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		return getServico().obterAvancadosPorPessoaInstituicao(idPessoa, idInstituicao, codigoTipoBem);
	}
	
	/**
	 * Obt�m as informa��es de bens m�veis avaliados
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<BemMovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterAvaliadosPorPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * Obt�m as informa��es de bens m�veis avaliados
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	public List<BemMovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		return getServico().obterAvaliadosPorPessoaInstituicao(idPessoa, idInstituicao, codigoTipoBem);
	}
	
	/**
	 * Obt�m as informa��es de bens m�veis b�sicos que possuem avalia��o
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<BemMovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterBasicosAvaliadosPorPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * Obt�m as informa��es de bens m�veis b�sicos que possuem avalia��o
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @param codigoTipoBem
	 * @return
	 * @throws BancoobException
	 */
	public List<BemMovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		return getServico().obterBasicosAvaliadosPorPessoaInstituicao(idPessoa, idInstituicao, codigoTipoBem);
	}

	/**
	 * Obt�m as informa��es de bens m�veis por id do bem.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	public BemMovelVO obterPorIdBem(Long idBem) throws BancoobException {
		return getServico().obterPorIdBem(idBem);
	}

}