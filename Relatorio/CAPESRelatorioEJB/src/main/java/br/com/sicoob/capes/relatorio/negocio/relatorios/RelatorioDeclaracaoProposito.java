package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.infraestrutura.FabricaServicos;
import br.com.bancoob.infraestrutura.relatorios.ServicoRelatorios;
import br.com.sicoob.capes.relatorio.negocio.dto.RelatorioDeclaracaoPropositoDTO;
import br.com.sicoob.capes.relatorio.negocio.vo.RelatorioDeclaracaoPropositoVO;
import br.com.sicoob.relatorio.api.datasource.ColecaoDataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * A Classe RelatorioDeclaracaoProposito.
 */
public class RelatorioDeclaracaoProposito extends RelatorioCapes {
	
	/** A constante serialVersionUID. */
	private static final long serialVersionUID = -1883178435834895760L;

	/** A constante NOME_RELATORIO_SESSAO. */
	public static final String NOME_RELATORIO_SESSAO = "RelatorioDeclaracaoProposito";
	
	/** A constante NOME_RELATORIO_ARQUIVO. */
	public static final String NOME_RELATORIO_ARQUIVO = "RelatorioDeclaracaoProposito.jasper";

	/** O atributo dados. */
	private List<RelatorioDeclaracaoPropositoVO> dados;

	/**
	 * Instancia um novo RelatorioDeclaracaoProposito.
	 *
	 * @param filtros o valor de filtros
	 * @param lista o valor de lista
	 */
	public RelatorioDeclaracaoProposito(RelatorioDeclaracaoPropositoDTO filtros, List<RelatorioDeclaracaoPropositoVO> lista) {
		super(NOME_RELATORIO_ARQUIVO);
		this.dados = lista;

		ServicoRelatorios servicoRelatorios = FabricaServicos.getInstance().criarServicoRelatorios();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		getParametros().put("URL_LOGO", classLoader.getResource("br/com/sicoob/capes/relatorio/sicoob.jpg"));
		getParametros().put("FILTRO", filtros);
		getParametros().put("servicoRelatorios", servicoRelatorios);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected JRDataSource getDataSource() throws BancoobException {
		return new ColecaoDataSource(this.dados, 1, true);
	}

}