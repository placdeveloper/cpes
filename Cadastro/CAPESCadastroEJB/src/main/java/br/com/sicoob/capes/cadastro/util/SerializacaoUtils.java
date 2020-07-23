// 20/02/2013 - 19:13:51
package br.com.sicoob.capes.cadastro.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;
import br.com.sicoob.capes.comum.util.json.CapesIterableTransformer;
import br.com.sicoob.capes.comum.util.json.FabricaDatas;
import br.com.sicoob.capes.negocio.entidades.interfaces.Aprovavel;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.transformer.IterableTransformer;

/**
 * TODO javadoc
 * 
 * 
 * @author Rodrigo.Chaves
 */
public class SerializacaoUtils {

	/**
	 * Construtor privado para evitar instancias de classe utilitaria
	 */
	private SerializacaoUtils() {
	}

	/**
	 * Serilizar json.
	 *
	 * @param objeto o valor de objeto
	 * @param atributosIgnorados o valor de atributos ignorados
	 * @return String
	 */
	public static String serilizarJson(Object objeto, String... atributosIgnorados) {
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude(atributosIgnorados);
		return serializer.deepSerialize(objeto);
	}

	/**
	 * TODO javadoc
	 * 
	 * @param aprovavel
	 * @param tipoOperacao
	 */
	public static String serilizarAprovavel(Aprovavel aprovavel, TipoOperacaoEnum tipoOperacao, String... atributosIgnorados) {

		Set<String> ignorar = new HashSet<String>(Arrays.asList(atributosIgnorados));
		ignorar.add("*.id");
		ignorar.add("documentosComprobatorios");

		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude(ignorar.toArray(new String[ignorar.size()]));
		if (tipoOperacao == TipoOperacaoEnum.E) {
			serializer.transform(new CapesIterableTransformer(), Collection.class);
		} else {
			serializer.transform(new IterableTransformer(), Collection.class);
		}
		return serializer.deepSerialize(aprovavel);
	}

	/**
	 * Deserializar json.
	 *
	 * @param <T> o tipo generico
	 * @param tipo o valor de tipo
	 * @param json o valor de json
	 * @return T
	 */
	public static <T> T deserializarJson(Class<T> tipo, String json) {

		JSONDeserializer<T> deserializer = new JSONDeserializer<T>();
		deserializer.use(Date.class, new FabricaDatas());
		return (T) deserializer.deserialize(json);
	}
}
