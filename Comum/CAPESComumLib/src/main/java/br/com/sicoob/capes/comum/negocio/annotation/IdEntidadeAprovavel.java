/*
 * SICOOB
 * 
 * IdEntidadeAprovavel.java(br.com.sicoob.capes.comum.negocio.annotation.IdEntidadeAprovavel)
 */
package br.com.sicoob.capes.comum.negocio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation utilizada para marcar o parametro que representa o identificador da entidade que será 
 * aprovada, o valor desse parametro será utilizado para consultar a entidade.
 * @author Juan.Damasceno
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value=ElementType.PARAMETER)
public @interface IdEntidadeAprovavel {

}
