package br.com.sicoob.capes.comum.arquivos.util.conversao;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

import br.com.sicoob.capes.comum.arquivos.excecao.CampoArquivoInvalidoException;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.CampoArquivo;
import br.com.sicoob.capes.comum.arquivos.util.StringUtils;

/**
 * Conversor utilizado para converter tipo numéricos (<code>Long</code>,
 * <code>Integer</code>, <code>Double</code>, <code>BigDecimal</code>, etc) para
 * <code>String</code> e vice-versa.
 * 
 * Criado em 21/7/2014.
 * 
 * @author rodrigo.chaves
 */
public class ConversorNumero extends ConversorAbstrato<Number> {

	/**
	 * {@inheritDoc}
	 */
	public Number converterDeString(String valor, CampoArquivo metadados) throws CampoArquivoInvalidoException {
		verificarObrigatoriedade(valor, metadados);
		
		Number numero = null;
		if (StringUtils.isNotBlank(valor)) {

			// Faz checagem de caracteres inválidos antes de converter.
			if (!isNumero(valor)) {
				throw new CampoArquivoInvalidoException("exception.conversao.numero", valor, getTipoDado().getName());
			}

			try {
				DecimalFormat formatador = criarFormatadorNumerico(metadados);
				BigDecimal decimal = (BigDecimal) formatador.parse(valor);
				numero = converterParaTipoEspecifico(decimal);
			} catch (ParseException e) {
				throw new CampoArquivoInvalidoException("exception.conversao.numero", e, valor, getTipoDado().getName());
			}
		}
		return numero;
	}

	/**
	 * {@inheritDoc}
	 */
	public String converterParaString(Number valor, CampoArquivo metadados) {

		DecimalFormat formatador = criarFormatadorNumerico(metadados);
		return valor != null ? formatador.format(valor) : StringUtils.EMPTY;
	}

	/**
	 * Cria um formatador de tipos numéricos baseado nos metadados do campo do
	 * arquivo.
	 * 
	 * @param metadados
	 *            - Dados de formatação do tipo.
	 * @return DecimalFormat - formatador numérico.
	 */
	private DecimalFormat criarFormatadorNumerico(CampoArquivo metadados) {

		// Verificando se os caracteres separadores serão utilizados.
		boolean usaSeparadorDecimal = StringUtils.isNotBlank(metadados.caracterSeparadorDecimal());
		boolean usaSeparadorGrupo = StringUtils.isNotBlank(metadados.caracterAgrupador());

		// Criando os símbolos usados como separadores.
		DecimalFormatSymbols caracteresFormatacao = new DecimalFormatSymbols();
		if (usaSeparadorDecimal) {
			char separador = metadados.caracterSeparadorDecimal().charAt(0);
			caracteresFormatacao.setMonetaryDecimalSeparator(separador);
			caracteresFormatacao.setDecimalSeparator(separador);
		}
		if (usaSeparadorGrupo) {
			caracteresFormatacao.setGroupingSeparator(metadados.caracterAgrupador().charAt(0));
		}

		// Cria o formatador e transforma o número em String.
		DecimalFormat formatador = new DecimalFormat(StringUtils.EMPTY, caracteresFormatacao);
		formatador.setParseBigDecimal(true);
		formatador.setGroupingUsed(usaSeparadorGrupo);
		formatador.setGroupingSize(metadados.tamanhoAgrupador());
		int precisao = metadados.precisaoDecimal();
		if (usaSeparadorDecimal) {
			formatador.setDecimalSeparatorAlwaysShown(true);
			formatador.setMinimumIntegerDigits(1);
			formatador.setMinimumFractionDigits(precisao);
			formatador.setMaximumFractionDigits(precisao);
		} else {
			formatador.setDecimalSeparatorAlwaysShown(false);
			formatador.setMultiplier((int) Math.pow(10, precisao));
			formatador.setMaximumFractionDigits(0);
		}
		return formatador;
	}

	/**
	 * Método que checa se a <code>String</code> informada é um número ou não.
	 * 
	 * @param valor
	 *            - Texto a ser testado.
	 * @return boolean - <code>true</code> caso seja um número ou
	 *         <code>false</code> caso contrário.
	 */
	private boolean isNumero(String valor) {

		boolean isNumero = true;
		try {
			Double.parseDouble(valor);

		} catch (NumberFormatException e) {
			isNumero = false;
		}
		return isNumero;
	}

	/**
	 * Converte para um tipo específico de número, ou seja, <code>Long</code>,
	 * <code>Integer</code>, <code>BigDecimal</code>, <code>Double</code>, etc.
	 * 
	 * @param numero
	 *            - Número do tipo <code>BigDecimal</code>.
	 * @return Number - número convertido para o seu tipo específico.
	 */
	private Number converterParaTipoEspecifico(BigDecimal numero) {

		Class<?> tipo = getTipoDado();
		Number numeroTipoEspecifico = null;
		if (tipo.equals(Long.class)) {
			numeroTipoEspecifico = numero.longValue();
		} else if (tipo.equals(Integer.class) || tipo.equals(int.class)) {
			numeroTipoEspecifico = numero.intValue();
		} else if (tipo.equals(BigDecimal.class)) {
			numeroTipoEspecifico = numero;
		} else if (tipo.equals(Double.class)) {
			numeroTipoEspecifico = new Double(numero.doubleValue());
		}
		return numeroTipoEspecifico;
	}

}