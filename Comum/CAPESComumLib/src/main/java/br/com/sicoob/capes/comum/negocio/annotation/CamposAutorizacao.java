/*
 * SICOOB
 * 
 * CamposAutorizacao.java(br.com.sicoob.capes.comum.negocio.annotation.CamposAutorizacao)
 */
package br.com.sicoob.capes.comum.negocio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface CamposAutorizacao.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CamposAutorizacao {
	
	/**
	 * Obtém o id do camposAutorizaaco.
	 *
	 * @return String
	 */
	String id();
	
	/**
	 * Campos exibicao.
	 *
	 * @return CampoAutorizacao[]
	 */
	CampoAutorizacao[] camposExibicao();
}
