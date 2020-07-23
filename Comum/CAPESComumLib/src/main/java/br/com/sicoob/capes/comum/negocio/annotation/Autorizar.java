/*
 * SICOOB
 * 
 * Autorizar.java(br.com.sicoob.capes.comum.negocio.annotation.Autorizar)
 */
package br.com.sicoob.capes.comum.negocio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.sicoob.capes.comum.negocio.enums.TipoOperacaoEnum;

/**
 * Annotation utilizada pelo interceptor AutorizacaoCadastroInterceptor, somente os metodos
 * que utilizam essa anotacao serao validados.
 * 
 * @author Juan.Damasceno
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Autorizar {

	/**
	 * Recupera o tipo de operacao da autorizacao.
	 *
	 * @return TipoOperacaoEnum
	 */
	TipoOperacaoEnum atualizacao();

	/**
	 * Metodo validacao.
	 *
	 * @return String
	 */
	String metodoValidacao() default "";
}