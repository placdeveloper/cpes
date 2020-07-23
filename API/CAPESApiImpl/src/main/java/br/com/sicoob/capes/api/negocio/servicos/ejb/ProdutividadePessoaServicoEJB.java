/*
 * SICOOB
 * 
 * ProdutividadePessoaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.ProdutividadePessoaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.ProdutividadePessoaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.ProdutividadePessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.ProdutividadePessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.ProdutividadePessoaDAO;
import br.com.sicoob.capes.cadastro.negocio.enums.SituacaoProdutividadeEnum;

/**
 * The Class ProdutividadePessoaServicoEJB.
 */
@Stateless
@Local({ ProdutividadePessoaServicoLocal.class })
@Remote({ ProdutividadePessoaServicoRemote.class })
public class ProdutividadePessoaServicoEJB extends AbstractCAPESApiServicoPessoaEJB<ProdutividadePessoaVO> implements ProdutividadePessoaServicoLocal, ProdutividadePessoaServicoRemote {

	@Inject
	@Default
	private ProdutividadePessoaDAO produtividadePessoaDAO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ProdutividadePessoaDAO obterDAO() {
		return produtividadePessoaDAO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProdutividadePessoaVO obterByID(Serializable id) throws BancoobException {
		return obterDAO().obterPorId(id, Arrays.asList(SituacaoProdutividadeEnum.EM_ABERTO.getCodigo()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProdutividadePessoaVO> obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao, Arrays.asList(SituacaoProdutividadeEnum.EM_ABERTO.getCodigo()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<ProdutividadePessoaVO> obterTodasPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao, null);
	}

}