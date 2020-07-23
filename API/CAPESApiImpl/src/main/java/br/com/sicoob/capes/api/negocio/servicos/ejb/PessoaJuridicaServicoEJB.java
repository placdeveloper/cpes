/*
 * SICOOB
 * 
 * PessoaJuridicaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.PessoaJuridicaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.excecao.InstituicaoNaoInformadaException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.PessoaJuridicaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.PessoaJuridicaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.PessoaJuridicaVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;
import br.com.sicoob.capes.api.persistencia.dao.PessoaJuridicaDAO;
import br.com.sicoob.capes.cadastro.negocio.excecao.CampoNaoInformadoException;

/**
 * The Class PessoaJuridicaServicoEJB.
 */
@Stateless
@Local(PessoaJuridicaServicoLocal.class)
@Remote(PessoaJuridicaServicoRemote.class)
public class PessoaJuridicaServicoEJB extends CAPESApiServicoEJB implements PessoaJuridicaServicoRemote, PessoaJuridicaServicoLocal {

	@Inject
	@Default
	private PessoaJuridicaDAO pessoaJuridicaDAO;

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PessoaJuridicaVO obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return pessoaJuridicaDAO.obterPorPessoaInstituicaoUnico(idPessoa, idInstituicao);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<PessoaJuridicaVO> obterMatrizFiliais(String raizCNPJ, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadeCNPJInstituicao(raizCNPJ, idInstituicao);
		return pessoaJuridicaDAO.obterMatrizFiliais(raizCNPJ.length() > 8 ? raizCNPJ.substring(0, 8) : raizCNPJ, idInstituicao);
	}
	
	/**
	 * O método Validar obrigatoriedade cnpj instituicao.
	 *
	 * @param raizCnpj o valor de raiz cnpj
	 * @param idInstituicao o valor de id instituicao
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private void validarObrigatoriedadeCNPJInstituicao(String raizCnpj, Integer idInstituicao) throws BancoobException {
		if (StringUtils.isBlank(raizCnpj)) {
			throw new CampoNaoInformadoException("CNPJ");
		}
		if (idInstituicao == null) {
			throw new InstituicaoNaoInformadaException();
		}
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return pessoaJuridicaDAO;
	}

}