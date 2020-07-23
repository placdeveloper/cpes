package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioTributacaoDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioTributacaoVO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * A Classe RelatorioTributacao.
 */
public class RelatorioTributacao extends RelatorioCapes {

    /** A constante serialVersionUID. */
    private static final long serialVersionUID = -56607489058976301L;
    
	/** O atributo dados. */
	private List<RelatorioTributacaoVO> dados;

	/**
	 * Instancia um novo RelatorioTributacao.
	 *
	 * @param filtro o valor de filtro
	 * @param dados o valor de dados
	 */
    public RelatorioTributacao(RelatorioTributacaoDTO filtro, List<RelatorioTributacaoVO> dados) {
		
		super("RelatorioTributacao.jasper");
		this.dados = dados;
		getParametros().put("TITULO_RELATORIO", "Relatório de Tributação de Clientes");
		getParametros().put("CODIGO_RELATORIO", "CLI011");
		getParametros().put("FILTRO", filtro);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected JRDataSource getDataSource() throws BancoobException {
		return new JRBeanCollectionDataSource(this.dados);
	}

}
