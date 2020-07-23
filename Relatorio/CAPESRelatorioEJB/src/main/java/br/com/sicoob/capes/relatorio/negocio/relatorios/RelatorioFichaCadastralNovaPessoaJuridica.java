package br.com.sicoob.capes.relatorio.negocio.relatorios;

import net.sf.jasperreports.engine.JRException;
import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralVO;

/**
 * A Classe RelatorioFichaCadastralPessoaJuridica.
 */
public class RelatorioFichaCadastralNovaPessoaJuridica extends
		RelatorioFichaCadastralNova {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 4389269354091381354L;

	/**
	 * Instancia um novo RelatorioFichaCadastralPessoaJuridica.
	 *
	 * @param fichaCadastralVO o valor de ficha cadastral vo
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	public RelatorioFichaCadastralNovaPessoaJuridica(FichaCadastralVO fichaCadastralVO) throws BancoobException {
		super(fichaCadastralVO);
	}
	
	/**
	 * {@inheritDoc}
	 */
	protected String recuperarTituloRelatorio() {
		return "FICHA CADASTRAL PESSOA JUR�DICA";
	}

	/**
	 * {@inheritDoc}
	 */
	protected Object recuperarRelatorioDadosPessoais() throws JRException {
		return recuperarRelatorio("FichaCadastralNova_DadosPessoaisPJ.jasper");
	}
}
