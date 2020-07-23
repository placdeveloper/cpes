package br.com.sicoob.capes.frontoffice.negocio.util;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import br.com.bancoob.srtb.montagemconteudo.objeto.annotations.AtributoRetorno;
import br.com.sicoob.capes.comum.util.ReflexaoUtils;
import br.com.sicoob.infraestrutura.log.ISicoobLogger;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Classe utilitária para auxiliar para os conteúdos de retorno das transações.
 * 
 * @author bruno.carneiro
 */
public final class ConteudoRetornoUtil {

	private static final String VALOR_PADRAO_CAMPOS_NULOS = "";
	private static final String VALOR_TRUE = "1";
	private static final String VALOR_FALSE = "0";

	private static final String DELIMITADOR_COLUNA = "\\t";
	private static final String DELIMITADOR_LINHA = "\\n";

	private static final String FORMATO_DATA = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * Construtor privado.
	 */
	private ConteudoRetornoUtil() {

	}
	
	/**
	 * Gera o conteúdo de retorno da transação à partir de um iterato
	 * (RetornoTransacaoObjeto);
	 * 
	 * @param iterator
	 *            o iterator
	 * @return o conteúdo da mensagem
	 */
	public static String gerarConteudoIterator(Iterator<? extends Serializable> iterator) {
		return gerarConteudoIterator(null, iterator);
	}
	
	/**
	 * Gera o conteúdo de retorno da transação à partir de um iterato
	 * (RetornoTransacaoObjeto);
	 * 
	 * @param codigoTipoRegistro
	 *            o tipo de registro de retorno de acordo com o DET.
	 * @param iterator
	 *            o iterator
	 * @return o conteúdo da mensagem
	 */
	public static String gerarConteudoIterator(Integer codigoTipoRegistro, Iterator<? extends Serializable> iterator) {
		StringBuilder retorno = new StringBuilder();
		if (iterator != null) {
			while (iterator.hasNext()) {
				Serializable objeto = iterator.next();
				getLogger().debug("[CAPES] Gerando o conteudo para o resultado:", String.valueOf(objeto));
				retorno.append(criarConteudoRetorno(codigoTipoRegistro, objeto));
			}
		}
		return retorno.toString();
	}

	/**
	 * Cria o conteúdo da mensagem de retordo da transação à partir do objeto
	 * informado.
	 * 
	 * @param codigoTipoRegistro
	 *            o tipo de registro de retorno de acordo com o DET.
	 * @param objeto
	 *            o objeto contendo as informações de retorno.
	 * @return o conteúdo da mensagem
	 */
	public static String criarConteudoRetorno(Integer codigoTipoRegistro, Serializable objeto) {
		if (objeto == null) {
			return VALOR_PADRAO_CAMPOS_NULOS;
		}

		StringBuilder retorno = new StringBuilder();
		if (codigoTipoRegistro != null) {
			retorno.append(codigoTipoRegistro);
			retorno.append(DELIMITADOR_COLUNA);
		}

		retorno.append(criarConteudoRetorno(objeto));
		retorno.append(DELIMITADOR_LINHA);

		return retorno.toString();
	}
	
	/**
	 * Cria o conteúdo do retorno da transação à partir do objeto informado.
	 * 
	 * @param objeto
	 *            o objeto que terá o conteúdo.
	 * @return o conteúdo da mensagem.
	 */
	private static String criarConteudoRetorno(Serializable objeto) {
		StringBuilder retorno = new StringBuilder();

		Map<Integer, String> mapa = obterMapa(objeto);
		Iterator<Entry<Integer, String>> iterator = mapa.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry<Integer, String> chaveValor = iterator.next();
			retorno.append(chaveValor.getValue());
			retorno.append(DELIMITADOR_COLUNA);
		}

		return retorno.toString();
	}

	/**
	 * Obtém o mapa com os valores dos campos anotados como retorno da
	 * transação.
	 * 
	 * @param objeto
	 *            o objeto
	 * @return retorna o mapa com o valor dos campos anotados.
	 */
	private static Map<Integer, String> obterMapa(Serializable objeto) {
		Map<Integer, String> mapa = new TreeMap<Integer, String>();

		if (objeto != null) {
			Field[] campos = objeto.getClass().getDeclaredFields();

			for (Field campo : campos) {
				AtributoRetorno atributo = campo.getAnnotation(AtributoRetorno.class);
				if (atributo != null) {
					Integer posicao = atributo.posicao();
					mapa.put(posicao, obterValorCampo(objeto, campo));
				}
			}
		}
		return mapa;
	}

	/**
	 * Obtem o valor do campo informado.
	 * 
	 * @param objeto
	 *            o objeto para obter o valor do campo
	 * @param campo
	 *            o campo que terá o valor
	 * @return {@code String} com o valor do campo representado como string.
	 */
	private static String obterValorCampo(Serializable objeto, Field campo) {
		Object valor = ReflexaoUtils.getValorAtributo(objeto, campo);

		if (valor != null) {
			return converterValorParaString(valor);
		}

		return VALOR_PADRAO_CAMPOS_NULOS;
	}
	
	/**
	 * Converte os valores para uma {@code String} de acordo com o tipo
	 * 
	 * @param valor
	 *            o valor do campo
	 * @return {@code String} com a representação do valor
	 */
	private static String converterValorParaString(Object valor) {
		Class<?> classeValor = valor.getClass();

		if (Boolean.TYPE == classeValor || Boolean.class == classeValor) {
			return ((Boolean) valor) ? VALOR_TRUE : VALOR_FALSE;
		}

		if (classeValor.isAssignableFrom(Date.class)) {
			return new SimpleDateFormat(FORMATO_DATA).format((Date) valor);
		}

		if (classeValor.isAssignableFrom(Calendar.class)) {
			return new SimpleDateFormat(FORMATO_DATA).format(((Calendar) valor).getTime());
		}

		return valor.toString();
	}
	
	/**
	 * Obtém o logger
	 * 
	 * @return {@code ISicoobLogger}
	 */
	private static ISicoobLogger getLogger() {
		return SicoobLoggerPadrao.getInstance(ConteudoRetornoUtil.class);
	}

}