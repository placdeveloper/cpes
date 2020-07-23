package br.com.sicoob.capes.comum.arquivos.util.conversao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.sicoob.capes.comum.arquivos.excecao.CampoArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.util.StringUtils;

/**
 * Conversor utilizado para converter <code>Character</code> para
 * <code>Date</code> e vice-versa.
 *
 * Criado em 21/7/2014.
 * @author rodrigo.chaves
 */
public class ConversorData extends ConversorAbstrato<Date> {
	
	/**
	 * {@inheritDoc}
	 */
	public Date converterDeString(String valor, CampoArquivo metadados) throws CampoArquivoInvalidoException {
		verificarObrigatoriedade(valor, metadados);

		Date data = null;
		if (StringUtils.isNotBlank(valor)) {
			try {
				data = getFormatador(metadados).parse(valor);

			} catch (ParseException e) {
				throw new CampoArquivoInvalidoException("exception.conversao.data", e, valor, Date.class.getName(), metadados.formatoData());
			}
		}
		return data;
	}

	/**
	 * {@inheritDoc}
	 */
	public String converterParaString(Date valor, CampoArquivo metadados) {
		return valor == null ? StringUtils.EMPTY : getFormatador(metadados).format(valor);
	}

	/**
	 * Cria um formatador de tipos data baseado nos metadados do campo
	 * do arquivo.
	 * 
	 * @param metadados - Dados de formatação do tipo.
	 * @return DateFormat - formatador de data.
	 */
	protected DateFormat getFormatador(CampoArquivo metadados) {
		DateFormat formatador = null;
		String formato = metadados.formatoData();
		if (StringUtils.isBlank(formato)) {
			formatador = DateFormat.getInstance();
		} else {
			formatador = new SimpleDateFormat(metadados.formatoData());
		}
		formatador.setLenient(false);
		return formatador;
	}
	
}