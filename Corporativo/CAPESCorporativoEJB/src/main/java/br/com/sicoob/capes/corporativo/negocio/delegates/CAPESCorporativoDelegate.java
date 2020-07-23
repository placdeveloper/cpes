package br.com.sicoob.capes.corporativo.negocio.delegates;

import br.com.bancoob.negocio.delegates.BancoobDelegate;
import br.com.sicoob.capes.corporativo.negocio.servicos.CAPESCorporativoServico;

/**
 * Delegate base para os demais delegates do projeto.
 * 
 * @author bruno.carneiro
 */
public abstract class CAPESCorporativoDelegate<S extends CAPESCorporativoServico> extends BancoobDelegate<S> {

}