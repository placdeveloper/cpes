/*
 * SICOOB
 * 
 * NaoVerificarGestorResponsavel.java(br.com.sicoob.capes.comum.negocio.annotation.NaoVerificarGestorResponsavel)
 */
package br.com.sicoob.capes.comum.negocio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação utilizada para indicar se a entidade NÃO deve verificar se 
 * existe Getor ou Responsavel.
 * 
 * @author Marcelo.Onofre
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface NaoVerificarGestorResponsavel {

}
