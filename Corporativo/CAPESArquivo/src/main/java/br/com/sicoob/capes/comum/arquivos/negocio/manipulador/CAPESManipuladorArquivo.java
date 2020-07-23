package br.com.sicoob.capes.comum.arquivos.negocio.manipulador;

import java.io.InputStream;

import br.com.sicoob.capes.comum.arquivos.excecao.ArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.negocio.vo.DadosArquivoVO;

/**
 * Interface do gerenciador de arquivo que contém métodos para importação e exportação de arquivos texto. Os casos de
 * uso que possuam importação e/ou exportação de arquivos texto devem herdar deste serviço.
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
	 *            - Tipo genérico dos dados do arquivo.
	 * @param conteudoArquivo
	 *            - O conteúdo em bytes do arquivo.
	 * @param classeDadosArquivo
	 *            - O tipo (classe) dos dados gerados do arquivo.
	 * @return DadosArquivoVo - o conteúdo do arquivo traduzido para seus objetos correspondentes.
	 * @throws ArquivoInvalidoException.
	 */
	public <D extends DadosArquivoVO> D importar(InputStream conteudoArquivo, Class<D> classeDadosArquivo)
			throws ArquivoInvalidoException;

	/**
	 * Exporta objetos para um arquivo de texto.
	 * 
	 * @param <D>
	 *            - Tipo genérico do tipo do arquivo.
	 * @param dadosArquivo
	 *            - Conteúdo do arquivo representado com objetos.
	 * @return InputStream - os dados do arquivo.
	 * @throws ArquivoInvalidoException.
	 */
	public <D extends DadosArquivoVO> InputStream exportar(D dadosArquivo) throws ArquivoInvalidoException;

}
