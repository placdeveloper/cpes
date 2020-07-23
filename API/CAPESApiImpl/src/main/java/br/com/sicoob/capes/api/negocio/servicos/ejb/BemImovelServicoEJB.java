package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.BemImovelServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.BemImovelServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.BemImovelParticipanteVO;
import br.com.sicoob.capes.api.negocio.vo.BemImovelVO;
import br.com.sicoob.capes.api.persistencia.dao.BemImovelDAO;

/**
 * Classe com os serviços de bens imóveis.
 * 
 * @author Bruno.Carneiro
 */
@Stateless
@Local(BemImovelServicoLocal.class)
@Remote(BemImovelServicoRemote.class)
public class BemImovelServicoEJB extends CAPESApiServicoEJB implements BemImovelServicoLocal, BemImovelServicoRemote {

	@Inject
	@Default
	private BemImovelDAO bemImovelDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemImovelDAO obterDAO() {
		return bemImovelDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterPorPessoaInstituicaoTipoBem(idPessoa, idInstituicao, null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovelVO> obterPorPessoaInstituicaoTipoBem(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao, Boolean.FALSE, Boolean.FALSE, codigoTipoBem);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterAvancadosPorPessoaInstituicao(idPessoa, idInstituicao, null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao, Boolean.TRUE, Boolean.FALSE, codigoTipoBem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterAvaliadosPorPessoaInstituicao(idPessoa, idInstituicao, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao, Boolean.TRUE, Boolean.TRUE, codigoTipoBem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao, Boolean.FALSE, Boolean.TRUE, null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao, Boolean.FALSE, Boolean.TRUE, codigoTipoBem);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BemImovelVO obterPorIdBem(Long idBem) throws BancoobException {
		return obterDAO().obterPorIdBem(idBem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected <V extends FiltroConsultaAPIAbstrato> void validarFiltroPesquisar(FiltroConsultaAPIAbstrato filtro) throws BancoobException {
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovelParticipanteVO> obterParticipantes(Long idBem) throws BancoobException {
		return obterDAO().obterParticipantes(idBem);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<BemImovelVO> obterTodosBensAssociados(Integer idPessoa, Integer idInstituicao) {
		return obterDAO().obterTodosBensAssociados(idPessoa, idInstituicao);
	}

}