/*
 * SICOOB
 * 
 * MapeamentoLinhas.java(br.com.sicoob.capes.comum.arquivos.negocio.annotation.MapeamentoLinhas)
 */
package br.com.sicoob.capes.comum.arquivos.negocio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;

/**
 * A anotacao MapeamentoLinhas.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MapeamentoLinhas {

	/**
	 * Posição inicial (começa em 0) do dado na linha.
	 */
	int inicio() default -1;

	/**
	 * Tamanho do dado na linha.
	 */
	int tamanho() default -1;

	/**
	 * Os identificadores dos tipos de linha.
	 * 
	 * @return os identificadores de linha
	 * 
	 * @see IdentificadorLinha
	 */
	IdentificadorLinha[] identificadores() default {};

	/**
	 * O tipo padrao para registros que não possuem identificadores.
	 * 
	 * @return the class<? extends RegistroArquivo>
	 * 
	 * @see RegistroArquivo
	 */
	Class<? extends RegistroArquivo> tipoPadrao() default RegistroArquivo.class;
}
