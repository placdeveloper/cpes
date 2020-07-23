package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IBemMovelDelegate;
import br.com.sicoob.capes.api.negocio.servicos.BemMovelServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.BemMovelVO;

/**
 * Classe contendo os serviços de bens móveis.
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
	 * Obtém as informações de bens móveis por idPessoa e idInstituicao
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
	 * Obtém os bens móveis por idPessoa, idInstituicao e codigo do tipo do bem.
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
	 * Obtém as informações de bens móveis avançados por idPessoa e
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
	 * Obtém os bens móveis por idPessoa, idInstituicao e codigo do tipo do bem.
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
	 * Obtém as informações de bens móveis avaliados
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
	 * Obtém as informações de bens móveis avaliados
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
	 * Obtém as informações de bens móveis básicos que possuem avaliação
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
	 * Obtém as informações de bens móveis básicos que possuem avaliação
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
	 * Obtém as informações de bens móveis por id do bem.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	public BemMovelVO obterPorIdBem(Long idBem) throws BancoobException {
		return getServico().obterPorIdBem(idBem);
	}

}