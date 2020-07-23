/*
 * SICOOB
 * 
 * DataUtils.java(br.com.sicoob.capes.comum.util.DataUtils)
 */
package br.com.sicoob.capes.comum.util;

import java.util.Calendar;
import java.util.Date;

import br.com.bancoob.persistencia.types.DateTimeDB;

/**
 * The Class DataUtils.
 */
public final class DataUtils {

	/**
	 * Cria uma nova instância de data utils.
	 */
	private DataUtils() {
	}

	/**
	 * Percorre uma {@code quantidade} específica de unidades em um {@code campo} de uma
	 * {@code data}
	 * 
	 * @param data
	 *            a data a partir da qual se deseja percorrer. Se não vier preenchida, utiliza a
	 *            data atual
	 * @param campo
	 *            o campo da data que se deseja percorrer. Utilize as constantes de {@link Calendar}
	 * @param quantidade
	 *            a quantidade que se deve de unidades do {@code campo} que se deve percorrer
	 * 
	 * @see Calendar
	 * 
	 * @return a nova data
	 */
	public static Date percorrer(Date data, Integer campo, Integer quantidade) {

		Calendar calendar = Calendar.getInstance();
		if (data != null) {
			calendar.setTime(data);
		}
		calendar.roll(campo, quantidade);
		return calendar.getTime();
	}
	
	/**
	 * Instanciar date time db.
	 * 
	 * @param data
	 *            the data
	 * @return date time db
	 */
	public static DateTimeDB instanciarDateTimeDB(Date data) {
		
		DateTimeDB retorno = null;
		if (data != null) {
			retorno = instanciarDateTimeDB(data.getTime());
		}
		return retorno;
	}
	
	/**
	 * Instanciar date time db.
	 * 
	 * @param millis
	 *            the millis
	 * @return date time db
	 */
	public static DateTimeDB instanciarDateTimeDB(long millis) {
		return new DateTimeDB(millis);
	}
	
	/**
	 * Converte um {@code DatetimeDB} para {@code Date}
	 * 
	 * @param dateTimeDB
	 *            o datetime para ser convertido
	 * @return {@code Date} a data convertida
	 */
	public static Date converterDateTimeDBParaDate(DateTimeDB dateTimeDB) {
		return new Date(dateTimeDB.getTime());
	}
}
