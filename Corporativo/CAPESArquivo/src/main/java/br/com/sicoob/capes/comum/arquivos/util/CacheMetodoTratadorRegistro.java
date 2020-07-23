/*
 * SICOOB
 * 
 * CacheMetodoTratadorRegistro.java(br.com.sicoob.capes.comum.arquivos.util.CacheMetodoTratadorRegistro)
 */
package br.com.sicoob.capes.comum.arquivos.util;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

import br.com.sicoob.capes.comum.arquivos.RegistroArquivo;
import br.com.sicoob.capes.comum.arquivos.excecao.UsoIncorretoApiException;
import br.com.sicoob.capes.comum.arquivos.negocio.annotation.Tratador;
import br.com.sicoob.capes.comum.arquivos.negocio.manipulador.CAPESManipuladorArquivo;

/**
 * Classe responsável por fazer cache dos métodos tratadores de registros de
 * arquivo para evitar uso desnecessário de reflection, aumentando assim, o
 * desempenho.
 * 
 * Criado em 21/7/2014.
 * 
 * @author rodrigo.chaves
 */
public class CacheMetodoTratadorRegistro {

	/**
	 * Nome do método responsável por tratar linhas (registros) de um arquivo
	 * texto usado para importação.
	 */
	public static final String NOME_METODO_TRATADOR = "tratarRegistroArquivo";

	/** O atributo cache. */
	private Map<Class<? extends RegistroArquivo>, Method> cache;

	/** O atributo tipoManipulador. */
	private Class<? extends CAPESManipuladorArquivo> tipoManipulador;

	/**
	 * Construtor
	 * 
	 * @param tipoManipulador
	 *            A classe to manipulador de arquivo que utilizara o cache
	 */
	public CacheMetodoTratadorRegistro(Class<? extends CAPESManipuladorArquivo> tipoManipulador) {
		this.tipoManipulador = tipoManipulador;
		this.cache = Collections
		        .synchronizedMap(new WeakHashMap<Class<? extends RegistroArquivo>, Method>());
		carregarCache();
	}

	/**
	 * Obtém um {@link java.lang.reflect.Method} do cache ou extrai da classe
	 * usando reflection.
	 * 
	 * @param tipoArgumento
	 *            - O tipo do argumento.
	 * 
	 * @return Method - o método.
	 */
	public Method get(Class<? extends RegistroArquivo> tipoArgumento) {

		synchronized (cache) {
			Method metodo = cache.get(tipoArgumento);
			if (metodo == null) {
				metodo = localizarMetodo(tipoArgumento);
				adiconarCache(tipoArgumento, metodo);
			}
			return metodo;
		}
	}

	/**
	 * O método Carregar cache.
	 */
	@SuppressWarnings("unchecked")
	private void carregarCache() {
		synchronized (cache) {
			Method[] methods = tipoManipulador.getMethods();
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (verificarMetodo(method, null)) {
					Class<? extends RegistroArquivo> chave = (Class<? extends RegistroArquivo>) method
					        .getParameterTypes()[0];
					adiconarCache(chave, method);
				}
			}
		}
	}

	/**
	 * Localizar metodo.
	 *
	 * @param tipoArgumento o valor de tipo argumento
	 * @return Method
	 */
	private Method localizarMetodo(Class<? extends RegistroArquivo> tipoArgumento) {

		Method method = null;
		Method[] methods = tipoManipulador.getMethods();
		for (int i = 0; (i < methods.length) && (method == null); i++) {
			Method temp = methods[i];
			if (verificarMetodo(temp, tipoArgumento)) {
				method = temp;
			}
		}
		return method;
	}

	/**
	 * Verificar metodo.
	 *
	 * @param temp o valor de temp
	 * @param tipoArgumento o valor de tipo argumento
	 * @return {@code true}, em caso de sucesso
	 */
	private boolean verificarMetodo(Method temp, Class<? extends RegistroArquivo> tipoArgumento) {

		return temp.isAnnotationPresent(Tratador.class)
		        && verificarTipoRetorno(temp.getReturnType())
		        && verificarParametro(temp.getParameterTypes(), tipoArgumento);
	}

	/**
	 * Verificar tipo retorno.
	 *
	 * @param tipoRetorno o valor de tipo retorno
	 * @return {@code true}, em caso de sucesso
	 */
	private boolean verificarTipoRetorno(Class<?> tipoRetorno) {
		return tipoRetorno.equals(Boolean.class) || tipoRetorno.equals(Boolean.TYPE);
	}

	/**
	 * Verificar parametro.
	 *
	 * @param parameterTypes o valor de parameter types
	 * @param tipoParametro o valor de tipo parametro
	 * @return {@code true}, em caso de sucesso
	 */
	private boolean verificarParametro(Class<?>[] parameterTypes,
	        Class<? extends RegistroArquivo> tipoParametro) {

		boolean ok = false;
		if ((parameterTypes != null) && (parameterTypes.length == 1)) {
			if (tipoParametro != null) {
				ok = tipoParametro.isAssignableFrom(parameterTypes[0]);
			} else {
				ok = RegistroArquivo.class.isAssignableFrom(parameterTypes[0]);
			}
		}
		return ok;
	}

	/**
	 * O método Adiconar cache.
	 *
	 * @param chave o valor de chave
	 * @param valor o valor de valor
	 */
	private void adiconarCache(Class<? extends RegistroArquivo> chave, Method valor) {
		if (valor != null) {
			if (this.cache.put(chave, valor) != null) {
				throw new UsoIncorretoApiException("exception.cache.tratador.duplicado",
				        chave.getName());
			}
		}
	}

}