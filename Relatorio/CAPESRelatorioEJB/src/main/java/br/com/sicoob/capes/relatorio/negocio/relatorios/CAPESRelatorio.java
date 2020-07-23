package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.relatorios.RepositorioRelatorios;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.capes.relatorio.negocio.enums.OrientacaoPaginaEnum;
import br.com.sicoob.relatorio.api.datasource.ColecaoDataSource;
import br.com.sicoob.relatorio.api.jasper.SicoobJasperReports;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Classe base para os novos relatorios do CAPES, aderentes ao layout padrao do
 * SICOOB
 * 
 * @author Rodrigo.Chaves
 */
public abstract class CAPESRelatorio extends SicoobJasperReports {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 4514375690726604672L;

	private Collection<?> dados;

	/**
	 * @param orientacao
	 *            a orietacao do relatorio:
	 *            <ul>
	 *            <li>Paisagem</li>
	 *            <li>Retrato</li>
	 *            <ul>
	 * @param dados
	 *            colecao contendo os dados do relatorio
	 * @throws BancoobException
	 */
	protected <C extends Collection<?>> CAPESRelatorio(OrientacaoPaginaEnum orientacao, C dados) throws BancoobException {
		super("CAPESTemplate" + orientacao.getDescricao() + ".jasper");
		this.dados = dados;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		getParametros().put("URL_LOGO", classLoader.getResource("br/com/sicoob/capes/relatorio/sicoob.jpg"));

		// comum
		getParametros().put("USUARIO", InformacoesUsuario.getInstance());

		// cabecalho
		getParametros().put("TITULO", getTituloRelatorio());
		getParametros().put("TEXTO_COMPLEMENTAR", getTextoComplementar());
		getParametros().put("CABECALHO", recuperarRelatorio("CAPESCabecalho" + orientacao.getDescricao() + ".jasper"));

		// DETALHE
		getParametros().put("DADOS", new ColecaoDataSource(this.dados, 1, true));
		getParametros().put("DETALHE", recuperarRelatorio(getNomeTemplateDados()));
		getParametros().put("SUBRELATORIOS", getSubrelatorios());

		// rodape
		getParametros().put("ID_RELATORIO", getIdRelatorio());
		getParametros().put("RODAPE", recuperarRelatorio("CAPESRodape" + orientacao.getDescricao() + ".jasper"));
	}

	/**
	 * Recupera os subrelatorios, caso existam
	 * 
	 * @return lista com os subrelatorios
	 * @throws BancoobException
	 */
	private List<JasperReport> getSubrelatorios() throws BancoobException {
		List<JasperReport> subrelatorios = new ArrayList<JasperReport>();
		List<String> templates = getSubTemplates();
		if (templates != null) {
			for (String template : templates) {
				subrelatorios.add(recuperarRelatorio(template));
			}
		}
		return subrelatorios;
	}

	/**
	 * Recupera os templates dos subrelatorios, caso existam
	 * 
	 * @return
	 */
	protected List<String> getSubTemplates() {
		return new ArrayList<String>();
	}

	/**
	 * Recuperar relatorio.
	 * 
	 * @param relatorio
	 *            o valor de relatorio
	 * @return JasperReport
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	protected JasperReport recuperarRelatorio(String relatorio) throws BancoobException {
		try {
			RepositorioRelatorios instance = RepositorioRelatorios.getInstance();
			InputStream headerIS = instance.recuperarRelatorio(relatorio);
			return (JasperReport) JRLoader.loadObject(headerIS);
		} catch (JRException e) {
			throw new BancoobException(e);
		}
	}

	/**
	 * recupera o nome do template para o subrelatorio com os dados
	 * 
	 * @return o nome do template
	 */
	protected abstract String getNomeTemplateDados();

	/**
	 * recupera o ID do relatorio para exibicao no rodape
	 * 
	 * @return o ID
	 */
	protected abstract String getIdRelatorio();

	/**
	 * recupera o titulo do relatorio que sera exibido no cabecalho
	 * 
	 * @return o titulo
	 */
	protected abstract String getTituloRelatorio();

	/**
	 * recupera o texto completar que sera exibido no cabecalho do relatorio
	 * 
	 * @returno texto complementar
	 */
	protected String getTextoComplementar() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final JRDataSource getDataSource() throws BancoobException {
		return new ColecaoDataSource(this.dados, 1, true);
	}
	
}