package br.com.sicoob.capes.replicacao.persistencia.dao;

import br.com.bancoob.persistencia.dao.BancoobCrudDaoIF;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidadeBase;

/**
 * @author Stefanini IT Solutions
 */
public interface CAPESServicoReplicacaoCrudDaoIF<T extends CAPESEntidadeBase<?>> extends
		BancoobCrudDaoIF<T> {

}
