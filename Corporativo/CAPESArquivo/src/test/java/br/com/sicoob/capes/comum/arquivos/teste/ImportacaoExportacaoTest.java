package br.com.sicoob.capes.comum.arquivos.teste;

import java.text.NumberFormat;

import org.apache.commons.lang.time.DateUtils;

/**
 * A Classe ImportacaoExportacaoTest.
 */
public abstract class ImportacaoExportacaoTest {

	/**
	 * Converter para segundos.
	 *
	 * @param milisegundos o valor de milisegundos
	 * @return String
	 */
	protected String converterParaSegundos(double milisegundos) {
	
		return NumberFormat.getInstance().format(milisegundos / DateUtils.MILLIS_PER_SECOND);
	}

}
