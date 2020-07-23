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
 * Anotação utilizada para marcar os serviços EJB que precisam de autorização
 * 
 * @author Rodrigo.Chaves
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegracaoGedGft {

}
