/*
 * SICOOB
 * 
 * ReflexaoUtils.java(br.com.sicoob.capes.comum.util.ReflexaoUtils)
 */
package br.com.sicoob.capes.comum.util;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.infraestrutura.log.SicoobLoggerPadrao;

/**
 * Classe utilitária com método para auxiliar o uso de reflection.
 *
 * @author rodrigo.chaves
 */
public final class ReflexaoUtils {

	/**
	 * Esta classe não pode ser instanciada. Useseus métodos <code>static</code>
	 */
	private ReflexaoUtils() {

	}

	/**
	 * Obtém o valor da propriedade com o nome informado na instância do objeto informado.
	 * <p>
	 * <strong>Atenção: &nbsp;</strong>Caso a propriedade não possua método <code>get</code> ou algum erro ocorra ao obter a propriedade, uma {@link BancoobRuntimeException} será lançada.
	 * </p>
	 *
	 * @param objeto
	 *            Instância do objeto.
	 * @param nomeAtributo
	 *            Nome da propriedade a ser obtida.
	 * @return Object - O valor da propriedade.
	 */
	public static Object getValorAtributo(Object objeto, String nomeAtributo) {

		try {
			return PropertyUtils.getNestedProperty(objeto, nomeAtributo);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new BancoobRuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new BancoobRuntimeException(e);
		}
	}

	/**
	 * Obtém o valor da propriedade com o nome informado na instância do objeto informado.
	 * <p>
	 * <strong>Atenção: &nbsp;</strong>Caso a propriedade não possua método <code>get</code> ou algum erro ocorra ao obter a propriedade, uma {@link BancoobRuntimeException} será lançada.
	 * </p>
	 *
	 * @param objeto
	 *            Instância do objeto.
	 * @param atributo
	 *            A propriedade da qual se deseja o valor.
	 * @return Object - O valor da propriedade.
	 */
	public static Object getValorAtributo(Object objeto, Field atributo) {

		return getValorAtributo(objeto, atributo.getName());
	}

	/**
	 * Obtém o valor do atributo recebido na instância do objeto informado.
	 * <p>
	 * <strong>Atenção: &nbsp;</strong>Caso algum erro ocorra ao obter a propriedade, uma {@link BancoobRuntimeException} será lançada.
	 * </p>
	 * 
	 * @param objeto
	 *            Instância do objeto.
	 * @param atributo
	 *            A propriedade da qual se deseja o valor.
	 * @param usarAcessores
	 *            indica se devem ser usados os metodos acessores ou acesso direto ao atributo
	 * @return Object - O valor da propriedade.
	 */
	public static Object getValorAtributo(Object objeto, Field atributo, boolean usarAcessores) {
		try {
			Object valor = null;
			if (usarAcessores) {
				valor = getValorAtributo(objeto, atributo);
			} else if (!atributo.isAccessible()) {
				atributo.setAccessible(true);
				valor = atributo.get(objeto);
				atributo.setAccessible(false);
			} else {
				valor = atributo.get(objeto);
			}
			return valor;
		} catch (IllegalArgumentException e) {
			throw new BancoobRuntimeException("Falha ao recuperar o valor do atributo: " + atributo.getName(), e);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException("Falha ao recuperar o valor do atributo: " + atributo.getName(), e);
		}
	}

	/**
	 * Atribui o {@code valor} à propriedade de nome {@code nomePropriedade} do {@code objeto}.
	 *
	 * @param objeto
	 *            Instância do objeto
	 * @param nomePropriedade
	 *            nome da propriedade a ser setada
	 * @param valor
	 *            valor a ser atribuído à propriedade
	 */
	public static void setPropriedade(Object objeto, String nomePropriedade, Object valor) {

		try {
			if (valor != null) {
				Class<?> classe = objeto.getClass();
				Method metodo = getMetodoSet(classe, nomePropriedade, valor.getClass());
				metodo.invoke(objeto, valor);
			}
		} catch (IllegalArgumentException e) {
			throw new BancoobRuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new BancoobRuntimeException(e);
		}
	}

	/**
	 * Atribui o {@code valor} ao {@code atributo} do {@code objeto}.
	 *
	 * @param objeto
	 *            Instância do objeto
	 * @param atributo
	 *            atributo a ser preenchido
	 * @param valor
	 *            valor a ser atribuído ao atributo
	 * @param usarAcessores
	 *            indica se devem ser usados os metodos acessores ou acesso direto ao atributo
	 */
	public static void setValorAtributo(Object objeto, Field atributo, Object valor, boolean usarAcessores) {

		try {
			if (usarAcessores) {
				setPropriedade(objeto, atributo.getName(), valor);
			} else if (!atributo.isAccessible()) {
				atributo.setAccessible(true);
				atributo.set(objeto, valor);
				atributo.setAccessible(false);
			} else {
				atributo.set(objeto, valor);
			}
		} catch (IllegalArgumentException e) {
			throw new BancoobRuntimeException("Falha ao recuperar o valor do atributo: " + atributo.getName(), e);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException("Falha ao recuperar o valor do atributo: " + atributo.getName(), e);
		}
	}

	/**
	 * Recupera o método acessor do atributo de nome {@code nomeAtributo} da {@code classe}.<br>
	 * <br>
	 *
	 * Devido às convenções Java, os métodos acessores procurados são os iniciados por "get" e "is" (usada para atributos do tipo {@code boolean} ).<br>
	 * <br>
	 *
	 * Caso o método não exista, uma {@link BancoobRuntimeException} será lançada.
	 *
	 * @param classe
	 *            Objeto {@link Class} representando a clase de onde deve-se recuperar o método.
	 * @param nomeAtributo
	 *            Nome do atributo ao qual se deseja acessar.
	 * @return Method - Objeto {@link Method} representando o método acessor para o atributo informado.
	 */
	public static Method getMetodoGet(Class<?> classe, String nomeAtributo) {

		Method metodo = null;
		String nomeMetodo = "get" + StringUtils.capitalize(nomeAtributo);
		String nomeMetodoAlternativo = nomeMetodo.replaceFirst("get", "is");
		try {
			metodo = classe.getMethod(nomeMetodo);
		} catch (SecurityException e) {
			throw new BancoobRuntimeException(e);
		} catch (NoSuchMethodException e) {
			try {
				metodo = classe.getMethod(nomeMetodoAlternativo);
			} catch (SecurityException e1) {
				throw new BancoobRuntimeException(e1);// NOPMD
			} catch (NoSuchMethodException e1) {
				throw new BancoobRuntimeException(e1); // NOPMD
			}
		}
		return metodo;
	}

