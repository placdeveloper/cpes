package br.com.sicoob.capes.api.negocio.delegates.crossContainer;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.interfaces.IBemDelegate;
import br.com.sicoob.capes.api.negocio.servicos.BemServico;
import br.com.sicoob.capes.api.negocio.vo.BemProprietarioVO;
import br.com.sicoob.capes.api.negocio.vo.BemVO;
import br.com.sicoob.capes.api.negocio.vo.TipoBemVO;
import br.com.sicoob.capes.api.negocio.vo.TipoClassificacaoBemVO;

/**
 * Classe com os servi�os de bens
 * 
 * @author Bruno.Carneiro
 */
public class BemDelegate extends CAPESApiDelegate<BemServico> implements IBemDelegate {
	
	/**
	 * 
	 */
	protected BemDelegate() {
	}

	/**
	 * 
	 * @return
	 */
	public static BemDelegate getInstance() {
		return valueOf(BemDelegate.class);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemServico localizarServico() {
		return getLocator().localizarBemServico();
	}

	/**
	 * Obt�m os bens por idPessoa e idInstituicao
	 * 
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public List<BemVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return getServico().obterPorPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * Obt�m as informa��es do bem por ID.
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	public BemVO obterPorIdBem(Long idBem) throws BancoobException {
		return getServico().obterPorIdBem(idBem);
	}
	
	/**
	 * Obt�m as classifica��es do bem.
	 * 
	 * @return
	 * @throws BancoobException
	 */
	public List<TipoClassificacaoBemVO> obterClassificacoesBem() throws BancoobException {
		return getServico().obterClassificacoesBem();
	}
	
	/**
	 * Obt�m os tipos de bem por tipo de classifica��o
	 * 
	 * @param codigoTipoClassificacaoBem
	 * @return
	 * @throws BancoobException
	 */
	public List<TipoBemVO> obterTiposBemPorClassificacao(Short codigoTipoClassificacaoBem) throws BancoobException {
		return getServico().obterTiposBemPorClassificacao(codigoTipoClassificacaoBem);
	}
	
	/**
	 * Obt�m a lista dos propriet�rios do bem
	 * 
	 * @param idBem
	 * @return
	 * @throws BancoobException
	 */
	public List<BemProprietarioVO> obterProprietarios(Long idBem) throws BancoobException {
		return getServico().obterProprietarios(idBem);
	}

}