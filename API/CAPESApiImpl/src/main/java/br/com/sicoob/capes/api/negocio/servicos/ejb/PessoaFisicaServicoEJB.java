/*
 * SICOOB
 * 
 * PessoaFisicaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.PessoaFisicaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.excecao.PessoaNaoInformadaException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.PessoaFisicaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.PessoaFisicaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.PessoaFisicaVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.PessoaFisicaDAO;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;

/**
 * The Class PessoaFisicaServicoEJB.
 */
@Stateless
@Local(PessoaFisicaServicoLocal.class)
@Remote(PessoaFisicaServicoRemote.class)
public class PessoaFisicaServicoEJB extends CAPESApiServicoEJB implements PessoaFisicaServicoRemote, PessoaFisicaServicoLocal {

	@Inject
	@Default
	private PessoaFisicaDAO pessoaFisicaDAO;
	
	/** O atributo logger. */
	private ISicoobLogger logger = getLogger();

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PessoaFisicaVO obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		logger.debug("PessoaFisicaServicoEJB obterPorPessoaInstituicao - idPessoa:" + idPessoa + " idInstituicao:" + idInstituicao);
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return pessoaFisicaDAO.obterPorPessoaInstituicaoUnico(idPessoa, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PessoaFisicaVO obterPorCPFInstituicao(String cpf, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadeCPFInstituicao(cpf, idInstituicao);
		return pessoaFisicaDAO.obterPorCPFInstituicao(cpf, idInstituicao);
	}

	private void validarObrigatoriedadeCPFInstituicao(String cpf, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadeInstituicao(idInstituicao);
		if (StringUtils.isEmpty(cpf)) {
			throw new PessoaNaoInformadaException();
		}
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return pessoaFisicaDAO;
	}

}