package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.Map;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.integracao.negocio.delegates.CAPESIntegracaoFabricaDelegates;
import br.com.sicoob.capes.integracao.negocio.delegates.SCIIntegracaoDelegate;
import br.com.sicoob.capes.negocio.entidades.vigente.PessoaFisica;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralVO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;

public class RelatorioFichaCadastralEmBranco extends RelatorioCapes{

	private static final long serialVersionUID = 1L;
	private static final int BANCOOB = 1;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RelatorioFichaCadastralEmBranco(FichaCadastralVO fichaCadastralVO) throws BancoobException {
		super(recuperaFichaPeloTipoPessoa(fichaCadastralVO));
		Map parametros = getParametros();
		if (fichaCadastralVO.getIdInstituicao() == BANCOOB) {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			getParametros().put("URL_LOGO", classLoader.getResource("br/com/sicoob/capes/relatorio/bancoob.jpg"));
		}
		parametros.put("SUB_HEADER", recuperarRelatorio("CabecalhoFichaCadastral_NOVO.jasper"));
		parametros.put("pessoa", fichaCadastralVO.getPessoa());
		parametros.put("LABEL_FILTRO_DATA", fichaCadastralVO.getLabelFiltroData());
		parametros.put("NUM_COOPERATIVA", obterNumCooperativa(fichaCadastralVO.getIdInstituicao()));
		parametros.put("ID_UNIDADE_INST", fichaCadastralVO.getIdUnidadeInst());
		parametros.put("ID_INSTITUICAO", fichaCadastralVO.getIdInstituicao());
	}
	
	private static String recuperaFichaPeloTipoPessoa(FichaCadastralVO vo) {
		return vo.getPessoa() instanceof PessoaFisica ? 
				"FichaCadastral_SimplesPF_EmBranco.jasper":
					"FichaCadastral_SimplesPJ_EmBranco.jasper";
	}

	@Override
	protected JRDataSource getDataSource() throws BancoobException {
		return new JREmptyDataSource();
	}
	
	private Integer obterNumCooperativa(Integer idInstituicao) throws BancoobException {
		SCIIntegracaoDelegate sciDelegate = CAPESIntegracaoFabricaDelegates.getInstance().criarSCIIntegracaoDelegate();
		return sciDelegate.obterNumeroCooperativa(idInstituicao);
	}
}
