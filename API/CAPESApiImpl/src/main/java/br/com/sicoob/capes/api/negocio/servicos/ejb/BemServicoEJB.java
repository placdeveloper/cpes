package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.BemServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.BemServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.BemProprietarioVO;
import br.com.sicoob.capes.api.negocio.vo.BemVO;
import br.com.sicoob.capes.api.negocio.vo.TipoBemVO;
import br.com.sicoob.capes.api.negocio.vo.TipoClassificacaoBemVO;
import br.com.sicoob.capes.api.persistencia.dao.BemDAO;
import br.com.sicoob.capes.comum.negocio.enums.TipoClassificacaoBemEnum;

/**
 * Classe com os serviços de bens.
 *
 * @author Bruno.Carneiro
 */
@Stateless
@Local(BemServicoLocal.class)
@Remote(BemServicoRemote.class)
public class BemServicoEJB extends CAPESApiServicoEJB implements BemServicoLocal, BemServicoRemote {

	@Inject
	@Default
	private BemDAO bemDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemDAO obterDAO() {
		return bemDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BemVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	public BemVO obterPorIdBem(Long idBem) throws BancoobException {
		return obterDAO().obterPorIdBem(idBem);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoClassificacaoBemVO> obterClassificacoesBem() throws BancoobException {
		return obterDAO().obterClassificacoesBem();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TipoBemVO> obterTiposBemPorClassificacao(Short codigoTipoClassificacaoBem) throws BancoobException {
		boolean bemImovel = TipoClassificacaoBemEnum.isImovel(codigoTipoClassificacaoBem);
		return obterDAO().obterTiposBemPorClassificacao(bemImovel);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<BemProprietarioVO> obterProprietarios(Long idBem) throws BancoobException {
		return obterDAO().obterProprietarios(idBem);
	}
	
}