package br.com.sicoob.capes.comum.arquivos.teste.pocli;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;

/**
 * A Classe RodapePortifolio.
 */
public class RodapePortifolio implements RegistroArquivo {

	/** O atributo numeroLinha. */
	private int numeroLinha;

	/** O atributo tipoRegistro. */
	@CampoArquivo(inicio = 0, tamanho = 1)
	private Short tipoRegistro = 9;

	/** O atributo totalRegistros. */
	@CampoArquivo(inicio = 1, tamanho = 8)
	private Integer totalRegistros;

	/** O atributo filler. */
	@CampoArquivo(inicio = 9, tamanho = 106)
	private String filler;

	/**
	 * Instancia um novo RodapePortifolio.
	 */
	public RodapePortifolio() {

	}

	/**
	 * Instancia um novo RodapePortifolio.
	 *
	 * @param qtdeLinhas o valor de qtde linhas
	 */
	public RodapePortifolio(Integer qtdeLinhas) {

		this.totalRegistros = qtdeLinhas;
	}

	/**
	 * Recupera o valor de tipoRegistro.
	 *
	 * @return o valor de tipoRegistro
	 */
	public Short getTipoRegistro() {

		return tipoRegistro;
	}

	/**
	 * Define o valor de tipoRegistro.
	 *
	 * @param tipoRegistro o novo valor de tipoRegistro
	 */
	public void setTipoRegistro(Short tipoRegistro) {

		this.tipoRegistro = tipoRegistro;
	}

	/**
	 * Recupera o valor de totalRegistros.
	 *
	 * @return o valor de totalRegistros
	 */
	public Integer getTotalRegistros() {

		return totalRegistros;
	}

	/**
	 * Define o valor de totalRegistros.
	 *
	 * @param totalRegistros o novo valor de totalRegistros
	 */
	public void setTotalRegistros(Integer totalRegistros) {

		this.totalRegistros = totalRegistros;
	}

	/**
	 * Recupera o valor de filler.
	 *
	 * @return o valor de filler
	 */
	public String getFiller() {

		return filler;
	}

	/**
	 * Define o valor de filler.
	 *
	 * @param filler o novo valor de filler
	 */
	public void setFiller(String filler) {

		this.filler = filler;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getNumeroLinha() {

		return numeroLinha;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setNumeroLinha(int numeroLinha) {

		this.numeroLinha = numeroLinha;
	}

}
