package br.com.sicoob.capes.relatorio.negocio.fachada;


/**
 * A Classe CAPESRelatorioComumFachada.
 */
public abstract class CAPESRelatorioComumFachada extends CAPESRelatorioFachada{
	
	/** O atributo chaveMapa. */
	protected String chaveMapa;

	/**
	 * Construtor
	 * 
	 * @param chaveMapa
	 */
	public CAPESRelatorioComumFachada(String chaveMapa) {
		this.chaveMapa = chaveMapa;
	}
	
}
