package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.filtros.FiltroConsultaAPIAbstrato;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.BemMovelServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.BemMovelServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.BemMovelVO;
import br.com.sicoob.capes.api.persistencia.dao.BemMovelDAO;

/**
 * Classe com os serviços de bens móveis.
 * 
 * @author Bruno.Carneiro
 */
@Stateless
@Local(BemMovelServicoLocal.class)
@Remote(BemMovelServicoRemote.class)
public class BemMovelServicoEJB extends CAPESApiServicoEJB implements BemMovelServicoLocal, BemMovelServicoRemote {

	@Inject
	@Default
	private BemMovelDAO bemMovelDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemMovelDAO obterDAO() {
		return bemMovelDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BemMovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterPorPessoaInstituicao(idPessoa, idInstituicao, null);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<BemMovelVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao, Boolean.FALSE, Boolean.FALSE, codigoTipoBem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<BemMovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterAvancadosPorPessoaInstituicao(idPessoa, idInstituicao, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BemMovelVO> obterAvancadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao, Boolean.TRUE, Boolean.FALSE, codigoTipoBem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<BemMovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterAvaliadosPorPessoaInstituicao(idPessoa, idInstituicao, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BemMovelVO> obterAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao, Boolean.TRUE, Boolean.TRUE, codigoTipoBem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<BemMovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao, Boolean.FALSE, Boolean.TRUE, null);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BemMovelVO> obterBasicosAvaliadosPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, Short codigoTipoBem) throws BancoobException {
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao, Boolean.FALSE, Boolean.TRUE, codigoTipoBem);
	}

	/**
	 * {@inheritDoc}
	 */
	public BemMovelVO obterPorIdBem(Long idBem) throws BancoobException {
		return obterDAO().obterPorIdBem(idBem);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected <V extends FiltroConsultaAPIAbstrato> void validarFiltroPesquisar(FiltroConsultaAPIAbstrato filtro) throws BancoobException {
		
	}

}