package br.com.sicoob.capes.relatorio.negocio.relatorios;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.relatorio.negocio.vo.FichaCadastralVO;

/**
 * A Classe RelatorioFichaCadastralPessoaFisica.
 */
public class RelatorioFichaCadastralPessoaFisica extends RelatorioFichaCadastral {

	/** A constante serialVersionUID. */
	private static final long serialVersionUID = 4389269354091381354L;

	/**
	 * Instancia um novo RelatorioFichaCadastralPessoaFisica.
	 *
	 * @param fichaCadastralVO o valor de ficha cadastral vo
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public RelatorioFichaCadastralPessoaFisica(FichaCadastralVO fichaCadastralVO) throws BancoobException {
		super(fichaCadastralVO);		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String recuperarTituloRelatorio() {
		return "FICHA CADASTRAL PESSOA FÍSICA";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object recuperarRelatorioDadosPessoais() {
		return recuperarRelatorio("FichaCadastral_DadosPessoaisPF.jasper");
	}
}


