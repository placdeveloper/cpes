/*
 * SICOOB
 * 
 * AnotacaoPessoaServicoEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.AnotacaoPessoaServicoEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.AnotacaoPessoaServicoLocal;
import br.com.sicoob.capes.api.negocio.servicos.interfaces.AnotacaoPessoaServicoRemote;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPeriodoVO;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.AnotacaoPessoaDAO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDao;

/**
 * The Class AnotacaoPessoaServicoEJB.
 */
@Stateless
@Local(AnotacaoPessoaServicoLocal.class)
@Remote(AnotacaoPessoaServicoRemote.class)
public class AnotacaoPessoaServicoEJB extends CAPESApiServicoEJB implements AnotacaoPessoaServicoRemote, AnotacaoPessoaServicoLocal {
	
	@Inject
	@Default
	private AnotacaoPessoaDAO anotacaoPessoaDAO;

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<AnotacaoPessoaVO> obterVigentesByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return anotacaoPessoaDAO.obterAnotacoesVigentesPorPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<AnotacaoPessoaVO> obterPorPessoaInstituicao(Integer idPessoa, Integer idInstituicao, boolean baixadas) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return anotacaoPessoaDAO.obterAnotacoesPorPessoaInstituicao(idPessoa, idInstituicao, baixadas);
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<AnotacaoPessoaVO> obterImpeditivasByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return anotacaoPessoaDAO.obterAnotacoesImpeditivasPorPessoaInstituicao(idPessoa, idInstituicao);
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<AnotacaoPessoaVO> obterVigentesByPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, Integer idTipoAnotacao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		if (idTipoAnotacao == null) {
			throw new BancoobException("ID tipo anotação não informado.");
		}
		return anotacaoPessoaDAO.obterVigentesPorTipo(idPessoa, null, idInstituicao, idTipoAnotacao);
	}

	/** 
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<AnotacaoPessoaVO> obterVigentesByPessoaInstituicaoTipo(String cpfCnpj, Integer idInstituicao, Integer idTipoAnotacao) throws BancoobException {
		return anotacaoPessoaDAO.obterVigentesPorTipo(null, cpfCnpj, idInstituicao, idTipoAnotacao);
	}

	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public AnotacaoPessoaVO obterAnotacaoPorId(Long idAnotacao) throws BancoobException {
		return anotacaoPessoaDAO.obterPorId(idAnotacao);
	}

    /**
	 * {@inheritDoc}
	 */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<AnotacaoPessoaVO> obterNaoVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, List<Integer> codigoTipoAnotacao,
			Date dataBaixa) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		if (codigoTipoAnotacao == null) {
			throw new BancoobException("Tipo anotação não informado.");
		}
		if (dataBaixa == null) {
			throw new BancoobException("Data da baixa da anotação não informado.");
		}
		return anotacaoPessoaDAO.obterNaoVigentesPorPessoaInstituicaoTipo(idPessoa, idInstituicao, codigoTipoAnotacao, dataBaixa);
	}

    /**
	 * {@inheritDoc}
	 */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<AnotacaoPessoaVO> obterVigentesPorPessoaInstituicaoTipo(Integer idPessoa, Integer idInstituicao, List<Integer> codigoTipoAnotacao)
			throws BancoobException {
    	validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
    	if (codigoTipoAnotacao == null) {
			throw new BancoobException("Tipo anotação não informado.");
		}
		return anotacaoPessoaDAO.obterVigentesPorPessoaInstituicaoTipo(idPessoa, idInstituicao, codigoTipoAnotacao);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AnotacaoPeriodoVO> obterPessoasSicoobPorTipoAnotacaoPeriodo(List<String> listaCpfCnpj, Integer idTipoAnotacao, Date dataAnotacao) throws BancoobException {
		return anotacaoPessoaDAO.obterPessoasSicoobPorTipoAnotacaoPeriodo(listaCpfCnpj, idTipoAnotacao, dataAnotacao);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AnotacaoPeriodoVO> obterPessoasSicoobPorTipoAnotacaoPeriodo(Integer idTipoAnotacao, Date dataAnotacao) throws BancoobException {
		return anotacaoPessoaDAO.obterPessoasSicoobPorTipoAnotacaoPeriodo(idTipoAnotacao, dataAnotacao);
	}

	@Override
	protected CAPESApiDao obterDAO() {
		return anotacaoPessoaDAO;
	}

}