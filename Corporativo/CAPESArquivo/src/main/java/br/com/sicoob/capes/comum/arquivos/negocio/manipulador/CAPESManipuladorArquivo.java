package br.com.sicoob.capes.comum.arquivos.negocio.manipulador;

import java.io.InputStream;

import br.com.sicoob.capes.comum.arquivos.excecao.ArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.negocio.vo.DadosArquivoVO;

/**
 * Interface do gerenciador de arquivo que cont�m m�todos para importa��o e exporta��o de arquivos texto. Os casos de
 * uso que possuam importa��o e/ou exporta��o de arquivos texto devem herdar deste servi�o.
 * 
 * Criado em 21/7/2014
 * 
 * @author rodrigo.chaves
 */
public interface CAPESManipuladorArquivo {

	/**
	 * Importa um arquivo de texto, ou seja, converte cada linha do arquivo em um objeto do tipo configurado em
	 * <code>D</code>.
	 * 
	 * @param <D>
	 *            - Tipo gen�rico dos dados do arquivo.
	 * @param conteudoArquivo
	 *            - O conte�do em bytes do arquivo.
	 * @param classeDadosArquivo
	 *            - O tipo (classe) dos dados gerados do arquivo.
	 * @return DadosArquivoVo - o conte�do do arquivo traduzido para seus objetos correspondentes.
	 * @throws ArquivoInvalidoException.
	 */
	public <D extends DadosArquivoVO> D importar(InputStream conteudoArquivo, Class<D> classeDadosArquivo)
			throws ArquivoInvalidoException;

	/**
	 * Exporta objetos para um arquivo de texto.
	 * 
	 * @param <D>
	 *            - Tipo gen�rico do tipo do arquivo.
	 * @param dadosArquivo
	 *            - Conte�do do arquivo representado com objetos.
	 * @return InputStream - os dados do arquivo.
	 * @throws ArquivoInvalidoException.
	 */
	public <D extends DadosArquivoVO> InputStream exportar(D dadosArquivo) throws ArquivoInvalidoException;

}
