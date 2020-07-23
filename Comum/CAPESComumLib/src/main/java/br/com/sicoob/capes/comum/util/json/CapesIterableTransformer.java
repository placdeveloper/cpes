/*
 * SICOOB
 * 
 * IterableTransformer.java(br.com.sicoob.capes.comum.util.json.IterableTransformer)
 */
package br.com.sicoob.capes.comum.util.json;

import flexjson.transformer.AbstractTransformer;

/**
 * Transformer utilizado para ignorar as coleções persistentes durante a serialização.
 * @author Juan.Damasceno
 *
 */
public class CapesIterableTransformer extends AbstractTransformer {
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public Boolean isInline() {
		return true;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	public void transform(Object object) {
		
	}
}