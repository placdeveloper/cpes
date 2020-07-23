/*
 * SICOOB
 * 
 * TributacaoPessoaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.TributacaoPessoaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TributacaoPessoaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.TributacaoPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.TributacaoPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.TributacaoPessoaDAO;

/**
 * The Class TributacaoPessoaServicoEJB.
 */
@Stateless
@Local(TributacaoPessoaServicoLocal.class)
@Remote(TributacaoPessoaServicoRemote.class)
public class TributacaoPessoaServicoEJB extends CAPESApiServicoEJB implements TributacaoPessoaServicoRemote, TributacaoPessoaServicoLocal {

	@Inject
	@Default
	private TributacaoPessoaDAO tributacaoPessoaDAO;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public TributacaoPessoaVO obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return tributacaoPessoaDAO.obterPorPessoaInstituicaoUnico(idPessoa, idInstituicao);
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return tributacaoPessoaDAO;
	}

}