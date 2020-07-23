/* 
 * Sicoob
 * RelatorioTipoAnotacao.java 
 * Criado em: 20/12/2010
 */
package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.comum.negocio.vo.GrupoCompartilhamentoVO;
import br.com.sicoob.relatorio.api.datasource.ColecaoDataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * A Classe RelatorioGrupoCompartilhamento.
 */
public class RelatorioGrupoCompartilhamento extends RelatorioCapes {


	/** Serial UID */
	private static final long serialVersionUID = -8342966445370210418L;
	
	/** O atributo dados. */
	private List<GrupoCompartilhamentoVO> dados;
	
	/**
	 * Construtor
	 * 
	 * @param dados
	 *            A lista com os dados do relatório
	 * @param numeroCooperativa
	 *            O número da cooperativa do usuário logado
	 * @param siglaCooperativa
	 *            A sigla da cooperativa do usuário logado
	 */
	public RelatorioGrupoCompartilhamento(List<GrupoCompartilhamentoVO> dados, Integer numeroCooperativa,
			String siglaCooperativa, String codigoRelatorio, String tituloRelatorio, String template) {
		
		super(template);
		this.dados = dados;
		getParametros().put("TITULO_RELATORIO", tituloRelatorio);
		getParametros().put("CODIGO_RELATORIO", codigoRelatorio);
		getParametros().put("NUMERO_COOPERATIVA", numeroCooperativa);
		getParametros().put("SIGLA_COOPERATIVA", siglaCooperativa);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected JRDataSource getDataSource() throws BancoobException {
		return new ColecaoDataSource(this.dados, 1, true);
	}

}
