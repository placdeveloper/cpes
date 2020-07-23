/**
 * 
 */
package br.com.sicoob.capes.relatorio.negocio.relatorios;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioProvaVidaDTO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

/**
 * Classe Relatorio de Prova de Vida.
 * 
 * @author Daniel.Domingues
 *
 */
public class RelatorioProvaVida extends RelatorioCapes {

	private static final long serialVersionUID = -6994693180185043312L;

	private static final String TEMPLATE_RELATORIO = "RelatorioProvaVida.jasper";
	private static final String PARAMETRO_FILTRO = "FILTRO";
	
	/**
	 * Construtor pelos paramentros informados.
	 * 
	 * @param filtro RelatorioProvaVidaDTO
	 */
	public RelatorioProvaVida(RelatorioProvaVidaDTO filtro) {
		super(TEMPLATE_RELATORIO);
		getParametros().put(PARAMETRO_FILTRO, filtro);
	}
	
	/**
	 * @see br.com.bancoob.negocio.relatorios.jasper.RelatorioJasperReports#getDataSource()
	 */
	@Override
	protected JRDataSource getDataSource() throws BancoobException {
		return new JREmptyDataSource();
	}

}
