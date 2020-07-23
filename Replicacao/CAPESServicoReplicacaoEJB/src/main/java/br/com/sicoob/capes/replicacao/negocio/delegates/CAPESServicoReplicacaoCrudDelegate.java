package br.com.sicoob.capes.replicacao.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobCrudDelegate;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidadeBase;
import br.com.sicoob.capes.replicacao.negocio.servicos.CAPESServicoReplicacaoCrudServico;

/**
 * Business delegate padrao para operacoes de CRUD do sistema CAPES
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESServicoReplicacaoCrudDelegate<T extends CAPESEntidadeBase<?>, S extends CAPESServicoReplicacaoCrudServico<T>>
		extends BancoobCrudDelegate<T, S> {

}
