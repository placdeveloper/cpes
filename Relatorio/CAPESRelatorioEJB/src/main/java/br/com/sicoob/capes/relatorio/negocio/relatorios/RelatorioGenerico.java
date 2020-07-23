package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.Collection;
import java.util.Date;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.sicoob.capes.comum.negocio.vo.InstituicaoVO;
import br.com.sicoob.relatorio.api.datasource.ColecaoDataSource;
import br.com.sicoob.relatorio.api.jasper.SicoobJasperReports;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * A Classe RelatorioGenerico.
 */
public class RelatorioGenerico extends SicoobJasperReports {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5677288383995585612L;

	private Collection<?> dados;

	public <C extends Collection<?>> RelatorioGenerico(Relatorios relatorio, C dados, InstituicaoVO instituicao) {
		super(relatorio.getNomeArquivo());
		this.dados = dados;
		parametrosGerais(instituicao, relatorio);
	}

	public <C extends Collection<?>> RelatorioGenerico(Object filtro, Relatorios relatorio, C dados, InstituicaoVO instituicao) {
		super(relatorio.getNomeArquivo());
		this.dados = dados;
		getParametros().put("FILTRO", filtro);
		parametrosGerais(instituicao, relatorio);
	}

	private void parametrosGerais(InstituicaoVO instituicao, Relatorios relatorio) {
		String loginUsuario = InformacoesUsuario.getInstance().getLogin();
		getParametros().put("LOGIN_USUARIO", loginUsuario);
		getParametros().put("SIGLA_COOPERATIVA", instituicao.getSiglaInstituicao());
		getParametros().put("DATA_PROCESSAMENTO", new Date());
		getParametros().put("NUMERO_COOPERATIVA", instituicao.getNumero());
		getParametros().put("COD_RELATORIO", relatorio.getCodigo());
	}

	@Override
	protected JRDataSource getDataSource() throws BancoobException {
		return new ColecaoDataSource(this.dados, 1, true);
	}

}
