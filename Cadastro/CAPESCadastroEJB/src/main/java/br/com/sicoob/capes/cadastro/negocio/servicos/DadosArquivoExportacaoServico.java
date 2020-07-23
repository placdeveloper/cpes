package br.com.sicoob.capes.cadastro.negocio.servicos;

import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.capes.negocio.entidades.DadosArquivoExportacao;
import br.com.sicoob.capes.negocio.entidades.Exportacao;
import br.com.sicoob.capes.negocio.entidades.TipoInformacao;

/**
 * @since 1.2.1.0
 * @author rodrigo.chaves
 */
public interface DadosArquivoExportacaoServico extends CAPESCadastroCrudServico<DadosArquivoExportacao> {

	/**
	 * O m�todo Incluir.
	 *
	 * @param listaDados o valor de lista dados
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	void incluir(List<DadosArquivoExportacao> listaDados) throws BancoobException;
	
	/**
	 * Obter codigos tipo informacao exportacao.
	 *
	 * @param exportacao o valor de exportacao
	 * @return List
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	List<TipoInformacao> obterCodigosTipoInformacaoExportacao(Exportacao exportacao) throws BancoobException;
	
	/**
	 * Obter proximo numero linha.
	 *
	 * @param exportacao o valor de exportacao
	 * @return Integer
	 * @throws BancoobException lan�a a exce��o BancoobException
	 */
	Integer obterProximoNumeroLinha(Exportacao exportacao) throws BancoobException;
	
}