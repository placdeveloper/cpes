package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.io.InputStream;

import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.infraestrutura.relatorios.RepositorioRelatorios;
import br.com.sicoob.relatorio.api.jasper.SicoobJasperReports;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @deprecated foi criada uma nova estrutura de relatorio. Utilize {@link CAPESRelatorio}
 */
@Deprecated
public abstract class RelatorioCapes extends SicoobJasperReports {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -2385833500444110473L;

	/**
	 * @deprecated foi criada uma nova estrutura de relatorio. Utilize {@link CAPESRelatorio}
	 */
	@Deprecated
	protected RelatorioCapes(String arquivo) {
		super(arquivo);
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		getParametros().put("URL_LOGO", classLoader.getResource("br/com/sicoob/capes/relatorio/sicoob.jpg"));
	}

	/**
	 * Recuperar relatorio.
	 *
	 * @param relatorio o valor de relatorio
	 * @return Object
	 * @throws JRException lança a exceção JRException
	 */
	protected Object recuperarRelatorio(String relatorio) {
    	try {
    		RepositorioRelatorios instance = RepositorioRelatorios.getInstance();
    		InputStream headerIS = instance.recuperarRelatorio(relatorio);
			return JRLoader.loadObject(headerIS);
		} catch (JRException e) {
			throw new BancoobRuntimeException(e);
		}
    }

}
