/* 
 * Sicoob
 * RelatorioTipoAnotacao.java 
 * Criado em: 20/12/2010
 */
package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.util.Arrays;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.TipoAnotacao;
import br.com.sicoob.capes.relatorio.negocio.enums.OrientacaoPaginaEnum;

/**
 * Relatório de tipos de anotação
 * 
 * 20/12/2010
 * 
 * @author rodrigo.chaves
 */
public class RelatorioTipoAnotacao extends CAPESRelatorio {

	/** Serial UID */
	private static final long serialVersionUID = 8317727117628008578L;

	/** A constante TEMPLATE_RELATORIO. */
	private static final String TEMPLATE_RELATORIO = "TipoAnotacao.jasper";
	
	/** A constante CODIGO_RELATORIO. */
	private static final String CODIGO_RELATORIO = "CLI013";

	/**
	 * Construtor
	 * 
	 * @param dados
	 *            A lista com os dados do relatório
	 * @throws BancoobException
	 */
	public RelatorioTipoAnotacao(List<TipoAnotacao> dados) throws BancoobException {
		super(OrientacaoPaginaEnum.PAISAGEM, dados);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getNomeTemplateDados() {
		return TEMPLATE_RELATORIO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getIdRelatorio() {
		return CODIGO_RELATORIO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTituloRelatorio() {
		return "Relatório de Tipo de Anotação";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<String> getSubTemplates() {
		return Arrays.asList(new String[] { "TipoAnotacao_OrgaosExternos.jasper" });
	}
}
