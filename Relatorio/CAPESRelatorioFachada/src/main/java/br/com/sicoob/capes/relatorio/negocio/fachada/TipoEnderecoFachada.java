/* 
 * Sicoob
 * TipoEnderecoFachada.java 
 * Criado em: 05/05/2011
 */
package br.com.sicoob.capes.relatorio.negocio.fachada;

import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.relatorio.negocio.delegates.CAPESRelatorioDominioDelegate;

/**
 * Define as operações do serviço de manipulação de tipo de endereço
 * 
 * 05/05/2011
 * 
 * @author Rodrigo.Chaves
 */
@RemoteService
public class TipoEnderecoFachada extends CAPESRelatorioDominioFachada  {

	/**
	 * Construtor
	 */
	public TipoEnderecoFachada() {
		super("tipoEndereco");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESRelatorioDominioDelegate<?> obterDelegate() {
		return obterFabricaDelegates().criarTipoEnderecoDelegate();
	}

}
