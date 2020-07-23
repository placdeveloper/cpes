package br.com.sicoob.capes.cadastro.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.capes.cadastro.negocio.servicos.CAPESCadastroServico;

/**
 * Business delegate a ser usado pelo Sistema CadastroUnicoClientesComum.
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESCadastroDelegate<T extends CAPESCadastroServico> extends
		BancoobDelegate<T> {

}
