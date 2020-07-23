package br.com.sicoob.capes.relatorio.negocio.fachada;

import br.com.sicoob.capes.relatorio.negocio.delegates.CAPESRelatorioDominioDelegate;

/**
 * A Classe CAPESRelatorioDominioFachada.
 */
public abstract class CAPESRelatorioDominioFachada extends CAPESRelatorioFachada {

	/** O atributo chaveMapa. */
	protected String chaveMapa;

	/**
	 * Construtor
	 * 
	 * @param chaveMapa
	 */
	public CAPESRelatorioDominioFachada(String chaveMapa) {
		this.chaveMapa = chaveMapa;
	}

	/**
	 * Recupera o delegate
	 * 
	 * @return o delegate
	 */
	protected abstract CAPESRelatorioDominioDelegate<?> obterDelegate();

}
