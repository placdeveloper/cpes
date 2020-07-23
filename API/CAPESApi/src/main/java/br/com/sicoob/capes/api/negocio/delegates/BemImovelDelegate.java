package br.com.sicoob.capes.api.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IBemImovelDelegate;
import br.com.sicoob.capes.api.negocio.servicos.BemImovelServico;
import br.com.sicoob.capes.api.negocio.servicos.locator.CAPESApiServiceLocator;
import br.com.sicoob.capes.api.negocio.vo.BemImovelParticipanteVO;
import br.com.sicoob.capes.api.negocio.vo.BemImovelVO;

/**
 * Classe com os serviços de bens imóveis.
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
	 * Obtém os bens imóveis por idPessoa e idInstituicao
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
	 * Obtém os bens imóveis por idPessoa, idInstituicao e codigo do tipo do bem
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
	 * Obtém os bens imóveis avançados por idPessoa e idInstituicao
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
	 * Obtém os bens imóveis avançados por idPessoa, idInstituicao e codigo do
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
	 * Obtém os bens avaliados.
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
	 * Obtém os bens avaliados.
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
	 * Obtém os bens básicos que possuem avaliação.
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
	 * Obtém os bens básicos que possuem avaliação.
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
	 * Obtém todos os bens associados à uma determinada pessoa.
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
	 * Obtém as informações do bem imóvel por id do bem.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	public BemImovelVO obterPorIdBem(Long idBem) throws BancoobException {
		return getServico().obterPorIdBem(idBem);
	}
	
	/**
	 * Obtém os participantes do bem imóvel.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	public List<BemImovelParticipanteVO> obterParticipantes(Long idBem) throws BancoobException {
		return getServico().obterParticipantes(idBem);
	}

}