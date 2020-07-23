/*
 * SICOOB
 * 
 * AbstractCAPESApiServicoPessoaEJB.java(br.com.sicoob.capes.api.negocio.servicos.ejb.AbstractCAPESApiServicoPessoaEJB)
 */
package br.com.sicoob.capes.api.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.api.negocio.servicos.CAPESApiServicoPessoa;
import br.com.sicoob.capes.api.negocio.vo.AbstractPessoaVO;
import br.com.sicoob.capes.api.persistencia.dao.CAPESApiDaoIF;

/**
 * Implementacao base de todos os servicos do sistema CAPES-API
 * 
 */
public abstract class AbstractCAPESApiServicoPessoaEJB<K extends AbstractPessoaVO> extends CAPESApiServicoEJB implements CAPESApiServicoPessoa<K> {

	/**
	 * Obter dao.
	 *
	 * @return CAPESApiDaoIF
	 */
	@Override
	protected abstract CAPESApiDaoIF<K> obterDAO();
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public K obterByID(Serializable id) throws BancoobException {
		if (id == null) {
			throw new BancoobException("ID não informado.");
		}
		return obterDAO().obterPorId(id);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<K> obterByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return obterDAO().obterPorPessoaInstituicao(idPessoa, idInstituicao);
	}
	
	/**
	 * Método que lista todos os registros VIGENTES e NÃO-VIGENTES por pessoa, instituição
	 * 
	 * @param idPessoa ID pessoa 
	 * @param idInstituicao ID instituição
	 * @return Lista de <K>
	 * @throws BancoobException
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<K> obterNaoVigenteByPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		validarObrigatoriedadePessoaInstituicao(idPessoa, idInstituicao);
		return obterDAO().obterNaoVigentePorPessoaInstituicao(idPessoa, idInstituicao);
	}
	
}