package br.com.sicoob.capes.relatorio.negocio.relatorios;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.vo.FormularioVisitaVO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

@SuppressWarnings("deprecation")
public class RelatorioFormularioVisita extends RelatorioCapes {
	private static final long serialVersionUID = 1L;

	public RelatorioFormularioVisita(FormularioVisitaVO vo) throws BancoobException{
		super("FormularioVisitaTemplate.jasper");
		getParametros().put("TITULO", vo.getTituloRelatorio());
		getParametros().put("CABECALHO", recuperarRelatorio("CabecalhoFormularioVisita.jasper"));
		
		//DETAIL
		getParametros().put("SUBREPORT_DIR", recuperarRelatorio(vo.getArquivoRelatorio()));
		getParametros().put("DADOS", vo);
	}

	@Override
	protected JRDataSource getDataSource() throws BancoobException {
		return new JREmptyDataSource();
	}

}