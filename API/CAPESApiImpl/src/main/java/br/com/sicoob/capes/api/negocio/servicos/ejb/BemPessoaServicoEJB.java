/*
 * SICOOB
 * 
 * BemPessoaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.BemPessoaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.InformacaoUtilizadaDelegate;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.BemPessoaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.BemPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.BemPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.BemPosseVO;
import br.com.sicoob.capes.api.negocio.vo.InformacaoUtilizadaVO;
import br.com.sicoob.capes.api.persistencia.dao.BemPessoaDAO;
import br.com.sicoob.capes.comum.negocio.enums.TipoInformacaoEnum;

/**
 * The Class BemPessoaServicoEJB.
 */
@Stateless
@Local({ BemPessoaServicoLocal.class })
@Remote({ BemPessoaServicoRemote.class })
public class BemPessoaServicoEJB extends AbstractCAPESApiServicoPessoaEJB<BemPessoaVO> implements BemPessoaServicoRemote, BemPessoaServicoLocal {
	
	@Inject
	@Default
	private BemPessoaDAO bemPessoaDAO;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public BemPessoaVO obterByID(Serializable id) throws BancoobException {
		BemPessoaVO bemPessoa = bemPessoaDAO.obterPorId(id);
		obterPosseObjeto(bemPessoa);
		return bemPessoa;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<BemPessoaVO> obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		List<BemPessoaVO> listaRetorno = bemPessoaDAO.obterPorPessoaInstituicaoParceiro(idPessoa, idInstituicao);
		obterPosseLista(listaRetorno);
		return listaRetorno;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<BemPessoaVO> obterNaoVigenteByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		List<BemPessoaVO> listaRetorno = bemPessoaDAO.obterNaoVigentePorPessoaInstituicao(idPessoa, idInstituicao);
		obterPosseLista(listaRetorno);
		return listaRetorno;
	}
	
	/**
	 * Obter posse objeto.
	 * 
	 * @param bem
	 *            the bem
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private void obterPosseObjeto(BemPessoaVO bem) throws BancoobException {
		if (bem != null) {
			List<BemPosseVO> listaPosses = obterPosses(bem.getIdBem());
			if (CollectionUtils.isNotEmpty(listaPosses)) {
				bem.setPosses(listaPosses);
			}
		}
	}
	
	/**
	 * Obter posse lista.
	 * 
	 * @param listaBem
	 *            the lista bem
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private void obterPosseLista(List<BemPessoaVO> listaBem) throws BancoobException {
		if (CollectionUtils.isNotEmpty(listaBem)) {
			for(BemPessoaVO vo : listaBem){
				obterPosseObjeto(vo);
			}
		}
	}
	
	/**
	 * Obter posses.
	 * 
	 * @param idBem
	 *            the id bem
	 * @return list
	 * @throws BancoobException
	 *             the bancoob exception
	 */
	private List<BemPosseVO> obterPosses(Long idBem) throws BancoobException {
		return bemPessoaDAO.obterPosses(idBem);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected BemPessoaDAO obterDAO() {
		return bemPessoaDAO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void bloquearBem(Short codigoUtilizaInformacao, Long idBemPessoa) throws BancoobException {
		Long idBemNovo = bemPessoaDAO.obterIdBemNovo(idBemPessoa);

		if (idBemNovo != null) {
			InformacaoUtilizadaVO informacaoUtilizadaVO = new InformacaoUtilizadaVO();
			informacaoUtilizadaVO.setCodigoInformacao(idBemNovo);
			informacaoUtilizadaVO.setCodigoTipoInformacao(TipoInformacaoEnum.BEM.getCodigo());
			informacaoUtilizadaVO.setCodigoUtilizaInformacao(codigoUtilizaInformacao);

			InformacaoUtilizadaDelegate delegate = CAPESApiFabricaDelegates.getInstance().criarInformacaoUtilizadaDelegate();
			delegate.incluir(informacaoUtilizadaVO);
		}
	}
	
}