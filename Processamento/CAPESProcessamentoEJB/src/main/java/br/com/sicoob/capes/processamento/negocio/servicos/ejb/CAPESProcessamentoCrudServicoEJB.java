/*
 * SICOOB
 * 
 * CAPESProcessamentoCrudServicoEJB.java(br.com.sicoob.capes.processamento.negocio.servicos.ejb.CAPESProcessamentoCrudServicoEJB)
 */
package br.com.sicoob.capes.processamento.negocio.servicos.ejb;

import java.io.Serializable;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidadeBase;
import br.com.sicoob.capes.processamento.negocio.servicos.CAPESProcessamentoCrudServico;
import br.com.sicoob.capes.processamento.persistencia.dao.CAPESProcessamentoCrudDaoIF;

/**
 * The Class CAPESProcessamentoCrudServicoEJB.
 * 
 * @param <T>
 *            the generic type
 */
public abstract class CAPESProcessamentoCrudServicoEJB<T extends CAPESEntidadeBase<?>> extends BancoobCrudServicoEJB<T>
		implements CAPESProcessamentoCrudServico<T> {

	/** 
	 * {@inheritDoc}
	 */
	protected abstract CAPESProcessamentoCrudDaoIF<T> getDAO();

	/** 
	 * {@inheritDoc}
	 */
	public T obter(Serializable chave, Integer numeroCooperativa) throws BancoobException {
		return getDAO().obter(chave, numeroCooperativa);
	}

}