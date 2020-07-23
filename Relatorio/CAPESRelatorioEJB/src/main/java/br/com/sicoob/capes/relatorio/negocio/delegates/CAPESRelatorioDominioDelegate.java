package br.com.sicoob.capes.relatorio.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.capes.relatorio.negocio.servicos.CAPESRelatorioDominioServico;

/**
 * Business delegate a ser usado pelo Sistema CAPESRelatorio.
 * 
 * @author Stefanini IT Solutions
 */
public abstract class CAPESRelatorioDominioDelegate<T extends CAPESRelatorioDominioServico<?>> extends BancoobDelegate<T> {

}