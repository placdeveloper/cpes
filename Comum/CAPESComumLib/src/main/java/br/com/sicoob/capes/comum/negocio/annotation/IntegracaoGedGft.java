/*
 * SICOOB
 * 
 * IntegracaoGedGft.java(br.com.sicoob.capes.comum.negocio.annotation.IntegracaoGedGft)
 */
package br.com.sicoob.capes.comum.negocio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anota��o utilizada para marcar os servi�os EJB que precisam de autoriza��o
 * 
 * @author Rodrigo.Chaves
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegracaoGedGft {

}
