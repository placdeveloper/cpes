/*
 * SICOOB
 * 
 * IgnorarAutorizar.java(br.com.sicoob.capes.comum.negocio.annotation.IgnorarAutorizar)
 */
package br.com.sicoob.capes.comum.negocio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A interface IgnorarAutorizar.
 * 
 * @author erico.junior
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IgnorarAutorizar {

}
