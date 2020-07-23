/* 
 * Sicoob
 * RelatorioAnotacao.java 
 * Criado em: 30/06/2011
 */
package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.dto.ConsultaAnotacaoDTO;
import br.com.sicoob.capes.negocio.entidades.Anotacao;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * TODO javadoc
 *
 * 30/06/2011
 * @author Rodrigo.Chaves
 * 
 */
public class RelatorioAnotacao extends RelatorioCapes {

	/** Serial UID */
	private static final long serialVersionUID = -5296941529247088795L;
	
	/** A constante TEMPLATE_RELATORIO. */
	private static final String TEMPLATE_RELATORIO = "RelatorioAnotacao.jasper";
	
	/** A constante CODIGO_RELATORIO. */
	private static final String CODIGO_RELATORIO = "CLI018";
	
	/** O atributo dados. */
	private List<Anotacao> dados;

	/**
	 * Construtor
	 * @param filtro TODO
	 * @param arquivo
	 */
	public RelatorioAnotacao(ConsultaAnotacaoDTO filtro, List<Anotacao> dados,
			Integer numeroCooperativa, String siglaCooperativa) {
		
		super(TEMPLATE_RELATORIO);
		this.dados = dados;
		getParametros().put("FILTRO", filtro);
		getParametros().put("CODIGO_RELATORIO", CODIGO_RELATORIO);
		getParametros().put("NUMERO_COOPERATIVA", numeroCooperativa);
		getParametros().put("SIGLA_COOPERATIVA", siglaCooperativa);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected JRDataSource getDataSource() throws BancoobException {
		return new JRBeanCollectionDataSource(this.dados);
	}

}
