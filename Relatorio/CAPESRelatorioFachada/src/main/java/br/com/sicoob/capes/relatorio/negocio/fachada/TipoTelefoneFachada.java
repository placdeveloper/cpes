/* 
 * Sicoob
 * TipoTelefoneFachada.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.relatorio.negocio.fachada;

import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.relatorio.negocio.delegates.CAPESRelatorioDominioDelegate;

/**
 * Define as operações do serviço de manipulação de tipo de telefone
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 */
@RemoteService
public class TipoTelefoneFachada extends CAPESRelatorioDominioFachada {

	/**
	 * Construtor
	 */
	public TipoTelefoneFachada() {
		super("tipoTelefone");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESRelatorioDominioDelegate<?> obterDelegate() {
		return obterFabricaDelegates().criarTipoTelefoneDelegate();
	}


}
