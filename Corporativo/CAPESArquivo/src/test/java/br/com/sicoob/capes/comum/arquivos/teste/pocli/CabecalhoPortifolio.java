package br.com.sicoob.capes.comum.arquivos.teste.pocli;

import java.util.Date;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;

/**
 * A Classe CabecalhoPortifolio.
 */
public class CabecalhoPortifolio implements RegistroArquivo {

	/** O atributo numeroLinha. */
	private int numeroLinha;

	/** O atributo tipoRegistro. */
	@CampoArquivo(inicio = 0, tamanho = 1)
	private Short tipoRegistro = 0;

	/** O atributo dataArquivo. */
	@CampoArquivo(inicio = 1, tamanho = 8, formatoData = "yyyyMMdd")
	private Date dataArquivo;

	/** O atributo filler. */
	@CampoArquivo(inicio = 9, tamanho = 106)
	private String filler;

	/**
	 * Instancia um novo CabecalhoPortifolio.
	 */
	public CabecalhoPortifolio() {

	}

	/**
	 * Instancia um novo CabecalhoPortifolio.
	 *
	 * @param data o valor de data
	 */
	public CabecalhoPortifolio(Date data) {

		this.dataArquivo = data;
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
	 * Recupera o valor de dataArquivo.
	 *
	 * @return o valor de dataArquivo
	 */
	public Date getDataArquivo() {

		return dataArquivo;
	}

	/**
	 * Define o valor de dataArquivo.
	 *
	 * @param dataArquivo o novo valor de dataArquivo
	 */
	public void setDataArquivo(Date dataArquivo) {

		this.dataArquivo = dataArquivo;
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

		return this.numeroLinha;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setNumeroLinha(int numeroLinha) {

		this.numeroLinha = numeroLinha;
	}

}
