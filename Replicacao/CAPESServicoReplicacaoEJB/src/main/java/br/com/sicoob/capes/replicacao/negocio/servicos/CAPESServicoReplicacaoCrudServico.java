package br.com.sicoob.capes.replicacao.negocio.servicos;

import br.com.bancoob.negocio.servicos.BancoobCrudServico;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidadeBase;

/**
 * Interface que define um contrato padrao para as operacoes de 
 * CRUD de todo o sistema ServicoReplicacaoCadastroUnicoClientes
 *
 * @author Stefanini IT Solutions
 */
public interface CAPESServicoReplicacaoCrudServico<T extends CAPESEntidadeBase<?>>
		extends CAPESServicoReplicacaoServico, BancoobCrudServico<T> {

}
