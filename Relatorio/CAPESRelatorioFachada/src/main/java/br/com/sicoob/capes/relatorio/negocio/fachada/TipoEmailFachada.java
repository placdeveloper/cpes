/* 
 * Sicoob
 * TipoEmailFachada.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.relatorio.negocio.fachada;

import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.relatorio.negocio.delegates.CAPESRelatorioDominioDelegate;

/**
 * Define as operações do serviço de manipulação de tipo de e-mail
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 */
@RemoteService
public class TipoEmailFachada extends CAPESRelatorioDominioFachada {

	/**
	 * Construtor
	 */
	public TipoEmailFachada() {
		super("tipoEmail");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESRelatorioDominioDelegate<?> obterDelegate() {
		return obterFabricaDelegates().criarTipoEmailDelegate();
	}


}