	/**
	 * Recupera o método modificador do atributo de nome {@code nomeAtributo} da {@code classe} que possui um parâmetro do tipo {@code tipoParametro}.<br>
	 * <br>
	 *
	 * Devido às convenções Java, os métodos acessores procurados são os iniciados por "set".<br>
	 * <br>
	 *
	 * Caso o método não exista, uma {@link BancoobRuntimeException} será lançada.
	 *
	 * @param classe
	 *            Obejto {@link Class} representando a classe de onde deve-se recuperar o método.
	 * @param nomeAtributo
	 *            Nome do atributo ao qual se deseja modificar.
	 * @param tipoParametro
	 *            Objeto {@link Class} representando o tipo de parâmetro que o método recebe.
	 * @return Method - Objeto {@link Method} representando o método modificador para o atributo informado.
	 */
	@SuppressWarnings("rawtypes")
	public static Method getMetodoSet(Class<?> classe, String nomeAtributo, Class<?> tipoParametro) {

		Set<Class> tipos = new LinkedHashSet<Class>();
		Method metodo = null;
		String nomeMetodo = "set" + StringUtils.capitalize(nomeAtributo);

		Class<?> tipoParam = tipoParametro;
		while (tipoParam != null) {
			tipos.add(tipoParam);
			tipos.addAll(Arrays.asList(tipoParam.getInterfaces()));
			tipoParam = tipoParam.getSuperclass();
		}
		for (Class<?> tipo : tipos) {
			try {
				metodo = classe.getMethod(nomeMetodo, tipo);
				break;
			} catch (SecurityException e) {
				continue;
			} catch (NoSuchMethodException e) {
				continue;
			}
		}
		if (metodo == null) {
			throw new BancoobRuntimeException("Método não encontrado: " + classe.getName() + "." + nomeMetodo + "()");
		}
		return metodo;
	}

	/***
	 * Cria uma nova instância da classe representada pelo objeto {@code classe} .<br>
	 * <br>
	 *
	 * Caso não seja possível instânciar o objeto, uma {@link BancoobRuntimeException} será lançada.
	 *
	 * @param Class<T> classe para ser instanciada.
	 *            Classe que será retornada (a mesma passada como atributo)
	 * @param classe
	 *            Objeto {@link Class} representando o tipo do objeto que será instanciado.
	 * @return T - Nova instância de {@code <T>}
	 */
	public static <T> T instanciar(Class<T> classe) {

		try {
			return classe.newInstance();
		} catch (InstantiationException e) {
			throw new BancoobRuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		}
	}

	/**
	 * Copia o valor da propriedade com o nome informado de um objeto (origem) para outro (destino).
	 *
	 * @param destino
	 *            Objeto destino do dado.
	 * @param origem
	 *            Objeto origem do dado.
	 * @param nomePropriedade
	 *            Nome da propriedade a ser copiada.
	 */
	public static void copiarPropriedade(Object destino, Object origem, String nomePropriedade) {

		Object valorOrigem = getValorAtributo(origem, nomePropriedade);
		setPropriedade(destino, nomePropriedade, valorOrigem);
	}

	/**
	 * Verifica se a propriedade com o nome informado possue o mesmo valor (ou referência) nos dois objetos.
	 *
	 * @param o1
	 *            Objeto 1
	 * @param o2
	 *            Objeto 2
	 * @param propriedade
	 *            Nome da propriedade
	 * @return boolean - <code>true</code> caso as propriedades sejam iguais nos dois objetos ou <code>false</code> caso contrário.
	 */
	public static boolean isPropriedadeIgual(Object o1, Object o2, String propriedade) {

		EqualsBuilder builder = new EqualsBuilder();
		builder.append(getValorAtributo(o1, propriedade), getValorAtributo(o2, propriedade));
		return builder.isEquals();
	}

	/**
	 * Obtém a propriedade ({@link java.lang.reflect.Field}) de nome {@code nomePropriedade} de uma determinada classe. O método busca a propriedade (independente da visibilidade) inclusive das superclasses.
	 * <p>
	 * <strong>Atenção:&nbsp;</strong>Caso a propriedade não seja encontrada, uma {@link BancoobRuntimeException} será lançada.
	 * </p>
	 *
	 * @param classe
	 *            A classe da qual se deseja recuperar o {@code Field}
	 * @param nomePropriedade
	 *            Nome da propriedade que se deseja recuperar
	 * @return Field - Objeto {@link java.lang.reflect.Field} representando a propriedade ou {@code null} caso a propriedade não seja encontrada.
	 */
	public static Field getField(final Class<?> classe, String nomePropriedade) {

		Field propriedade = null;
		Class<?> clazz = classe;
		while ((clazz != null) && (propriedade == null)) {
			try {
				propriedade = clazz.getDeclaredField(nomePropriedade);
			} catch (SecurityException e) {
				continue;
			} catch (NoSuchFieldException e) {
				continue;
			} finally {
				clazz = clazz.getSuperclass();
			}
		}
		if (propriedade == null) {
			throw new BancoobRuntimeException("Propriedade não encontrada: " + classe.getName() + "." + nomePropriedade);
		}
		return propriedade;
	}

	/**
	 * Obtém todas as propriedades ({@link java.lang.reflect.Field}) de uma determinada classe, desconsiderando propriedade <code>final</code>, <code>status</code> e aquelas que não possuem métodos <code>get</code> nem <code>set</code>. O método busca todas as propriedades
	 * (independente da visibilidade) inclusive as propriedades das superclasses.
	 * 
	 * @param classe
	 *            Classe usada para extrair as propriedades (campos).
	 * @param exigeAcessores
	 *            define se eh necessario possuir metodos acessores para retornar o {@code field}
	 * @return {@code List<java.lang.reflect.Field>} - Lista de propriedades (campos) da classe.
	 */
	public static List<Field> getFields(final Class<?> classe, boolean exigeAcessores) {

		List<Field> propriedadesOrdenadas = new ArrayList<Field>();
		List<Field> outrasPropriedades = new ArrayList<Field>();

		// Filtrando e ordenando campos
		Class<?> clazz = classe;
		while (clazz != null) {
			for (Field field : clazz.getDeclaredFields()) {
				if (!(isStatic(field) || isFinal(field))) {
					if (!exigeAcessores || (exigeAcessores && possuiMetodoGet(clazz, field.getName()) && possuiMetodoSet(clazz, field.getName(), field.getType()))) {
						if (isTipoBasico(field)) {
							propriedadesOrdenadas.add(field);
						} else {
							outrasPropriedades.add(field);
						}
					}
				}
			}
			clazz = clazz.getSuperclass();
		}
		propriedadesOrdenadas.addAll(outrasPropriedades);
		return propriedadesOrdenadas;
	}

	/**
	 * Obtém todas as propriedades ({@link java.lang.reflect.Field}) de uma determinada classe, desconsiderando propriedade <code>final</code>, <code>status</code> e aquelas que não possuem métodos <code>get</code> nem <code>set</code>. O método busca todas as propriedades
	 * (independente da visibilidade) inclusive as propriedades das superclasses.
	 *
	 * @param classe
	 *            Classe usada para extrair as propriedades (campos).
	 * @return {@code List<java.lang.reflect.Field>} - Lista de propriedades (campos) da classe.
	 */
	public static List<Field> getFields(final Class<?> classe) {

		return getFields(classe, true);
	}

