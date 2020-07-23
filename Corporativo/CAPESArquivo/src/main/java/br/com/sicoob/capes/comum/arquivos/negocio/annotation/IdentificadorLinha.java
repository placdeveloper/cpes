/*
 * SICOOB
 * 
 * IdentificadorLinha.java(br.com.sicoob.capes.comum.arquivos.negocio.annotation.IdentificadorLinha)
 */
package br.com.sicoob.capes.comum.arquivos.negocio.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;

/**
 * A anotacao IdentificadorLinha.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IdentificadorLinha {

	/**
	 * O valor que identifca este tipo de linha.
	 * 
	 * @return string
	 */
	String value();
	
	/**
	 * A classe associada a este tipo de linha.
	 * 
	 * @return Uma classe &lt;? extends registroarquivo&gt;
	 * 
	 * @see RegistroArquivo
	 */
	Class<? extends RegistroArquivo> tipoRegistro();
}
