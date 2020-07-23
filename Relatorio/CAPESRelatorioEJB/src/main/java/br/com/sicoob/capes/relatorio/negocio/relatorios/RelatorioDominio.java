/* 
 * Sicoob
 * RelatorioTipoAnotacao.java 
 * Criado em: 06/05/2011
 */
package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.CAPESEntidade;
import br.com.sicoob.relatorio.api.datasource.ColecaoDataSource;
import net.sf.jasperreports.engine.JRDataSource;

/**
 * Relatório de domínios
 * 
 * 06/05/2011
 * 
 * @author rodrigo.chaves
 * 
 */
public class RelatorioDominio<E extends CAPESEntidade<?>> extends
		RelatorioCapes {

	/** Serial UID */
	private static final long serialVersionUID = -7450689321320660472L;

	/** O atributo dados. */
	private List<E> dados;

	/**
	 * Construtor
	 * 
	 * @param templateRelatorio
	 *            Nome do arquivo de template do relatório
	 * @param tituloRelatorio
	 *            TODO
	 * @param codigoRelatorio
	 *            O código do relatório
	 * @param dadosRelatorio
	 *            A lista com os dados do relatório
	 * @param numeroCooperativa
	 *            O número da cooperativa do usuário logado
	 * @param siglaCooperativa
	 *            A sigla da cooperativa do usuário logado
	 */
	public RelatorioDominio(String templateRelatorio, String tituloRelatorio,
			String codigoRelatorio, List<E> dadosRelatorio, Integer numeroCooperativa,
			String siglaCooperativa) {

		super(templateRelatorio);
		this.dados = dadosRelatorio;
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