	/**
	 * Verifica se uma propriedade de determinada classe possui um método <code>set</code> relacionado.
	 *
	 * @param classe
	 *            Classe para verificar a presença do método set.
	 * @param propriedade
	 *            Nome da propriedade.
	 * @param tipoParametro
	 *            Tipo da propriedade
	 * @return boolean - <code>true</code> caso possua método <code>set</code> ou <code>false</code> caso contrário.
	 */
	public static boolean possuiMetodoSet(Class<?> classe, String propriedade, Class<?> tipoParametro) {
		boolean possui = true;
		try {
			getMetodoSet(classe, propriedade, tipoParametro);
		} catch (BancoobRuntimeException e) {
			possui = false;
		}
		return possui;
	}

	/**
	 * Verifica se uma propriedade de determinada classe possui um método <code>get</code> relacionado.
	 *
	 * @param classe
	 *            Classe para verificar a presença do método get.
	 * @param propriedade
	 *            Nome da propriedade.
	 * @return boolean - <code>true</code> caso possua método <code>get</code> ou <code>false</code> caso contrário.
	 */
	public static boolean possuiMetodoGet(Class<?> classe, String propriedade) {
		boolean possui = true;
		try {
			getMetodoGet(classe, propriedade);
		} catch (BancoobRuntimeException e) {
			possui = false;
		}
		return possui;
	}

	/**
	 * Verifica se o parâmetro {@link java.lang.reflect.Field} informado é de um tipo java básico (classes do pacote {@code java.lang}, {@link Date}, {@link BigDecimal}, etc.), de um tipo primitivo ou é um array.
	 *
	 * @param field
	 *            Propriedade
	 * @return boolean - Retorna {@code true} caso o {@code field} seja de um tipo java comum, primitivo ou um {@code array} e {@code false} caso seja de qualquer outro tipo.
	 */
	public static boolean isTipoBasico(Field field) {

		final String pacoteJavaLang = "java.lang";
		Class<?> tipo = field.getType();
		boolean tipoJavaBasico = false;
		boolean tipoPrimitivo = tipo.isPrimitive();
		if (!tipoPrimitivo && !tipo.isArray()) {
			tipoJavaBasico = getPacote(tipo).getName().equals(pacoteJavaLang) || tipo.equals(Date.class) || tipo.equals(BigDecimal.class);
		}
		return tipoPrimitivo || tipoJavaBasico;
	}

	/**
	 * Obtém o pacote da classe informada.
	 *
	 * @param classe
	 *            Classe para obter o pacote de origem.
	 * @return Package - o objeto que representa o pacote da classe.
	 */
	public static Package getPacote(Class<?> classe) {

		return classe.getPackage();
	}

	/**
	 * Verifica se um campo (propriedade) possui o modificador <code>final</code>.
	 *
	 * @param field
	 *            Propriedade
	 * @return boolean - <code>true</code> caso possua o modificador <code>final</code> ou <code>false</code> caso contrário.
	 */
	public static boolean isFinal(Field field) {

		return Modifier.isFinal(field.getModifiers());
	}

	/**
	 * Verifica se um campo (propriedade) possui o modificador <code>static</code>.
	 *
	 * @param field
	 *            Propriedade
	 * @return boolean - <code>true</code> caso possua o modificador <code>static</code> ou <code>false</code> caso contrário.
	 */
	public static boolean isStatic(Field field) {

		return Modifier.isStatic(field.getModifiers());
	}

	/**
	 * Recupera a {@link Annotation} do tipo {@code classeAnnotation} no objeto passado como parâmetro.
	 * <p>
	 * <strong>Atenção:&nbsp;</strong>Caso a {@link Annotation} não tenha sido declarada, uma {@link BancoobRuntimeException} será lançada.
	 * </p>
	 *
	 * @param <T>
	 *            Classe da hierárquia de {@code Annotation}
	 * @param classeAnnotation
	 *            Objeto {@link Class} representando a {@code Annotation} a ser recuperada.
	 * @param objeto
	 *            Objeto de onde deve-se recuperar a annotation.
	 * @return T - A annotation recuperada.
	 */
	public static <T extends Annotation> T getAnnotation(Class<T> classeAnnotation, Object objeto) {

		Class<?> classe = objeto.getClass();
		T annotation = classe.getAnnotation(classeAnnotation);
		if (annotation == null) {
			throw new BancoobRuntimeException("Annotation não declarada: " + classeAnnotation + "(" + classe + ")");
		}
		return annotation;
	}

	/**
	 * Usa reflexão para gerar um <code>hashCode</code> válido usando reflexão nas propriedades informadas (ou em todas caso nenhuma seja informada).
	 *
	 * @param obj
	 *            O objeto de onde será criado o hashCode.
	 * @param propriedades
	 *            Nomes das propriedades usadas na geração do hashCode.
	 * @return int - o hash code.
	 */
	public static int hashCode(Object obj, String... propriedades) {

		int hashCode = 0;
		if (propriedades == null || propriedades.length == 0) {
			hashCode = HashCodeBuilder.reflectionHashCode(obj);
		} else {
			HashCodeBuilder hashCodeBuilder = new HashCodeBuilder();
			for (String propriedade : propriedades) {
				hashCodeBuilder.append(getValorAtributo(obj, propriedade));
			}
			hashCode = hashCodeBuilder.toHashCode();
		}
		return hashCode;
	}

	/**
	 * Determina se os dois objetos são iguais por meio de reflexão.
	 *
	 * @param o1
	 *            Objeto de origem a ser testado (this)
	 * @param o2
	 *            Outro objeto a ser testado.
	 * @param propriedades
	 *            Nomes das propriedades usadas na comparação.
	 * @return boolean - <code>true</code> caso os objeto forem iguais ou <code>false</code> caso contrário.
	 */
	public static boolean equals(Object o1, Object o2, String... propriedades) {

		boolean test = false;
		if (propriedades == null || propriedades.length == 0) {
			test = EqualsBuilder.reflectionEquals(o1, o2);
		} else {
			test = o2 == null;
			test = !test && o1.getClass().isInstance(o2);
			if (test) {
				EqualsBuilder equalsBuilder = new EqualsBuilder();
				for (String propriedade : propriedades) {
					equalsBuilder.append(getValorAtributo(o1, propriedade), getValorAtributo(o2, propriedade));
				}
				test = equalsBuilder.isEquals();
			}
		}
		return test;
	}

