package br.com.sicoob.capes.relatorio.negocio.relatorios;

import java.text.MessageFormat;
import java.util.Collection;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.enums.OrientacaoPaginaEnum;
import br.com.sicoob.capes.relatorio.negocio.enums.TipoRelatorioEnum;

/**
 * @author Rodrigo.Chaves
 */
public abstract class RelatorioValidacaoCadastral extends CAPESRelatorio {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 2867018590859453392L;
	
	/** A constante NOME_RELATORIO. */
	private static final String NOME_RELATORIO = "RELATORIO_VALIDACAO_CADASTRAL_";

	/**
	 * @param orientacao
	 *            a orientacao do relatorio
	 * @param dados 
	 * @throws BancoobException
	 */
	public <C extends Collection<?>> RelatorioValidacaoCadastral(OrientacaoPaginaEnum orientacao,
	        C dados) throws BancoobException {

		super(orientacao, dados);
	}
	
	/**
	 * recupera o tipo do relatorio
	 */
	protected abstract TipoRelatorioEnum getTipoRelatorio();

	/**
	 * {@inheritDoc}
	 */
	@Override
    protected String getNomeTemplateDados() {
    	return MessageFormat.format("RelatorioValidacaoCadastral_{0}.jasper", getTipoRelatorio()
    	        .getNome());
    }

	/**
     * recupera o nome do relatorio para ser usado na sessao http
     * 
     * @return o nome do relatorio
     */
    public String getNomeRelatorio() {
    	return NOME_RELATORIO + getTipoRelatorio().name();
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    protected String getTituloRelatorio() {
    	return "Relatório de Validação Cadastral - " + getTipoRelatorio().getDescricao();
    }
}
