package br.com.sicoob.capes.cadastro.negocio.delegates;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.cadastro.negocio.servicos.DadosArquivoExportacaoServico;
import br.com.sicoob.capes.cadastro.negocio.servicos.locator.CAPESCadastroServiceLocator;
import br.com.sicoob.capes.negocio.entidades.DadosArquivoExportacao;
import br.com.sicoob.capes.negocio.entidades.Exportacao;
import br.com.sicoob.capes.negocio.entidades.TipoInformacao;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public class DadosArquivoExportacaoDelegate extends CAPESCadastroCrudDelegate<DadosArquivoExportacao, DadosArquivoExportacaoServico> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DadosArquivoExportacaoServico localizarServico() {
		return CAPESCadastroServiceLocator.getInstance().localizarDadosArquivoExportacaoServico();
	}
	
	/**
	 * O método Incluir.
	 *
	 * @param listaDados o valor de lista dados
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public void incluir(List<DadosArquivoExportacao> listaDados) throws BancoobException {
		getServico().incluir(listaDados);
	}
	
	/**
	 * Obter codigos tipo informacao exportacao.
	 *
	 * @param exportacao o valor de exportacao
	 * @return List
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public List<TipoInformacao> obterCodigosTipoInformacaoExportacao(Exportacao exportacao) throws BancoobException {
		return getServico().obterCodigosTipoInformacaoExportacao(exportacao);
	}
	
	/**
	 * Obter proximo numero linha.
	 *
	 * @param exportacao o valor de exportacao
	 * @return Integer
	 * @throws BancoobException lança a exceção BancoobException
	 */
	public Integer obterProximoNumeroLinha(Exportacao exportacao) throws BancoobException {
		return getServico().obterProximoNumeroLinha(exportacao);
	}

}