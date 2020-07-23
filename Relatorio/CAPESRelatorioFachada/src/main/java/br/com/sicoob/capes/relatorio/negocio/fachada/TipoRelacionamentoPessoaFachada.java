/* 
 * Sicoob
 * TipoRelacionamentoPessoaFachada.java 
 * Criado em: 08/08/2011
 */
package br.com.sicoob.capes.relatorio.negocio.fachada;

import br.com.bancoob.sisbrweb.seguranca.RemoteService;
import br.com.sicoob.capes.relatorio.negocio.delegates.CAPESRelatorioDominioDelegate;

/**
 * 08/08/2011
 * 
 * @author Rodrigo.Chaves
 */
@RemoteService
public class TipoRelacionamentoPessoaFachada extends CAPESRelatorioDominioFachada {

	/** A constante TIPO_RELACIONAMENTO_PESSOA. */
	private static final String TIPO_RELACIONAMENTO_PESSOA = "tipoRelacionamentoPessoa";

	/**
	 * Construtor
	 * 
	 * @param chaveMapa
	 */
	public TipoRelacionamentoPessoaFachada() {
		super(TIPO_RELACIONAMENTO_PESSOA);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CAPESRelatorioDominioDelegate<?> obterDelegate() {
		return obterFabricaDelegates().criarTipoRelacionamentoDelegate();
	}


}
