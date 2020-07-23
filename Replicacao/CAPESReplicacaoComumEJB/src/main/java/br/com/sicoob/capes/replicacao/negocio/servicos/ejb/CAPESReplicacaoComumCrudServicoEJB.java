/*
 * SICOOB
 * 
 * CAPESReplicacaoComumCrudServicoEJB.java(br.com.sicoob.capes.replicacao.negocio.servicos.ejb.CAPESReplicacaoComumCrudServicoEJB)
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import java.io.Serializable;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.dto.ConsultaDto;
import br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.legado.CAPESEntidadeLegado;
import br.com.sicoob.capes.replicacao.negocio.servicos.CAPESReplicacaoComumCrudServico;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESReplicacaoComumCrudDaoIF;

/**
 * Implementacao padrao do contrato de servicos CRUD de todo o sistema ReplicacaoClientesBO
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESReplicacaoComumCrudServicoEJB<T extends CAPESEntidadeLegado<?>> extends
		BancoobCrudServicoEJB<T> implements CAPESReplicacaoComumCrudServico<T> {

	/** 
	 * {@inheritDoc}
	 */
	protected abstract CAPESReplicacaoComumCrudDaoIF<T> getDAO();

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void alterar(T objeto) throws BancoobException {

		throw new UnsupportedOperationException();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void excluir(Serializable chave) throws BancoobException {

		throw new UnsupportedOperationException();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public T incluir(T objeto) throws BancoobException {

		throw new UnsupportedOperationException();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public List<T> listar() throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public List<T> listar(ConsultaDto<T> criterios) throws BancoobException {
		throw new UnsupportedOperationException();
	}

	/**
	 * {@inheritDoc}
	 */
	public T incluir(T objeto, Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		return getDAO().incluir(objeto, numeroCooperativa);
	}

	/**
	 * {@inheritDoc}
	 */
	public T obter(Serializable chave, Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		return getDAO().obter(chave, numeroCooperativa);
	}

	/**
	 * {@inheritDoc}
	 */
	public void alterar(T objeto, Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		getDAO().alterar(objeto, numeroCooperativa);
	}

	/**
	 * {@inheritDoc}
	 */
	public void excluir(T objeto, Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		getDAO().excluir(objeto.getIdSQL(), numeroCooperativa);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> listar(Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		return getDAO().listar(numeroCooperativa);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<T> listar(ConsultaDto<T> criterios, Integer idInstituicao) throws BancoobException {
		Integer numeroCooperativa = obterNumeroCooperativa(idInstituicao);
		return getDAO().listar(criterios, numeroCooperativa);
	}

	/**
	 * Recupera o número da cooperativa a partir do idInstituicao informado.
	 * 
	 * @param instituicao
	 *            A instituição.
	 * @return O número da cooperativa a partir do idInstituicao informado.
	 */
	protected Integer obterNumeroCooperativa(Integer idInstituicao) throws BancoobException {

		SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		Integer numCooperativa = sciDelegate.obterNumeroCooperativa(idInstituicao);
		getLogger().info("numCooperativa: " + numCooperativa);
		return numCooperativa;
	}

}