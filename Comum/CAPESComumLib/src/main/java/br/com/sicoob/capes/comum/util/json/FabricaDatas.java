/*
 * SICOOB
 * 
 * FabricaDatas.java(br.com.sicoob.capes.comum.util.json.FabricaDatas)
 */
package br.com.sicoob.capes.comum.util.json;

import java.lang.reflect.Type;
import java.util.Date;

import flexjson.JSONException;
import flexjson.ObjectBinder;
import flexjson.factories.DateObjectFactory;

/**
 * @author Erico.Junior
 *
 */
public class FabricaDatas extends DateObjectFactory {

	
	/** 
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
	public Object instantiate(ObjectBinder context, Object value,
			Type targetType, Class targetClass) {
		
		Date data = null;
		
		if (value instanceof Integer) {
			
			try {
				data = (Date) ((Class) targetType).newInstance();
				data.setTime(((Integer) value).longValue());
			} catch (IllegalAccessException e) {
				throw new JSONException(String.format("%s:  Error encountered trying to instantiate %s",
						context.getCurrentPath(),((Class) targetType).getName()), e);
			} catch (InstantiationException e) {
				throw new JSONException(String.format(
						"%s:  Error encountered trying to instantiate %s.  Make sure there is a public no-arg constructor.",
								context.getCurrentPath(),((Class) targetType).getName()), e);
			}
	
		}else {
			data = (Date) super.instantiate(context, value, targetType, targetClass);
		}

		return data;
	}


}