	/**
	 * Faz a comparação das listas utilizando o método {@code equals} dos objetos.
	 * 
	 * <p>
	 * Os objetos {@code nulos} são tratados sem exceções. Duas referências nulas são consideradas iguais.
	 * </p>
	 * 
	 * <pre>
	 * ReflexaoUtils.isMesmaColecao(null, null)                       = true
	 * ReflexaoUtils.isMesmaColecao(null, new ArrayList())            = false
	 * ReflexaoUtils.isMesmaColecao(new ArrayList(), null)            = false
	 * ReflexaoUtils.isMesmaColecao(new ArrayList(), new ArrayList()) = true
	 * </pre>
	 *
	 * @param <O>
	 *            o tipo generico
	 * @param colecao1
	 *            A primeira coleção a ser usada na comparação, podendo ser nula.
	 * @param colecao2
	 *            A segunda coleção a ser usada na comparação, podendo ser nula.
	 * @return {@code Boolean} Se as coleções contêm os mesmos elementos.
	 */
	public static <O> boolean isMesmaColecao(Collection<O> colecao1, Collection<O> colecao2) {
		if (colecao1 == colecao2) {
			return true;
		}
		if (colecao1 == null || colecao2 == null) {
			return false;
		}
		if (colecao1.size() != colecao2.size()) {
			return false;
		}

		Iterator<O> it = colecao1.iterator();
		while (it.hasNext()) {
			O objeto = it.next();
			if (!colecao2.contains(objeto)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Extrai o valor de um determinado bean.
	 *
	 * @param bean
	 *            - O objeto que contém o valor a ser recuperado
	 * @param nomeAtributo
	 *            - O nome do atributo que deseja recuperar o valor
	 * @return Object - O valor para a propriedade fornecida
	 */
	public static Object extrairDados(Object bean, String nomeAtributo) {

		Object dados = null;
		if (bean != null) {
			if (StringUtils.isNotEmpty(nomeAtributo) && nomeAtributo.indexOf('.') > 0) {
				Object beanInterno = invocarMetodoGet(bean, nomeAtributo.substring(0, nomeAtributo.indexOf('.')));
				dados = extrairDados(beanInterno, nomeAtributo.substring(nomeAtributo.indexOf('.') + 1));
			} else if (StringUtils.isNotEmpty(nomeAtributo)) {
				dados = invocarMetodoGet(bean, nomeAtributo);
			}
		}
		return dados;
	}

	/**
	 * Método que retorna uma nova instância da classe informada (ou null se um erro ocorrer). A classe deve ter um contrutor sem parâmetros.
	 *
	 * @param <T>
	 *            Classe que define o tipo de retorno
	 * @param classeObjeto
	 *            Definição da classe.
	 * @return <T> - Instância da classe.
	 */
	public static <T> T construirObjetoPorClasse(Class<T> classeObjeto) {

		T novaInstancia = null;
		try {
			novaInstancia = classeObjeto.newInstance();
		} catch (InstantiationException e) {
			throw new BancoobRuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		}
		return novaInstancia;
	}

	/**
	 * Construir objeto por classe.
	 * 
	 * @param <C>
	 *            the generic type
	 * @param <T>
	 *            the generic type
	 * @param superClasse
	 *            the super classe
	 * @param classeObjeto
	 *            the classe objeto
	 * @return t
	 */
	@SuppressWarnings("unchecked")
	public static <C, T extends C> T construirObjetoPorClasse(Class<C> superClasse, String classeObjeto) {

		Class<T> classe = null;
		try {
			classe = (Class<T>) Class.forName(classeObjeto);
		} catch (ClassNotFoundException e) {
			throw new BancoobRuntimeException(e);
		}
		return construirObjetoPorClasse(classe);
	}

	/**
	 * Construir singleton por classe.
	 *
	 * @param classeObjeto
	 *            the classe objeto
	 * @param metodoSingleton
	 *            o valor de metodo singleton
	 * @return A classe construida.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T construirSingletonPorClasse(String classeObjeto, String metodoSingleton) {

		Class<T> classe = null;
		try {
			classe = (Class<T>) Class.forName(classeObjeto);
			Method metodo = classe.getMethod(metodoSingleton);
			return (T) invocarMetodo(null, metodo);
		} catch (ClassNotFoundException e) {
			throw new BancoobRuntimeException(e);
		} catch (SecurityException e) {
			throw new BancoobRuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new BancoobRuntimeException(e);
		} catch (BancoobException e) {
			throw new BancoobRuntimeException(e);
		}
	}

	/**
	 * Método utilizado para verificar se um objeto tem método com a assinatura informada.
	 *
	 * @param objeto
	 *            objeto que possui ou não o método.
	 * @param nomeDoMetodo
	 *            nome do método desejado.
	 * @param tipoParametros
	 *            tipo de dados dos parâmetros.
	 * @return true se o objeto tem o método informado, false em caso contrário
	 */
	public static boolean possuiMetodo(Object objeto, String nomeDoMetodo, Class<?>[] tipoParametros) {

		boolean possuiMetodo;
		try {
			objeto.getClass().getMethod(nomeDoMetodo, tipoParametros);
			possuiMetodo = true;
			// Se levantou exceção é porque o método não existe
		} catch (NoSuchMethodException e) {
			possuiMetodo = false;
		}
		return possuiMetodo;
	}

	/**
	 * Método utilizado para verificar se um objeto tem método getCampo com a assinatura informada.
	 *
	 * @param objeto
	 *            objeto que possui ou não o método.
	 * @param nomeCampo
	 *            nome do campo, usado para definir o get chamado.
	 * @return true se o objeto tem o método informado, false em caso contrário
	 */
	public static boolean possuiMetodoGet(Object objeto, String nomeCampo) {

		return possuiMetodoGet(objeto.getClass(), nomeCampo);
	}

	/**
	 * Método utilizado para verificar se um objeto tem método getCampo(int index).
	 *
	 * @param objeto
	 *            objeto que possui ou não o método.
	 * @param nomeCampo
	 *            nome do campo, usado para definir o get chamado.
	 * @return true se o objeto tem o método informado, false em caso contrário
	 */
	public static boolean objetoPossuiMetodoGetIndexado(Object objeto, String nomeCampo) {

		String nomeMetodoGet = getNomeMetodoGet(nomeCampo);
		return possuiMetodo(objeto, nomeMetodoGet, new Class[] { int.class });
	}

	/**
	 * Chama um método de um objeto, usando reflection.
	 *
	 * @param objeto
	 *            Objeto que possui o método.
	 * @param nomeMetodo
	 *            Nome do método desejado.
	 * @param tiposParametros
	 *            Tipo de dados dos parâmetros.
	 * @param argumentos
	 *            Argumentos para os parâmetros.
	 * @return O resultado da chamada
	 */
	public static Object invocarMetodo(Object objeto, String nomeMetodo, Class<?>[] tiposParametros, Object[] argumentos) {
		try {
			Method metodo = objeto.getClass().getMethod(nomeMetodo, tiposParametros);
			return invocarMetodo(objeto, metodo, argumentos);
		} catch (SecurityException e) {
			throw new BancoobRuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new BancoobRuntimeException(e);
		} catch (BancoobException e) {
			throw new BancoobRuntimeException(e);
		}
	}

	/**
	 * Chama um método de um objeto, usando reflection.
	 *
	 * @param objeto
	 *            Objeto que possui o método
	 * @param metodo
	 *            A instância do método que se deseja recuperar
	 * @param argumentos
	 *            Argumentos para o método
	 * @return O resultado da chamada ou null se algum erro ocorrer.
	 * @throws BancoobException
	 *             lança a exceção BancoobException
	 */
	public static Object invocarMetodo(Object objeto, Method metodo, Object... argumentos) throws BancoobException {
		try {
			return metodo.invoke(objeto, argumentos);
		} catch (InvocationTargetException e) {
			Throwable causa = e.getCause();
			if (causa instanceof BancoobException) {
				throw (BancoobException) causa;
			} else {
				throw new BancoobRuntimeException(e);
			}
		} catch (IllegalArgumentException e) {
			throw new BancoobRuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		}
	}

	/**
	 * Método usado para chamar o método get de um objeto, definido o nome do campo correspondente.
	 *
	 * @param objeto
	 *            objeto que possui ou não o método.
	 * @param nomeCampo
	 *            nome do campo, usado para definir o get chamado.
	 *
	 * @return O resultado da chamada ou null se algum erro ocorrer.
	 */
	public static Object invocarMetodoGet(Object objeto, String nomeCampo) {

		String nomeMetodoGet = getNomeMetodoGet(nomeCampo);
		return invocarMetodo(objeto, nomeMetodoGet, new Class[0], new Object[0]);
	}

	// ------------------------------------------------------------
	// Métodos get/set
	// ------------------------------------------------------------

	/**
	 * Retorna o nome do método get associado ao campo informado.
	 *
	 * @param nomeCampo
	 *            Nome do campo.
	 * @return Nome do método get.
	 */
	public static String getNomeMetodoGet(String nomeCampo) {

		return "get" + StringUtils.capitalize(nomeCampo);
	}

	/**
	 * Obtém a annotation do método, caso não exista na classe concreta, efetua a busca nas superclasses.
	 *
	 * @param <T>
	 *            O tipo parametrizado.
	 * @param annotation
	 *            a annotation que será verificada.
	 * @param m
	 *            o método que será verificado.
	 * @param actualParameters
	 *            os parâmetros do método.
	 * @return a annotation do método, caso não seja encontrada, <code>null</code> é retornado.
	 */
	public static <T extends Annotation> T getAnnotationMetodoSobrescrito(Class<T> annotation, Method m, Object[] actualParameters) {

		T annotationEncontrada = null;
		if (m != null) {
			annotationEncontrada = m.getAnnotation(annotation);
			if (annotationEncontrada == null) {
				Class<?> parent = m.getDeclaringClass().getSuperclass();
				if (parent != null) {
					Method superMethod = obterMetodoPorParametros(m.getName(), actualParameters, parent);
					annotationEncontrada = getAnnotationMetodoSobrescrito(annotation, superMethod, actualParameters);
				}
			}
		}
		return annotationEncontrada;
	}

	/**
	 * Recupera a {@code annotation} na {@code classe} e em todas as superclasses, ordenadas de pai para filho.
	 *
	 * @param <A>
	 *            o tipo generico
	 * @param annotation
	 *            a annotation que será verificada.
	 * @param classe
	 *            a classe que será verificada
	 * @return lista com todas as anotações
	 */
	public static <A extends Annotation> List<A> getAnnotationsSuperClasses(final Class<A> annotation, final Class<?> classe) {

		List<A> resultado = new ArrayList<A>();
		A a = classe.getAnnotation(annotation);
		if (a != null) {
			resultado.add(a);
		}
		Class<?> parent = classe.getSuperclass();
		if (parent != null) {
			resultado.addAll(getAnnotationsSuperClasses(annotation, parent));
		}
		Collections.reverse(resultado);
		return resultado;
	}

	/**
	 * Retorna o método da classe apartir dos parâmetros informados.
	 *
	 * @param methodName
	 *            o nome do método.
	 * @param actualParameters
	 *            os parâmetros.
	 * @param cls
	 *            a classe do objeto.
	 * @return o método da classe apartir dos parâmetros informados.
	 */
	public static Method obterMetodoPorParametros(String methodName, Object[] actualParameters, Class<?> cls) {

		Method[] publicMethods = cls.getMethods();
		Method method = null;
		int idxMethod = 0;
		while ((method == null) && (idxMethod < publicMethods.length) && (actualParameters != null)) {
			method = publicMethods[idxMethod];
			if (!method.getName().equals(methodName) || Modifier.isVolatile(method.getModifiers()) || !isTiposParametrosCorretos(method, actualParameters)) {
				method = null;
			}
			idxMethod++;
		}
		return method;
	}

	/**
	 * Verifica se eh tipos parametros corretos.
	 *
	 * @param method
	 *            o valor de method
	 * @param parameters
	 *            o valor de parameters
	 * @return {@code true}, se for tipos parametros corretos
	 */
	private static boolean isTiposParametrosCorretos(final Method method, Object[] parameters) {
		boolean result = (method != null);
		if (result) {
			Class<?>[] formalParameters = method.getParameterTypes();
			if (parameters.length == formalParameters.length) {
				int idxParam = 0;
				while (idxParam < formalParameters.length) {
					Class<?> param = formalParameters[idxParam];
					if (parameters[idxParam] == null || !param.isAssignableFrom(parameters[idxParam].getClass())) {
						result = false;
					}
					idxParam++;
				}
			} else {
				result = false;
			}
		}
		return result;
	}

	/**
	 * Obtém o índice do parâmetro que possue a annotation recebida como parâmetro, caso não exista na classe concreta, procura nas superclasses.
	 *
	 * @param <T>
	 *            o tipo generico
	 * @param m
	 *            o valor de m
	 * @param actualParameters
	 *            os parametros no parametro.
	 * @param annotationClass
	 *            o valor de annotation class
	 * @return Integer
	 * @returno índice do parâmetro que possue a annotation, caso não exista na classe concreta, procura nas superclasses.
	 */
	public static <T extends Annotation> Integer obterIndexParametroMetodoSobrescrito(Method m, Object[] actualParameters, Class<T> annotationClass) {

		Integer indexParametro = null;
		if (m != null) {
			indexParametro = obterIndexParametroAnotado(m, annotationClass);

			if (indexParametro == null) {
				Class<?> parent = m.getDeclaringClass().getSuperclass();
				if (parent != null) {
					Method superMethod = obterMetodoPorParametros(m.getName(), actualParameters, parent);
					indexParametro = obterIndexParametroMetodoSobrescrito(superMethod, actualParameters, annotationClass);
				}
			}
		}
		return indexParametro;
	}

	/**
	 * Obtém o indice do parâmetro que possui a annotation recebida como parâmetro.
	 *
	 * @param <T>
	 *            o tipo generico
	 * @param businessMethod
	 *            o método de onde o parametro será obtido.
	 * @param annotationClass
	 *            o valor de annotation class
	 * @return o indice do parâmetro que possui a annotation
	 */
	public static <T extends Annotation> Integer obterIndexParametroAnotado(Method businessMethod, Class<T> annotationClass) {

		Integer indexParameter = 0;

		for (Annotation[] annotations : businessMethod.getParameterAnnotations()) {
			for (Annotation annotation : annotations) {
				if (annotationClass.isAssignableFrom(annotation.getClass())) {
					return indexParameter;
				}
			}
			indexParameter++;
		}

		return null;
	}

	/**
	 * Retrieve the JavaBeans <code>PropertyDescriptor</code>s of a given class.
	 *
	 * @param clazz
	 *            the Class to retrieve the PropertyDescriptors for
	 * @return an array of <code>PropertyDescriptors</code> for the given class
	 */
	public static PropertyDescriptor[] getPropertyDescriptors(Class<?> clazz) {

		return PropertyUtils.getPropertyDescriptors(clazz);
	}

	/**
	 * Retorna uma instancia de PropertyDescriptor.
	 *
	 * @param o
	 *            o objeto de onde a propriedade sera extraida.
	 * @param name
	 *            o nome da propriedade.
	 * @return uma instancia de PropertyDescriptor
	 */
	public static PropertyDescriptor getPropertyDescriptor(Object o, String name) {
		try {
			return PropertyUtils.getPropertyDescriptor(o, name);
		} catch (IllegalAccessException e) {
			throw new BancoobRuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new BancoobRuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new BancoobRuntimeException(e);
		}
	}

	/**
	 * Copia as propriedades de <code>source</code> para <code>target</code> e ignora as propriedades que estão em <code>ignoreProperties</code>.
	 *
	 * @param target
	 *            bean onde as propriedades serão atribuidas
	 * @param source
	 *            bean de onde as propriedades serão extraidas.
	 * @param ignoreProperties
	 *            as propriedades que serao ignoradas.
	 */
	public static void copiarPropriedades(Object target, Object source, String... ignoreProperties) {

		Class<?> actualEditable = target.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<?> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;

		if (source != null) {
			for (int i = 0; i < targetPds.length; i++) {
				PropertyDescriptor targetPd = targetPds[i];
				if ((targetPd.getWriteMethod() != null) && ((ignoreProperties == null) || !ignoreList.contains(targetPd.getName()))) {
					copiar(source, getPropertyDescriptor(source, targetPd.getName()), target, targetPd);
				}
			}
		}
	}

	/**
	 * Copiar.
	 * 
	 * @param source
	 *            the source
	 * @param sourcePd
	 *            the source pd
	 * @param target
	 *            the target
	 * @param targetPd
	 *            the target pd
	 */
	private static void copiar(Object source, PropertyDescriptor sourcePd, Object target, PropertyDescriptor targetPd) {

		if ((sourcePd != null) && (sourcePd.getReadMethod() != null)) {
			try {
				Method writeMethod = targetPd.getWriteMethod();
				tornarAcessivel(writeMethod);

				Method readMethod = sourcePd.getReadMethod();
				tornarAcessivel(readMethod);

				writeMethod.invoke(target, new Object[] { readMethod.invoke(source, new Object[0]) });
			} catch (IllegalArgumentException e) {
				throw new BancoobRuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new BancoobRuntimeException(e);
			} catch (InvocationTargetException e) {
				throw new BancoobRuntimeException(e);
			}
		}
	}

	/**
	 * Tornar acessivel.
	 * 
	 * @param method
	 *            the method
	 */
	private static void tornarAcessivel(Method method) {

		if (!Modifier.isPublic(method.getModifiers())) {
			method.setAccessible(true);
		}
	}

	/**
	 * Obtém o parâmetro declarado via Generics na {@code classe} e no {@code indice}.
	 *
	 * @param classe
	 *            A classe da qual se deseja obter o tipo genérico
	 * @param indice
	 *            o índice do parâmetro
	 * @return o tipo genérico
	 */
	public static Class<?> obterParametroGenerico(Class<?> classe, int indice) {
		ParameterizedType generic = (ParameterizedType) classe.getGenericSuperclass();
		Type[] genericParams = generic.getActualTypeArguments();
		Class<?> genericParamType = null;
		if (genericParams != null && (indice < genericParams.length)) {
			genericParamType = (Class<?>) genericParams[indice];
		}
		return genericParamType;
	}

	/**
	 * <p>
	 * Obtém o primeiro parâmetro declarado via Generics na {@code classe}.
	 * </p>
	 * <p>
	 * Funciona da mesma forma que {@code ReflexaoUtils.obterParametroGenerico(<classe>, 0)}
	 *
	 * @param classe
	 *            A classe da qual se deseja obter o parâmetro genérico
	 * @return o parâmetro genérico
	 * @see #obterParametroGenerico(Class, int)
	 */
	public static Class<?> obterParametroGenerico(Class<?> classe) {
		return obterParametroGenerico(classe, 0);
	}

	/**
	 * Recupera o valor de um atributo "aninhado" (propriedades separadas por ".")
	 *
	 * @param objeto
	 *            o objeto do qual se deseja recuperar o valor da propriedade
	 * @param propriedade
	 *            o caminho da propriedade que se deseja recuperar
	 * @return o valor do atributo ou {@code null} caso o {@code objeto} ou a {@code propriedade} estejam nulos
	 * @see PropertyUtils#getNestedProperty(Object, String)
	 */
	public static Object getValorAtributoAninhado(Object objeto, String propriedade) {

		Object valor = null;
		try {
			valor = PropertyUtils.getNestedProperty(objeto, propriedade);
		} catch (IllegalArgumentException e) {
			SicoobLoggerPadrao.getInstance(ReflexaoUtils.class).alerta(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		return valor;
	}

	/**
	 * Recupera o valor de um atributo "aninhado" (propriedades separadas por ".")
	 *
	 * @param objeto
	 *            o objeto do qual se deseja recuperar o valor da propriedade
	 * @param propriedade
	 *            o caminho da propriedade que se deseja recuperar
	 * @return o valor do atributo ou {@code null} caso o {@code objeto} ou a {@code propriedade} estejam nulos
	 * @see PropertyUtils#getNestedProperty(Object, String)
	 */
	public static Object getValorAtributoAninhadoThreadSafe(Object objeto, String propriedade) {
		Object valor = null;
		try {
			valor = PropertyUtils.getNestedProperty(objeto, propriedade);
		} catch (IllegalArgumentException e) {
			;
		} catch (IllegalAccessException e) {
			;
		} catch (InvocationTargetException e) {
			;
		} catch (NoSuchMethodException e) {
			;
		}
		return valor;
	}

	/**
	 * Obtém toda a hierarquia do tipo passado até a classe {@link java.lang.Object}.
	 * 
	 * @param tipo
	 *            - Classe origem da hierarquia.
	 * @return Set - com toda a hierarquia (incluindo as interfaces).
	 */
	@SuppressWarnings("unchecked")
	public static Set<Class<?>> getHierarquia(Class<?> tipo) {

		Set<Class<?>> hierarquia = new LinkedHashSet<Class<?>>();
		hierarquia.add(tipo);
		hierarquia.addAll(ClassUtils.getAllSuperclasses(tipo));
		hierarquia.addAll(ClassUtils.getAllInterfaces(tipo));
		return hierarquia;
	}

	/**
	 * Recupera todos os métodos de uma classe. <code>
	 * for (Map.Entry<Class<?>, List<Method>> entrada : recuperarTodosMetodos(ArrayList.class).entrySet()) {
	 * 		System.out.println("-" + entrada.getKey());
	 * 		for (Method metodo : entrada.getValue()) {
	 * 			System.out.println("\t- " + metodo.getName());
	 * 		}
	 * 	}
	 * </code>
	 * 
	 * @param classe
	 *            a classe a ter os métodos recuperados
	 * @return os métodos da classe
	 */
	public static Map<Class<?>, List<Method>> recuperarTodosMetodos(Class<?> classe) {
		return recuperarTodosMetodos(classe, Object.class);
	}

	/**
	 * Recupera todos os métodos de uma classe. <code>
	 * for (Map.Entry<Class<?>, List<Method>> entrada : recuperarTodosMetodos(ArrayList.class).entrySet()) {
	 * 		System.out.println("-" + entrada.getKey());
	 * 		for (Method metodo : entrada.getValue()) {
	 * 			System.out.println("\t- " + metodo.getName());
	 * 		}
	 * 	}
	 * </code>
	 *
	 * @param classe
	 *            a classe a ter os métodos recuperados
	 * @param classeParada
	 *            a classe que deseja parar, caso não seja passado nada, será usada a classe {@code Object}
	 * @return Map
	 */
	public static Map<Class<?>, List<Method>> recuperarTodosMetodos(Class<?> classe, Class<?> classeParada) {
		Map<Class<?>, List<Method>> resultado = new HashMap<Class<?>, List<Method>>();
		Method[] declaredMethods = classe.getDeclaredMethods();
		if ((declaredMethods != null) && (declaredMethods.length > 0)) {
			resultado.put(classe, Arrays.asList(declaredMethods));
		}
		Class<?> parent = classe.getSuperclass();
		if ((parent != null) && !parent.equals(classeParada)) {
			resultado.putAll(recuperarTodosMetodos(parent, classeParada != null ? classe : Object.class));
		}
		return resultado;
	}

	/**
	 * Copia as propriedades de um objeto ignorando as diferenças de tipos
	 * 
	 * <br />
	 * <b>Exemplo:</b> Date Objeto1.getDataInclusao() -> DateTimeDB objeto2.getDataInclusao()
	 *
	 * @param destino
	 *            o valor de destino
	 * @param fonte
	 *            o valor de fonte
	 * @param propriedadesIgnoradas
	 *            o valor de propriedades ignoradas
	 */
	public static void copiarPropriedadesIgnorandoDiferencas(Object destino, Object fonte, String... propriedadesIgnoradas) {
		Class<?> actualEditable = destino.getClass();
		PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
		List<?> ignoreList = (propriedadesIgnoradas != null) ? Arrays.asList(propriedadesIgnoradas) : null;

		for (int i = 0; i < targetPds.length; i++) {
			PropertyDescriptor targetPd = targetPds[i];
			if ((targetPd.getWriteMethod() != null) && ((propriedadesIgnoradas == null) || !ignoreList.contains(targetPd.getName()))) {
				copiarIgnorandoDiferencas(fonte, getPropertyDescriptor(fonte, targetPd.getName()), destino, targetPd);
			}
		}
	}

	/**
	 * O método Copiar ignorando diferencas.
	 *
	 * @param fonte
	 *            o valor de fonte
	 * @param fontePd
	 *            o valor de fonte pd
	 * @param destino
	 *            o valor de destino
	 * @param destinoPd
	 *            o valor de destino pd
	 */
	private static void copiarIgnorandoDiferencas(Object fonte, PropertyDescriptor fontePd, Object destino, PropertyDescriptor destinoPd) {
		if ((fontePd != null) && (fontePd.getReadMethod() != null)) {
			try {
				Method writeMethod = destinoPd.getWriteMethod();
				tornarAcessivel(writeMethod);

				Method readMethod = fontePd.getReadMethod();
				tornarAcessivel(readMethod);

				writeMethod.invoke(destino, new Object[] { readMethod.invoke(fonte, new Object[0]) });
			} catch (IllegalArgumentException e) {
				;
			} catch (IllegalAccessException e) {
				;
			} catch (InvocationTargetException e) {
				;
			}
		}
	}

	/**
	 * Gera uma nova lista à partir da lista informada.
	 *
	 * @param <E>
	 *            o tipo generico
	 * @param lista
	 *            o valor de lista
	 * @return {@code Collection} a lista com os objetos
	 */
	@SuppressWarnings({ "rawtypes" })
	public static <E extends Collection> E clonarLista(E lista) {
		return clonarLista(lista, lista.getClass());
	}

	/**
	 * Gera uma nova lista à partir da lista informada.
	 *
	 * @param <E>
	 *            o tipo generico
	 * @param lista
	 *            A lista a ser clonada.
	 * @param classeLista
	 *            A classe da lista para criação da nova.
	 * @return A nova lista com os objetos.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <E extends Collection> E clonarLista(E lista, Class<?> classeLista) {
		E colecao = null;

		if (lista != null && lista.size() > 0) {
			colecao = (E) instanciar(classeLista);
			colecao.addAll(lista);
		}

		return colecao;
	}

	/**
	 * Obtém os objetos em comum entre duas listas, usando as propriedades informadas para comparação dos objetos.
	 * 
	 * @param colecao1
	 *            A primeira coleção
	 * @param colecao2
	 *            A segunda coleção
	 * @param propriedades
	 *            As propriedades que serão usadas na comparação dos objetos.
	 * @return A {@code Collection} com os objetos iguais entre as duas listas.
	 */
	public static Collection<?> intersecaoColecaoSemTipo(Collection<?> colecao1, Collection<?> colecao2, String... propriedades) {
		List<Object> colecaoRetorno = new ArrayList<Object>();

		if (CollectionUtils.isNotEmpty(colecao1) && CollectionUtils.isNotEmpty(colecao2)) {
			for (Object obj1 : colecao1) {
				for (Object obj2 : colecao2) {
					if (equals(obj1, obj2, propriedades)) {
						colecaoRetorno.add(obj1);
						break;
					}
				}
			}
		}

		return colecaoRetorno;
	}

	/**
	 * Remove de uma coleção, os objetos informados na segunda coleção, usando como comparação as propriedades informadas.
	 * 
	 * @param colecao1
	 *            A coleção para ter seus objetos removidos.
	 * @param colecao2
	 *            A coleção com os objetos a serem removidos da primeira coleção.
	 * @param propriedades
	 *            As propriedades que serão usadas na comparação dos objetos.
	 * @return Uma nova {@code Collection} com o resultado da remoção dos objetos da segunda coleção com primeira coleção.
	 */
	public static Collection<?> subtrairColecaoSemTipo(final Collection<?> colecao1, final Collection<?> colecao2, String... propriedades) {
		List<Object> colecaoRetorno = new ArrayList<Object>();
		if (!CollectionUtils.isEmpty(colecao1)) {
			colecaoRetorno = new ArrayList<Object>(colecao1);

			for (Iterator<?> it = colecao2.iterator(); it.hasNext();) {
				Object obj1 = it.next();
				for (int i = 0; i < colecaoRetorno.size(); i++) {
					Object obj2 = colecaoRetorno.get(i);
					if (equals(obj1, obj2, propriedades)) {
						colecaoRetorno.remove(i);
						break;
					}
				}
			}
		}

		return colecaoRetorno;
	}

	/**
	 * Obtém os objetos em comum entre duas listas, usando as propriedades informadas para comparação dos objetos.
	 *
	 * @param <T>
	 *            o tipo generico
	 * @param colecao1
	 *            A primeira coleção
	 * @param colecao2
	 *            A segunda coleção
	 * @param propriedades
	 *            As propriedades que serão usadas na comparação dos objetos.
	 * @return A {@code Collection} com os objetos iguais entre as duas listas.
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> intersecaoColecao(final Collection<T> colecao1, final Collection<T> colecao2, String... propriedades) {
		Collection<T> colecaoRetorno = instanciar(colecao1.getClass());

		if (CollectionUtils.isNotEmpty(colecao1) && CollectionUtils.isNotEmpty(colecao2)) {
			for (T obj1 : colecao1) {
				for (T obj2 : colecao2) {
					if (equals(obj1, obj2, propriedades)) {
						colecaoRetorno.add(obj1);
						break;
					}
				}
			}
		}

		return colecaoRetorno;
	}

	/**
	 * Remove de uma coleção, os objetos informados na segunda coleção, usando como comparação as propriedades informadas.
	 *
	 * @param <T>
	 *            o tipo generico
	 * @param colecao1
	 *            A coleção para ter seus objetos removidos.
	 * @param colecao2
	 *            A coleção com os objetos a serem removidos da primeira coleção.
	 * @param propriedades
	 *            As propriedades que serão usadas na comparação dos objetos.
	 * @return Uma nova {@code Collection} com o resultado da remoção dos objetos da segunda coleção com primeira coleção.
	 */
	public static <T> Collection<T> subtrairColecao(final Collection<T> colecao1, final Collection<T> colecao2, String... propriedades) {
		List<T> colecaoRetorno = new ArrayList<T>();
		if (!CollectionUtils.isEmpty(colecao1)) {
			colecaoRetorno = new ArrayList<T>(colecao1);
			
			if(colecao2 != null && !colecao2.isEmpty()) {
				for (Iterator<?> it = colecao2.iterator(); it.hasNext();) {
					Object obj1 = it.next();
					for (int i = 0; i < colecaoRetorno.size(); i++) {
						Object obj2 = colecaoRetorno.get(i);
						if (equals(obj1, obj2, propriedades)) {
							colecaoRetorno.remove(i);
							break;
						}
					}
				}
			}
		}

		return colecaoRetorno;
	}

	/**
	 * Obtém um objeto dentro de uma coleção usando as propriedades informadas.
	 *
	 * @param <T>
	 *            o tipo generico
	 * @param colecao
	 *            Coleção com o objeto a ser recuperado.
	 * @param objeto
	 *            O objeto a ser recuperado dentro da coleção
	 * @param propriedades
	 *            o valor de propriedades
	 * @return o objeto equivalente da lista.
	 */
	public static <T> T obterObjetoLista(Collection<T> colecao, Object objeto, String... propriedades) {
		if (colecao != null) {
			for (Iterator<T> it = colecao.iterator(); it.hasNext();) {
				T obj = it.next();
				if (equals(obj, objeto, propriedades)) {
					return obj;
				}
			}
		}
		return null;
	}

	/**
	 * Verifica se o objeto possui o atributo informado.
	 * 
	 * @param objeto
	 *            Objeto a ser verificado.
	 * @param atributo
	 *            Nome do atributo para verificação.
	 * @return {@code Boolean} se o objeto possui o atributo.
	 */
	public static boolean objetoPossuiAtributo(Object objeto, String atributo) {
		Field propriedade = null;
		Class<?> classe = objeto.getClass();
		while ((classe != null) && (propriedade == null)) {
			try {
				propriedade = classe.getDeclaredField(atributo);
			} catch (SecurityException e) {
				continue;
			} catch (NoSuchFieldException e) {
				continue;
			} finally {
				classe = classe.getSuperclass();
			}
		}
		return propriedade != null;
	}

	/**
	 * Construir objeto por classe.
	 *
	 * @param <T>
	 *            the generic type
	 * @param classeObjeto
	 *            the classe objeto
	 * @return t
	 */
	@SuppressWarnings("unchecked")
	public static <T> T construirObjetoPorClasse(String classeObjeto) {
		Class<T> classe = null;
		try {
			classe = (Class<T>) Class.forName(classeObjeto);
			return instanciar(classe);
		} catch (ClassNotFoundException e) {
			throw new BancoobRuntimeException(e);
		} catch (SecurityException e) {
			throw new BancoobRuntimeException(e);
		}
	}

	/**
	 * <p>
	 * Retorna uma lista com o nome dos atributos informados
	 * </p>
	 * 
	 * <pre>
	 * juntarInformacoes(null)            = null
	 * juntarInformacoes([])              = ""
	 * juntarInformacoes([null])          = ""
	 * juntarInformacoes(["a", "b", "c"]) = "abc"
	 * juntarInformacoes([null, "", "a"]) = "a"
	 * </pre>
	 * 
	 * .
	 *
	 * @param lista
	 *            o valor de lista
	 * @return String
	 */
	public static String juntarInformacoes(Object[] lista) {
		return juntarInformacoes(lista, null);
	}

	/**
	 * <p>
	 * Retorna uma lista com o nome dos atributos informados separados por um separador específico.
	 * </p>
	 * 
	 * <pre>
	 * juntarInformacoes([null], *)                 = "" 
	 * juntarInformacoes(["a", "b", "c"], * "--")   = "a--b--c" 
	 * juntarInformacoes(["a", "b", "c"], null)     = "abc"
	 * juntarInformacoes(["a", "b", "c"], "")       = "abc" 
	 * juntarInformacoes([null, * "", "a"], ',')    = ",,a"
	 * </pre>
	 * 
	 * @param lista
	 *            o array com os objetos que serão adicionados.
	 * @param separador
	 *            o separador
	 * @return {@code String} com os parâmetros informados separados devidamente
	 */
	public static String juntarInformacoes(Object[] lista, String separador) {
		if (lista == null) {
			return null;
		}

		if (separador == null) {
			separador = "";
		}

		int tamanhoLista = lista.length;
		int tamanhoRetorno = ((tamanhoLista == 0) ? 0 : tamanhoLista * ((lista[0] == null ? 16 : lista[0].toString().length()) + (separador.length())));

		StringBuffer retorno = new StringBuffer(tamanhoRetorno);
		for (int i = 0; i < tamanhoLista; i++) {
			if ((separador != null) && (i > 0)) {
				retorno.append(separador);
			}
			if (lista[i] != null) {
				retorno.append(lista[i]);
			}
		}
		return retorno.toString();
	}

}