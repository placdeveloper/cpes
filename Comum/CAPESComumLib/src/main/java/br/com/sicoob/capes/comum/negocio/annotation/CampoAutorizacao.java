/*
 * SICOOB
 * 
 * CampoAutorizacao.java(br.com.sicoob.capes.comum.negocio.annotation.CampoAutorizacao)
 */
package br.com.sicoob.capes.comum.negocio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anota��o usada para identificar e configurar atributos que s�o utilizados em workflows.
 *
 * @author Rodrigo.Chaves
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CampoAutorizacao {

	/**
	 * Recupera a propriedade do campo.
	 *
	 * @return String
	 */
	String propriedade();

	/**
	 * Recupera o label do campo.
	 *
	 * @return String
	 */
	String label();

	/**
	 * Recupera o formatador do campo.
	 *
	 * @return String
	 */
	String formatador() default "br.com.sicoob.capes.comum.util.formatacao.FormatadorPadrao";

	/**
	 * Recupera a Mascara do campo.
	 *
	 * @return String
	 */
	String mascara() default "";

	/**
	 * Recupera a ordem do campo para a tela de autoriza��o.
	 *
	 * @return int
	 */
	int ordenacao() default -1;

	/**
	 * Verifica se o atributo � do tipo de lista.
	 *
	 * @return {@code true}, se for lista
	 */
	boolean isLista() default false;

	/**
	 * Propriedades a serem usadas na compara��o dos objetos na lista.
	 *
	 * @return String[] as propriedades para compara��o
	 */
	String[] propriedadesComparacaoLista() default "";

}