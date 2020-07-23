/*
 * SICOOB
 * 
 * CAPESServicoReplicacaoCrudServicoEJB.java(br.com.sicoob.capes.replicacao.negocio.servicos.ejb.CAPESServicoReplicacaoCrudServicoEJB)
 */
package br.com.sicoob.capes.replicacao.negocio.servicos.ejb;

import br.com.bancoob.negocio.servicos.ejb.BancoobCrudServicoEJB;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidadeBase;
import br.com.sicoob.capes.replicacao.persistencia.dao.CAPESServicoReplicacaoCrudDaoIF;

/**
 * Implementacao padrao do contrato de servicos CRUD de todo o sistema ServicoReplicacaoCadastroUnicoClientes
 *
 * @author Stefanini IT Solutions
 */
public abstract class CAPESServicoReplicacaoCrudServicoEJB<T extends CAPESEntidadeBase<?>> extends
		BancoobCrudServicoEJB<T> {

	/**
	 * @return
	 */
	protected abstract CAPESServicoReplicacaoCrudDaoIF<T> getDAO();

}