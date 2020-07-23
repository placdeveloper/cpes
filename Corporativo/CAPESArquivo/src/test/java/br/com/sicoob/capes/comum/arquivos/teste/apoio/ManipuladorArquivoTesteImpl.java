package br.com.sicoob.capes.comum.arquivos.teste.apoio;

import org.apache.commons.lang.math.RandomUtils;

import br.com.sicoob.capes.comum.arquivos.excecao.ArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.Tratador;
import br.com.sicoob.capes.comum.arquivos.negocio.manipulador.CAPESManipuladorArquivoAbstract;

/**
 * A Classe ManipuladorArquivoTesteImpl.
 */
public class ManipuladorArquivoTesteImpl extends CAPESManipuladorArquivoAbstract implements ManipuladorArquivoTeste {

	/** O atributo i. */
	private int i = 0;

	/**
	 * Tratar registro arquivo.
	 *
	 * @param cabecalho o valor de cabecalho
	 * @return {@code true}, em caso de sucesso
	 */
	@Tratador
	public boolean tratarRegistroArquivo(CabecalhoEntidadeTeste cabecalho) {

		System.out.println("ManipuladorArquivoTesteImpl.tratarRegistroArquivo(CabecalhoEntidadeTeste): linha " + i);
		i++;
		return true;
	}

	/**
	 * Tratar registro arquivo.
	 *
	 * @param registro o valor de registro
	 * @return {@code true}, em caso de sucesso
	 */
	@Tratador
	public boolean tratarRegistroArquivo(EntidadeTeste registro) {

		if (i % 100 == 0) {
			System.out.println("ManipuladorArquivoTesteImpl.tratarRegistroArquivo(EntidadeTeste): linha " + i);
		}
		i++;
		return RandomUtils.nextBoolean() || RandomUtils.nextBoolean();
	}

	/**
	 * Tratar registro arquivo.
	 *
	 * @param rodape o valor de rodape
	 * @return {@code true}, em caso de sucesso
	 */
	@Tratador
	public boolean tratarRegistroArquivo(RodapeEntidadeTeste rodape) {

		System.out.println("ManipuladorArquivoTesteImpl.tratarRegistroArquivo(RodapeEntidadeTeste): linha " + i);
		i++;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void tratarRegistroInvalido(String linha, int numeroLinha) throws ArquivoInvalidoException {

		System.err.println("ManipuladorArquivoTesteImpl.tratarRegistroInvalido(): linha " + i + " = " + linha);
	}
}
